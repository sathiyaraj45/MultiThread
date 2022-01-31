package com.sathiya.MultiThread.service;

import com.sathiya.MultiThread.model.User;
import com.sathiya.MultiThread.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Author Sathiyaraj created on 30-01-2022
 * <p>
 * TODO
 */

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    Logger log = LoggerFactory.getLogger(UserService.class);

    public List<User> getUserList() {
        long start = System.currentTimeMillis();
        List<User> list = userRepo.findAll();
        long end = System.currentTimeMillis();
        System.out.println("Total Time ==>"+ (end - start));
        return list;
    }

    @Async
    public CompletableFuture<List<User>> getUser() throws InterruptedException {
        long start = System.currentTimeMillis();
        List<User> users = userRepo.findAll();
        Thread.sleep(1000L);
        long end = System.currentTimeMillis();
        System.out.println("Total Time ==>"+ (end - start));
        System.out.println("Running thread =>"+ Thread.currentThread().getName());
        return CompletableFuture.completedFuture(users);
    }
}
