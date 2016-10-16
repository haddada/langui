package maarifa.tn.langui.ui.shooseLanguage;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;

import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Language;
import maarifa.tn.langui.utils.Constants;

/**
 * Created by hamza on 23/05/2016.
 */
public class MyLanguagesFragment extends Fragment {

    private MyLanguageRecyclerAdapter languageAdapter;
    private Query shoosenLanguageQuery;
    private String mEncodedEmail;
    private ListView listView;
    private RecyclerView recyclerView;
    MyLanguageAdapter shosenLocalAdapter;
    RecyclerItemClickListener clickListener;
    LinearLayoutManager mLayoutManager;
    View emptyView;

    protected static Bundle newInstanceHelper(String encodedEmail) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_ENCODED_EMAIL, encodedEmail);
        return bundle;
    }

    public static MyLanguagesFragment newInstance(String encodedEmail) {
        MyLanguagesFragment myLanguagesFragment = new MyLanguagesFragment();

        Bundle bundle = myLanguagesFragment.newInstanceHelper( encodedEmail);
        myLanguagesFragment.setArguments(bundle);

        return myLanguagesFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mylanguage, container, false);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mEncodedEmail = getArguments().getString(Constants.KEY_ENCODED_EMAIL);
            Firebase myLanguagesRef = new Firebase(Constants.FIREBASE_URL_LANGAUAGES).child(mEncodedEmail);
            shoosenLanguageQuery = myLanguagesRef.orderByKey();
            languageAdapter=new MyLanguageRecyclerAdapter(shoosenLanguageQuery,Language.class,null,null,this.getActivity().getApplication());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shosenLocalAdapter=null;
        clickListener=null;
        languageAdapter.destroy();
        mLayoutManager=null;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shosenLocalAdapter=new MyLanguageAdapter(getActivity(),Language.class,R.layout.language_item,shoosenLanguageQuery);
        recyclerView=(RecyclerView) view.findViewById(R.id.my_language_recycler_view);
        emptyView = view.findViewById(R.id.listview_langauges_empty);
       // updateEmptyView();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(languageAdapter);
        clickListener=new RecyclerItemClickListener(getActivity().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                // do whatever
                TextView langageName=(TextView)view.findViewById(R.id.title);
                ImageView langageFlag=(ImageView)view.findViewById(R.id.thumbnail);
                TextView vocabNumb=(TextView)view.findViewById(R.id.word_vocab);
                TextView gramNumb=(TextView)view.findViewById(R.id.grammar_rule);

                int nbrVocab=Integer.parseInt(vocabNumb.getText().toString().split(",")[0].trim());
                int nbrGram =Integer.parseInt(gramNumb.getText().toString().split(",")[0].trim());


                Language language=new Language(langageName.getText().toString(),langageFlag.getId(),nbrVocab,nbrGram);
                String languageId = shosenLocalAdapter.getRef(position).getKey();

                if (language != null) {
                    ((MyLanguagesFragment.Callback) getActivity())
                            .onItemSelected(language,languageId);
                }
            }

        });
        recyclerView.addOnItemTouchListener(clickListener);
    }

    public interface Callback {
        void onItemSelected(Language language,String languageId);
    }

    public void  updateEmptyView(){
        if ( shosenLocalAdapter.getCount() == 0 ) {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            TextView tv = (TextView) getView().findViewById(R.id.listview_language_msg_empty);
            if ( null != tv ) {
                // if cursor is empty, why? do we have an invalid location
                int message = R.string.no_languages;
                tv.setText(message);
            }
        }else{
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }


}
