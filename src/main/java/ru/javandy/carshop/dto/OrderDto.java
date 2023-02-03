package ru.javandy.carshop.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class OrderDto {
    private int id;
    private Date created;
    private double prepayment;
    private boolean delivered;
    private boolean cardPayment;
    private String note;
    private CarDto car;
    private List<DetailDto> details = new ArrayList<>();
    private CustomerDto customer;
    private double totalOrder;
    private double payOrder;

    public OrderDto(Date created, double prepayment, boolean delivered, boolean cardPayment, String note,
                    CarDto car, CustomerDto customer, double totalOrder, double payOrder) {
        this.created = created;
        this.prepayment = prepayment;
        this.delivered = delivered;
        this.cardPayment = cardPayment;
        this.note = note;
        this.car = car;
        this.customer = customer;
        this.totalOrder = totalOrder;
        this.payOrder = payOrder;
    }

    public void addDetailDto(DetailDto detailDTO) {
        this.details.add(detailDTO);
    }

    public void removeDetailDto(DetailDto detailDTO) {
        this.details.remove(detailDTO);
    }
}
