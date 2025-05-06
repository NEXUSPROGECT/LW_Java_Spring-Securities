package spring.n4d3sh1k4.securities.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.n4d3sh1k4.securities.model.Bond;
import spring.n4d3sh1k4.securities.repository.BondRepository;
import spring.n4d3sh1k4.securities.service.BondService;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BondServiceTest {

    @Mock
    private BondRepository bondRepository;

    @InjectMocks
    private BondService bondService;

    private Bond bond;

    @BeforeEach
    void setUp() {
        bond = new Bond(1L, null, null, 5, 3.5);
    }

    @Test
    void testListBonds() {
        // Подготовка данных
        when(bondRepository.findAll()).thenReturn(Collections.singletonList(bond));

        // Вызов метода
        var bonds = bondService.listBonds();

        // Проверка
        assertNotNull(bonds);
        assertEquals(1, bonds.size());
        assertEquals(bond, bonds.get(0));
        verify(bondRepository, times(1)).findAll();
    }

    @Test
    void testGetBondById() {
        // Подготовка данных
        when(bondRepository.findById(1L)).thenReturn(Optional.of(bond));

        // Вызов метода
        Bond foundBond = bondService.getBondById(1L);

        // Проверка
        assertNotNull(foundBond);
        assertEquals(bond.getId(), foundBond.getId());
        verify(bondRepository, times(1)).findById(1L);
    }

    @Test
    void testGetBondByIdNotFound() {
        // Подготовка данных
        when(bondRepository.findById(1L)).thenReturn(Optional.empty());

        // Вызов метода
        Bond foundBond = bondService.getBondById(1L);

        // Проверка
        assertNull(foundBond);
        verify(bondRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveBond() {
        // Вызов метода
        bondService.saveBond(bond);

        // Проверка
        verify(bondRepository, times(1)).save(bond);
    }

    @Test
    void testDeleteBond() {
        // Вызов метода
        bondService.deleteBond(1L);

        // Проверка
        verify(bondRepository, times(1)).deleteById(1L);
    }
}

