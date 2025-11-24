package com.xworkz.library_mangement.restcontroller;

import com.xworkz.library_mangement.dto.LibraryDTO;
import com.xworkz.library_mangement.service.LibraryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/library")
public class LibraryRestController {

    private final LibraryService service;

    public LibraryRestController(LibraryService service) {
        this.service = service;
        log.info("Library Rest Controller Constructor");
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody LibraryDTO dto,
                                    BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(e -> e.getField() + ": " + e.getDefaultMessage())
                            .toList()
            );
        }

        try {
            LibraryDTO saved = service.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating book: " + e.getMessage());
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @Valid @RequestBody LibraryDTO dto,
                                    BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(e -> e.getField() + ": " + e.getDefaultMessage())
                            .collect(Collectors.toList())
            );
        }

        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book with ID " + id + " not found");
        }

        try {
            dto.setId(id);
            LibraryDTO updated = service.save(dto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating book: " + e.getMessage());
        }
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {

        Optional<LibraryDTO> book = service.findById(id);

        return book
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Book with ID " + id + " not found")
                );
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book with ID " + id + " not found");
        }

        service.deleteById(id);
        return ResponseEntity.ok("Deleted book with id " + id);
    }

    // BULK CREATE
    @PostMapping("/books")
    public ResponseEntity<?> bulkCreate(@Valid @RequestBody List<LibraryDTO> dtoList,
                                        BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(e -> e.getField() + ": " + e.getDefaultMessage())
                            .toList()
            );
        }

        try {
            return ResponseEntity.ok(service.saveAll(dtoList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Bulk create failed: " + e.getMessage());
        }
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // BULK UPDATE
    @PutMapping("/books")
    public ResponseEntity<?> bulkUpdate(@RequestBody List<LibraryDTO> dtoList) {
        return ResponseEntity.ok(service.saveAll(dtoList));
    }

    // BULK DELETE
    @DeleteMapping("/books")
    public ResponseEntity<?> bulkDelete(@RequestBody List<Integer> ids) {

        ids.forEach(service::deleteById);

        return ResponseEntity.ok("Deleted books: " + ids);
    }

    // GET BOOKS BY NAME
    @GetMapping("/book/{name}")
    public ResponseEntity<?> getByBookName(@PathVariable String name) {
        return ResponseEntity.ok(service.findAllByBookName(name));
    }

    // GET BOOKS BY AUTHOR
    @GetMapping("/author/{authorName}")
    public ResponseEntity<?> getByAuthorName(@PathVariable String authorName) {
        return ResponseEntity.ok(service.findAllByAuthorName(authorName));
    }

    // SEARCH KEYWORD
    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchByKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok(service.findAllByBookNameContaining(keyword));
    }

    // DELETE BY GENRE
    @DeleteMapping("/genre/{genre}")
    public ResponseEntity<?> deleteByGenre(@PathVariable String genre) {
        service.deleteByGenre(genre);
        return ResponseEntity.ok("Deleted all books of genre: " + genre);
    }

    // GET BY GENRE
    @GetMapping("/genre/{genre}")
    public ResponseEntity<?> getByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(service.findAllByGenre(genre));
    }
}
