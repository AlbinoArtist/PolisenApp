package elbainteraction.polisenapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginPasswordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginPasswordFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginPasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginPasswordFragment newInstance(String param1, String param2) {
        LoginPasswordFragment fragment = new LoginPasswordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public LoginPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_password, container, false);
    }

    // TODO: Get p-number and password and hook method into UI event
    public void login(View view) {
        EditText pNumberText = (EditText) getView().findViewById(R.id.editpersonnummer);
        String pNumber = pNumberText.getText().toString();

        EditText pWordText = (EditText) getView().findViewById(R.id.editpersonnummer);
        String pWord = pWordText.getText().toString();

        //login successful
        if( pNumber.equals("197001011234") && pWord.equals("1234")){
            Toast.makeText(getContext(), "Inloggning lyckades, HURRA HURRA.", Toast.LENGTH_LONG);

            SharedPreferences mPrefs = new MikesAss().getPreferenceManager().getSharedPreferences();
            SharedPreferences.Editor mEditor = mPrefs.edit();
            mEditor.putBoolean("Logged in", true).commit();

        }
        else { //login not successful.
            Toast.makeText(getContext(), "Fel personnummer eller lösenord.", Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    public class MikesAss extends PreferenceFragment {

    }
}