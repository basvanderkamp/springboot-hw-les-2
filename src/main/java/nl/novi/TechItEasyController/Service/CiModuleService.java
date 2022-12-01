package nl.novi.TechItEasyController.Service;

import nl.novi.TechItEasyController.Repositorys.CiModuleRepository;
import org.springframework.stereotype.Service;

@Service
public class CiModuleService {

    private final CiModuleRepository repos;

    public CiModuleService(CiModuleRepository repos) {
        this.repos = repos;
    }
}
