package spring.n4d3sh1k4.securities.repository;

import spring.n4d3sh1k4.securities.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepository extends JpaRepository<Security, Long> {
}
