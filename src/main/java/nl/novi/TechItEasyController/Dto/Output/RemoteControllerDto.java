package nl.novi.TechItEasyController.Dto.Output;

import nl.novi.TechItEasyController.Models.Television;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class RemoteControllerDto {



    private String compatibleWith;
    @NotBlank
    private String batteryType;
    @NotBlank
    private String brand;
    @Positive
    private double price;
    private int originalStock;


    //Relation
    private Television television;
    public Television getTelevision() {
        return television;
    }
    public void setTelevision(Television television) {
        this.television = television;
    }



    //Getters
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
