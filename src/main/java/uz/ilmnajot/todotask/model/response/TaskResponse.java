package uz.ilmnajot.todotask.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private Long id;

    private String title;

    private String description;

    private LocalDate dueDate;

    private boolean completed;
}
