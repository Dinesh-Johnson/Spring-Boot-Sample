package com.xworkz.library_mangement.service;

import com.xworkz.library_mangement.entity.LibraryEntity;
import com.xworkz.library_mangement.repository.LibraryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LibraryServiceImpl implements LibraryService{

    @Autowired
    private LibraryRepo repo;

    @Override
    public LibraryEntity save   (LibraryEntity entity) {
        log.info("Saved Book : {}",entity);
        return repo.save(entity);
    }

//    @Override
//    public LibraryEntity update(LibraryEntity updateEntity) {
//
//        if (repo.existsById(updateEntity.getId())) {
//            log.info("Updating Entity : {}", updateEntity);
//            return repo.save(updateEntity);   // safe update
//        }
//
//        throw new IllegalArgumentException("Book ID not found: " + updateEntity.getId());
//    }

    @Override
    public Optional<LibraryEntity> findById(Integer id) {
        log.info("id :{} found",id);
        return repo.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("id :{} deleted",id);
        repo.deleteById(id);
    }

    @Override
    public Iterable<LibraryEntity> saveAll(Iterable<LibraryEntity> entities) {
        log.info("Saved Bulk : {}",entities);
        return repo.saveAll(entities);
    }

    @Override
    public Long count(){
        log.info("Service Count Method");
        return repo.count();
    }

    @Override
    public List<LibraryEntity> findAllByBookName(String name) {
        log.info("Name : {}",name);
        return repo.findAllByBookName(name);
    }

    @Override
    public Iterable<LibraryEntity> findAll() {
        log.info("Find All Methods");
        return repo.findAll();
    }

    @Override
    public Boolean existsById(Integer id) {
        log.info("Exists ID {}",id);
        return repo.existsById(id);
    }

    @Override
    public List<LibraryEntity> findAllByAuthorName(String authorName) {
        return repo.findAllByAuthorName(authorName);
    }

    @Override
    public List<LibraryEntity> findAllByBookNameContaining(String keyword) {
        return repo.findAllByBookNameContaining(keyword);
    }

    @Override
    public void deleteByGenre(String genre) {
        repo.deleteByGenre(genre);
    }

    @Override
    public List<LibraryEntity> findAllByGenre(String genre) {
        return repo.findAllByGenre(genre);
    }

}
