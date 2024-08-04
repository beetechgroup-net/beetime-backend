package net.beetechgroup.resource.output;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskOutput {
    private UUID id;
    private String description;
    private String category;
    private List<TaskItemOutput> taskItems;
}
