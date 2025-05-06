package spring.n4d3sh1k4.securities.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.n4d3sh1k4.securities.model.FinAsset;
import spring.n4d3sh1k4.securities.model.Tiker;
import spring.n4d3sh1k4.securities.service.FinAssetService;
import spring.n4d3sh1k4.securities.service.TikerService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FinAssetControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FinAssetService finAssetService;

    @Mock
    private TikerService tikerService;

    @InjectMocks
    private FinAssetController finAssetController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(finAssetController).build();
    }

    @Test
    void shouldReturnFinAssetsPage() throws Exception {
        List<FinAsset> finAssets = Arrays.asList(new FinAsset(), new FinAsset());
        when(finAssetService.listFinAssets(null)).thenReturn(finAssets);

        mockMvc.perform(get("/finassets"))
               .andExpect(status().isOk())
               .andExpect(view().name("finassets"))
               .andExpect(model().attributeExists("finassets"))
               .andExpect(model().attribute("finassets", finAssets));
    }

    @Test
    void shouldCreateFinAsset() throws Exception {
        FinAsset finAsset = new FinAsset();
        finAsset.setId(1L);  // Пример создания финансового актива

        mockMvc.perform(post("/finasset/create")
                .flashAttr("finAsset", finAsset))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/finassets"));

        verify(finAssetService).saveFinAsset(finAsset);
    }

    @Test
    void shouldReturnCreateFinAssetForm() throws Exception {
        List<Tiker> tikers = Arrays.asList(new Tiker(), new Tiker());  // Пример списка тикеров
        when(tikerService.listTikers(null)).thenReturn(tikers);

        mockMvc.perform(get("/finasset/create-form"))
               .andExpect(status().isOk())
               .andExpect(view().name("finasset-create"))
               .andExpect(model().attributeExists("finAsset"))
               .andExpect(model().attributeExists("tikers"));
    }

    @Test
    void shouldDeleteFinAsset() throws Exception {
        Long finAssetId = 1L;

        mockMvc.perform(post("/finasset/delete/{id}", finAssetId))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/finassets"));

        verify(finAssetService).deleteFinAsset(finAssetId);
    }

    @Test
    void shouldReturnEditFinAssetForm() throws Exception {
        Long finAssetId = 1L;
        FinAsset finAsset = new FinAsset();
        when(finAssetService.getFinAssetById(finAssetId)).thenReturn(finAsset);

        List<Tiker> tikers = Arrays.asList(new Tiker(), new Tiker());
        when(tikerService.listTikers(null)).thenReturn(tikers);

        mockMvc.perform(get("/finasset/edit-form/{id}", finAssetId))
               .andExpect(status().isOk())
               .andExpect(view().name("finasset-edit"))
               .andExpect(model().attributeExists("finasset"))
               .andExpect(model().attributeExists("tikers"));
    }

    @Test
    void shouldUpdateFinAsset() throws Exception {
        Long finAssetId = 1L;
        FinAsset finAsset = new FinAsset();
        finAsset.setId(finAssetId);

        mockMvc.perform(post("/finasset/edit-form/{id}", finAssetId)
                .flashAttr("finAsset", finAsset))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/finassets"));

        verify(finAssetService).saveFinAsset(finAsset);
    }
}
