package nl.novi.TechItEasyController.Controllers;

import nl.novi.TechItEasyController.Dto.CiModuleDto;
import nl.novi.TechItEasyController.Service.CiModuleService;
import nl.novi.TechItEasyController.Util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("cimodules")
public class CiModuleController {

    private final CiModuleService service;

    public CiModuleController(CiModuleService service) {
        this.service = service;
    }



    @GetMapping("")
    public ResponseEntity<Iterable<CiModuleDto>> getCiModules() {
        return ResponseEntity.ok(service.getCiModules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiModuleDto> getCiModulesById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOneCiModule(id));
    }


    @PostMapping("")
    public ResponseEntity<String> createTelevision(@Valid @RequestBody CiModuleDto ciModuleDto, BindingResult br) {

        if (br.hasErrors()) {
            String errorString = Utils.reportErrors(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = service.createCiModule(ciModuleDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/cimodules/" + createdId).toUriString());

            return ResponseEntity.created(uri).body("Ci-module created!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CiModuleDto> overWriteCiModule(@PathVariable long id,@RequestBody CiModuleDto ciModuleDto) {
        return ResponseEntity.ok(service.overrideCiModule(id, ciModuleDto));
    }





    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCiModuleById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteCiModule(id));
    }
}
