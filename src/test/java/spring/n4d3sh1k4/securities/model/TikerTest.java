package spring.n4d3sh1k4.securities.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TikerTest {

    private Tiker tiker;

    @BeforeEach
    void setUp() {
        tiker = new Tiker(
                1L,
                "AAPL",
                "NASDAQ"
        );
    }

    @Test
    void testTikerFields() {
        assertEquals(1L, tiker.getId());
        assertEquals("AAPL", tiker.getNameTiker());
        assertEquals("NASDAQ", tiker.getDealPlace());
    }

    @Test
    void testSettersAndGetters() {
        tiker.setNameTiker("GOOG");
        tiker.setDealPlace("NYSE");

        assertEquals("GOOG", tiker.getNameTiker());
        assertEquals("NYSE", tiker.getDealPlace());
    }
}