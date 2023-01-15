package ru.javandy.carshop.model;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private double prepayment;
    private boolean delivered;
    private boolean cardPayment;
    private String note;

    @OneToOne()
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<Detail> details = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public void addDetail(Detail detail) {
        details.add(detail);
    }

    public void removeDetail(Detail detail) {
        details.remove(detail);
    }
}
