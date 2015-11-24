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
public class CulpritItemAdapter extends RecyclerView.Adapter<CulpritItemAdapter.ViewHolder>{

    private List<Culprit> mItems;
    private Culprit currentItem;
    private Activity activity;

    public CulpritItemAdapter(List<Culprit> mItems, Activity activity) {
        super();

        this.mItems = mItems;
        this.activity = activity;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_culprit_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Culprit culpritItem = mItems.get(i);
        if(culpritItem.isKnown()){
            viewHolder.itemName.setText(culpritItem.getFirstName() + " " + culpritItem.getSurName());
        } else {
            viewHolder.itemName.setText("Okänd gärningsman");
        }

        viewHolder.sex.setText("Kön: " + culpritItem.getSex());
        viewHolder.age.setText("Ålder: " + culpritItem.getAge());
        currentItem = mItems.get(i);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public TextView sex;
        public TextView age;
        public View currentItemView;


        public ViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            sex = (TextView) itemView.findViewById(R.id.sex);
            age = (TextView) itemView.findViewById(R.id.age);
            currentItemView = itemView;
        }
    }

}
