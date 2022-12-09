package ru.javandy.carshop.model;

import javax.persistence.*;

@Entity
@Table(name = "details")
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

    public Detail() {
    }

    public Detail(String name, int amount, double purchasePrice, double retailPrice, String supplier, boolean bringing) {
        this.name = name;
        this.amount = amount;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
        this.supplier = supplier;
        this.bringing = bringing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public boolean isBringing() {
        return bringing;
    }

    public void setBringing(boolean bringing) {
        this.bringing = bringing;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", purchasePrice=" + purchasePrice +
                ", retailPrice=" + retailPrice +
                ", supplier='" + supplier + '\'' +
                ", bringing=" + bringing +
                '}';
    }
}
