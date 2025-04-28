package spring.n4d3sh1k4.securities.service;

import spring.n4d3sh1k4.securities.model.Security;
import spring.n4d3sh1k4.securities.repository.SecurityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityService {
    private final SecurityRepository securityRepository;

    // ВОЗВРАТ ВСЕХ ЗАПИСЕЙ
    public List<Security> listSecurities() {
        return securityRepository.findAll();
    }

    // ВОЗВРАТ ЗАПИСИ ПО ID
    public Security getSecurityById(Long id) {
        return securityRepository.findById(id).orElse(null);
    }

    // СОХРАНЕНИЕ ЗАПИСИ
    public void saveSecurity(Security security) {
        log.info("Saving Security for FinAsset id: {}", security.getFinAsset().getId());
        securityRepository.save(security);
    }

    // УДАЛЕНИЕ ЗАПИСИ
    public void deleteSecurity(Long id) {
        log.info("Deleting Security with id: {}", id);
        securityRepository.deleteById(id);
    }
}