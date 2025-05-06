package spring.n4d3sh1k4.securities.repository;

import spring.n4d3sh1k4.securities.model.Bond;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BondRepository extends JpaRepository<Bond, Long> {
}
