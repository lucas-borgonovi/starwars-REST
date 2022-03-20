package com.letscode.starwarsrest;

import com.letscode.starwarsrest.model.Rebelde;
import com.letscode.starwarsrest.repository.RebeldeRepository;
import com.letscode.starwarsrest.service.RebeldeService;
import org.assertj.core.api.Assertions;
import org.hibernate.annotations.NotFound;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.NoSuchElementException;
import java.util.Random;

@SpringBootTest
class StarWarsRestApplicationTests {

    @Test
    void contextLoads() {
    }
}
