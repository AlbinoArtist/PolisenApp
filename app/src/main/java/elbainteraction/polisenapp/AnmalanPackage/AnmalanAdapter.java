package elbainteraction.polisenapp.AnmalanPackage;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import elbainteraction.polisenapp.AnmalanPackage.EditReportPackage.EditReportActivity;
import elbainteraction.polisenapp.AnmalanPackage.nyAnmalanPackage.NewAnmalanActivity;
import elbainteraction.polisenapp.R;

/**
 * Created by Alexander on 2015-10-29.
 */
public class AnmalanAdapter extends RecyclerView.Adapter<AnmalanAdapter.ViewHolder> {

    private List<AnmalanItem> mItems;
    private AnmalanItem currentItem;
    private Activity activity;


    public AnmalanAdapter(List<AnmalanItem> mItems, Activity activity) {
        super();

        this.mItems = mItems;
        this.activity = activity;
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
        final AnmalanItem anmalanItem = mItems.get(i);
        viewHolder.status.setText(anmalanItem.isSubmitted());
        viewHolder.anmalanDescription.setText(anmalanItem.getDes());
        viewHolder.brottstyp.setText(anmalanItem.getBrottsTyp());
        currentItem = mItems.get(i);

        viewHolder.currentItemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //SKICKA ITEM TILL editREPORT
                Intent intent = new Intent(activity, EditReportActivity.class);
                intent.putExtra("anmalanItem", anmalanItem);
                activity.startActivity(intent);

            }

        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView anmalanDescription;
        public TextView brottstyp;
        public TextView status;
        public View currentItemView;


        public ViewHolder(View itemView) {
            super(itemView);
            anmalanDescription = (TextView) itemView.findViewById(R.id.anmalanDescription);
            brottstyp = (TextView) itemView.findViewById(R.id.brottstyp);
            status = (TextView) itemView.findViewById(R.id.status);
            currentItemView = itemView;
        }
    }
}

