package spring.n4d3sh1k4.securities.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.n4d3sh1k4.securities.model.Tiker;
import spring.n4d3sh1k4.securities.repository.TikerRepository;
import spring.n4d3sh1k4.securities.service.TikerService;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TikerServiceTest {

    @Mock
    private TikerRepository tikerRepository;

    @InjectMocks
    private TikerService tikerService;

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
    void testListTikers() {
        // Подготовка данных
        when(tikerRepository.findAll()).thenReturn(Collections.singletonList(tiker));

        // Вызов метода
        var tikers = tikerService.listTikers(null);

        // Проверка
        assertNotNull(tikers);
        assertEquals(1, tikers.size());
        assertEquals(tiker, tikers.get(0));
        verify(tikerRepository, times(1)).findAll();
    }

    @Test
    void testListTikersByName() {
        // Подготовка данных
        when(tikerRepository.findByNameTiker("AAPL")).thenReturn(Collections.singletonList(tiker));

        // Вызов метода
        var tikers = tikerService.listTikers("AAPL");

        // Проверка
        assertNotNull(tikers);
        assertEquals(1, tikers.size());
        assertEquals(tiker, tikers.get(0));
        verify(tikerRepository, times(1)).findByNameTiker("AAPL");
    }

    @Test
    void testGetTikerById() {
        // Подготовка данных
        when(tikerRepository.findById(1L)).thenReturn(Optional.of(tiker));

        // Вызов метода
        Tiker foundTiker = tikerService.getTikerById(1L);

        // Проверка
        assertNotNull(foundTiker);
        assertEquals(tiker.getId(), foundTiker.getId());
        verify(tikerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetTikerByIdNotFound() {
        // Подготовка данных
        when(tikerRepository.findById(1L)).thenReturn(Optional.empty());

        // Вызов метода
        Tiker foundTiker = tikerService.getTikerById(1L);

        // Проверка
        assertNull(foundTiker);
        verify(tikerRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveTiker() {
        // Вызов метода
        tikerService.saveTiker(tiker);

        // Проверка
        verify(tikerRepository, times(1)).save(tiker);
    }

    @Test
    void testDeleteTiker() {
        // Вызов метода
        tikerService.deleteTiker(1L);

        // Проверка
        verify(tikerRepository, times(1)).deleteById(1L);
    }
}

