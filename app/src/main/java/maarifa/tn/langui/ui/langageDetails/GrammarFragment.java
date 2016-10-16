package maarifa.tn.langui.ui.langageDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;
import com.firebase.client.Query;

import maarifa.tn.langui.LanguiApplication;
import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Grammar;
import maarifa.tn.langui.model.Vocablary;
import maarifa.tn.langui.utils.Constants;

/**
 * Created by hamza on 24/05/2016.
 */
public class GrammarFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton tag;
    GrammarAdapter mAdapter;
    Query grammarQuery;
    Firebase languageRef;
    String mEncodedEmail;
    String langageId;


    protected static Bundle newInstanceHelper(String encodedEmail, String langageId) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_ENCODED_EMAIL, encodedEmail);
        bundle.putString(Constants.LANGUAGE_KEY, langageId);
        return bundle;
    }

    public static GrammarFragment newInstance(String encodedEmail, String languageId) {
        GrammarFragment grammarFragment = new GrammarFragment();

        Bundle bundle = grammarFragment.newInstanceHelper(encodedEmail, languageId);
        grammarFragment.setArguments(bundle);

        return grammarFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mEncodedEmail = getArguments().getString(Constants.KEY_ENCODED_EMAIL);
            langageId = getArguments().getString(Constants.LANGUAGE_KEY);
            languageRef = new Firebase(Constants.FIREBASE_URL_LANGAUAGES).child(mEncodedEmail).child(langageId).child("grammar");
            grammarQuery = languageRef.orderByKey();

        }

        mAdapter = new GrammarAdapter(grammarQuery, Grammar.class, null, null,getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grammar, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.grammar_recycler);
        tag=(FloatingActionButton)view.findViewById(R.id.fab);
        tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CallBack)getActivity()).openAddGrammar();
            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        ((LanguiApplication) this.getActivity().getApplication()).nbrGrammar=mAdapter.getItemCount();
    }

    public interface CallBack{
        public void openAddGrammar();
    }
}
