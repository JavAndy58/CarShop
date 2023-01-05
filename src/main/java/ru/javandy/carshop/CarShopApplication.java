package ru.javandy.carshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.repository.CustomerRepository;
import ru.javandy.carshop.repository.OrderRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class CarShopApplication {


    public static void main(String[] args) {
        SpringApplication.run(CarShopApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner dataDetailLoader(CustomerRepository customerRepository, OrderRepository orderRepository)
//            throws ParseException {
//
//        Car carFocus = new Car("Focus 2", "XXEERTY525SA626");
//        Car carLogan = new Car("Logan 1", "TTTYYY525SA626");
//        Car carAudi = new Car("Audi 100", "YUTYRYREYRA626");
//
//        Detail detailRel = new Detail("Реле", 2, 100, 150, "", false);
//        Detail detailScr = new Detail("Шрус", 1, 1750, 2100, "", false);
//        Detail detailRul = new Detail("Рулевая рейка ", 1, 10000, 12550, "", false);
//        Detail detailClips = new Detail("Клипса", 10, 15, 50, "", false);
//        Detail detailPl = new Detail("Пыльник шруса", 1, 985, 1270, "", false);
//        Detail detailRadio = new Detail("Магнитола LG", 1, 7500, 10253, "", false);
//
//        Customer customerIvan = new Customer("Иван Потапов", "+79273893138");
//        Customer customerEvg = new Customer("Ершое Евгений", "+72273893138");
//        Customer customerVlad = new Customer("Макаров Владимир", "+75553893138");
//
//
//        customerIvan.addCar(carFocus);
//        customerIvan.addCar(carLogan);
//        customerEvg.addCar(carAudi);
//        List<Customer> customers = Arrays.asList(customerIvan, customerEvg, customerVlad);
//        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
//        Date dateOrderOne = formatDate.parse("01.12.2022");
//        Date dateOrderTwo = formatDate.parse("03.12.2022");
//        Date dateOrderThree = formatDate.parse("05.12.2022");
//        Date dateOrderFour = formatDate.parse("07.12.2022");
//
//
//        Order orderOne = new Order(dateOrderOne, 500, false, false, "", carAudi, customerEvg);
//        orderOne.addDetail(detailRel);
//        orderOne.addDetail(detailScr);
//        Order orderTwo = new Order(dateOrderTwo, 0, false, true, "оплата по приезду", carLogan, customerIvan);
//        orderTwo.addDetail(detailRul);
//        orderTwo.addDetail(detailClips);
//        Order orderThree = new Order(dateOrderThree, 100, false, false, " доставка месяц", carFocus, customerIvan);
//        orderThree.addDetail(detailPl);
//        Order orderFour = new Order(dateOrderFour, 5000, false, false, "", null, customerVlad);
//        orderFour.addDetail(detailRadio);
//        List<Order> orders = Arrays.asList(orderOne, orderTwo, orderThree, orderFour);
//
//        return args -> {
//            customerRepository.saveAll(customers);
//            orderRepository.saveAll(orders);
//        };
//    }
}
