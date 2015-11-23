package elbainteraction.polisenapp.AnmalanPackage.EditReportPackage;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fbont on 2015-11-16.
 */
public class Event implements Serializable{

    private String name, time, address, description;
    private String date;
    private boolean isApproximate;
    private double latitude, longitude;


    public Event(String name, boolean isApproximate){
        this.name = name;
        this.isApproximate = isApproximate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isApproximate() {
        return isApproximate;
    }

    public void setIsApproximate(boolean isApproximate) {
        this.isApproximate = isApproximate;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
