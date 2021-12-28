package com.example.pruebas.repositorys;

import com.example.pruebas.models.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

    Mono<Person> findByName(String name);
}
