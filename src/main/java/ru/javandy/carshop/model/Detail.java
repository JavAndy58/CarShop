package ru.javandy.carshop.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@Table(name = "details")
@NoArgsConstructor
@AllArgsConstructor
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int amount;
    private double purchasePrice;
    private double retailPrice;
    private String supplier;
    private boolean bringing;
}
