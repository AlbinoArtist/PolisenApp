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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import elbainteraction.polisenapp.AnmalanPackage.AnmalanItem;
import elbainteraction.polisenapp.R;


public class EventListActivity extends AppCompatActivity {


    private AnmalanItem anmalanItem;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private TextView headerEventtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
    }

        private FloatingActionButton mFab;
        private FrameLayout mFabContainer;
        private LinearLayout mAddNewContainer;

        public final static float SCALE_FACTOR      = 20f;
        public final static int ANIMATION_DURATION  = 300;
        public final static int MINIMUN_X_DISTANCE  = 200;

        private boolean mRevealFlag;
        private float mFabSize;


        private void bindViews() {
            setContentView(R.layout.activity_event_list);
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

            mAdapter = new EventItemAdapter(anmalanItem.getEventList(), this);
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


            Spinner programSpinner = new Spinner(this);
            programSpinner.setPadding(0,5,0,5);
            ArrayAdapter<CharSequence> spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.foremal_choices, android.R.layout.simple_spinner_item);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            programSpinner.setAdapter(spinnerArrayAdapter);
            programSpinner.setScaleY(0);
            programSpinner.setScaleX(0);
            mAddNewContainer.addView(programSpinner, 2);

            headerEventtList = (TextView) findViewById(R.id.headerEventList);
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
            super.onBackPressed();
            Intent intent = new Intent(this, EditReportActivity.class);
            intent.putExtra("anmalanItem", anmalanItem);
            startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mFab.setScaleX(1);
        mFab.setScaleY(1);
        mFab.setX(fabStartX);
        mFab.setY(fabStartY);
        mFab.setImageAlpha(254);
    }

    private AnimatorListenerAdapter mEndRevealListener = new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {

                Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
                intent.putExtra("anmalanItem", anmalanItem);
                startActivity(intent);
/*                mFab.setScaleX(1);
                mFab.setScaleY(1);
                mFab.show();
                mFab.setX(fabStartX);
                mFab.setY(fabStartY);
                mFab.setImageAlpha(254);*/
                mAddNewContainer.setVisibility(View.GONE);
                mRevealFlag = false;
                clearInput();
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


        // TODO: 2015-11-16 Implement connection between data class and GUI.
        /*TextView tv;

        //eventType
        int eventType = Event.MULTIPLE_EVENTS;
        RadioButton rb = (RadioButton) findViewById(R.id.input_singe_event);
        if (rb.isChecked()){
            eventType = Event.SINGLE_EVENT;
        }

        //Create event
        Event event = new Event(eventType, name, isApproximate);
        */



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
