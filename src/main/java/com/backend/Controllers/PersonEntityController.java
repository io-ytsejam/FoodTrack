package com.backend.Controllers;

import java.util.List;
import java.util.Optional;

import com.backend.Dto.UserRegistrationDto;
import com.backend.Models.CommentEntity;
import com.backend.Models.PersonEntity;
import com.backend.Repositories.PersonEntityRepository;
import com.backend.Services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.xml.stream.events.Comment;

/*
Get Person info for given person id: @GetMapping(/api/people/{id}) @PathVariable Long id

Get Person info for current user: @GetMapping(/api/people/user)
 */

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

    @GetMapping("/api/people/{id}/comments")
    public ResponseEntity<CommentEntity> getCommentsByPersonId(@PathVariable Long id, Pageable pageable){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (repository.existsById(id)) {
                List<CommentEntity> comments = repository.findById(id).get().getComments();
                int start = (int) pageable.getOffset();
                int end = (int) ((start + pageable.getPageSize()) > comments.size() ? comments.size()
                        : (start + pageable.getPageSize()));
                Page<CommentEntity> page
                        = new PageImpl<CommentEntity>(comments.subList(start, end), pageable, comments.size());
                return new ResponseEntity(page, HttpStatus.OK);
            } else
                return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
