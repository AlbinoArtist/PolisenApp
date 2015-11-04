package elbainteraction.polisenapp.senastePackage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import elbainteraction.polisenapp.R;

/**
 * Created by Alexander on 2015-10-29.
 */
public class SenasteAdapter extends RecyclerView.Adapter<SenasteAdapter.ViewHolder> {

    List<SenasteItem> mItems;

    public SenasteAdapter() {
        super();
        mItems = new ArrayList<SenasteItem>();
        SenasteItem senasteItem = new SenasteItem();
        senasteItem.setName("Långa bussköer");
        senasteItem.setDes("Trots avstängda vägar och ändrade rutter råder det fortfarande långa bussköer och trafikkaos i Lund.");
        senasteItem.setThumbnail(R.drawable.lund_bussar);
        mItems.add(senasteItem);

        senasteItem = new SenasteItem();
        senasteItem.setName("Olycka centrala Lund");
        senasteItem.setDes("En man i 65-årsålder omkom och tre kvinnor skadades efter att de blivit påkörda av en buss utanför Centralstationen i Lund på fredagskvällen. " +
                "Olyckan inträffade efter att hett vatten strömmat upp ur en sprucken ledning och lagt en tät dimma över staden.");
        senasteItem.setThumbnail(R.drawable.lundvattenlacka);
        mItems.add(senasteItem);

        senasteItem = new SenasteItem();
        senasteItem.setName("Ambulans på plats");
        senasteItem.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis.");
        senasteItem.setThumbnail(R.drawable.ambulans);
        mItems.add(senasteItem);

        senasteItem = new SenasteItem();
        senasteItem.setName("Senaste 3");
        senasteItem.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam.");
        senasteItem.setThumbnail(R.drawable.ambulans);
        mItems.add(senasteItem);


        senasteItem = new SenasteItem();
        senasteItem.setName("Senaste 4");
        senasteItem.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.");
        senasteItem.setThumbnail(R.drawable.lundvattenlacka);
        mItems.add(senasteItem);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_senaste_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        SenasteItem nature = mItems.get(i);
        viewHolder.tvNature.setText(nature.getName());
        viewHolder.tvDesNature.setText(nature.getDes());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvNature;
        public TextView tvDesNature;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvNature = (TextView)itemView.findViewById(R.id.tv_nature);
            tvDesNature = (TextView)itemView.findViewById(R.id.tv_des_nature);
        }
    }
}
