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
        viewHolder.itemName.setText("Okänd man");
        viewHolder.sex.setText("Kön: " + culpritItem.getSex());
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

        public TextView itemName;
        public TextView sex;
        public View currentItemView;


        public ViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            sex = (TextView) itemView.findViewById(R.id.sex);
            currentItemView = itemView;
        }
    }

}
