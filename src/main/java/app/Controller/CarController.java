package app.Controller;

import app.data.model.Car;
import app.data.model.Item;
import app.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private IItemService carService;

    @PostMapping
    public Item addCar(@RequestBody Car car){
        return carService.saveCar(car);
    }

    @GetMapping(value="/{id}")
    public Item findCarByID(@PathVariable long id){
        return carService.findCarById(id);
    }
}
