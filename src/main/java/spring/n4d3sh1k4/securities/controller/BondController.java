package spring.n4d3sh1k4.securities.controller;

import spring.n4d3sh1k4.securities.model.Bond;
import spring.n4d3sh1k4.securities.model.FinAsset;
import spring.n4d3sh1k4.securities.service.BondService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.n4d3sh1k4.securities.service.FinAssetService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BondController {
    private final BondService bondService;
    private final FinAssetService finAssetService;


    // ОТОБРАЖЕНИЕ ВСЕХ ЗАПИСЕЙ
    @GetMapping("/bonds")
    public String bonds(Model model) {
        model.addAttribute("bonds", bondService.listBonds());
        return "bonds";
    }


    // УДАЛЕНИЕ ЗАПИСИ
    @PostMapping("/bond/delete/{id}")
    public String deleteBond(@PathVariable Long id) {
        bondService.deleteBond(id);
        return "redirect:/bonds";
    }


    // СОЗДАНИЕ ЗАПИСИ
    @GetMapping("/bond/create-form")
    public String createBondForm(Model model) {
        model.addAttribute("bond", new Bond());
        List<FinAsset> finassets = finAssetService.listFinAssets(null);
        model.addAttribute("finassets", finassets);
    return "bond-create";
    }

    @PostMapping("/bond/create")
    public String createBond(Bond bond) {
        bondService.saveBond(bond);
        return "redirect:/bonds";
    }


    // РЕДАКТИРОВАНИЕ ОДНИЙ ЗАПИСИ
    @GetMapping("/bond/edit-form/{id}")
    public String editBondForm(@PathVariable Long id, Model model) {
        model.addAttribute("bond", bondService.getBondById(id));
        List<FinAsset> finassets = finAssetService.listFinAssets(null);
        model.addAttribute("finassets", finassets);
        return "bond-edit";
    }

    @PostMapping("/bond/edit-form/{id}")
    public String updateBond(@PathVariable Long id, @ModelAttribute Bond bond) {
        bond.setId(id);
        bondService.saveBond(bond);
        return "redirect:/bonds";
    }
}
