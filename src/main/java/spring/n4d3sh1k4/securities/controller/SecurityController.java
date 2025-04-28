package spring.n4d3sh1k4.securities.controller;

import spring.n4d3sh1k4.securities.model.FinAsset;
import spring.n4d3sh1k4.securities.model.Security;
import spring.n4d3sh1k4.securities.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.n4d3sh1k4.securities.service.FinAssetService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService securityService;
    private final FinAssetService finAssetService;

    // ОТОБРАЖЕНИЕ ВСЕХ ЗАПИСЕЙ
    @GetMapping("/securities")
    public String securities(Model model) {
        model.addAttribute("securities", securityService.listSecurities());
        return "securities";
    }


    // СОЗДАНИЕ ЗАПИСИ
    @PostMapping("/security/create")
    public String createSecurity(Security security) {
        securityService.saveSecurity(security);
        return "redirect:/securities";
    }

    @GetMapping("/security/create-form")
    public String createSecurityForm(Model model) {
        model.addAttribute("security", new Security());
        List<FinAsset> finassets = finAssetService.listFinAssets(null);
        model.addAttribute("finassets", finassets);
        return "security-create";
    }


    // УДАЛЕНИЕ ЗАПИСИ
    @PostMapping("/security/delete/{id}")
    public String deleteSecurity(@PathVariable Long id) {
        securityService.deleteSecurity(id);
        return "redirect:/securities";
    }


    // РЕДАКТИРОВАНИЕ ОДНИЙ ЗАПИСИ
    @GetMapping("/security/edit-form/{id}")
    public String editSecurityInfo(@PathVariable Long id, Model model) {
        model.addAttribute("security", securityService.getSecurityById(id));
        List<FinAsset> finassets = finAssetService.listFinAssets(null);
        model.addAttribute("finassets", finassets);
        return "security-edit";
    }

    @PostMapping("/security/edit-form/{id}")
        public String updateSecurity(@PathVariable Long id, @ModelAttribute Security security) {
        security.setId(id);
        securityService.saveSecurity(security);
        return "redirect:/securities";
    }
}

