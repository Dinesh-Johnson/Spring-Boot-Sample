package com.xworkz.todolist.service;

import com.xworkz.todolist.entity.Task;
import com.xworkz.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService{

    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public Task createTodo(Task toDoEntity) {
        return repo.save(toDoEntity);
    }

    public List<Task> findAllTodo() {
        return repo.findAll();
    }

    public Task updateTask(Long id,Task updatedTask){
        Task task = repo.findById(id)
                .orElseThrow(()->new RuntimeException("Task Not FOund"));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());

        return repo.save(task);
    }

    public void deleteTask(Long id) {
        repo.deleteById(id);
    }


}
