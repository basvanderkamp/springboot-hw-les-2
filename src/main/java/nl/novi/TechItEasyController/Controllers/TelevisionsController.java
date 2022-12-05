package nl.novi.TechItEasyController.Controllers;

import nl.novi.TechItEasyController.Dto.Output.TelevisionDto;
import nl.novi.TechItEasyController.Dto.Input.TelevisionInputDto;
import nl.novi.TechItEasyController.Service.TelevisionService;
import nl.novi.TechItEasyController.Util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("televisions")
public class TelevisionsController {

    private final TelevisionService service;

    public TelevisionsController(TelevisionService service) {
        this.service = service;
    }




    @GetMapping("")
    public ResponseEntity<Iterable<TelevisionDto>> getTelevisions() {
        return ResponseEntity.ok(service.getTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevisionsById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOneTelevision(id));
    }



    @PostMapping("")
    public ResponseEntity<String> createTelevision(@Valid @RequestBody TelevisionDto televisionDto, BindingResult br) {

        if (br.hasErrors()) {
            String errorString = Utils.reportErrors(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = service.createTelevision(televisionDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/televisions/" + createdId).toUriString());

            return ResponseEntity.created(uri).body("Television created!");
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> overWriteTelevision(@PathVariable long id, @RequestBody TelevisionInputDto televisionInputDto) {
        return ResponseEntity.ok(service.overrideTelevision(id, televisionInputDto));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevisionById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteTelevision(id));
    }


    @PutMapping("/{id}/remotecontrollers/{remoteControllerId}")
    public void assignRemoteControllerToTelevision(@PathVariable Long id, @PathVariable Long remoteControllerId) {
        TelevisionService.assignRemoteControllerToTelevision(id, remoteControllerId);
    }




}
