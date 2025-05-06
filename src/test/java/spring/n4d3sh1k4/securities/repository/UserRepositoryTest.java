package spring.n4d3sh1k4.securities.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spring.n4d3sh1k4.securities.model.User;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(
                1L,
                "testuser",
                "password123",
                true,
                Set.of("ROLE_USER")
        );
    }

    @Test
    void testSaveUser() {
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
        assertEquals(user.getUsername(), savedUser.getUsername());
    }

    @Test
    void testFindByUsername() {
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUsername("testuser");
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    void testFindByUsernameNoMatch() {
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUsername("nonexistent");
        assertFalse(foundUser.isPresent());
    }

    @Test
    void testDeleteUser() {
        User savedUser = userRepository.save(user);
        userRepository.delete(savedUser);
        assertFalse(userRepository.findById(savedUser.getId()).isPresent());
    }
}
