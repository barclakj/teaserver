package io.realizr.teaserver.service;


import io.realizr.teaserver.model.Task;
import org.springframework.stereotype.Service;

@Service
/**
 * Task service interface.
 */
public interface TaskService {

    /**
     * Returns an individual task.
     * @param taskId
     * @return
     */
    Task getTask(final int taskId);

    /**
     * Adds a new task.
     * @param task
     */
    void addNewTask(final Task task) throws TaskExistsException;

    /**
     * Deletes an existing task
     * @param id
     */
    void deleteTask(final int id);

}
