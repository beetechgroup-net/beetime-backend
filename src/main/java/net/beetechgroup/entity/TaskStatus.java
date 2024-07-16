package net.beetechgroup.entity;

import lombok.Getter;

@Getter
public enum TaskStatus {
    NOTSTARTED("Not Started"),
    STARTED("Started"),
    FINISHED("Finished");

    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }
}
