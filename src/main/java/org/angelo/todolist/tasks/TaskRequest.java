package org.angelo.todolist.tasks;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TaskRequest (
    @NotBlank(message = "Title cannot be empty") @Size(min = 1, max = 50)
    String title,
    @Size(max = 255, message = "max 255 chars")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-.,!?:;()]*$", message = "safe chars only")
    String description,
    @NotNull(message = "state required")
    boolean completed
) {}
