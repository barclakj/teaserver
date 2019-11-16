package io.realizr.teaserver.controller;


import io.realizr.teaserver.model.Task;
import io.realizr.teaserver.service.TaskExistsException;
import io.realizr.teaserver.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/t")
/**
 * Main employee API controller.
 */
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping(value = "/{taskId}", produces = "application/json")
    public ResponseEntity<Task> getTask(@NotNull @PathVariable("taskId") int taskId) {
        Task task = service.getTask(taskId);
        if (task==null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        else return new ResponseEntity(task, HttpStatus.OK);
    }

    @DeleteMapping(value ="/{taskId}", produces = "application/json")
    public ResponseEntity deleteTask(@NotNull @PathVariable("taskId") int taskId) {
        service.deleteTask(taskId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public ResponseEntity addTask(@Validated @RequestBody Task task) {
        log.info("Handling POST request for {}", task.getClass());
        // realistically you shouldnt be able to specify the task id on input!
        try {
            service.addNewTask(task);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/task/" + task.getId());
            return new ResponseEntity(headers, HttpStatus.CREATED);
        } catch (TaskExistsException e) {
            // normally would not want to send the exception details back to the client to
            // aviod leakage of internal workings but here as example considered acceptable..
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
