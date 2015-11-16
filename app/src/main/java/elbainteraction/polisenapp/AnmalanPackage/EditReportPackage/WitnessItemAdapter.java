package elbainteraction.polisenapp.AnmalanPackage.EditReportPackage;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import elbainteraction.polisenapp.R;

/**
 * Created by Alexander on 2015-11-10.
 */
public class WitnessItemAdapter extends RecyclerView.Adapter<WitnessItemAdapter.ViewHolder>{

    private List<Witness> mItems;
    private Witness currentItem;
    private Activity activity;

    public WitnessItemAdapter(List<Witness> mItems, Activity activity) {
        super();

        this.mItems = mItems;
        this.activity = activity;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_witness_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Witness witnessItem = mItems.get(i);
        viewHolder.itemName.setText("Namn: " + witnessItem.getSurName() + ", " + witnessItem.getFirstName());
        viewHolder.phoneNumber.setText("Telefonnummer: " + witnessItem.getPhoneNumber());
        currentItem = mItems.get(i);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public TextView phoneNumber;
        public View currentItemView;


        public ViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            phoneNumber = (TextView) itemView.findViewById(R.id.phoneNumber);
            currentItemView = itemView;
        }
    }

}
