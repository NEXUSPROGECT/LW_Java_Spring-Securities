package spring.n4d3sh1k4.securities.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.n4d3sh1k4.securities.dto.UserRegistrationDto;
import spring.n4d3sh1k4.securities.model.User;
import spring.n4d3sh1k4.securities.repository.UserRepository;
import spring.n4d3sh1k4.securities.service.UserService;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private UserRegistrationDto registrationDto;
    private User user;

    @BeforeEach
    void setUp() {
        registrationDto = new UserRegistrationDto();
        registrationDto.setUsername("testUser");
        registrationDto.setPassword("password123");
        registrationDto.setConfirmPassword("password123");
        user = new User(1L, "testUser", "encodedPassword", true, Collections.singleton("ROLE_USER"));
    }

    @Test
    void testRegisterNewUser() {
        // Мокаем метод findByUsername, чтобы он возвращал Optional.empty() (пользователь не найден)
        when(userRepository.findByUsername(registrationDto.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(registrationDto.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Вызов метода
        User registeredUser = userService.registerNewUser(registrationDto);

        // Проверка
        assertNotNull(registeredUser);
        assertEquals("testUser", registeredUser.getUsername());
        assertEquals("encodedPassword", registeredUser.getPassword());
        assertTrue(registeredUser.isEnabled());
        assertTrue(registeredUser.getRoles().contains("ROLE_USER"));

        // Проверяем, что методы репозитория были вызваны
        verify(userRepository, times(1)).findByUsername(registrationDto.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterNewUser_UsernameAlreadyTaken() {
        // Мокаем метод findByUsername, чтобы он возвращал уже существующего пользователя
        when(userRepository.findByUsername(registrationDto.getUsername())).thenReturn(Optional.of(user));

        // Вызов метода и проверка на исключение
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerNewUser(registrationDto));
        assertEquals("Username already taken", exception.getMessage());

        // Проверяем, что метод save не был вызван
        verify(userRepository, times(1)).findByUsername(registrationDto.getUsername());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testLoadUserByUsername() {
        // Мокаем метод findByUsername, чтобы он возвращал пользователя
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        // Вызов метода
        var loadedUser = userService.loadUserByUsername("testUser");

        // Проверка
        assertNotNull(loadedUser);
        assertEquals("testUser", loadedUser.getUsername());
        assertEquals("encodedPassword", loadedUser.getPassword());
        assertTrue(loadedUser.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));

        // Проверяем, что метод findByUsername был вызван
        verify(userRepository, times(1)).findByUsername("testUser");
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Мокаем метод findByUsername, чтобы он возвращал пустой Optional
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());

        // Вызов метода и проверка на исключение
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("testUser"));
        assertEquals("User not found", exception.getMessage());

        // Проверяем, что метод findByUsername был вызван
        verify(userRepository, times(1)).findByUsername("testUser");
    }
}

