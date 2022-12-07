package nl.novi.TechItEasyController.Dto.Input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class TelevisionInputDto {



    @NotBlank
    public String type;
    @NotBlank
    public String brand;
    @NotBlank
    public String name;
    @Positive
    public double price;

    public double availableSize;

    public double refreshRate;

    public  String screenType;

    public String screenQuality;

    public boolean smartTv;

    public boolean wifi;

    public boolean voiceControl;

    public boolean hdr;

    public boolean bluetooth;

    public boolean ambiLight;

    public int originalStock;

    public int sold;



    //Getter
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



    //Setters
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
