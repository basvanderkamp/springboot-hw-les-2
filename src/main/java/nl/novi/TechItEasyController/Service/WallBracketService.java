package nl.novi.TechItEasyController.Service;

import nl.novi.TechItEasyController.Repositorys.WallBracketRepository;
import org.springframework.stereotype.Service;

@Service
public class WallBracketService {

    private final WallBracketRepository repos;

    public WallBracketService(WallBracketRepository repos) {
        this.repos = repos;
    }
}
