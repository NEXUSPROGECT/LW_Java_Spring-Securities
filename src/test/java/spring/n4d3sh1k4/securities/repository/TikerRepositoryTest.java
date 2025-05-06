package spring.n4d3sh1k4.securities.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spring.n4d3sh1k4.securities.model.Tiker;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TikerRepositoryTest {

    @Autowired
    private TikerRepository tikerRepository;

    private Tiker tiker;

    @BeforeEach
    void setUp() {
        tiker = new Tiker(1L, "AAPL", "NASDAQ");
    }

    @Test
    void testSaveTiker() {
        Tiker savedTiker = tikerRepository.save(tiker);
        assertNotNull(savedTiker.getId());
        assertEquals(tiker.getNameTiker(), savedTiker.getNameTiker());
    }

    @Test
    void testFindByNameTiker() {
        tikerRepository.save(tiker);

        List<Tiker> foundTikers = tikerRepository.findByNameTiker("AAPL");
        assertNotNull(foundTikers);
        assertFalse(foundTikers.isEmpty());
        assertEquals("AAPL", foundTikers.get(0).getNameTiker());
    }

    @Test
    void testFindByNameTikerNoMatch() {
        tikerRepository.save(tiker);

        List<Tiker> foundTikers = tikerRepository.findByNameTiker("NON_EXISTENT");
        assertTrue(foundTikers.isEmpty());
    }

    @Test
    void testDeleteTiker() {
        Tiker savedTiker = tikerRepository.save(tiker);
        tikerRepository.delete(savedTiker);
        assertFalse(tikerRepository.findById(savedTiker.getId()).isPresent());
    }
}