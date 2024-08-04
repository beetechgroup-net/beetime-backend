package net.beetechgroup.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import net.beetechgroup.entity.Task;
import net.beetechgroup.repository.TaskRepository;
import net.beetechgroup.resource.input.TaskInput;

@ApplicationScoped
public class CreateTaskService {

    private final TaskRepository taskRepository;

    public CreateTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task execute(TaskInput taskInput){
        Task task = new Task();
        task.updateFromInput(taskInput);
        this.taskRepository.persistAndFlush(task);
        return task;
    }
}
