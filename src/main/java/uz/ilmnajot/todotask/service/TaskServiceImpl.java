package uz.ilmnajot.todotask.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.todotask.Entity.Task;
import uz.ilmnajot.todotask.exception.TaskException;
import uz.ilmnajot.todotask.model.request.TaskRequest;
import uz.ilmnajot.todotask.model.common.ApiResponse;
import uz.ilmnajot.todotask.model.response.TaskResponse;
import uz.ilmnajot.todotask.repository.TaskRepository;

import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ApiResponse addTask(TaskRequest request) {
        Optional<Task> byTitle = taskRepository.findByTitle(request.getTitle());
        if (byTitle.isEmpty()) {
            Task task = new Task();
            task.setTitle(request.getTitle());
            task.setDescription(request.getDescription());
            task.setDueDate(request.getDueDate());
            Task savedTask = taskRepository.save(task);
            TaskResponse taskResponse = modelMapper.map(savedTask, TaskResponse.class);
            return new ApiResponse("successfully task created", true, taskResponse);
        }
        throw new TaskException("task already created");
    }

    @Override
    public ApiResponse updateTask(TaskRequest request, Long taskId) {
        Task task = getTaskById(taskId);
        task.setId(taskId);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
//        task.setCompleted(request.isCompleted());
        Task savedTask = taskRepository.save(task);
        TaskResponse taskResponse = modelMapper.map(savedTask, TaskResponse.class);
        return new ApiResponse("successfully task updated", true, taskResponse);
    }

    @Override
    public ApiResponse getTask(Long taskId) {
        Task taskById = getTaskById(taskId);
        TaskResponse map = modelMapper.map(taskById, TaskResponse.class);
        return new ApiResponse("success", true, map);
    }

    @Override
    public ApiResponse getTasks() {
        List<Task> taskList = taskRepository.findAll();
        if (!taskList.isEmpty()) {
            List<TaskResponse> responseList = taskList
                    .stream()
                    .map(task -> modelMapper.map(task, TaskResponse.class)).toList();
            return new ApiResponse("success", true, responseList);
        }
        throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ApiResponse deleteTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            taskRepository.deleteById(taskId);
            return new ApiResponse("success", true,"Task deleted successfully");
        }
        throw new TaskException("Task not found", HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        if (taskList.isEmpty()) {
            throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
        }
        return taskList;
    }

    @Override
    public ApiResponse completeTask(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            Task task = new Task();
            task.setCompleted(true);
            taskRepository.save(task);
            return new ApiResponse("success", true, "task successfully completed");
        }
        throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
    }

    public Task getTaskById(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }
        throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
    }
}
