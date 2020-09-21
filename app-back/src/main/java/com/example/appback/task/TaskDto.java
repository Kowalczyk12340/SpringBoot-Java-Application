package com.example.appback.task;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class TaskDto
{
    private String name;
    private Boolean done;

    public TaskDto(String name,Boolean done) {
        this.name = name;
        this.done = done;
    }
}
