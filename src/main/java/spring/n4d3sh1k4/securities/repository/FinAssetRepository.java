package spring.n4d3sh1k4.securities.repository;

import spring.n4d3sh1k4.securities.model.FinAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinAssetRepository extends JpaRepository<FinAsset, Long> {
    List<FinAsset> findByRegistration(String registration);
}
