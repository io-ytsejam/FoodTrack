package com.backend.Controllers;


import java.util.List;
import com.backend.Models.CommentEntity;
import com.backend.Models.PersonEntity;
import com.backend.Repositories.PersonEntityRepository;
import com.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

/*
Get Person info for given person id: @GetMapping(/api/people/{id}) @PathVariable Long id

Get Person info for current user: @GetMapping(/api/people/user)
 */

@RestController
public class PersonEntityController {
    private final PersonEntityRepository repository;

    @Autowired
    private UserService userService;

    PersonEntityController(PersonEntityRepository repository)
    {
        this.repository=repository;
    }
    @GetMapping(value = "/api/people")
    Page<PersonEntity> all(Pageable pageable) {

        return repository.findAll(pageable).map(person ->
        {
            if(person.getPersonSettingEntities().stream().
                    anyMatch(setting -> {if(setting.getValue()=='t' &&
                            setting.getSettingEntity().getName().equals("privacy"))return true;
                        return false;}))
            {person.setFirstname("");
                person.setLastname("");}
            return person;
        });
    }

    @GetMapping(value = "/api/people/{id}")
    PersonEntity one(@PathVariable Long id) throws ResourceNotFoundException {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        PersonEntity person = repository.findById(id).orElse(null);
        if(person != null) {
            if(person.getNickname().equals(authentication.getName()))
                return person;
            if(person.getPersonSettingEntities().stream().
                    anyMatch(setting -> {if(setting.getValue()=='t' &&
                            setting.getSettingEntity().getName().equals("privacy"))return true;
                    return false;}))
            {person.setFirstname("");
            person.setLastname("");}
            return person;
        }else throw new ResourceNotFoundException("No person with given id");
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
