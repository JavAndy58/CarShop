package ru.javandy.carshop;

import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@SpringBootApplication
public class CarShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataDetailLoader(DetailService detailService, CustomerService customerService,
                                              CarService carService, OrderService orderService) throws ParseException {
        Customer customerIvan = new Customer("Иван Петров", "+79273893138");
        Customer customerEvg = new Customer("Ершое Евгений", "+72273893138");
        Customer customerVlad = new Customer("Макаров Владимир", "+75553893138");
        List<Customer> customers = Arrays.asList(customerIvan, customerEvg, customerVlad);

        Detail detailRel = new Detail("Реле", 2, 100, 150, "", false);
        Detail detailScr = new Detail("Шрус", 1, 1750, 2100, "", false);
        Detail detailRul = new Detail("Рулевая рейка ", 1, 10000, 12550, "", false);
        Detail detailClips = new Detail("Клипса", 10, 15, 50, "", false);
        Detail detailPl = new Detail("Пыльник шруса", 1, 985, 1270, "", false);
        List<Detail> details = Arrays.asList(detailRel, detailScr, detailRul, detailClips, detailPl);

        Car carFocus = new Car("Focus 2", "XXEERTY525SA626", customerEvg);
        Car carLogan = new Car("Logan 1", "TTTYYY525SA626", customerIvan);
        Car carAudi = new Car("Audi 100", "YUTYRYREYRA626", customerIvan);
        List<Car> cars = Arrays.asList(carFocus, carLogan, carAudi);

        List<Detail> detailOrderOne = Arrays.asList(detailRel, detailRul);
        List<Detail> detailOrderTwo = List.of(detailClips);
        List<Detail> detailOrderThree = Arrays.asList(detailPl, detailScr);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dateOrderOne = format.parse("01.12.2022");
        Date dateOrderTwo = format.parse("03.12.2022");
        Date dateOrderThree = format.parse("05.12.2022");

        return args -> {
            detailService.saveAll(details);
            customerService.saveAll(customers);
            carService.saveAll(cars);

            orderService.save(new Order(dateOrderOne, 500, false, false, "", carFocus, detailOrderOne, customerEvg));
            orderService.save(new Order(dateOrderTwo, 0, false, true, "оплата по приезду", carLogan, detailOrderTwo, customerIvan));
            orderService.save(new Order(dateOrderThree, 100, false, false, " доставка месяц", carAudi, detailOrderThree, customerIvan));
        };
    }
}
