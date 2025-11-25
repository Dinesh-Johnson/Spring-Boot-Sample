package com.xworkz.library_mangement.service;

import com.xworkz.library_mangement.dto.LibraryDTO;
import com.xworkz.library_mangement.entity.LibraryEntity;
import com.xworkz.library_mangement.repository.LibraryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class LibraryServiceImpl implements LibraryService{

    @Autowired
    private LibraryRepo repo;

    // Convert DTO -> Entity
    private LibraryEntity toEntity(LibraryDTO dto) {
        LibraryEntity entity = new LibraryEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    // Convert Entity -> Dto
    private LibraryDTO toDTO(LibraryEntity entity) {
        LibraryDTO dto = new LibraryDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public LibraryDTO save(LibraryDTO dto) {
        log.debug("Saving book: {}", dto.getBookName());
        try {
            LibraryEntity entity = toEntity(dto);
            LibraryEntity saved = repo.save(entity);
            log.info("Successfully saved book with ID: {}", saved.getId());
            return toDTO(saved);
        } catch (Exception e) {
            log.error("Error occurred while saving book: {}", dto.getBookName(), e);
            throw e;
        }
    }

    
    // Save All (DTO List)
    @Override
    public List<LibraryDTO> saveAll(List<LibraryDTO> dtoList) {

        List<LibraryEntity> entities =
                dtoList.stream()
                        .map(this::toEntity)
                        .collect(Collectors.toList());

        List<LibraryEntity> savedEntities = repo.saveAll(entities);

        return savedEntities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    
    // Delete By ID
    
    @Override
    public void deleteById(Integer id) {
        log.info("Deleting book with ID: {}", id);
        try {
            repo.deleteById(id);
            log.info("Successfully deleted book with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting book with ID: {}", id, e);
            throw e;
        }
    }

    
    // Find By ID
    
    @Override
    public Optional<LibraryDTO> findById(Integer id) {
        log.debug("Fetching book with ID: {}", id);
        try {
            Optional<LibraryEntity> entityOpt = repo.findById(id);
            if (entityOpt.isPresent()) {
                log.debug("Found book with ID: {}", id);
            } else {
                log.warn("No book found with ID: {}", id);
            }
            return entityOpt.map(this::toDTO);
        } catch (Exception e) {
            log.error("Error fetching book with ID: {}", id, e);
            throw e;
        }
    }

    
    // Count
    
    @Override
    public Long count() {
        return repo.count();
    }

    
    // Find All By Book Name
    
    @Override
    public List<LibraryDTO> findAllByBookName(String name) {
        return repo.findAllByBookName(name)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    
    // Find All
    @Override
    public List<LibraryDTO> findAll() {
        log.debug("Fetching all books");
        try {
            List<LibraryDTO> books = repo.findAll()
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
            log.debug("Found {} books", books.size());
            return books;
        } catch (Exception e) {
            log.error("Error fetching all books", e);
            throw e;
        }
    }

    
    // Exists By ID
    @Override
    public Boolean existsById(Integer id) {
        return repo.existsById(id);
    }

    
    // Find All By Author Name
    @Override
    public List<LibraryDTO> findAllByAuthorName(String authorName) {
        return repo.findAllByAuthorName(authorName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    
    // Search by keyword in book name
    
    @Override
    public List<LibraryDTO> findAllByBookNameContaining(String keyword) {
        return repo.findAllByBookNameContaining(keyword)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    
    // Delete by Genre
    @Override
    public void deleteByGenre(String genre) {
        repo.deleteByGenre(genre);
    }

    @Override
    public Page<LibraryDTO> getPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<LibraryEntity> entityPage = repo.findAll(pageable);

        return entityPage.map(this::toDTO);
    }

    @Override
    public Page<LibraryDTO> findAllByGenre(String genre, Pageable pageable) {
        return repo.findAllByGenre(genre, pageable)
                .map(this::toDTO);
    }


}
