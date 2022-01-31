package com.sathiya.MultiThread.controller;

import com.sathiya.MultiThread.model.User;
import com.sathiya.MultiThread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Author Sathiyaraj created on 30-01-2022
 * <p>
 * TODO
 */

@RestController
public class Controller {

    @Autowired
    private UserService userService;

    @GetMapping("/normal/users")
    public @ResponseBody
    List<User> findnormalAllUser() {
        return userService.getUserList();
    }

    @GetMapping("/async/users")
    public CompletableFuture<ResponseEntity> findAsyncAllUser() throws InterruptedException {
        return userService.getUser().thenApply(ResponseEntity::ok);
    }

    @GetMapping("/multi/users")
    public CompletableFuture<ResponseEntity> findMultiAllUser() throws InterruptedException {
        CompletableFuture<List<User>> user1 = userService.getUser();
        CompletableFuture<List<User>> user2 = userService.getUser();
        CompletableFuture<List<User>> user3 = userService.getUser();
        CompletableFuture.allOf(user1, user2, user3).join();
        return user1.thenApply(ResponseEntity::ok);
    }
}
