package ru.javandy.carshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.javandy.carshop.Exeption.CarNotFoundException;
import ru.javandy.carshop.Exeption.CustomerNotFoundException;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.service.CarService;
import ru.javandy.carshop.service.CustomerService;
import ru.javandy.carshop.service.DetailService;

@SpringBootApplication
public class CarShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataDetailLoader(DetailService detailService, CustomerService customerService,
                                              CarService carService) {
        Customer customerIvan = new Customer("Иван Петров", "+79273893138");
        Customer customerEvg = new Customer("Ершое Евгений", "+72273893138");
        Customer customerVlad = new Customer("Макаров Владимир", "+75553893138");


        return args -> {
            detailService.save(new Detail("Реле", 100, 150, false));
            detailService.save(new Detail("Шрус", 1750, 2100, false));
            detailService.save(new Detail("Рулевая рейка ", 10000, 12550, false));
            detailService.save(new Detail("Клипса", 15, 50, false));
            detailService.save(new Detail("Пыльник шруса", 985, 1270, false));

            customerService.save(customerIvan);
            customerService.save(customerEvg);
            customerService.save(customerVlad);

            carService.save(new Car("Focus 2", "XXEERTY525SA626", customerEvg));
            carService.save(new Car("Logan 1", "TTTYYY525SA626", customerIvan));
            carService.save(new Car("Audi 100", "YUTYRYREYRA626", customerIvan));


        };
    }
}
