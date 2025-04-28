package spring.n4d3sh1k4.securities.controller;

import spring.n4d3sh1k4.securities.model.FinAsset;
import spring.n4d3sh1k4.securities.service.FinAssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import spring.n4d3sh1k4.securities.model.Tiker;
import spring.n4d3sh1k4.securities.service.TikerService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FinAssetController {
    private final FinAssetService finAssetService;
    private final TikerService tikerService;

    // ОТОБРАЖЕНИЕ ВСЕХ ЗАПИСЕЙ
    @GetMapping("/finassets")
    public String finAssets(@RequestParam(name = "registration", required = false) String registration, Model model) {
        try {
            model.addAttribute("finassets", finAssetService.listFinAssets(registration));
            return "finassets";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/"; // или другой шаблон ошибки
        }
    }


    // СОЗДАНИЕ ЗАПИСИ
    @PostMapping("/finasset/create")
    public String createFinAsset(FinAsset finAsset) {
        finAssetService.saveFinAsset(finAsset);
        return "redirect:/finassets";
    }

    @GetMapping("/finasset/create-form")
    public String createFinAssetForm(Model model) {
        model.addAttribute("finAsset", new FinAsset());
        List<Tiker> tikers = tikerService.listTikers(null);
        model.addAttribute("tikers", tikers);

        return "finasset-create";
    }


    // УДАЛЕНИЕ ЗАПИСИ
    @PostMapping("/finasset/delete/{id}")
    public String deleteFinAsset(@PathVariable Long id) {
        finAssetService.deleteFinAsset(id);
        return "redirect:/finassets";
    }


    // РЕДАКТИРОВАНИЕ ОДНИЙ ЗАПИСИ
    @GetMapping("/finasset/edit-form/{id}")
    public String editFinAssetInfo(@PathVariable Long id, Model model) {
        model.addAttribute("finasset", finAssetService.getFinAssetById(id));
        List<Tiker> tikers = tikerService.listTikers(null);
        model.addAttribute("tikers", tikers);
        return "finasset-edit";
    }

    @PostMapping("/finasset/edit-form/{id}")
        public String updateFinAsset(@PathVariable Long id, @ModelAttribute FinAsset finAsset) {
        finAsset.setId(id);
        finAssetService.saveFinAsset(finAsset);
        return "redirect:/finassets";
    }
}

