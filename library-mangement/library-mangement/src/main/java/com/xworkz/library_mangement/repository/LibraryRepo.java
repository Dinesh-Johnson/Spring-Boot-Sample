package com.xworkz.library_mangement.repository;

import com.xworkz.library_mangement.entity.LibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepo extends JpaRepository<LibraryEntity,Integer> {

    List<LibraryEntity> findAllByBookName(String name);

    List<LibraryEntity> findAllByAuthorName(String authorName);

    List<LibraryEntity> findAllByBookNameContaining(String keyword);

    void deleteByGenre(String genre);

    List<LibraryEntity> findAllByGenre(String genre);


}
