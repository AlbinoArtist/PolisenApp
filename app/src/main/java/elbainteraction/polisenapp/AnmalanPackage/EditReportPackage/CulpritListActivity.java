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
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import elbainteraction.polisenapp.AnmalanPackage.AnmalanItem;
import elbainteraction.polisenapp.R;


public class CulpritListActivity extends AppCompatActivity {

    AnmalanItem anmalanItem;

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

        //fullösning
        private Spinner programSpinner;


        private void bindViews() {
            setContentView(R.layout.activity_culprit_list);
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


            programSpinner = new Spinner(this);
            programSpinner.setPadding(0, 5, 0, 5);
            ArrayAdapter<CharSequence> spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.sex, android.R.layout.simple_spinner_item);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            programSpinner.setAdapter(spinnerArrayAdapter);
            programSpinner.setScaleY(0);
            programSpinner.setScaleX(0);
            mAddNewContainer.addView(programSpinner, 4);

            layoutUnknown =(LinearLayout) findViewById(R.id.layout_linear_unknown);
            layoutKnown =(LinearLayout) findViewById(R.id.layout_linear_known);
            RadioGroup groupKnownUnknown = (RadioGroup)findViewById(R.id.radio_knownunknown);
            groupKnownUnknown.setOnCheckedChangeListener(new OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch(checkedId){
                        case R.id.radio_known:
                            hideView(layoutUnknown);
                            showView(layoutKnown);
                            break;
                        case R.id.radio_unknown:
                            hideView(layoutKnown);
                            showView(layoutUnknown);
                            break;
                    }
                }
            });
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
        ViewPropertyAnimator animator = mAddNewContainer.animate()
                .scaleY(0)
                .setDuration(600)
                .setListener(mEndAddListener);
        animator.start();

        anim.removeAllListeners();


        //create Culprit and add info
        RadioButton knownRadio = (RadioButton) findViewById(R.id.radio_known);
        boolean known = knownRadio.isChecked();
        Culprit c = new Culprit(known);
        String s;
        TextView tv;

        //firstName
        tv = (TextView) findViewById(R.id.input_firstname);
        s = (String) tv.getText();
        if(!s.equals("")) c.setFirstName(s);

        //lastName
        tv = (TextView) findViewById(R.id.input_lastname);
        s = (String) tv.getText();
        if(!s.equals("")) c.setSurName(s);

        //personNumber
        tv = (TextView) findViewById(R.id.input_ssnumber);
        s = (String) tv.getText();
        if(!s.equals("")) c.setPersonNumber(s);

        //phonenumber
        tv = (TextView) findViewById(R.id.input_phonenbr);
        s = (String) tv.getText();
        if(!s.equals("")) c.setPhoneNumber(s);

        //email
        tv = (TextView) findViewById(R.id.input_email);
        s = (String) tv.getText();
        if(!s.equals("")) c.setEmail(s);

        //sex
        s = programSpinner.getSelectedItem().toString();
        if(!s.equals("")) c.setSex(s);

        //length
        tv = (TextView) findViewById(R.id.input_height);
        s = (String) tv.getText();
        if(!s.equals("")){
            int length = Integer.parseInt((String) tv.getText());
            c.setLength(length);
        }

        //age
        tv = (TextView) findViewById(R.id.input_age);
        s = (String) tv.getText();
        if(!s.equals("")){
            int age = Integer.parseInt((String) tv.getText());
            c.setAge(age);
        }

        //signalement
        tv = (TextView) findViewById(R.id.input_description);
        s = (String) tv.getText();
        if(!s.equals("")) c.setDescription(s);

        // TODO: 2015-11-10 Add method addCulprit(Culprit c) to anmalanItem.
        //anmalanItem.addCulprit(c);

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
            if(v instanceof LinearLayout){
                LinearLayout childV = (LinearLayout) v;
                for(int j=0;j < childV.getChildCount();j++){
                    View childChildV = childV.getChildAt(j);
                    if(childChildV instanceof TextInputLayout){
                        ((EditText)((TextInputLayout) childChildV).getChildAt(0)).setText("");
                        ((EditText)((TextInputLayout) childChildV).getChildAt(0)).clearFocus();
                    }

                }
            }

        }

    }
    private LinearLayout layoutUnknown;
    private LinearLayout layoutKnown;

    private void showView(LinearLayout layout){
        layout.setVisibility(View.VISIBLE);
        for (int i = 0; i < layout.getChildCount(); i++) {

            View v = layout.getChildAt(i);
            ViewPropertyAnimator animator = v.animate()
                    .scaleX(1).scaleY(1)
                    .setDuration(ANIMATION_DURATION);

            animator.setStartDelay(i * 50);
            animator.start();
        }

    }
    private void hideView(LinearLayout layout){
        layout.setVisibility(View.GONE);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View v = layout.getChildAt(i);
            v.setScaleX(0);
            v.setScaleY(0);
        }

    }




}
