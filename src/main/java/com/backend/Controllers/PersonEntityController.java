package com.backend.Controllers;

import java.util.Collections;
import java.util.Optional;

import com.backend.Models.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonEntityController {
    private final PersonEntityRepository repository;
    //private final
    //private final RecipeEntityRepository recipeRepository;
    //private final

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

    @PostMapping(value = "/api/people}")
    PersonEntity newPersonEntity(@RequestBody PersonEntity newPersonEntity) {
        return repository.save(newPersonEntity);
    }

    /*@PostMapping("/api/people/{id}")
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
    }*/

    @DeleteMapping("/api/people/{id}")
    void deletePersonEntity(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
