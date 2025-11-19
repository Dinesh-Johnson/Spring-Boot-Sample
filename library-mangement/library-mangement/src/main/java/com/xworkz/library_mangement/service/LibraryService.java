package com.xworkz.library_mangement.service;

import com.xworkz.library_mangement.LibraryMangementApplication;
import com.xworkz.library_mangement.entity.LibraryEntity;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    LibraryEntity save(LibraryEntity entity);


    void deleteById(Integer id);

    Iterable<LibraryEntity> saveAll(Iterable<LibraryEntity> entities);

    Optional<LibraryEntity> findById(Integer id);

    Long count();

    List<LibraryEntity> findAllByBookName(String name);

    Iterable<LibraryEntity> findAll();

    Boolean existsById(Integer id);

    List<LibraryEntity> findAllByAuthorName(String authorName);

    List<LibraryEntity> findAllByBookNameContaining(String keyword);

    void deleteByGenre(String genre);

    List<LibraryEntity> findAllByGenre(String genre);



}
