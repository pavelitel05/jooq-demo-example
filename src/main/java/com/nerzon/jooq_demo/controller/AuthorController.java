package com.nerzon.jooq_demo.controller;

import com.nerzon.jooq.generated.public_.tables.records.AuthorRecord;
import com.nerzon.jooq_demo.DTO.AuthorDTO;
import com.nerzon.jooq_demo.repository.AuthorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/all")
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorRecord -> new AuthorDTO(
                        authorRecord.getId(),
                        authorRecord.getFirstName(),
                        authorRecord.getLastName()
                    )
                )
                .collect(Collectors.toList());
    }

    @GetMapping
    public AuthorDTO getAuthorById(@RequestParam int id) {
        AuthorRecord authorRecord = authorRepository.findById(id);
        AuthorDTO authorDTO = new AuthorDTO(
                authorRecord.getId(),
                authorRecord.getFirstName(),
                authorRecord.getLastName()
        );
        return authorDTO;
    }

    @PostMapping
    public void addAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        authorRepository.insertAuthor(firstName, lastName);
    }

    @PutMapping
    public void updateAuthor(
            @RequestParam int id,
            @RequestParam String firstName,
            @RequestParam String lastName
    ) {
        authorRepository.updateAuthor(id, firstName, lastName);
    }

    @DeleteMapping
    public void deleteAuthor(@RequestParam int id) {
        authorRepository.deleteAuthor(id);
    }
}