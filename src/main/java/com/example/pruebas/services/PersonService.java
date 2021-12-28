package com.example.pruebas.services;

import com.example.pruebas.models.Person;
import com.example.pruebas.repositorys.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

public class PersonService {
    @Autowired
    private PersonRepository repository;

    public Flux<Person> listAll(){
        return repository.findAll();
    }

    public Mono<Void> insert(Person person) {
        return validateBeforeInsert.apply(repository, person)
                .switchIfEmpty(Mono.defer(() -> repository.save(person)))
                //.flatMap(repository::save)
                .then();
        //return repository.save(person).then();
    }

    private final BiFunction<PersonRepository, Person, Mono<Person>> validateBeforeInsert
            = (repo, person) -> repo.findByName(person.getName());

}
