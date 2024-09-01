package org.gary.reactor.controller;

import jakarta.transaction.Transactional;
import jdk.incubator.concurrent.StructuredTaskScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.gary.blocking.model.UserJpa;
import org.gary.blocking.repository.BlockingAvatarService;
import org.gary.blocking.repository.BlockingEnrollmentService;
import org.gary.blocking.repository.BlockingUserDao;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class LoomJUserController {

    private final BlockingUserDao blockingUserDao;
    private final BlockingAvatarService blockingAvatarService;
    private final BlockingEnrollmentService blockingEnrollmentService;

    @Autowired
    public LoomJUserController(BlockingUserDao blockingUserDao, BlockingAvatarService blockingAvatarService, BlockingEnrollmentService blockingEnrollmentService) {
        this.blockingUserDao = blockingUserDao;
        this.blockingAvatarService = blockingAvatarService;
        this.blockingEnrollmentService = blockingEnrollmentService;
    }


    @PostMapping(name = "/loomj/users", value = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @Transactional
    public UserJpa storeUser(@RequestBody UserJpa user, @RequestParam(required = false) Optional<Long> delay) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var emailVerified = scope.fork(() ->
                    blockingEnrollmentService.verifyEmail(user.getEmail(), delay.orElse(0L))
            );
            var avatarUrl = scope.fork(() ->
                    blockingAvatarService.randomAvatar(delay.orElse(0L))
            );
            scope.join();
            scope.throwIfFailed();
            return blockingUserDao.save(UserBuilder.from(user)
                    .withEmailVerified(emailVerified.get())
                    .withAvatarUrl(avatarUrl.get().getUrl())
                    .build());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
