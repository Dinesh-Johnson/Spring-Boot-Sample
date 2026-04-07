package com.xworkz.todolist.repository;

import com.xworkz.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Task,Long> {

}
