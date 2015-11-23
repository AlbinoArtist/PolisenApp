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
public class EventItemAdapter extends RecyclerView.Adapter<EventItemAdapter.ViewHolder>{

    private List<Event> mItems;
    private Event currentItem;
    private Activity activity;

    public EventItemAdapter(List<Event> mItems, Activity activity) {
        super();

        this.mItems = mItems;
        this.activity = activity;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_event_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Event eventItem = mItems.get(i);
        viewHolder.itemName.setText(eventItem.getName());
        viewHolder.description.setText("Kön: " + eventItem.getDescription());
        currentItem = mItems.get(i);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public TextView description;
        public View currentItemView;


        public ViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            description = (TextView) itemView.findViewById(R.id.description);
            currentItemView = itemView;
        }
    }

}
