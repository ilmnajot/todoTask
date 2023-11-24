package uz.ilmnajot.todotask.model.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TaskRequest {

    private String title;

    private String description;

    private LocalDate dueDate;

    private boolean completed;
}
