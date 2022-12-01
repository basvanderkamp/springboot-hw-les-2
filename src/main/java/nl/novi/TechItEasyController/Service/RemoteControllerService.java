package nl.novi.TechItEasyController.Service;

import nl.novi.TechItEasyController.Repositorys.RemoteControllerRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository repos;

    public RemoteControllerService(RemoteControllerRepository repos) {
        this.repos = repos;
    }


}
