package spring.n4d3sh1k4.securities.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.n4d3sh1k4.securities.model.FinAsset;
import spring.n4d3sh1k4.securities.model.Security;
import spring.n4d3sh1k4.securities.service.FinAssetService;
import spring.n4d3sh1k4.securities.service.SecurityService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SecurityControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SecurityService securityService;

    @Mock
    private FinAssetService finAssetService;

    @InjectMocks
    private SecurityController securityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(securityController).build();
    }

    @Test
    void shouldReturnSecuritiesPage() throws Exception {
        List<Security> securities = Arrays.asList(new Security(), new Security());
        when(securityService.listSecurities()).thenReturn(securities);

        mockMvc.perform(get("/securities"))
               .andExpect(status().isOk())
               .andExpect(view().name("securities"))
               .andExpect(model().attributeExists("securities"))
               .andExpect(model().attribute("securities", securities));
    }

    @Test
    void shouldReturnCreateSecurityForm() throws Exception {
        List<FinAsset> finAssets = Arrays.asList(new FinAsset(), new FinAsset());
        when(finAssetService.listFinAssets(null)).thenReturn(finAssets);

        mockMvc.perform(get("/security/create-form"))
               .andExpect(status().isOk())
               .andExpect(view().name("security-create"))
               .andExpect(model().attributeExists("security"))
               .andExpect(model().attributeExists("finassets"));
    }

    @Test
    void shouldCreateSecurity() throws Exception {
        Security security = new Security();

        mockMvc.perform(post("/security/create")
                        .flashAttr("security", security))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/securities"));

        verify(securityService).saveSecurity(security);
    }

    @Test
    void shouldDeleteSecurity() throws Exception {
        Long id = 1L;

        mockMvc.perform(post("/security/delete/{id}", id))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/securities"));

        verify(securityService).deleteSecurity(id);
    }

    @Test
    void shouldReturnEditSecurityForm() throws Exception {
        Long id = 1L;
        Security security = new Security();
        List<FinAsset> finAssets = Arrays.asList(new FinAsset(), new FinAsset());

        when(securityService.getSecurityById(id)).thenReturn(security);
        when(finAssetService.listFinAssets(null)).thenReturn(finAssets);

        mockMvc.perform(get("/security/edit-form/{id}", id))
               .andExpect(status().isOk())
               .andExpect(view().name("security-edit"))
               .andExpect(model().attributeExists("security"))
               .andExpect(model().attributeExists("finassets"))
               .andExpect(model().attribute("security", security));
    }

    @Test
    void shouldUpdateSecurity() throws Exception {
        Long id = 1L;
        Security security = new Security();
        security.setId(id);

        mockMvc.perform(post("/security/edit-form/{id}", id)
                        .flashAttr("security", security))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/securities"));

        verify(securityService).saveSecurity(security);
    }
}
