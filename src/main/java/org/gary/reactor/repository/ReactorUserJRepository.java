package org.gary.reactor.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.gary.coroutines.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactorUserJRepository extends ReactiveCrudRepository<User, Long> {

    Mono<User> findByUserName(String name);

    Flux<User> findById_GreaterThan(Long id);

}