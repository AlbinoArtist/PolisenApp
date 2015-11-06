package elbainteraction.polisenapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class UploadPictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_picture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /*
    package elbainteraction.polisenapp.AnmalanPackage.nyAnmalanPackage;

    import android.content.Intent;
    import android.database.Cursor;
    import android.graphics.BitmapFactory;
    import android.net.Uri;
    import android.provider.MediaStore;
    import android.support.v4.app.FragmentActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.ImageView;
    import android.widget.LinearLayout;

    import com.getbase.floatingactionbutton.FloatingActionButton;

    import elbainteraction.polisenapp.R;


    public class NewAnmalanActivity extends FragmentActivity {

        private static int RESULT_LOAD_IMAGE = 1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ny_anmalan);



            final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.buttonLoadPicture);
            actionA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }
            });

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                //ImageView imageView = (ImageView) findViewById(R.id.imgView);
                //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                ImageView iv = new ImageView(this);
                iv.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                LinearLayout linLay = (LinearLayout) findViewById(R.id.linearLayout);
                linLay.addView(iv);
                iv.getLayoutParams().width = 300;
                iv.getLayoutParams().height = 300;
            }


        }


    }
    */

}
