package elbainteraction.polisenapp.AnmalanPackage.EditReportPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import elbainteraction.polisenapp.AnmalanPackage.AnmalanFragment;
import elbainteraction.polisenapp.AnmalanPackage.AnmalanItem;
import elbainteraction.polisenapp.DrawerActivity;
import elbainteraction.polisenapp.R;

public class EditReportActivity extends AppCompatActivity {

    AnmalanItem anmalanItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        anmalanItem = (AnmalanItem) getIntent().getSerializableExtra("anmalanItem");
        TextView tv = (TextView) findViewById(R.id.textHeader);
        tv.setText("Anmälan: " + anmalanItem.getBrottsTyp());
    }

/*    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, DrawerActivity.class);

        startActivity(intent);

    }*/


}
