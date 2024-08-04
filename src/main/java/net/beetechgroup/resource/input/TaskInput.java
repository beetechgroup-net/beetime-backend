package net.beetechgroup.resource.input;

import java.util.List;
import lombok.Data;

@Data
public class TaskInput {
    private String description;
    private String category;
    private List<TaskItemInput> taskItems;
}
