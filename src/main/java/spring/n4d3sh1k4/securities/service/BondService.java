package spring.n4d3sh1k4.securities.service;

import spring.n4d3sh1k4.securities.model.Bond;
import spring.n4d3sh1k4.securities.repository.BondRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BondService {
    private final BondRepository bondRepository;

    // ВОЗВРАТ ВСЕХ ЗАПИСЕЙ
    public List<Bond> listBonds() {
        return bondRepository.findAll();
    }

    // ВОЗВРАТ ЗАПИСИ ПО ID
    public Bond getBondById(Long id) {
        return bondRepository.findById(id).orElse(null);
    }

    // СОХРАНЕНИЕ ЗАПИСИ
    public void saveBond(Bond bond) {
        log.info("Saving Bond for FinAsset id: {}", bond.getFinAsset().getId());
        bondRepository.save(bond);
    }

    // УДАЛЕНИЕ ЗАПИСИ
    public void deleteBond(Long id) {
        log.info("Deleting Bond with id: {}", id);
        bondRepository.deleteById(id);
    }
}