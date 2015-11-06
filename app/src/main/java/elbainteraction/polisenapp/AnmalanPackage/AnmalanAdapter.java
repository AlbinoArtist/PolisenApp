package elbainteraction.polisenapp.AnmalanPackage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import elbainteraction.polisenapp.R;

/**
 * Created by Alexander on 2015-10-29.
 */
public class AnmalanAdapter extends RecyclerView.Adapter<AnmalanAdapter.ViewHolder> {

    List<AnmalanItem> mItems;

    public AnmalanAdapter(List<AnmalanItem> mItems) {
        super();

        this.mItems = mItems;
        /*
        AnmalanItem anmalan = new AnmalanItem();
        anmalan.setName("Anmälan 1");
        anmalan.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam.");
        mItems.add(anmalan);

        anmalan = new AnmalanItem();
        anmalan.setName("Anmälan 2");
        anmalan.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua.");
        mItems.add(anmalan);

        anmalan = new AnmalanItem();
        anmalan.setName("Anmälan 3");
        anmalan.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis.");
        mItems.add(anmalan);

        anmalan = new AnmalanItem();
        anmalan.setName("Anmälan 3");
        anmalan.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam.");
        mItems.add(anmalan);


        anmalan = new AnmalanItem();
        anmalan.setName("Anmälan 4");
        anmalan.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.");
        mItems.add(anmalan);
        */
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_anmalan_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        AnmalanItem anmalanItem = mItems.get(i);
        viewHolder.name.setText(anmalanItem.getName());
        viewHolder.anmalanDescription.setText(anmalanItem.getDes());
        viewHolder.brottstyp.setText(anmalanItem.getBrottsTyp());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView anmalanDescription;
        public TextView brottstyp;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            anmalanDescription = (TextView)itemView.findViewById(R.id.anmalanDescription);
            brottstyp = (TextView)itemView.findViewById(R.id.brottstyp);
        }
    }
}

