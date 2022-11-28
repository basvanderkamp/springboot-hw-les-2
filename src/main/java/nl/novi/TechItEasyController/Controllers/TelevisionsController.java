package nl.novi.TechItEasyController.Controllers;

import nl.novi.TechItEasyController.Dto.TelevisionDto;
import nl.novi.TechItEasyController.Exceptions.IndexOutOfBounceException;
import nl.novi.TechItEasyController.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasyController.Models.Television;
import nl.novi.TechItEasyController.Repositorys.TelevisionRepository;
import nl.novi.TechItEasyController.Service.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;


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
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError fieldError : br.getFieldErrors()) {
                stringBuilder.append(fieldError.getField() + ":");
                stringBuilder.append(fieldError.getDefaultMessage());
                stringBuilder.append("\n");
            }
            return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = service.createTelevision(televisionDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/televisions/" + createdId).toUriString());

            return ResponseEntity.created(uri).body("Television created!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> overrideTelevision(@PathVariable Long id, @RequestBody TelevisionDto televisionDto) {

        Long overridedId = service.overrideTelevision(televisionDto, id);


        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/televisions/" + overridedId).toUriString());
        return ResponseEntity.created(uri).body("television updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevisionById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteTelevision(id));
    }
}
