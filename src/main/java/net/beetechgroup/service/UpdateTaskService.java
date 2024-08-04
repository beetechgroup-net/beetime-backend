package net.beetechgroup.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.UUID;
import net.beetechgroup.entity.Task;
import net.beetechgroup.repository.TaskRepository;
import net.beetechgroup.resource.input.TaskInput;

@ApplicationScoped
public class UpdateTaskService {

    private final TaskRepository taskRepository;

    public UpdateTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task execute(UUID id, TaskInput taskInput){
        Task task = this.taskRepository.findById(id);
        task.updateFromInput(taskInput);
        this.taskRepository.persistAndFlush(task);
        return task;
    }

}
