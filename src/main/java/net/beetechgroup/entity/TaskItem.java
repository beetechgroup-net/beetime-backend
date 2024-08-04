package net.beetechgroup.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.beetechgroup.resource.input.TaskItemInput;
import net.beetechgroup.resource.output.TaskItemOutput;

@Data
@Entity
@NoArgsConstructor
public class TaskItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;

    public TaskItem(TaskItemInput taskItemInput) {
        this.startTime = taskItemInput.getStartTime();
        this.finishTime = taskItemInput.getFinishTime();
    }

    public TaskItemOutput toOutput() {
        return new TaskItemOutput(this.id, this.startTime, this.finishTime);
    }
}
