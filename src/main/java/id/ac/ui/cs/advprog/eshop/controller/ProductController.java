package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public String index(){
        return "home";
    }

    @GetMapping("/product/create")
    public String createProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/product/create")
    public String createProductPost(@ModelAttribute Product product, Model model){
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/product/list")
    public String productListPage(Model model){
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }


    // Edit Product
    @GetMapping("/product/edit/{id}")
    public String editProductPage(@PathVariable("id") String id, Model model){
        Product product = service.findById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/product/edit")
    public String editProductPost(@ModelAttribute Product product, Model model){
        service.save(product);
        return "redirect:list";
    }
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable String id, Model model){
        service.deleteProductById(id);
        return "redirect:../list";
    }

}


@Controller
@RequestMapping("/car")
class CarController extends ProductController{
    @Autowired
    private CarServiceImpl carservice;

    @GetMapping("/createCar")
    public String createCarPage (Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "createCar";
    }

    @PostMapping("/createCar")
    public String createCarPost (@ModelAttribute Car car, Model model){
        carservice.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carListPage (Model model) {
        List<Car> allCars = carservice.findAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage (@PathVariable String carId, Model model) {
        Car car = carservice.findById(carId);
        model.addAttribute("car", car);
        return "editCar";
    }
    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        System.out.println(car.getCarId());
        carservice.update(car.getCarId(), car);
        return "redirect:listCar";
    }
    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") String carId) {
        carservice.deleteCarById(carId);
        return "redirect:listCar";
    }
}