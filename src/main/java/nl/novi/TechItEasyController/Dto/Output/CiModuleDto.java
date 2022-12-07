package nl.novi.TechItEasyController.Dto.Output;

import nl.novi.TechItEasyController.Models.Television;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CiModuleDto {



    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @Positive
    private double price;


    //Relation
    private Television television;
    public Television getTelevision() {
        return television;
    }
    public void setTelevision(Television television) {
        this.television = television;
    }



    //Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }


    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
