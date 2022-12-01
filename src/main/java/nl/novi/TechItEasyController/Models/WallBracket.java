package nl.novi.TechItEasyController.Models;


import javax.persistence.*;

@Entity
@Table(name = "WallBrackets")
public class WallBracket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;
    private String type;
    private boolean adjustable;
    private String name;
    private double price;





    //Getters

    public Long getId() {
        return id;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

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
