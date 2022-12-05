package nl.novi.TechItEasyController.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "remote_controllers")
public class RemoteController {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String compatibleWith;
    private String batteryType;
    private String brand;
    private double price;
    private int originalStock;



    //Relations
    @OneToOne(mappedBy = "remoteController")
    @JsonIgnore
    private Television television;

    public Television getTelevision() {
        return television;
    }

    public void setTelevision(Television television) {
        this.television = television;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getOriginalStock() {
        return originalStock;
    }


    //Setters
    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOriginalStock(int originalStock) {
        this.originalStock = originalStock;
    }
}
