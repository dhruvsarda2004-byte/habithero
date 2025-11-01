package com.habithero.habithero.controller;

import com.habithero.habithero.model.Habit;
import com.habithero.habithero.model.User;
import com.habithero.habithero.repository.HabitRepository;
import com.habithero.habithero.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
@CrossOrigin
public class HabitController {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitController(HabitRepository habitRepository, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    @PostMapping
    public Habit createHabit(@RequestBody Habit habit) {
        // ✅ Fetch the full user from DB before saving
        if (habit.getUser() != null && habit.getUser().getId() != null) {
            Long userId = habit.getUser().getId();
            User existingUser = userRepository.findById(userId).orElse(null);
            habit.setUser(existingUser);
        }
        return habitRepository.save(habit);
    }

    @PutMapping("/{id}")
    public Habit updateHabit(@PathVariable Long id, @RequestBody Habit updatedHabit) {
        Habit habit = habitRepository.findById(id).orElseThrow();
        habit.setTitle(updatedHabit.getTitle());
        habit.setDescription(updatedHabit.getDescription());
        habit.setStatus(updatedHabit.getStatus());
        return habitRepository.save(habit);
    }

    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable Long id) {
        habitRepository.deleteById(id);
    }
}
