package elbainteraction.polisenapp.AnsokanPackage;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import elbainteraction.polisenapp.R;

/**
 * Created by Alexander on 2015-10-29.
 */
public class AnsokningarAdapter extends RecyclerView.Adapter<AnsokningarAdapter.ViewHolder> {

    List<AnsokningarItem> mItems;

    public AnsokningarAdapter() {
        super();
        mItems = new ArrayList<AnsokningarItem>();
        AnsokningarItem ansokan = new AnsokningarItem();
        ansokan.setName("Ansökan 1");
        ansokan.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam.");
        mItems.add(ansokan);

        ansokan = new AnsokningarItem();
        ansokan.setName("Ansökan 2");
        ansokan.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua.");
        mItems.add(ansokan);

        ansokan = new AnsokningarItem();
        ansokan.setName("Ansökan 3");
        ansokan.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis.");
        mItems.add(ansokan);

        ansokan = new AnsokningarItem();
        ansokan.setName("Ansökan 3");
        ansokan.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam.");
        mItems.add(ansokan);


        ansokan = new AnsokningarItem();
        ansokan.setName("Ansökan 4");
        ansokan.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.");
        mItems.add(ansokan);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_ansokningar_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        AnsokningarItem ansokanItem = mItems.get(i);
        viewHolder.ansokanNamn.setText(ansokanItem.getName());
        viewHolder.ansokanDescription.setText(ansokanItem.getDes());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ansokanNamn;
        public TextView ansokanDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ansokanNamn = (TextView)itemView.findViewById(R.id.ansokanNamn);
            ansokanDescription = (TextView)itemView.findViewById(R.id.ansokanDescription);
        }
    }
}
