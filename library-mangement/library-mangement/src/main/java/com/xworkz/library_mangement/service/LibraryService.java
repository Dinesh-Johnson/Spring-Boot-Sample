package com.xworkz.library_mangement.service;

import com.xworkz.library_mangement.dto.LibraryDTO;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    LibraryDTO save(LibraryDTO dto);

    void deleteById(Integer id);

    List<LibraryDTO> saveAll(List<LibraryDTO> dtoList);

    Optional<LibraryDTO> findById(Integer id);

    Long count();

    List<LibraryDTO> findAllByBookName(String name);

    List<LibraryDTO> findAll();

    Boolean existsById(Integer id);

    List<LibraryDTO> findAllByAuthorName(String authorName);

    List<LibraryDTO> findAllByBookNameContaining(String keyword);

    void deleteByGenre(String genre);

    List<LibraryDTO> findAllByGenre(String genre);
}
