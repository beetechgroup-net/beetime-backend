package net.beetechgroup.resource.input;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class TaskItemInput {
    private UUID id;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
}
