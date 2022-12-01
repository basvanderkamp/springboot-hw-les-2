package nl.novi.TechItEasyController.Controllers;

import nl.novi.TechItEasyController.Service.CiModuleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cimodules")
public class CiModuleController {

    private final CiModuleService service;

    public CiModuleController(CiModuleService service) {
        this.service = service;
    }
}
