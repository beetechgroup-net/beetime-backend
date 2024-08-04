package net.beetechgroup.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.beetechgroup.resource.input.TaskInput;
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
    @OneToMany( cascade = CascadeType.ALL)
    private List<TaskItem> taskItems;

    public void updateFromInput(TaskInput taskInput) {
        this.setDescription(taskInput.getDescription());
        this.setCategory(taskInput.getCategory());
        this.setTaskItems(taskInput.getTaskItems().stream().map(TaskItem::new).toList());
    }

    public TaskOutput toOutput() {
        return new TaskOutput(this.id, this.description, this.category, this.taskItems.stream().map(TaskItem::toOutput).toList());
    }
}
