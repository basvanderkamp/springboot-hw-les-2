package nl.novi.TechItEasyController.Repositorys;

import nl.novi.TechItEasyController.Models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelevisionRepository extends JpaRepository<Television, Long> {

    public Iterable<Television> findByBrandContaining(String brand);

}
