package io.realizr.teaserver.service;

import io.realizr.teaserver.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
/**
 * Basic implementation of the task service.
 *
 * This implementation simple stores task details in memory rather than
 * using a proper datasource.
 */
public class TaskServiceImpl implements TaskService {
    private Map<Integer, Task> allEmployees = new HashMap<Integer, Task>();

    @Override
    public Task getTask(int taskId) {
        log.info("Fetching data for task by id: {}", taskId);
        Task task = this.allEmployees.get(taskId);
        return task;
    }

    @Override
    public void addNewTask(Task task) throws TaskExistsException {
        // todo: should validate task here
        log.info("Adding task by id: {}", task.getId());
        Task existingEmp = this.getTask(task.getId());
        if (existingEmp!=null) {
            log.warn("Task already exists!");
            throw new TaskExistsException("Task already exists!");
        } else {
            allEmployees.put(task.getId(), task);
        }
    }

    @Override
    public void deleteTask(int id) {
        log.info("Deleting task by id: {}", id);
        allEmployees.remove(id);
    }

}
