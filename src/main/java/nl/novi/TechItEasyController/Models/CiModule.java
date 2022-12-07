package nl.novi.TechItEasyController.Models;


import javax.persistence.*;

@Entity
@Table(name = "ci_modules")
public class CiModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private double price;



    //Relations
    @ManyToOne
    @JoinColumn(name = "television_id")
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
