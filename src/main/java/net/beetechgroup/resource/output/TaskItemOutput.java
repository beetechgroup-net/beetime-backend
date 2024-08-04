package net.beetechgroup.resource.output;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskItemOutput {
    private UUID id;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
}
