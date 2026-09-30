package org.example.SpringBoot.Repositories;

import org.example.SpringBoot.Models.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

// Estendere CrudRepository<Author, Long> dà già gratis i metodi base
// (save, findById, findAll, deleteById, ecc.) per l'entità Author,
// la cui chiave primaria è di tipo Long. Non serve scrivere nessuna
// implementazione: ci pensa Spring Data a generarla a runtime.
public interface AuthorRepositories extends ListCrudRepository<Author, Long> {

    // Derived query: Spring Data legge il nome del metodo e capisce da solo
    // che deve generare "SELECT * FROM authors WHERE firstname = ?",
    // perché "Name" nel nome del metodo corrisponde al campo "name" dell'entità.
    List<Author> findByName(String firstname);

    // Stessa logica: "Surname" nel nome del metodo deve combaciare
    // esattamente col campo "surname" dell'entità Author.
    List<Author> findBySurname(String surname);

    // "And" nel nome combina due condizioni: cerca autori che abbiano
    // ESATTAMENTE quel firstname E quel surname insieme.
    List<Author> findByNameAndSurname(String firstname, String surname);
}
