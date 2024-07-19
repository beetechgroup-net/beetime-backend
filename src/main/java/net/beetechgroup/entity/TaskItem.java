package net.beetechgroup.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.beetechgroup.resource.TaskItemInput;
import net.beetechgroup.resource.TaskItemOutput;

@Data
@Entity
@NoArgsConstructor
public class TaskItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    @ManyToOne
    private Task task;

    public TaskItem(TaskItemInput taskItemInput) {
        this.startTime = taskItemInput.getStartTime();
        this.finishTime = taskItemInput.getFinishTime();
    }

    public TaskItemOutput toOutput(){
        TaskItemOutput taskItemOutput = new TaskItemOutput();
        taskItemOutput.setId(this.id);
        taskItemOutput.setStartTime(this.startTime);
        taskItemOutput.setFinishTime(this.finishTime);
        return taskItemOutput;
    }
}
