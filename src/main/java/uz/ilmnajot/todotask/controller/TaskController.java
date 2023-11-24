package uz.ilmnajot.todotask.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.todotask.Entity.Task;
import uz.ilmnajot.todotask.model.request.TaskRequest;
import uz.ilmnajot.todotask.model.common.ApiResponse;
import uz.ilmnajot.todotask.service.TaskService;

import java.util.List;

import static uz.ilmnajot.todotask.utils.Constants.*;

@RestController
@RequestMapping("/api/todo")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping(ADD_TASK)
    public HttpEntity<ApiResponse> addTask(@RequestBody TaskRequest request){
        ApiResponse apiResponse = taskService.addTask(request);
        return apiResponse!=null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping(GET_TASK)
    public HttpEntity<ApiResponse> getTask(@PathVariable(name = "taskId") Long taskId){
        ApiResponse apiResponse = taskService.getTask(taskId);
        return apiResponse!=null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping(GET_ALL_TASK)
    public HttpEntity<ApiResponse> getAllTask(){
        ApiResponse apiResponse = taskService.getTasks();
        return apiResponse!=null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PutMapping(UPDATE_TASK)
    public HttpEntity<ApiResponse> updateTask(@RequestBody TaskRequest request, @PathVariable(name = "taskId") Long taskId){
        ApiResponse apiResponse = taskService.updateTask(request, taskId);
        return apiResponse!=null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PutMapping(COMPLETE_TASK)
    public HttpEntity<ApiResponse> completeTask(@PathVariable(name = "taskId") Long taskId){
        ApiResponse apiResponse = taskService.completeTask(taskId);
        return apiResponse!=null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @DeleteMapping(DELETE_TASK)
    public HttpEntity<ApiResponse> deleteTask(@PathVariable(name = "taskId") Long taskId){
        ApiResponse apiResponse = taskService.deleteTask(taskId);
        return apiResponse!=null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping("/tasks")
    public String showTasks(Model model){
        List<Task> tasks= taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }
}
