package com.project.board.author.service;

import com.project.board.author.domain.Author;
import com.project.board.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void save(Author author){
        authorRepository.save(author);
    }

    public Author findByEmail(String email){
        return authorRepository.findByEmail(email);
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseGet(null);
    }





}
