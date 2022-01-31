package com.sathiya.MultiThread.repository;

import com.sathiya.MultiThread.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Sathiyaraj created on 30-01-2022
 * <p>
 * TODO
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
