package elbainteraction.polisenapp.AnmalanPackage.EditReportPackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import elbainteraction.polisenapp.AnmalanPackage.AnmalanItem;
import elbainteraction.polisenapp.R;


public class StolenListActivity extends AppCompatActivity {

    private AnmalanItem anmalanItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();

       anmalanItem = (AnmalanItem) getIntent().getSerializableExtra("anmalanItem");

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
            setContentView(R.layout.activity_stolen_list);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
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


            Spinner programSpinner = new Spinner(this);
            programSpinner.setPadding(0,5,0,5);
            ArrayAdapter<CharSequence> spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.foremal_choices, android.R.layout.simple_spinner_item);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            programSpinner.setAdapter(spinnerArrayAdapter);
            programSpinner.setScaleY(0);
            programSpinner.setScaleX(0);
            mAddNewContainer.addView(programSpinner,2);

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

        private AnimatorListenerAdapter mEndRevealListener = new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {


                super.onAnimationEnd(animation);
                Toast.makeText(getApplicationContext(),"Fuck",Toast.LENGTH_LONG).show();
                mFab.setVisibility(View.INVISIBLE);
               // findViewById(R.id.add_new_container).setBackgroundColor(getResources()
                 //       .getColor(R.color.colorAccent));
                findViewById(R.id.text).setVisibility(View.GONE);
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

        //Create stolenItem






        //Add bikeItem to anmalanItem

        //StolenItemLIst
        ViewPropertyAnimator animator = mAddNewContainer.animate()
                .scaleY(0)
                .setDuration(600)
                .setListener(mEndAddListener);
        animator.start();

        anim.removeAllListeners();

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
            findViewById(R.id.text).setVisibility(View.VISIBLE);
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
