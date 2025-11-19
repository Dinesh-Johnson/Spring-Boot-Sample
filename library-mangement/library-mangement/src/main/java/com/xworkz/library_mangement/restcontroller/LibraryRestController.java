package com.xworkz.library_mangement.restcontroller;

import com.xworkz.library_mangement.entity.LibraryEntity;
import com.xworkz.library_mangement.service.LibraryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/library")
public class LibraryRestController {


    private final LibraryService service;

    public LibraryRestController(LibraryService service) {
        this.service = service;
        log.info("Library Rest controller Constructor");
    }

    @PostMapping
    public Object create(@Valid @RequestBody LibraryEntity entity, BindingResult result){
        log.info("Book : {}",entity);
        if (result.hasErrors()) {
            return result.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + " : " + e.getDefaultMessage())
                    .toList();
        }
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public Object update(@Valid @PathVariable Integer id,
                                @RequestBody LibraryEntity entity,BindingResult result){
        if (result.hasErrors()) {
            return result.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + " : " + e.getDefaultMessage())
                    .toList();
        }
        entity.setId(id);
        return service.save(entity);
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable Integer id) {
        log.info("Fetching book with ID {}", id);
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        log.info("Deleting book {}", id);
        service.deleteById(id);
        return "Deleted book with id " + id;
    }

    @PostMapping("/books")
    public Object bulkCreate(@Valid @RequestBody Iterable<LibraryEntity> entities,
                             BindingResult result) {

        log.info("Bulk create books");

        if (result.hasErrors()) {
            return result.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + " : " + e.getDefaultMessage())
                    .toList();
        }

        return service.saveAll(entities);
    }

    @GetMapping
    public Object getAll() {
        log.info("Fetching all books");
        return service.findAll(); // You must add this method in service + repo
    }

    @PutMapping("/books")
    public Object bulkUpdate(@RequestBody Iterable<LibraryEntity> entities) {

        log.info("Bulk updating books");

        return service.saveAll(entities);
    }

    @DeleteMapping("/books")
    public String bulkDelete(@RequestBody Iterable<Integer> ids) {

        log.info("Bulk delete books");

        ids.forEach(service::deleteById);

        return "Deleted books: " + ids;
    }

    @GetMapping("/book/{name}")
    public Object getByBookName(@PathVariable String name) {
        log.info("Searching books by name: {}", name);
        return service.findAllByBookName(name);
    }

    @GetMapping("/author/{authorName}")
    public Object getByAuthorName(@PathVariable String authorName) {
        log.info("Searching books by author: {}", authorName);
        return service.findAllByAuthorName(authorName);
    }

    @GetMapping("/search/{keyword}")
    public Object searchByKeyword(@PathVariable String keyword) {
        log.info("Searching books by keyword: {}", keyword);
        return service.findAllByBookNameContaining(keyword);
    }

    @DeleteMapping("/genre/{genre}")
    public String deleteByGenre(@PathVariable String genre) {
        log.info("Deleting all books in genre: {}", genre);
        service.deleteByGenre(genre);
        return "Deleted all books of genre: " + genre;
    }

    @GetMapping("/genre/{genre}")
    public Object getByGenre(@PathVariable String genre) {
        log.info("Searching books by genre: {}", genre);
        return service.findAllByGenre(genre);
    }
}
