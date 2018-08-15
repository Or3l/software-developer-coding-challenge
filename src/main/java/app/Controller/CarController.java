package app.Controller;

import app.data.model.Car;
import app.data.model.Item;
import app.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {

    private IItemService carService;

    @Autowired
    public CarController(IItemService carService) {
        this.carService = carService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> addCar(@RequestBody Car car) {
        return new ResponseEntity<>(carService.saveCar(car), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Item findCarByID(@PathVariable long id) {
        return carService.findCarById(id);
    }
}
