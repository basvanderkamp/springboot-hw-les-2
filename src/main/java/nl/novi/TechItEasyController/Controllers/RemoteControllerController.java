package nl.novi.TechItEasyController.Controllers;


import nl.novi.TechItEasyController.Service.RemoteControllerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("remotecontrollers")
public class RemoteControllerController {


    private final RemoteControllerService service;

    public RemoteControllerController(RemoteControllerService service) {
        this.service = service;
    }


}