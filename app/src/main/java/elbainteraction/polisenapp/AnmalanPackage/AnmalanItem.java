package elbainteraction.polisenapp.AnmalanPackage;

import java.io.Serializable;
import java.util.ArrayList;

import elbainteraction.polisenapp.AnmalanPackage.EditReportPackage.BikeItem;
import elbainteraction.polisenapp.AnmalanPackage.EditReportPackage.Culprit;
import elbainteraction.polisenapp.AnmalanPackage.EditReportPackage.Event;
import elbainteraction.polisenapp.AnmalanPackage.EditReportPackage.Witness;

/**
 * Created by Alexander on 2015-10-29.
 */
public class AnmalanItem implements Serializable{
    private String mName;
    private String brottsTyp;
    private boolean submitted;
    private String mDes;
    private int id;
    private int nbrOfStolenItems, nbrOfWitness;
    private ArrayList<BikeItem> stolenItems;
    private ArrayList<Culprit> culpritList;
    private ArrayList<Witness> witnessList;
    private ArrayList<Event> eventList;

    public AnmalanItem(int id, String brottsTyp){
        this.brottsTyp = brottsTyp;
        this.id = id;
        nbrOfStolenItems = 0;
        nbrOfWitness = 0;
        submitted = false;

    }

    public void setStolenItem(BikeItem bikeItem){
        if(stolenItems == null){
            stolenItems = new ArrayList<BikeItem>();
        }
        stolenItems.add(bikeItem);
    }

    public void addCulprit(Culprit culprit){
        if(culpritList == null){
            culpritList = new ArrayList<Culprit>();
        }
        culpritList.add(culprit);
    }

    public void addWitness(Witness witness){
        if(witnessList == null){
            witnessList = new ArrayList<Witness>();
        }
        witnessList.add(witness);
    }

    public void addEvent(Event event){
        if(eventList == null){
            eventList = new ArrayList<Event>();
        }
        eventList.add(event);
    }

    public ArrayList<BikeItem> getStolenItems(){
        if(stolenItems == null){
            stolenItems = new ArrayList<BikeItem>();
        }
        return stolenItems;
    }

    public int getNbrOfStolenItems(){

        if(stolenItems != null){
            return stolenItems.size();
        }
        return 0;
    }

    public int getNbrOfWitness(){

        if(witnessList != null){
            return witnessList.size();
        }
        return 0;
    }

    public ArrayList<Culprit> getCulpritList(){
        if(culpritList == null){
            culpritList = new ArrayList<Culprit>();
        }
        return culpritList;
    }

    public ArrayList<Witness> getWitnessList(){
        if( witnessList == null){
            witnessList = new ArrayList<Witness>();
        }
        return witnessList;
    }

    public ArrayList<Event> getEventList(){
        if( eventList == null){
            eventList = new ArrayList<Event>();
        }
        return eventList;
    }

    public int getId(){
        return id;
    }

    public void setSubmitted(){
        submitted = true;
    }

    public String isSubmitted(){
        if(submitted){
            return "Inlämnad";
        } else {
            return "Ej inlämnad";
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDes() {
        return mDes;
    }

    public String getBrottsTyp() {
        return brottsTyp;
    }

    public void setDes(String des) {
        this.mDes = des;
    }

}
