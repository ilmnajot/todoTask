package uz.ilmnajot.todotask.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.ilmnajot.todotask.Entity.Task;
import uz.ilmnajot.todotask.repository.TaskRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    private final TaskRepository taskRepository;

    @Value("${spring.sql.init.mode}")
    private String mode;
    public DataLoader(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (mode.equals("always")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            taskRepository.save(
                    Task
                            .builder()
                            .id(1L)
                            .title("going to Gym")
                            .description("work out for legs today")
                            .dueDate(LocalDate.parse("2023-11-23 10:00",formatter))
                            .completed(false)
                            .build());
        }
    }
}
