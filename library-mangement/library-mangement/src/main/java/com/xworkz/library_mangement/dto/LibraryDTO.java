package com.xworkz.library_mangement.dto;

import com.xworkz.library_mangement.validation.ValidBookCode;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LibraryDTO {
    
    private Integer id;
    
    @NotBlank(message = "Book code is required")
    @ValidBookCode
    private String bookCode;
    
    @NotBlank(message = "Book name is required")
    @Size(max = 100, message = "Book name must be less than 100 characters")
    private String bookName;
    
    @NotBlank(message = "Author name is required")
    @Size(max = 100, message = "Author name must be less than 100 characters")
    private String authorName;
    
    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be a positive number")
    private Double price;
    
    @Size(max = 50, message = "Genre must be less than 50 characters")
    private String genre;
    
    private boolean available = true;
}
