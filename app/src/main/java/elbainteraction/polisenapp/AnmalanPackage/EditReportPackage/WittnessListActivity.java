package elbainteraction.polisenapp.AnmalanPackage.EditReportPackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import elbainteraction.polisenapp.AnmalanPackage.AnmalanItem;
import elbainteraction.polisenapp.R;


public class WittnessListActivity extends AppCompatActivity {

    private AnmalanItem anmalanItem;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
    }


        private FloatingActionButton mFab;
        private FrameLayout mFabContainer;
        private LinearLayout mAddNewContainer;

        public final static float SCALE_FACTOR      = 18f;
        public final static int ANIMATION_DURATION  = 300;
        public final static int MINIMUN_X_DISTANCE  = 200;

        private boolean mRevealFlag;
        private float mFabSize;


        private void bindViews() {
            setContentView(R.layout.activity_wittness_list);
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

            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(true);

            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            mAdapter = new WitnessItemAdapter(anmalanItem.getWitnessList(), this);
            mRecyclerView.setAdapter(mAdapter);


            mFab = (FloatingActionButton)findViewById(R.id.reveal_add_fab);
            mFabSize = 16f;
            mFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onFabPressed(v);
                }
            });


            mFabContainer = (FrameLayout) findViewById(R.id.content);
            mAddNewContainer = (LinearLayout) findViewById(R.id.add_new_container);

        }
        private ObjectAnimator anim;
        private float fabStartX;
        private float fabStartY;
        public void onFabPressed(View view) {
            fabStartX = mFab.getX();
            fabStartY = mFab.getY();
            mFab.setImageAlpha(0);

            AnimatorPath path = new AnimatorPath();
            path.moveTo(0, 0);
            path.curveTo(-200, 200, -400, 100, -600, 50);
            anim= ObjectAnimator.ofObject(this, "fabLoc",
                    new PathEvaluator(), path.getPoints().toArray());

            anim.setInterpolator(new AccelerateInterpolator());
            anim.setDuration(ANIMATION_DURATION);
            anim.start();


            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    if (Math.abs(fabStartX - mFab.getX()) > MINIMUN_X_DISTANCE) {

                        if (!mRevealFlag) {
                            mFabContainer.setY(mFabContainer.getY() + mFabSize / 2);

                            mFab.animate()
                                    .scaleXBy(SCALE_FACTOR)
                                    .scaleYBy(SCALE_FACTOR)
                                    .setListener(mEndRevealListener)
                                    .setDuration(ANIMATION_DURATION);

                            mRevealFlag = true;
                        }
                    }
                }
            });
        }

    @Override
    public void onBackPressed() {
        if(mAddNewContainer.getVisibility() == View.GONE) {
            super.onBackPressed();
            Intent intent = new Intent(this, EditReportActivity.class);
            intent.putExtra("anmalanItem", anmalanItem);
            startActivity(intent);
        } else {
            //StolenItemLIst
            ViewPropertyAnimator animator = mAddNewContainer.animate()
                    .scaleY(0)
                    .setDuration(600)
                    .setListener(mEndAddListener);
            animator.start();

            anim.removeAllListeners();
        }
    }

        private AnimatorListenerAdapter mEndRevealListener = new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {


                super.onAnimationEnd(animation);
                mFab.setVisibility(View.INVISIBLE);
                findViewById(R.id.recycler_view).setVisibility(View.GONE);
               // findViewById(R.id.add_new_container).setBackgroundColor(getResources()
                 //       .getColor(R.color.colorAccent));
                mAddNewContainer.setScaleX(1);
                mAddNewContainer.setScaleY(1);
                mAddNewContainer.setVisibility(View.VISIBLE);


                for (int i = 0; i < mAddNewContainer.getChildCount(); i++) {

                    View v = mAddNewContainer.getChildAt(i);
                    ViewPropertyAnimator animator = v.animate()
                            .scaleX(1).scaleY(1)
                            .setDuration(ANIMATION_DURATION);

                    animator.setStartDelay(i * 50);
                    animator.start();
                }


            }

        };



        /**
         * We need this setter to translate between the information the animator
         * produces (a new "PathPoint" describing the current animated location)
         * and the information that the button requires (an xy location). The
         * setter will be called by the ObjectAnimator given the 'fabLoc'
         * property string.
         */
        public void setFabLoc(PathPoint newLoc) {

            mFab.setTranslationX(newLoc.mX);


            if (mRevealFlag)
                mFab.setTranslationY(newLoc.mY - (mFabSize / 2));
            else
                mFab.setTranslationY(newLoc.mY);
        }
    public void onCreatePressed(View v){
        ViewPropertyAnimator animator = mAddNewContainer.animate()
                .scaleY(0)
                .setDuration(600)
                .setListener(mEndAddListener);
        animator.start();

        anim.removeAllListeners();

        //create Witness and add information
        Witness w = new Witness();
        String s;
        TextView tv;

        //firstName
        tv = (TextView) findViewById(R.id.input_firstname);
        s = (String) tv.getText().toString();
        if(!s.equals("")) w.setFirstName(s);

        //efternamn
        tv = (TextView) findViewById(R.id.input_lastname);
        s = (String) tv.getText().toString();
        if(!s.equals("")) w.setSurName(s);

        //personnummer
        tv = (TextView) findViewById(R.id.input_ssnumber);
        s = (String) tv.getText().toString();
        if(!s.equals("")) w.setPersonNumber(s);

        //phoneNumber
        tv = (TextView) findViewById(R.id.input_phonenbr);
        s = (String) tv.getText().toString();
        if(!s.equals("")) w.setPhoneNumber(s);

        //email
        tv = (TextView) findViewById(R.id.input_email);
        s = (String) tv.getText().toString();
        if(!s.equals("")) w.setEmail(s);

        anmalanItem.addWitness(w);

    }

    private AnimatorListenerAdapter mEndAddListener = new AnimatorListenerAdapter() {

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            mFab.show();
            mFab.setX(fabStartX);
            mFab.setY(fabStartY);
            mFab.setImageAlpha(254);
            mAddNewContainer.setVisibility(View.GONE);
            findViewById(R.id.recycler_view).setVisibility(View.VISIBLE);
            mRevealFlag = false;
            clearInput();

        }

    };
    private void clearInput(){
        for(int i=0;i<mAddNewContainer.getChildCount();i++){
            View v = mAddNewContainer.getChildAt(i);
            v.setScaleX(0);
            v.setScaleY(0);
            if(v instanceof TextInputLayout){
                ((EditText)((TextInputLayout) v).getChildAt(0)).setText("");
                ((EditText)((TextInputLayout) v).getChildAt(0)).clearFocus();
            }

        }

    }



}
