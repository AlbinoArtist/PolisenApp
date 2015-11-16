package elbainteraction.polisenapp.AnmalanPackage.EditReportPackage;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import elbainteraction.polisenapp.AnmalanPackage.AnmalanAdapter;
import elbainteraction.polisenapp.AnmalanPackage.AnmalanItem;
import elbainteraction.polisenapp.R;

/**
 * Created by Alexander on 2015-11-10.
 */
public class StolenItemAdapter extends RecyclerView.Adapter<StolenItemAdapter.ViewHolder>{

    private List<BikeItem> mItems;
    private BikeItem currentItem;
    private Activity activity;

    public StolenItemAdapter(List<BikeItem> mItems, Activity activity) {
        super();

        this.mItems = mItems;
        this.activity = activity;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_stolen_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final BikeItem bikeItem = mItems.get(i);
        viewHolder.itemType.setText("Cykel");
        viewHolder.brand.setText("Fabrikat: " + bikeItem.getBrand());
        currentItem = mItems.get(i);

        /*
        viewHolder.currentItemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //SKICKA ITEM TILL editREPORT
                Intent intent = new Intent(activity, EditReportActivity.class);
                intent.putExtra("anmalanItem", anmalanItem);
                activity.startActivity(intent);

            }

        });
        */

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemType;
        public TextView brand;
        public View currentItemView;


        public ViewHolder(View itemView) {
            super(itemView);
            itemType = (TextView) itemView.findViewById(R.id.itemType);
            brand = (TextView) itemView.findViewById(R.id.brand);
            currentItemView = itemView;
        }
    }

}
