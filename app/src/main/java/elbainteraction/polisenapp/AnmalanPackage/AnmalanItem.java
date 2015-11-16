package elbainteraction.polisenapp.AnmalanPackage;

import java.io.Serializable;
import java.util.ArrayList;

import elbainteraction.polisenapp.AnmalanPackage.EditReportPackage.BikeItem;
import elbainteraction.polisenapp.AnmalanPackage.EditReportPackage.Culprit;

/**
 * Created by Alexander on 2015-10-29.
 */
public class AnmalanItem implements Serializable{
    private String mName;
    private String brottsTyp;
    private boolean submitted;
    private String mDes;
    private int id;
    private ArrayList<BikeItem> stolenItems;
    private ArrayList<Culprit> culpritList;

    public AnmalanItem(int id, String brottsTyp){
        this.brottsTyp = brottsTyp;
        this.id = id;
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

    public ArrayList<BikeItem> getStolenItems(){
        if(stolenItems == null){
            stolenItems = new ArrayList<BikeItem>();
        }
        return stolenItems;
    }

    public ArrayList<Culprit> getCulpritList(){
        if(culpritList == null){
            culpritList = new ArrayList<Culprit>();
        }
        return culpritList;
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
