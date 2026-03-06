package org.angelo.todolist.tasks;

public record TaskResponse(
    Long id,
    String title,
    String description,
    boolean completed
) {}
