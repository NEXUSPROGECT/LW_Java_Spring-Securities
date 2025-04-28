package spring.n4d3sh1k4.securities.repository;

import spring.n4d3sh1k4.securities.model.Tiker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TikerRepository extends JpaRepository<Tiker, Long> {
    List<Tiker> findByNameTiker(String nameTiker);
}
