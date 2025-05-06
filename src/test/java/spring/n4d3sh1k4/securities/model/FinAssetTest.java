package spring.n4d3sh1k4.securities.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FinAssetTest {

    private FinAsset finAsset;
    private Tiker mockTiker;

    @BeforeEach
    void setUp() {
        mockTiker = Mockito.mock(Tiker.class);
        finAsset = new FinAsset(
                1L,
                mockTiker,
                "NY-1234",
                LocalDateTime.of(2020, 10, 1, 10, 0),
                "Emitent Name",
                "Form A",
                1000.0,
                500
        );
    }

    @Test
    void testFinAssetFields() {
        assertEquals(1L, finAsset.getId());
        assertEquals(mockTiker, finAsset.getTiker());
        assertEquals("NY-1234", finAsset.getRegistration());
        assertEquals(LocalDateTime.of(2020, 10, 1, 10, 0), finAsset.getDataRegistration());
        assertEquals("Emitent Name", finAsset.getEmitent());
        assertEquals("Form A", finAsset.getFormIssue());
        assertEquals(1000.0, finAsset.getPrincipal());
        assertEquals(500, finAsset.getAmount());
    }

    @Test
    void testSettersAndGetters() {
        Tiker anotherMockTiker = Mockito.mock(Tiker.class);
        finAsset.setTiker(anotherMockTiker);
        finAsset.setRegistration("XYZ-9876");
        finAsset.setPrincipal(2000.0);
        finAsset.setAmount(600);

        assertEquals(anotherMockTiker, finAsset.getTiker());
        assertEquals("XYZ-9876", finAsset.getRegistration());
        assertEquals(2000.0, finAsset.getPrincipal());
        assertEquals(600, finAsset.getAmount());
    }
}

