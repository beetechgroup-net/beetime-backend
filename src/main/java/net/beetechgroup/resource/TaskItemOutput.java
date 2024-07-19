package net.beetechgroup.resource;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class TaskItemOutput {
    private UUID id;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
}
