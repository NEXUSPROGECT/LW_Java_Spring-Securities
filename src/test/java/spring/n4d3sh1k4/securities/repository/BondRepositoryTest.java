package spring.n4d3sh1k4.securities.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spring.n4d3sh1k4.securities.model.Bond;
import spring.n4d3sh1k4.securities.model.FinAsset;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BondRepositoryTest {

    @Autowired
    private BondRepository bondRepository;

    private Bond bond;
    private FinAsset finAsset;

    @BeforeEach
    void setUp() {
        // Создаём зависимость FinAsset для связывания с Bond
        finAsset = new FinAsset(1L, null, "NY-1234", LocalDateTime.now(), "Emitent", "Form A", 1000.0, 500);

        bond = new Bond(
                1L,
                finAsset,
                LocalDateTime.of(2025, 5, 6, 12, 0),
                4,
                7.5
        );
    }

    @Test
    void testSaveBond() {
        Bond savedBond = bondRepository.save(bond);
        assertNotNull(savedBond.getId());
        assertEquals(bond.getDataRepayment(), savedBond.getDataRepayment());
        assertEquals(bond.getCouponsAmount(), savedBond.getCouponsAmount());
    }

    @Test
    void testFindById() {
        Bond savedBond = bondRepository.save(bond);
        Optional<Bond> foundBond = bondRepository.findById(savedBond.getId());
        assertTrue(foundBond.isPresent());
        assertEquals(savedBond.getId(), foundBond.get().getId());
    }

    @Test
    void testDeleteBond() {
        Bond savedBond = bondRepository.save(bond);
        bondRepository.delete(savedBond);
        Optional<Bond> deletedBond = bondRepository.findById(savedBond.getId());
        assertFalse(deletedBond.isPresent());
    }
}

