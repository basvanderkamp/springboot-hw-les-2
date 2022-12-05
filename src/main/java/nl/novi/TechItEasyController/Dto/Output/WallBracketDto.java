package nl.novi.TechItEasyController.Dto.Output;

import nl.novi.TechItEasyController.Models.Television;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public class WallBracketDto {


    @NotBlank
    private String size;
    @NotBlank
    private String type;

    private boolean adjustable;
    @NotBlank
    private String name;
    @Positive
    private double price;



    //Relation
    private List<Television> televisions;
    public List<Television> getTelevisions() {
        return televisions;
    }
    public void setTelevisions(List<Television> televisions) {
        this.televisions = televisions;
    }



    //Getters
    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public boolean isAdjustable() {
        return adjustable;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }



    //Setters
    public void setSize(String size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAdjustable(boolean adjustable) {
        this.adjustable = adjustable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}