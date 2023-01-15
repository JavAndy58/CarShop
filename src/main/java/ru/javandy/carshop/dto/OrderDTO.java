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
public class OrderDTO {
    private int id;
    private Date created;
    private double prepayment;
    private boolean delivered;
    private boolean cardPayment;
    private String note;
    private CarDTO car;
    private List<DetailDTO> details = new ArrayList<>();
    private CustomerDTO customer;
    private double totalOrder;
    private double payOrder;

    public OrderDTO(Date created, double prepayment, boolean delivered, boolean cardPayment, String note,
                    CarDTO car, CustomerDTO customer, double totalOrder, double payOrder) {
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

    public void addDetailDTO(DetailDTO detailDTO) {
        this.details.add(detailDTO);
    }

    public void removeDetailDTO(DetailDTO detailDTO) {
        this.details.remove(detailDTO);
    }
}
