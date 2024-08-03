package com.api.biblioteca.controller;

import com.api.biblioteca.controller.books.BookController;
import com.api.biblioteca.dto.books.BookDto;
import com.api.biblioteca.service.books.BookServiceMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookServiceMap bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBookById() throws Exception {
        BookDto bookDto = new BookDto(1L, "Test Book", "Author", "Editorial", "ISBN", "2024-01-01", 20.0, "Available", null);

        when(bookService.getBookById(1L)).thenReturn(Optional.of(bookDto));

        mockMvc.perform(get("/v1/books/1"))
                .andExpect(status().isOk());
    }
}
