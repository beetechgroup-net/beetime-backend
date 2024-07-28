package net.beetechgroup.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.beetechgroup.resource.input.TaskInput;
import net.beetechgroup.resource.output.TaskItemOutput;
import net.beetechgroup.resource.output.TaskOutput;

@Data
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String category;
    @OneToMany(mappedBy = "task",cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<TaskItem> taskItems;

    public Task(TaskInput taskInput) {
        this.description = taskInput.getDescription();
        this.category = taskInput.getCategory();
        setTaskItems(taskInput);
    }

    private void setTaskItems(TaskInput taskInput) {
        if (Objects.nonNull(taskInput.getTaskItems())) {
            this.taskItems = taskInput.getTaskItems().stream().map(TaskItem::new).toList();
        }
    }

    public TaskOutput toOutput() {
        TaskOutput taskOutput = new TaskOutput();
        taskOutput.setId(this.id);
        taskOutput.setDescription(this.description);
        taskOutput.setCategory(this.category);
        setTaskItemsOutput(taskOutput);
        return taskOutput;
    }

    private void setTaskItemsOutput(TaskOutput taskOutput) {
        if (Objects.nonNull(this.taskItems)) {
            List<TaskItemOutput> taskItemsOutput = this.taskItems.stream()
                    .map(TaskItem::toOutput).toList();
            taskOutput.setTaskItems(taskItemsOutput);
        }
    }

}
