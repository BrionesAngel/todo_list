package org.angelo.todolist.tasks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserEmail(String email);
    List<Task> findByCompleted(boolean completed);
    List<Task> findByTitleContaining(String title);
}