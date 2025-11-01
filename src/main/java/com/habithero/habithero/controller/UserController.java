package com.habithero.habithero.controller;

import com.habithero.habithero.model.User;
import com.habithero.habithero.model.Habit;
import com.habithero.habithero.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Add a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // ✅ NEW: Get all habits for one user
    @GetMapping("/{id}/habits")
    public List<Habit> getHabitsByUserId(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        return (user != null) ? user.getHabits() : List.of();
    }
}
