package com.xworkz.todolist.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="todo")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String title;
    private String description;

    private boolean completed;


}
