package elbainteraction.polisenapp.AnmalanPackage.EditReportPackage;

/**
 * Created by fbont on 2015-11-10.
 */
public class BikeItem {

    private String brand, type, model, color, branding, frameNumber;
    private String insuranceCompany, stealNumber;
    private String lockState;
    private int value;


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBranding() {
        return branding;
    }

    public void setBranding(String branding) {
        this.branding = branding;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getStealNumber() {
        return stealNumber;
    }

    public void setStealNumber(String stealNumber) {
        this.stealNumber = stealNumber;
    }

    public String getLockState() {
        return lockState;
    }

    public void setLockState(String lockState) {
        this.lockState = lockState;
    }

    public int getValue() {
        return value;
    }

    /*@return Returns true if value is not negative*/
    public boolean setValue(int value) {
        if(value >= 0) {
            this.value = value;
            return true;
        }
        else {
            return false;
        }
    }
}
