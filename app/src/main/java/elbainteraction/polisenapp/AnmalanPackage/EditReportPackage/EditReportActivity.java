package elbainteraction.polisenapp.AnmalanPackage.EditReportPackage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import elbainteraction.polisenapp.AnmalanPackage.AnmalanFragment;
import elbainteraction.polisenapp.AnmalanPackage.AnmalanItem;
import elbainteraction.polisenapp.AnmalanPackage.nyAnmalanPackage.NewAnmalanActivity;
import elbainteraction.polisenapp.DrawerActivity;
import elbainteraction.polisenapp.R;
import me.drakeet.materialdialog.MaterialDialog;

public class EditReportActivity extends AppCompatActivity {

    private AnmalanItem anmalanItem;
    private MaterialDialog mMaterialDialog;
    private ArrayList<AnmalanItem> anmalanItemList;
    private TextView stulnaForemalButton, garningsManButon, vittnenButton, eventButton, lamnaAnmalanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_report);
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
        TextView tv = (TextView) findViewById(R.id.textHeader);
        tv.setText("Anmälan: " + anmalanItem.getBrottsTyp());

        stulnaForemalButton = (TextView) findViewById(R.id.stulnaForemal);
        garningsManButon = (TextView) findViewById(R.id.garningsman);
        vittnenButton = (TextView) findViewById(R.id.vittnen);
        lamnaAnmalanButton = (TextView) findViewById(R.id.lamnaAnmalan);
        eventButton = (TextView) findViewById(R.id.event);

        if(anmalanItem.isSubmitted().equals("Inlämnad")){
            lamnaAnmalanButton.setTextColor(getResources().getColor(R.color.colorDivider));
        }

        stulnaForemalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StolenListActivity.class);
                intent.putExtra("anmalanItem", anmalanItem);
                startActivity(intent);
            }
        });

        garningsManButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CulpritListActivity.class);
                intent.putExtra("anmalanItem", anmalanItem);
                startActivity(intent);
            }
        });

        vittnenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WittnessListActivity.class);
                intent.putExtra("anmalanItem", anmalanItem);
                startActivity(intent);
            }
        });

        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
                intent.putExtra("anmalanItem", anmalanItem);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAnmalanList();
        anmalanItem = (AnmalanItem) getIntent().getSerializableExtra("anmalanItem");
        if(anmalanItem != null) {
            saveAnmalanList(anmalanItem, 0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, DrawerActivity.class);
        intent.putExtra("anmalanFragment", 1);
        startActivity(intent);

    }


    public void dialogWindow(View v) {

        if(anmalanItem.isSubmitted().equals("Ej inlämnad")) {

            mMaterialDialog = new MaterialDialog(this)
                    .setTitle("MaterialDialog")
                    .setMessage("Hello world!")
                    .setPositiveButton("Lämna in", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                            anmalanItem.setSubmitted();
                            loadAnmalanList();
                            saveAnmalanList(anmalanItem, 1);
                        }
                    })
                    .setNegativeButton("Avbryt", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                        }
                    });

            mMaterialDialog.show();
            mMaterialDialog.setTitle("Lämna in anmälan");
            mMaterialDialog.show();
            mMaterialDialog.setMessage("Vill du verkligen lämna in din anmälan till Polisen?");
        } else {
            Snackbar.make(v, "Din anmälan är redan inskickad!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

    }

    public void loadAnmalanList() {

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

    public void saveAnmalanList(AnmalanItem anmalanItem, int submitAnmalan) {

        AnmalanItem aItem;
        for (int i = 0; i < anmalanItemList.size(); i++) {
            aItem = anmalanItemList.get(i);

            if (aItem.getId() == anmalanItem.getId()) {
                anmalanItemList.set(i, anmalanItem);
                break;
            }
        }


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

        if(submitAnmalan == 1){

            //After save go to anmalan fragment
            Intent intent = new Intent(this, DrawerActivity.class);
            intent.putExtra("anmalanFragment", 1);
            startActivity(intent);
        }
    }


}
