package spring.n4d3sh1k4.securities.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.n4d3sh1k4.securities.model.FinAsset;
import spring.n4d3sh1k4.securities.repository.FinAssetRepository;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FinAssetServiceTest {

    @Mock
    private FinAssetRepository finAssetRepository;

    @InjectMocks
    private FinAssetService finAssetService;

    private FinAsset finAsset;

    @BeforeEach
    void setUp() {
        finAsset = new FinAsset(
                1L,
                null,
                "Registration1",
                null,
                "Emitent1",
                "FormIssue1",
                1000.0,
                10
        );
    }

    @Test
    void testListFinAssets() {
        // Подготовка данных
        when(finAssetRepository.findAll()).thenReturn(Collections.singletonList(finAsset));

        // Вызов метода
        var finAssets = finAssetService.listFinAssets(null);

        // Проверка
        assertNotNull(finAssets);
        assertEquals(1, finAssets.size());
        assertEquals(finAsset, finAssets.get(0));
        verify(finAssetRepository, times(1)).findAll();
    }

    @Test
    void testListFinAssetsWithRegistration() {
        // Подготовка данных
        when(finAssetRepository.findByRegistration("Registration1")).thenReturn(Collections.singletonList(finAsset));

        // Вызов метода
        var finAssets = finAssetService.listFinAssets("Registration1");

        // Проверка
        assertNotNull(finAssets);
        assertEquals(1, finAssets.size());
        assertEquals(finAsset, finAssets.get(0));
        verify(finAssetRepository, times(1)).findByRegistration("Registration1");
    }

    @Test
    void testGetFinAssetById() {
        // Подготовка данных
        when(finAssetRepository.findById(1L)).thenReturn(Optional.of(finAsset));

        // Вызов метода
        FinAsset foundFinAsset = finAssetService.getFinAssetById(1L);

        // Проверка
        assertNotNull(foundFinAsset);
        assertEquals(finAsset.getId(), foundFinAsset.getId());
        verify(finAssetRepository, times(1)).findById(1L);
    }

    @Test
    void testGetFinAssetByIdNotFound() {
        // Подготовка данных
        when(finAssetRepository.findById(1L)).thenReturn(Optional.empty());

        // Вызов метода
        FinAsset foundFinAsset = finAssetService.getFinAssetById(1L);

        // Проверка
        assertNull(foundFinAsset);
        verify(finAssetRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveFinAsset() {
        // Вызов метода
        finAssetService.saveFinAsset(finAsset);

        // Проверка
        verify(finAssetRepository, times(1)).save(finAsset);
    }

    @Test
    void testDeleteFinAsset() {
        // Вызов метода
        finAssetService.deleteFinAsset(1L);

        // Проверка
        verify(finAssetRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateFinAsset() {
        // Вызов метода
        finAssetService.updateFinAsset(finAsset);

        // Проверка
        verify(finAssetRepository, times(1)).save(finAsset);
    }
}

