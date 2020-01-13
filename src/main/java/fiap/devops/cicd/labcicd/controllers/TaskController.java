package fiap.devops.cicd.labcicd.controllers;

import fiap.devops.cicd.labcicd.models.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private static final List<Task> TASK_LIST = new ArrayList<>();

    @GetMapping
    public List<Task> findAll() {
        return TASK_LIST;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Integer id) {
        Task taskFound = TASK_LIST.stream().filter(t -> id.equals(t.getId())).findFirst().orElse(null);
        if (taskFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskFound);
    }

    @PostMapping
    public ResponseEntity<Task> createNew(@RequestBody Task task) {
        TASK_LIST.add(task);
        return ResponseEntity.created(URI.create("/task/" + task.getId())).body(task);
    }

}
