package com.backend.Controllers;

import java.util.Optional;

import com.backend.Dto.UserRegistrationDto;
import com.backend.Models.PersonEntity;
import com.backend.Repositories.PersonEntityRepository;
import com.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@RestController
public class PersonEntityController {
    private final PersonEntityRepository repository;

    @Autowired
    private UserService userService;

    //private final RecipeEntityRepository recipeRepository;

    PersonEntityController(PersonEntityRepository repository)
    {
        this.repository=repository;
    }
    @GetMapping(value = "/api/peopleAll")
    Iterable<PersonEntity> all() {
        return repository.findAll();
    }

    /*@GetMapping(value = "/test")
    HttpEntity<PagedModel<PersonEntity>> persons(Pageable pageable, PagedResourcesAssembler<PersonEntity> assembler)
    {
        Page<PersonEntity> persons = repository.findAll(pageable);
        return new ResponseEntity<PagedModel<PersonEntity>>(assembler.toModel(persons), HttpStatus.OK);
    }*/

    @GetMapping(value = "/api/people/{id}")
    Optional<PersonEntity> one(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping(value = "/api/people/user")
    RedirectView currentUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken) )
            return new RedirectView("/api/people/"+repository.findByNickname(authentication.getName()).getPersonid());
        return new RedirectView("/register");
    }

    @PostMapping(value = "/api/people}")
    PersonEntity newPersonEntity(@RequestBody PersonEntity newPersonEntity) {
        return repository.save(newPersonEntity);
    }

    @PostMapping("/api/people/{id}")
    PersonEntity replacePersonEntity(@RequestBody PersonEntity newPersonEntity, @PathVariable Long id) {

        return repository.findById(id)
                .map(person -> {
                    person.setNickname(newPersonEntity.getNickname());
                    person.setFirstname(newPersonEntity.getFirstname());
                    person.setLastname(newPersonEntity.getLastname());
                    person.setPassword(newPersonEntity.getPassword());
                    return repository.save(person);
                })
                .orElseGet(() -> {
                    newPersonEntity.setPersonid(id);
                    return repository.save(newPersonEntity);
                });
    }

    @DeleteMapping("/api/people/{id}")
    void deletePersonEntity(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
