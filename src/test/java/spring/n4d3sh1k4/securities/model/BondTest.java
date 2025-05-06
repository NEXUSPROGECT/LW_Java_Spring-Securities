package spring.n4d3sh1k4.securities.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BondTest {

    private Bond bond;
    private FinAsset mockFinAsset;

    @BeforeEach
    void setUp() {
        mockFinAsset = Mockito.mock(FinAsset.class);
        bond = new Bond(
                1L,
                mockFinAsset,
                LocalDateTime.of(2030, 5, 6, 12, 0),
                4,
                7.5
        );
    }

    @Test
    void testBondFields() {
        assertEquals(1L, bond.getId());
        assertEquals(mockFinAsset, bond.getFinAsset());
        assertEquals(LocalDateTime.of(2030, 5, 6, 12, 0), bond.getDataRepayment());
        assertEquals(4, bond.getCouponsAmount());
        assertEquals(7.5, bond.getCouponsRate());
    }

    @Test
    void testSettersAndGetters() {
        FinAsset anotherMockAsset = Mockito.mock(FinAsset.class);
        bond.setFinAsset(anotherMockAsset);
        bond.setCouponsAmount(10);
        bond.setCouponsRate(5.0);

        assertEquals(anotherMockAsset, bond.getFinAsset());
        assertEquals(10, bond.getCouponsAmount());
        assertEquals(5.0, bond.getCouponsRate());
    }
}
