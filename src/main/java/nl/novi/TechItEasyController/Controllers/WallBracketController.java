package nl.novi.TechItEasyController.Controllers;

import nl.novi.TechItEasyController.Dto.WallBracketDto;
import nl.novi.TechItEasyController.Service.WallBracketService;
import nl.novi.TechItEasyController.Util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("wallbrackets")
public class WallBracketController {

    private final WallBracketService service;

    public WallBracketController(WallBracketService service) {
        this.service = service;
    }




    @GetMapping("")
    public ResponseEntity<Iterable<WallBracketDto>> getWallBrackets() {
        return ResponseEntity.ok(service.getWallBrackets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDto> getWallBracketsById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOneWallBracket(id));
    }



    @PostMapping("")
    public ResponseEntity<String> createWallBracket(@Valid @RequestBody WallBracketDto wallBracketDto, BindingResult br) {

        if (br.hasErrors()) {
            String errorString = Utils.reportErrors(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = service.createWallBracket(wallBracketDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/wallbrackets/" + createdId).toUriString());

            return ResponseEntity.created(uri).body("Wall bracket created!");
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<WallBracketDto> overWriteWallBracket(@PathVariable long id,@RequestBody WallBracketDto wallBracketDto) {
        return ResponseEntity.ok(service.overrideWallBracket(id, wallBracketDto));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWallBracketById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteWallBracket(id));
    }
}
