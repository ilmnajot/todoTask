package uz.ilmnajot.todotask.service;

import uz.ilmnajot.todotask.Entity.Task;
import uz.ilmnajot.todotask.model.request.TaskRequest;
import uz.ilmnajot.todotask.model.common.ApiResponse;

import java.util.List;

public interface TaskService {
    ApiResponse addTask(TaskRequest request);
    ApiResponse updateTask(TaskRequest request, Long taskId);
    ApiResponse getTask(Long taskId);
    ApiResponse getTasks();
    ApiResponse deleteTask(Long taskId);

    List<Task> getAllTasks();

    ApiResponse completeTask(Long taskId);
}
