package maarifa.tn.langui.ui.progress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;
import com.firebase.client.Query;

import maarifa.tn.langui.LanguiApplication;
import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Vocablary;
import maarifa.tn.langui.ui.langageDetails.VocablaryAdapter;
import maarifa.tn.langui.utils.Constants;

/**
 * Created by seif on 28/05/2016.
 */
public class ProgressDetailFragment extends Fragment {

    String mEncodedEmail;
    String langageId;
    Firebase languageRef;
    Query progressQuery;

    protected static Bundle newInstanceHelper(String encodedEmail, String langageId) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_ENCODED_EMAIL, encodedEmail);
        bundle.putString(Constants.LANGUAGE_KEY, langageId);
        return bundle;
    }

    public static ProgressDetailFragment newInstance(String encodedEmail, String languageId) {
        ProgressDetailFragment vocablaryFragment = new ProgressDetailFragment();

        Bundle bundle = vocablaryFragment.newInstanceHelper(encodedEmail, languageId);
        vocablaryFragment.setArguments(bundle);

        return vocablaryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mEncodedEmail = getArguments().getString(Constants.KEY_ENCODED_EMAIL);
            langageId = getArguments().getString(Constants.LANGUAGE_KEY);
            languageRef = new Firebase(Constants.FIREBASE_URL_LANGAUAGES).child(mEncodedEmail).child(langageId).child("vocab");
            progressQuery = languageRef.orderByKey();


        }
        /*
        mAdapter = new VocablaryAdapter(progressQuery, Vocablary.class, null, null,getContext());
        Log.d("ITEM",mAdapter.getItemCount()+"");
        ((LanguiApplication) this.getActivity().getApplication()).nbrVcab=mAdapter.getItemCount();*/
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vocablary, container, false);
    }


}
