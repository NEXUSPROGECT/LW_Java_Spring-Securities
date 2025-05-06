package spring.n4d3sh1k4.securities.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");

        user = new User(
                1L,
                "john_doe",
                "password123",
                true,
                roles
        );
    }

    @Test
    void testUserFields() {
        assertEquals(1L, user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertTrue(user.isEnabled());
        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains("ROLE_USER"));
        assertTrue(user.getRoles().contains("ROLE_ADMIN"));
    }

    @Test
    void testSettersAndGetters() {
        user.setUsername("jane_doe");
        user.setPassword("newpassword123");
        user.setEnabled(false);

        Set<String> newRoles = new HashSet<>();
        newRoles.add("ROLE_USER");
        user.setRoles(newRoles);

        assertEquals("jane_doe", user.getUsername());
        assertEquals("newpassword123", user.getPassword());
        assertFalse(user.isEnabled());
        assertEquals(1, user.getRoles().size());
        assertTrue(user.getRoles().contains("ROLE_USER"));
    }
}
