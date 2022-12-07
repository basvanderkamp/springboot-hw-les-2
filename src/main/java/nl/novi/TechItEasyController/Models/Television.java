package nl.novi.TechItEasyController.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "televisions")
public class Television {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String brand;
    private String name;
    private double price;
    private double availableSize;
    private double refreshRate;
    private String screenType;
    private String screenQuality;
    private boolean smartTv;
    private boolean wifi;
    private boolean voiceControl;
    private boolean hdr;
    private boolean bluetooth;
    private boolean ambiLight;
    private int originalStock;
    private int sold;



    //Relations
    @OneToOne
    private RemoteController remoteController;

    @OneToMany(mappedBy = "television")
    @JsonIgnore
    private List<CiModule> ciModules;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "television_id"),
        inverseJoinColumns = @JoinColumn(name = "wall_bracket_id"),
        name = "television_wall_bracket")
    private List<WallBracket> wallBrackets;



    //Relation Getters en Setters
    public RemoteController getRemoteController() {
        return remoteController;
    }
    public void setRemoteController(RemoteController remoteController) {
        this.remoteController = remoteController;
    }

    public List<CiModule> getCiModules() {
        return ciModules;
    }
    public void setCiModules(List<CiModule> ciModules) {
        this.ciModules = ciModules;
    }

    public List<WallBracket> getWallBrackets() {
        return wallBrackets;
    }
    public void setWallBrackets(List<WallBracket> wallBrackets) {
        this.wallBrackets = wallBrackets;
    }



    //Getters
    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getAvailableSize() {
        return availableSize;
    }

    public double getRefreshRate() {
        return refreshRate;
    }

    public String getScreenType() {
        return screenType;
    }

    public String getScreenQuality() {
        return screenQuality;
    }

    public boolean isSmartTv() {
        return smartTv;
    }

    public boolean isWifi() {
        return wifi;
    }

    public boolean isVoiceControl() {
        return voiceControl;
    }

    public boolean isHdr() {
        return hdr;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public boolean isAmbiLight() {
        return ambiLight;
    }

    public int getOriginalStock() {
        return originalStock;
    }

    public int getSold() {
        return sold;
    }


    //setters


    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailableSize(double availableSize) {
        this.availableSize = availableSize;
    }

    public void setRefreshRate(double refreshRate) {
        this.refreshRate = refreshRate;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public void setScreenQuality(String screenQuality) {
        this.screenQuality = screenQuality;
    }

    public void setSmartTv(boolean smartTv) {
        this.smartTv = smartTv;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public void setVoiceControl(boolean voiceControl) {
        this.voiceControl = voiceControl;
    }

    public void setHdr(boolean hdr) {
        this.hdr = hdr;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public void setAmbiLight(boolean ambiLight) {
        this.ambiLight = ambiLight;
    }

    public void setOriginalStock(int originalStock) {
        this.originalStock = originalStock;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
}