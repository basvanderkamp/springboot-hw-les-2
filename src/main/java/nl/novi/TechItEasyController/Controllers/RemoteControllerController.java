package nl.novi.TechItEasyController.Controllers;

import nl.novi.TechItEasyController.Dto.RemoteControllerDto;
import nl.novi.TechItEasyController.Service.RemoteControllerService;
import nl.novi.TechItEasyController.Util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("remotecontrollers")
public class RemoteControllerController {


    private final RemoteControllerService service;

    public RemoteControllerController(RemoteControllerService service) {
        this.service = service;
    }



    @GetMapping("")
    public ResponseEntity<Iterable<RemoteControllerDto>> getRemoteControllers() {
        return ResponseEntity.ok(service.getRemoteControllers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteControllerById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOneRemoteController(id));
    }


    @PostMapping("")
    public ResponseEntity<String> createRemotecontroller(@Valid @RequestBody RemoteControllerDto remoteControllerDto, BindingResult br) {

        if (br.hasErrors()) {
            String errorString = Utils.reportErrors(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = service.createRemoteController(remoteControllerDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/remotecontrollers/" + createdId).toUriString());

            return ResponseEntity.created(uri).body("Remote created!");
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> overWriteRemoteController(@PathVariable long id,@RequestBody RemoteControllerDto remoteControllerDto) {
        return ResponseEntity.ok(service.overrideRemoteController(id, remoteControllerDto));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRemoteControllerById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteRemoteController(id));
    }
}
