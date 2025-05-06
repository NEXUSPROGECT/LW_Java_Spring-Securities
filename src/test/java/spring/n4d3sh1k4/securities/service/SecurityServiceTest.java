package spring.n4d3sh1k4.securities.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.n4d3sh1k4.securities.model.Security;
import spring.n4d3sh1k4.securities.repository.SecurityRepository;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceTest {

    @Mock
    private SecurityRepository securityRepository;

    @InjectMocks
    private SecurityService securityService;

    private Security security;

    @BeforeEach
    void setUp() {
        security = new Security(
                1L,
                null,
                null,
                null
        );
    }

    @Test
    void testListSecurities() {
        // Подготовка данных
        when(securityRepository.findAll()).thenReturn(Collections.singletonList(security));

        // Вызов метода
        var securities = securityService.listSecurities();

        // Проверка
        assertNotNull(securities);
        assertEquals(1, securities.size());
        assertEquals(security, securities.get(0));
        verify(securityRepository, times(1)).findAll();
    }

    @Test
    void testGetSecurityById() {
        // Подготовка данных
        when(securityRepository.findById(1L)).thenReturn(Optional.of(security));

        // Вызов метода
        Security foundSecurity = securityService.getSecurityById(1L);

        // Проверка
        assertNotNull(foundSecurity);
        assertEquals(security.getId(), foundSecurity.getId());
        verify(securityRepository, times(1)).findById(1L);
    }

    @Test
    void testGetSecurityByIdNotFound() {
        // Подготовка данных
        when(securityRepository.findById(1L)).thenReturn(Optional.empty());

        // Вызов метода
        Security foundSecurity = securityService.getSecurityById(1L);

        // Проверка
        assertNull(foundSecurity);
        verify(securityRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveSecurity() {
        // Вызов метода
        securityService.saveSecurity(security);

        // Проверка
        verify(securityRepository, times(1)).save(security);
    }

    @Test
    void testDeleteSecurity() {
        // Вызов метода
        securityService.deleteSecurity(1L);

        // Проверка
        verify(securityRepository, times(1)).deleteById(1L);
    }
}

