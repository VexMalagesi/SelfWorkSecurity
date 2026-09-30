package org.example.SpringBoot;

import org.example.SpringBoot.Models.Author;
import org.example.SpringBoot.Repositories.AuthorRepositories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

// @SpringBootTest avvierebbe TUTTO il contesto Spring (controller, service, tutto).
// Per testare solo la parte JPA/repository non serve: @DataJpaTest è più leggero
// e veloce, avvia solo quello che serve per parlare col database.
// @SpringBootTest

// @DataJpaTest configura solo il layer di persistenza (EntityManager, repository)
// e per default avvolge ogni test in una transazione che viene fatta ROLLBACK
// alla fine, così i test non si "sporcano" a vicenda con dati lasciati nel DB.
@DataJpaTest

// Di default @DataJpaTest sostituirebbe il tuo datasource con un H2 in memoria
// creato al volo, ignorando application.properties.
// Replace.NONE dice "no, usa il datasource che ho configurato io" (utile se vuoi
// testare davvero contro le tabelle create dal tuo create.sql, non uno fittizio)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ApplicationTests {

    // Spring inietta automaticamente un'istanza pronta della repository,
    // stesso meccanismo di dependency injection già visto con @Autowired altrove
    @Autowired
    AuthorRepositories authorRepositories;

    // @BeforeEach: questo metodo gira PRIMA di ogni singolo @Test in questa classe.
    // Serve per avere sempre gli stessi dati di partenza "puliti" su cui testare,
    // invece di dipendere da cosa c'è già nel database
    @BeforeEach
    void load(){
        Author a1 = new Author();
        a1.setName("Giuseppe");
        a1.setSurname("Verdi");
        a1.setEmail("VerdiG@test.it");

        authorRepositories.save(a1);
    }

    // Test "segnaposto": verifica solo che il contesto Spring si carichi
    // senza errori (nessuna assert dentro, se il metodo non lancia eccezioni
    // il test passa). Utile come primo controllo di base.
    @Test
    void contextLoads() {
    }

    // Test vero: verifica che la derived query findByName funzioni davvero
    @Test
    void findByName(){
        // authorRepositories.findByName("Giuseppe") interroga il DB e restituisce
        // una List<Author> con tutti gli autori che hanno quel nome

        assertThat(authorRepositories.findByName("Giuseppe"))
                // .extracting("name") prende, da OGNI Author nella lista,
                // solo il valore del campo "name" (via reflection: la stringa
                // deve combaciare esattamente col nome del campo/getter)
                .extracting("name")
                // .containsOnly(...) verifica che i valori estratti siano
                // ESATTAMENTE questi, né di più né di meno
                .containsOnly("Giuseppe");
    }
}
