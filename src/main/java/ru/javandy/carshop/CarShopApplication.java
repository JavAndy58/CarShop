package ru.javandy.carshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.service.CarService;
import ru.javandy.carshop.service.CustomerService;
import ru.javandy.carshop.service.DetailService;
import ru.javandy.carshop.service.OrderService;

import java.util.Date;

@SpringBootApplication
public class CarShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataDetailLoader(DetailService detailService, CustomerService customerService,
                                              CarService carService, OrderService orderService) {
        Customer customerIvan = new Customer("Иван Петров", "+79273893138");
        Customer customerEvg = new Customer("Ершое Евгений", "+72273893138");
        Customer customerVlad = new Customer("Макаров Владимир", "+75553893138");

        Detail detailRel = new Detail("Реле", 2, 100, 150, "",false);
        Detail detailScr = new Detail("Шрус", 1, 1750, 2100, "",false);
        Detail detailRul = new Detail("Рулевая рейка ", 1, 10000, 12550, "",false);
        Detail detailClips = new Detail("Клипса", 10, 15, 50, "", false);
        Detail detailPl = new Detail("Пыльник шруса", 1, 985, 1270, "",false);

        Car carFocus = new Car("Focus 2", "XXEERTY525SA626", customerEvg);
        Car carLogan = new Car("Logan 1", "TTTYYY525SA626", customerIvan);
        Car carAudi = new Car("Audi 100", "YUTYRYREYRA626", customerIvan);



        return args -> {
            detailService.save(detailRel);
            detailService.save(detailScr);
            detailService.save(detailRul);
            detailService.save(detailClips);
            detailService.save(detailPl);

            customerService.save(customerIvan);
            customerService.save(customerEvg);
            customerService.save(customerVlad);

            carService.save(carFocus);
            carService.save(carLogan);
            carService.save(carAudi);

            orderService.save(new Order(new Date(), 500, false, false, "", carAudi, detailRel, customerIvan));



        };
    }
}
