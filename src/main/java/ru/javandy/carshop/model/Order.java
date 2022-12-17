package ru.javandy.carshop.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
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

    public Order() {
    }

    public Order(Date created, double prepayment, boolean delivered, boolean cardPayment,
                 String note, Car car, Customer customer) {
        this.created = created;
        this.prepayment = prepayment;
        this.delivered = delivered;
        this.cardPayment = cardPayment;
        this.note = note;
        this.car = car;
        this.customer = customer;
    }

    public void addDetail(Detail detail) {
        details.add(detail);
    }

    public void removeDetail(Detail detail) {
        details.remove(detail);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public double getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(double prepayment) {
        this.prepayment = prepayment;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(boolean cardPayment) {
        this.cardPayment = cardPayment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.prepayment, prepayment) == 0 && delivered == order.delivered
                && cardPayment == order.cardPayment && Objects.equals(created, order.created)
                && Objects.equals(note, order.note) && Objects.equals(car, order.car)
                && Objects.equals(details, order.details) && Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, prepayment, delivered, cardPayment, note, car, details, customer);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", created=" + created +
                ", prepayment=" + prepayment +
                ", delivered=" + delivered +
                ", cardPayment=" + cardPayment +
                ", note='" + note + '\'' +
                ", car=" + car +
                ", details=" + details +
                ", customer=" + customer +
                '}';
    }
}
