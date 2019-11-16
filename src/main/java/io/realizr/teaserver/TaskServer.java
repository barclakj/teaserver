package io.realizr.teaserver;


import io.realizr.teaserver.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
/**
 * Main spring boot application.
 */
public class TaskServer {
    @Autowired
    public TaskService service;

    public static void main(String[] args) {
        SpringApplication.run(TaskServer.class, args);
    }

}
