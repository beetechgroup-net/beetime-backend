package net.beetechgroup.resource;

import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class TaskOutput {
    private UUID id;
    private String description;
    private String category;
    private List<TaskItemOutput> taskItems;
}
