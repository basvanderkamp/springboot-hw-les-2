package nl.novi.TechItEasyController.Controllers;

import nl.novi.TechItEasyController.Exceptions.IndexOutOfBounceException;
import nl.novi.TechItEasyController.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasyController.Module.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;


@RestController
public class TelevisionsController {
    //variable

    private ArrayList<Television> televisions;

    public TelevisionsController() {
        televisions = new ArrayList<>();

        Television television = new Television();
        television.setName("SamsungTV1");
        television.setBrand("Samsung");
        television.setPrice(500);
        televisions.add(television);
    }

    @GetMapping("/televisions")
    public ResponseEntity<Object> getTelevisions() {
        return new ResponseEntity<>(televisions, HttpStatus.OK);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> getTelevisions(@PathVariable int id) {
        if (id < televisions.size()) {
            return new ResponseEntity<>(televisions.get(id), HttpStatus.OK);
        } else {
            throw new IndexOutOfBounceException("id not found");
        }
    }


    @PostMapping("/televisions")
    public ResponseEntity<Object> createTelevision (@RequestBody Television television) {
        televisions.add(television);
        return new ResponseEntity<>(television, HttpStatus.CREATED);
    }


    @PutMapping("/televisions/{id}")
    public ResponseEntity<Television> updatePerson (@PathVariable int id, @RequestBody Television television) {
        if (id >= 0 && id < televisions.size()) {
            televisions.set(id, television);
            return new ResponseEntity<>(television, HttpStatus.OK);
        } else {
            throw new RecordNotFoundException("invalid id");
        }
    }

    @DeleteMapping("/televisions")
    public ResponseEntity<String> deleteTelevision (@RequestBody String name) {
        for (int i = 0; i < televisions.size(); i++) {
            if (televisions.get(i).getName().equals(name)) {
                televisions.remove(i);
                return new ResponseEntity<>("Item Removed", HttpStatus.ACCEPTED);
            }
        }
        throw new RecordNotFoundException("no television found");
    }
}
