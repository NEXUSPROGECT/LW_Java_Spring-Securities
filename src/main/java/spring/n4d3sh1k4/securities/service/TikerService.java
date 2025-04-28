package spring.n4d3sh1k4.securities.service;

import spring.n4d3sh1k4.securities.model.Tiker;
import spring.n4d3sh1k4.securities.repository.TikerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TikerService {
    private final TikerRepository tikerRepository;

    // ВОЗВРАТ ВСЕХ ЗАПИСЕЙ
    public List<Tiker> listTikers(String nameTiker) {
        if (nameTiker != null) {
            return tikerRepository.findByNameTiker(nameTiker);
        }
        return tikerRepository.findAll();
    }

    // ВОЗВРАТ ЗАПИСИ ПО ID
    public Tiker getTikerById(Long id) {
        return tikerRepository.findById(id).orElse(null);
    }

    // СОХРАНЕНИЕ ЗАПИСИ
    public void saveTiker(Tiker tiker) {
        log.info("Saving Tiker with name: {}", tiker.getNameTiker());
        tikerRepository.save(tiker);
    }

    // УДАЛЕНИЕ ЗАПИСИ
    public void deleteTiker(Long id) {
        log.info("Deleting Tiker with id: {}", id);
        tikerRepository.deleteById(id);
    }
}