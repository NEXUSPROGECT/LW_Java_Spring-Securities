package spring.n4d3sh1k4.securities.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spring.n4d3sh1k4.securities.model.FinAsset;
import spring.n4d3sh1k4.securities.model.Security;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SecurityRepositoryTest {

    @Autowired
    private SecurityRepository securityRepository;

    private Security security;

    @BeforeEach
    void setUp() {
        // Создаём объект FinAsset для связи с Security
        FinAsset finAsset = new FinAsset(1L, null, "NY-1234", LocalDateTime.now(), "Emitent", "Form A", 1000.0, 500);

        security = new Security(
                1L,
                finAsset,
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(6)
        );
    }

    @Test
    void testSaveSecurity() {
        Security savedSecurity = securityRepository.save(security);
        assertNotNull(savedSecurity.getId());
        assertEquals(security.getDateAccommodation(), savedSecurity.getDateAccommodation());
    }

    @Test
    void testFindById() {
        Security savedSecurity = securityRepository.save(security);
        Security foundSecurity = securityRepository.findById(savedSecurity.getId()).orElse(null);
        assertNotNull(foundSecurity);
        assertEquals(savedSecurity.getId(), foundSecurity.getId());
    }

    @Test
    void testDeleteSecurity() {
        Security savedSecurity = securityRepository.save(security);
        securityRepository.delete(savedSecurity);
        assertFalse(securityRepository.findById(savedSecurity.getId()).isPresent());
    }
}

