package com.habithero.habithero.repository;

import com.habithero.habithero.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
