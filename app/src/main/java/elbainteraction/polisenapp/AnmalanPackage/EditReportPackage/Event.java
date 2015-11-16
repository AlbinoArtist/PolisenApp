package elbainteraction.polisenapp.AnmalanPackage.EditReportPackage;

import java.util.Date;

/**
 * Created by fbont on 2015-11-16.
 */
public class Event {

    public static int SINGLE_EVENT = 0, MULTIPLE_EVENTS = 1;
    public int eventType;

    private String name, time, address, description;
    private Date date;
    private boolean isApproximate;
    private float latitude, longitude;


    public Event(int eventType, String name, boolean isApproximate){
        this.eventType = eventType;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isApproximate() {
        return isApproximate;
    }

    public void setIsApproximate(boolean isApproximate) {
        this.isApproximate = isApproximate;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
