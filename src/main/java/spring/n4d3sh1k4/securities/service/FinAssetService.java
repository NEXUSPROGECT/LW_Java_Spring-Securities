package spring.n4d3sh1k4.securities.service;

import spring.n4d3sh1k4.securities.model.FinAsset;
import spring.n4d3sh1k4.securities.repository.FinAssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FinAssetService {
    private final FinAssetRepository finAssetRepository;

    // ВОЗВРАТ ВСЕХ ЗАПИСЕЙ
    public List<FinAsset> listFinAssets(String registration) {
        if (registration != null) {
            return finAssetRepository.findByRegistration(registration);
        }
        return finAssetRepository.findAll();
    }

    // ВОЗВРАТ ЗАПИСИ ПО ID
    public FinAsset getFinAssetById(Long id) {
        return finAssetRepository.findById(id).orElse(null);
    }

    // СОХРАНЕНИЕ ЗАПИСИ
    public void saveFinAsset(FinAsset finAsset) {
        log.info("Saving FinAsset with registration: {}", finAsset.getRegistration());
        finAssetRepository.save(finAsset);
    }

    // УДАЛЕНИЕ ЗАПИСИ
    public void deleteFinAsset(Long id) {
        log.info("Deleting FinAsset with id: {}", id);
        finAssetRepository.deleteById(id);
    }

    public void updateFinAsset(FinAsset finAsset) {
        log.info("Updating FinAsset with registration: {}", finAsset.getRegistration());
        finAssetRepository.save(finAsset);
    }
}
