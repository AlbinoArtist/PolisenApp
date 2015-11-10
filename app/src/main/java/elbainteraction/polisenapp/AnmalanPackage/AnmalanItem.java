package elbainteraction.polisenapp.AnmalanPackage;

import java.io.Serializable;

/**
 * Created by Alexander on 2015-10-29.
 */
public class AnmalanItem implements Serializable{
    private String mName;
    private String brottsTyp;
    private boolean submitted;
    private String mDes;
    private int id;

    public AnmalanItem(int id, String brottsTyp){
        this.brottsTyp = brottsTyp;
        this.id = id;
        submitted = false;

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
