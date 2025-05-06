package spring.n4d3sh1k4.securities.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.n4d3sh1k4.securities.model.Tiker;
import spring.n4d3sh1k4.securities.service.TikerService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TikerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TikerService tikerService;

    @InjectMocks
    private TikerController tikerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tikerController).build();
    }

    @Test
    void shouldReturnTikersPage() throws Exception {
        List<Tiker> tikers = Arrays.asList(new Tiker(), new Tiker());
        when(tikerService.listTikers(null)).thenReturn(tikers);

        mockMvc.perform(get("/tikers"))
               .andExpect(status().isOk())
               .andExpect(view().name("tikers"))
               .andExpect(model().attributeExists("tikers"))
               .andExpect(model().attribute("tikers", tikers));
    }

    @Test
    void shouldFilterTikersByName() throws Exception {
        String nameTiker = "AAPL";
        List<Tiker> filtered = List.of(new Tiker());
        when(tikerService.listTikers(nameTiker)).thenReturn(filtered);

        mockMvc.perform(get("/tikers").param("nameTiker", nameTiker))
               .andExpect(status().isOk())
               .andExpect(view().name("tikers"))
               .andExpect(model().attribute("tikers", filtered));
    }

    @Test
    void shouldReturnCreateTikerForm() throws Exception {
        mockMvc.perform(get("/tiker/create-form"))
               .andExpect(status().isOk())
               .andExpect(view().name("tiker-create"));
    }

    @Test
    void shouldCreateTiker() throws Exception {
        Tiker tiker = new Tiker();

        mockMvc.perform(post("/tiker/create")
                        .flashAttr("tiker", tiker))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/tikers"));

        verify(tikerService).saveTiker(tiker);
    }

    @Test
    void shouldDeleteTiker() throws Exception {
        Long id = 1L;

        mockMvc.perform(post("/tiker/delete/{id}", id))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/tikers"));

        verify(tikerService).deleteTiker(id);
    }

    @Test
    void shouldReturnEditTikerForm() throws Exception {
        Long id = 1L;
        Tiker tiker = new Tiker();
        when(tikerService.getTikerById(id)).thenReturn(tiker);

        mockMvc.perform(get("/tiker/edit-form/{id}", id))
               .andExpect(status().isOk())
               .andExpect(view().name("tiker-edit"))
               .andExpect(model().attributeExists("tiker"))
               .andExpect(model().attribute("tiker", tiker));
    }

    @Test
    void shouldUpdateTikerViaEditForm() throws Exception {
        Long id = 1L;
        Tiker tiker = new Tiker();

        mockMvc.perform(post("/tiker/edit-form/{id}", id)
                        .flashAttr("tiker", tiker))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/tikers"));

        verify(tikerService).saveTiker(tiker);
    }

    @Test
    void shouldUpdateTikerViaUpdateEndpoint() throws Exception {
        Tiker tiker = new Tiker();

        mockMvc.perform(post("/tiker/update")
                        .flashAttr("tiker", tiker))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/tikers"));

        verify(tikerService).saveTiker(tiker);
    }
}
