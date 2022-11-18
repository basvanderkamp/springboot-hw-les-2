package nl.novi.TechItEasyController.Controllers;

import nl.novi.TechItEasyController.Exceptions.IndexOutOfBounceException;
import nl.novi.TechItEasyController.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasyController.Models.Television;
import nl.novi.TechItEasyController.Repositorys.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;


@RestController
@RequestMapping("televisions")
public class TelevisionsController {

    @Autowired
    private TelevisionRepository repos;


    @GetMapping("")
    public ResponseEntity<Iterable<Television>> getTelevisions() {
        return ResponseEntity.ok(repos.findAll());
    }

    @GetMapping("/brand")
    public ResponseEntity<Iterable<Television>> getTelevisionsByBrand(@RequestParam String brand) {
        return ResponseEntity.ok(repos.findByBrandContaining(brand));
    }

    @PostMapping("")
    public ResponseEntity<String> createTelevision(@RequestBody Television television) {
        Television savedTelevision = repos.save(television);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/televisions/" + savedTelevision.getId()).toUriString());

        return ResponseEntity.created(uri).body("Television created !");
    }

    //@PutMapping("/{id}")
    //public ResponseEntity<String> updateTelevision(@PathVariable Long id, @RequestBody Television television) {
      //  repos.findById(id)
        //URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/televisions/{id}").toUriString());
        //return ResponseEntity.created(uri).body("television updated");
    //}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable Long id) {
        repos.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
