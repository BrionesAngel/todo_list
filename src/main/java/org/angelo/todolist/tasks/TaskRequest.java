package org.angelo.todolist.tasks;

public record TaskRequest (
    String title,
    String description,
    boolean completed
) {}
