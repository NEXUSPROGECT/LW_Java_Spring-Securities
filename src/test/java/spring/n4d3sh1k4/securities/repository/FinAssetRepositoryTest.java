package spring.n4d3sh1k4.securities.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spring.n4d3sh1k4.securities.model.FinAsset;
import spring.n4d3sh1k4.securities.model.Tiker;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class FinAssetRepositoryTest {

    @Autowired
    private FinAssetRepository finAssetRepository;

    private FinAsset finAsset;

    @BeforeEach
    void setUp() {
        // Создаём объект Tiker для связи с FinAsset
        Tiker tiker = new Tiker(1L, "AAPL", "NASDAQ");

        finAsset = new FinAsset(
                1L,
                tiker,
                "NY-1234",
                LocalDateTime.now(),
                "Emitent",
                "Form A",
                1000.0,
                500
        );
    }

    @Test
    void testSaveFinAsset() {
        FinAsset savedFinAsset = finAssetRepository.save(finAsset);
        assertNotNull(savedFinAsset.getId());
        assertEquals(finAsset.getRegistration(), savedFinAsset.getRegistration());
    }

    @Test
    void testFindByRegistration() {
        finAssetRepository.save(finAsset);

        List<FinAsset> foundAssets = finAssetRepository.findByRegistration("NY-1234");
        assertNotNull(foundAssets);
        assertFalse(foundAssets.isEmpty());
        assertEquals("NY-1234", foundAssets.get(0).getRegistration());
    }

    @Test
    void testFindByRegistrationNoMatch() {
        finAssetRepository.save(finAsset);

        List<FinAsset> foundAssets = finAssetRepository.findByRegistration("NON_EXISTENT_REG");
        assertTrue(foundAssets.isEmpty());
    }

    @Test
    void testDeleteFinAsset() {
        FinAsset savedFinAsset = finAssetRepository.save(finAsset);
        finAssetRepository.delete(savedFinAsset);

        assertFalse(finAssetRepository.findById(savedFinAsset.getId()).isPresent());
    }
}

