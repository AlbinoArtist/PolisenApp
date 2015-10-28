package elbainteraction.polisenapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class LoginActivity extends AppCompatActivity {
    FragmentPagerItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("BankID", LoginBankidFragment.class)
                .add("Lösenord", LoginPasswordFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }

    public void onPageSelected(int position) {

        //.instantiateItem() from until .destoryItem() is called it will be able to get the Fragment of page.

        Fragment page = adapter.getPage(position);
    }


    public void login(View view) {
        EditText pNumberText = (EditText) findViewById(R.id.editpersonnummer);
        String pNumber = pNumberText.getText().toString();

        EditText pWordText = (EditText) findViewById(R.id.editpassword);
        String pWord = pWordText.getText().toString();

        SharedPreferences mPrefs = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean("Logged in", false).commit();


        //login successful
        if( pNumber.equals("197001011234") && pWord.equals("1234")){
            Toast.makeText(getApplicationContext(), "Inloggning lyckades, HURRA HURRA.", Toast.LENGTH_LONG).show();


            mEditor.putBoolean("Logged in", true).commit();

            Intent intent = new Intent(this, DrawerActivity.class);
            startActivity(intent);

        }
        else { //login not successful.
            Toast.makeText(getApplicationContext(), "Fel personnummer eller lösenord.", Toast.LENGTH_LONG).show();
        }


    }


}
