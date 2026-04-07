package com.xworkz.todolist.controller;

import com.xworkz.todolist.entity.Task;
import com.xworkz.todolist.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TodoService service;

    public TaskController(TodoService service){
        this.service=service;
    }

    @PostMapping
    public Task create(@RequestBody Task task){
        return service.createTodo(task);
    }

    @GetMapping
    public List<Task> getAll(){
        return service.findAllTodo();
    }

    @PostMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return service.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        service.deleteTask(id);
    }

}
