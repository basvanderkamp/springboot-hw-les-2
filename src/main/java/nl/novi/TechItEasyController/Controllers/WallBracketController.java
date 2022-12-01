package nl.novi.TechItEasyController.Controllers;


import nl.novi.TechItEasyController.Service.WallBracketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wallbrackets")
public class WallBracketController {

    private final WallBracketService service;

    public WallBracketController(WallBracketService service) {
        this.service = service;
    }
}
