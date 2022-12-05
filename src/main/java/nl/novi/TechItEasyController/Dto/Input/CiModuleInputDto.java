package nl.novi.TechItEasyController.Dto.Input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CiModuleInputDto {



    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @Positive
    private double price;





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
