package spring.n4d3sh1k4.securities.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SecurityTest {

    private Security security;
    private FinAsset mockFinAsset;

    @BeforeEach
    void setUp() {
        mockFinAsset = Mockito.mock(FinAsset.class);
        security = new Security(
                1L,
                mockFinAsset,
                LocalDateTime.of(2025, 1, 1, 10, 0),
                LocalDateTime.of(2025, 2, 1, 10, 0)
        );
    }

    @Test
    void testSecurityFields() {
        assertEquals(1L, security.getId());
        assertEquals(mockFinAsset, security.getFinAsset());
        assertEquals(LocalDateTime.of(2025, 1, 1, 10, 0), security.getDateAccommodation());
        assertEquals(LocalDateTime.of(2025, 2, 1, 10, 0), security.getDateReport());
    }

    @Test
    void testSettersAndGetters() {
        FinAsset anotherMockAsset = Mockito.mock(FinAsset.class);
        security.setFinAsset(anotherMockAsset);
        security.setDateAccommodation(LocalDateTime.of(2026, 1, 1, 10, 0));
        security.setDateReport(LocalDateTime.of(2026, 2, 1, 10, 0));

        assertEquals(anotherMockAsset, security.getFinAsset());
        assertEquals(LocalDateTime.of(2026, 1, 1, 10, 0), security.getDateAccommodation());
        assertEquals(LocalDateTime.of(2026, 2, 1, 10, 0), security.getDateReport());
    }
}

