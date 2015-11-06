package elbainteraction.polisenapp.AnmalanPackage;

import java.io.Serializable;

/**
 * Created by Alexander on 2015-10-29.
 */
public class AnmalanItem implements Serializable{
    private String mName;
    private String brottsTyp;
    private String mDes;

    public AnmalanItem(String brottsTyp){
        this.brottsTyp = brottsTyp;

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
