package elbainteraction.polisenapp.AnmalanPackage.EditReportPackage;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import elbainteraction.polisenapp.AnmalanPackage.AnmalanItem;
import elbainteraction.polisenapp.R;


public class AddEventActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private AnmalanItem anmalanItem;
    private PlaceholderFragment1 pf1;
    private AddPlaceFragment pf2;
    private PlaceholderFragment3 pf3;
    private PlaceholderFragment4 pf4;
    private static int RESULT_LOAD_IMAGE = 1;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private GridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        anmalanItem = (AnmalanItem) getIntent().getSerializableExtra("anmalanItem");
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private boolean firstTime = true;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 1) {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.fragment2_top), "Släpp en markör på platsen där händelsen ägde rum.", Snackbar.LENGTH_LONG).setAction("OK", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                        }
                                    }
                            );
                    snackbar.show();
                } else if (position == 2 && firstTime) {

                    View v3 = pf3.getView3();
                    mRecyclerView = (RecyclerView) v3.findViewById(R.id.recycler_view);
                    mAdapter = new GridAdapter();
                    mRecyclerView.setAdapter(mAdapter);
                    mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    firstTime = false;

                }


            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public void addEvent(View view) {

        Event event;

        ////VIEW 1
        View v1 = pf1.getView1();

        EditText t = (EditText) v1.findViewById(R.id.input_namnhandelse);
        String namnHandelse = t.getText().toString();

        CheckBox isApproximate = (CheckBox) v1.findViewById(R.id.isApproximate);

        if (isApproximate.isChecked()) {
            event = new Event(namnHandelse, true);
        } else {
            event = new Event(namnHandelse, false);
        }


        String date = ((TextView) v1.findViewById(R.id.input_datepicker)).getText().toString();

        event.setDate(date);

        String time = ((TextView) v1.findViewById(R.id.input_timepicker)).getText().toString();

        event.setTime(time);

        ////VIEW 2

        if (pf2.isToggled()) {
            event.setLatitude(pf2.getLatitude());
            event.setLongitude(pf2.getLongitude());
        }

        ////VIEW 3


        ////VIEW 4
        View v4 = pf4.getView4();

        EditText input_beskrivning = (EditText) v4.findViewById(R.id.input_beskrivningHandelse);
        String beskrivningHandelse = input_beskrivning.getText().toString();

        event.setDescription(beskrivningHandelse);


        anmalanItem.addEvent(event);
        Intent intent = new Intent(this, EventListActivity.class);
        intent.putExtra("anmalanItem", anmalanItem);
        startActivity(intent);
    }


    public void addMedia(View view) {

        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();


            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            //ImageView imageView = (ImageView) findViewById(R.id.imgView);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            mAdapter.add(BitmapFactory.decodeFile(picturePath));
            mAdapter.notifyDataSetChanged();


        }


    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = PlaceholderFragment1.newInstance();
                    pf1 = (PlaceholderFragment1) fragment;
                    break;
                case 1:
                    fragment = new AddPlaceFragment().newInstance();
                    pf2 = (AddPlaceFragment) fragment;
                    break;
                case 2:
                    fragment = PlaceholderFragment3.newInstance();
                    pf3 = (PlaceholderFragment3) fragment;
                    break;
                case 3:
                    fragment = PlaceholderFragment4.newInstance();
                    pf4 = (PlaceholderFragment4) fragment;
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Allmänt";
                case 1:
                    return "Plats";
                case 2:
                    return "Media";
                case 3:
                    return "Beskrivning";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment1 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment1 newInstance() {
            PlaceholderFragment1 fragment = new PlaceholderFragment1();
            return fragment;
        }

        public PlaceholderFragment1() {

        }

        Calendar myCalendar = Calendar.getInstance();
        EditText namnHandelse;
        TextView datePicker;
        TextView timePicker;
        View view;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment1_add_event, container, false);
            namnHandelse = (EditText) rootView.findViewById(R.id.input_namnhandelse);
            datePicker = (TextView) rootView.findViewById(R.id.input_datepicker);
            timePicker = (TextView) rootView.findViewById(R.id.input_timepicker);


            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDateLabel();
                }

            };
            final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    myCalendar.set(Calendar.MINUTE, minute);
                    updateTimeLabel();
                }


            };

            datePicker.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(getContext(), date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                }
            });

            timePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new TimePickerDialog(getContext(), time, myCalendar
                            .get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
                }
            });
            view = rootView;
            return rootView;
        }

        public View getView1() {
            return view;
        }


        private void updateDateLabel() {

            String myFormat = "dd-MM-yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            datePicker.setText(sdf.format(myCalendar.getTime()));
        }

        private void updateTimeLabel() {

            String myFormat = "HH:mm"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            timePicker.setText(sdf.format(myCalendar.getTime()));
        }
    }


    public static class PlaceholderFragment3 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment3 newInstance() {
            PlaceholderFragment3 fragment = new PlaceholderFragment3();
            return fragment;
        }

        public PlaceholderFragment3() {

        }

        View view;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment3_add_event, container, false);
            view = rootView;
            return rootView;
        }

        public View getView3() {
            return view;
        }
    }

    public static class PlaceholderFragment4 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment4 newInstance() {
            PlaceholderFragment4 fragment = new PlaceholderFragment4();
            return fragment;
        }

        public PlaceholderFragment4() {
        }

        View view;
        EditText namnHandelse;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment4_add_event, container, false);
            FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.finish_button);
            namnHandelse = (EditText) rootView.findViewById(R.id.input_beskrivningHandelse);
            /*fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Finisha skapandet av en händelse.
                }
            });*/
            view = rootView;
            return rootView;
        }

        public View getView4() {
            return view;
        }
    }

    public static class InnerCard {
        private String mName;
        private Bitmap mThumbnail;

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            this.mName = name;
        }

        public Bitmap getThumbnail() {
            return mThumbnail;
        }

        public void setThumbnail(Bitmap thumbnail) {
            this.mThumbnail = thumbnail;
        }
    }

}

class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    List<AddEventActivity.InnerCard> mItems;

    public GridAdapter() {
        super();
        mItems = new ArrayList<AddEventActivity.InnerCard>();
        AddEventActivity.InnerCard species = new AddEventActivity.InnerCard();
        species.setName("Medialista tom");
        species.setThumbnail(Bitmap.createBitmap(150, 150, Bitmap.Config.ARGB_8888));
        mItems.add(species);


    }

    private boolean firstTime = true;

    public void add(Bitmap bt) {
        if (firstTime) {
            mItems.remove(0);
            firstTime = false;
        }
        AddEventActivity.InnerCard species = new AddEventActivity.InnerCard();
        species.setName("Bild " + (mItems.size() + 1));
        species.setThumbnail(bt);
        mItems.add(species);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_image_asset, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        AddEventActivity.InnerCard nature = mItems.get(i);
        viewHolder.tvspecies.setText(nature.getName());
        viewHolder.imgThumbnail.setImageBitmap(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView tvspecies;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvspecies = (TextView) itemView.findViewById(R.id.tv_species);
        }
    }
}

