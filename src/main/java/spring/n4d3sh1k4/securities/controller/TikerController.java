package spring.n4d3sh1k4.securities.controller;

import spring.n4d3sh1k4.securities.model.Tiker;
import spring.n4d3sh1k4.securities.service.TikerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TikerController {
    private final TikerService tikerService;

    @GetMapping("/tikers")
    public String tikers(@RequestParam(name = "nameTiker", required = false) String nameTiker, Model model) {
        model.addAttribute("tikers", tikerService.listTikers(nameTiker));
        return "tikers";
    }

    @PostMapping("/tiker/create")
    public String createTiker(Tiker tiker) {
        tikerService.saveTiker(tiker);
        return "redirect:/tikers";
    }

    @GetMapping("/tiker/create-form")
    public String createTikerForm() {
    return "tiker-create";
    }

    @PostMapping("/tiker/delete/{id}")
    public String deleteTiker(@PathVariable Long id) {
        tikerService.deleteTiker(id);
        return "redirect:/tikers";
    }

    @PostMapping("/tiker/update")
    public String updateTiker(Tiker tiker) {
        tikerService.saveTiker(tiker);
        return "redirect:/tikers";
    }

    @GetMapping("/tiker/edit-form/{id}")
    public String editTikerInfo(@PathVariable Long id, Model model) {
        model.addAttribute("tiker", tikerService.getTikerById(id));
        return "tiker-edit";
    }

    @PostMapping("/tiker/edit-form/{id}")
    public String updateTiker(@PathVariable Long id, @ModelAttribute Tiker tiker) {
    tiker.setId(id);
    tikerService.saveTiker(tiker);
    return "redirect:/tikers";
    }
}
