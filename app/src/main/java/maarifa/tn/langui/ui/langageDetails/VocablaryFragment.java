package maarifa.tn.langui.ui.langageDetails;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.firebase.client.Firebase;
import com.firebase.client.Query;

import maarifa.tn.langui.LanguiApplication;
import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Language;
import maarifa.tn.langui.model.Vocablary;
import maarifa.tn.langui.ui.shooseLanguage.MyLanguageAdapter;
import maarifa.tn.langui.ui.shooseLanguage.ShooseLanguageAdapter;
import maarifa.tn.langui.utils.Constants;

/**
 * Created by seif on 23/05/2016.
 */
public class VocablaryFragment extends Fragment {

    FloatingActionButton fab;
    String mEncodedEmail;
    Query shoosenLanguageQuery;
    Firebase languageRef;
    String langageId;
    VocablaryAdapter mAdapter;
    RecyclerView recyclerView;
    ImageButton edit;


    protected static Bundle newInstanceHelper(String encodedEmail, String langageId) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_ENCODED_EMAIL, encodedEmail);
        bundle.putString(Constants.LANGUAGE_KEY, langageId);
        return bundle;
    }

    public static VocablaryFragment newInstance(String encodedEmail, String languageId) {
        VocablaryFragment vocablaryFragment = new VocablaryFragment();

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
            shoosenLanguageQuery = languageRef.orderByKey();


        }
        mAdapter = new VocablaryAdapter(shoosenLanguageQuery, Vocablary.class, null, null,getContext());
        Log.d("ITEM",mAdapter.getItemCount()+"");
        ((LanguiApplication) this.getActivity().getApplication()).nbrVcab=mAdapter.getItemCount();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vocablary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edit=(ImageButton)view.findViewById(R.id.edit_button);
        recyclerView = (RecyclerView) view.findViewById(R.id.vocablary_recycler);
        fab=(FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CallBack)getActivity()).openAddWord();
            }
        });
       // registerForContextMenu(edit);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


    }

    public interface CallBack{
        public void openAddWord();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Action 1");
        menu.add(0, v.getId(), 0, "Action 2");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }



}
