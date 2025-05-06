package spring.n4d3sh1k4.securities.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.n4d3sh1k4.securities.dto.UserRegistrationDto;
import spring.n4d3sh1k4.securities.service.UserService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void shouldReturnRegistrationPageOnGetRequest() throws Exception {
        mockMvc.perform(get("/register"))
               .andExpect(status().isOk())
               .andExpect(view().name("register"))
               .andExpect(model().attributeExists("user"));
    }

    @Test
    void shouldRedirectToLoginOnSuccessfulRegistration() throws Exception {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUsername("testUser");
        dto.setPassword("password");

        // Предполагаем, что userService.registerNewUser() будет вызван
        mockMvc.perform(post("/register")
                .flashAttr("user", dto))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/login"));

        verify(userService).registerNewUser(dto);  // Проверяем, что метод registerNewUser был вызван
    }

    @Test
    void shouldReturnLoginPageOnGetRequest() throws Exception {
        mockMvc.perform(get("/login"))
               .andExpect(status().isOk())
               .andExpect(view().name("login"));
    }
}