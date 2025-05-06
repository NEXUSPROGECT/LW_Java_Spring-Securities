package spring.n4d3sh1k4.securities.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.n4d3sh1k4.securities.model.Bond;
import spring.n4d3sh1k4.securities.model.FinAsset;
import spring.n4d3sh1k4.securities.service.BondService;
import spring.n4d3sh1k4.securities.service.FinAssetService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BondControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BondService bondService;

    @Mock
    private FinAssetService finAssetService;

    @InjectMocks
    private BondController bondController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bondController).build();
    }

    @Test
    void shouldReturnBondsPage() throws Exception {
        List<Bond> bonds = Arrays.asList(new Bond(), new Bond());  // Два примера облигаций
        when(bondService.listBonds()).thenReturn(bonds);

        mockMvc.perform(get("/bonds"))
               .andExpect(status().isOk())
               .andExpect(view().name("bonds"))
               .andExpect(model().attributeExists("bonds"))
               .andExpect(model().attribute("bonds", bonds));
    }

    @Test
    void shouldDeleteBond() throws Exception {
        Long bondId = 1L;

        mockMvc.perform(post("/bond/delete/{id}", bondId))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/bonds"));

        verify(bondService).deleteBond(bondId);
    }

    @Test
    void shouldReturnCreateBondForm() throws Exception {
        List<FinAsset> finAssets = Arrays.asList(new FinAsset(), new FinAsset());  // Пример списка финансовых активов
        when(finAssetService.listFinAssets(null)).thenReturn(finAssets);

        mockMvc.perform(get("/bond/create-form"))
               .andExpect(status().isOk())
               .andExpect(view().name("bond-create"))
               .andExpect(model().attributeExists("bond"))
               .andExpect(model().attributeExists("finassets"));
    }

    @Test
    void shouldCreateBond() throws Exception {
        Bond bond = new Bond();
        bond.setId(1L);  // Пример создания объекта облигации

        mockMvc.perform(post("/bond/create")
                .flashAttr("bond", bond))  // Передаем объект в модель
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/bonds"));

        verify(bondService).saveBond(bond);
    }

    @Test
    void shouldReturnEditBondForm() throws Exception {
        Long bondId = 1L;
        Bond bond = new Bond();
        when(bondService.getBondById(bondId)).thenReturn(bond);

        List<FinAsset> finAssets = Arrays.asList(new FinAsset(), new FinAsset());
        when(finAssetService.listFinAssets(null)).thenReturn(finAssets);

        mockMvc.perform(get("/bond/edit-form/{id}", bondId))
               .andExpect(status().isOk())
               .andExpect(view().name("bond-edit"))
               .andExpect(model().attributeExists("bond"))
               .andExpect(model().attributeExists("finassets"));
    }

    @Test
    void shouldUpdateBond() throws Exception {
        Long bondId = 1L;
        Bond bond = new Bond();
        bond.setId(bondId);

        mockMvc.perform(post("/bond/edit-form/{id}", bondId)
                .flashAttr("bond", bond))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/bonds"));

        verify(bondService).saveBond(bond);
    }
}
