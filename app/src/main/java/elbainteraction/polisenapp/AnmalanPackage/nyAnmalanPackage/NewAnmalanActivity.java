package elbainteraction.polisenapp.AnmalanPackage.nyAnmalanPackage;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import elbainteraction.polisenapp.AnmalanPackage.AnmalanItem;
import elbainteraction.polisenapp.AnmalanPackage.EditReportPackage.EditReportActivity;
import elbainteraction.polisenapp.DrawerActivity;
import elbainteraction.polisenapp.R;


public class NewAnmalanActivity extends AppCompatActivity{

    RadioButton r1,r2,r3;
    FloatingActionButton button;
    List<AnmalanItem> anmalanItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ny_anmalan);

        r1 = (RadioButton) findViewById(R.id.radioButton);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        r3 = (RadioButton) findViewById(R.id.radioButton3);
        button = (FloatingActionButton) findViewById(R.id.confirm_anmalan_button);
        loadAnmalanList();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void radioClick(View v){

        RadioButton clickedRadio = (RadioButton) v;
        r1.setChecked(false);
        r2.setChecked(false);
        r3.setChecked(false);

        clickedRadio.setChecked(true);
        button.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, DrawerActivity.class);
        intent.putExtra("anmalanFragment", 1);
        startActivity(intent);

    }

    public void confirmAnmalan(View v) {

        String brottsTyp;

        if(r1.isChecked()){
            brottsTyp = "Fordonsstöld";
        } else if(r2.isChecked()){
            brottsTyp = "Stöld";
        } else {
            brottsTyp = "Kontokortsbedrägeri";
        }

        AnmalanItem anmalanItem;
        if(anmalanItemList.size() > 0) {
             anmalanItem = new AnmalanItem(anmalanItemList.get(anmalanItemList.size() - 1).getId() + 1, brottsTyp);
        } else {
             anmalanItem = new AnmalanItem(0, brottsTyp);
        }
        saveAnmalanList(anmalanItem);

        Intent intent = new Intent(this, EditReportActivity.class);
        intent.putExtra("anmalanItem", anmalanItem);
        startActivity(intent);


    }

    public void loadAnmalanList(){

            anmalanItemList = new ArrayList<AnmalanItem>();

        try {
            FileInputStream fis = openFileInput("SavedAnmalanList.txt");

            ObjectInputStream is = new ObjectInputStream(fis);

            anmalanItemList = (ArrayList<AnmalanItem>) is.readObject();

            is.close();
            fis.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAnmalanList(AnmalanItem anmalanItem){

        anmalanItemList.add(anmalanItem);
        FileOutputStream fos = null;

        try {
            fos = openFileOutput("SavedAnmalanList.txt", MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(anmalanItemList);
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
