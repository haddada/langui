package maarifa.tn.langui.ui.shooseLanguage;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ServerValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Language;
import maarifa.tn.langui.model.data.LanguiContract;
import maarifa.tn.langui.utils.Constants;

/**
 * Created by hamza on 22/04/2016.
 */
public class ShooseLanguageFragment extends Fragment {

    public final String LANGUAGE_LIST="language_list";
    public final String LANGUAGE_SHOSEN_LIST="language_list";
    ArrayList<Language> languageList;
    ArrayList<Language> shoosenLanguageList;
    Firebase languageRef ;
    ShooseLanguageAdapter mAdapter;
    ShooseLanguageAdapter shosenAdapter;
    MyLanguageAdapter shosenLocalAdapter;
    ListView mList;
    ListView mShosenList;
    String mEncodedEmail;
    Query shoosenLanguageQuery;
    ChildEventListener childEventListener;
    AdapterView.OnItemClickListener mListClickListener;
    AdapterView.OnItemClickListener shosenListClickListener;

    protected static Bundle newInstanceHelper(String encodedEmail) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_ENCODED_EMAIL, encodedEmail);
        return bundle;
    }

    public static ShooseLanguageFragment newInstance(String encodedEmail) {
        ShooseLanguageFragment shooseLanguageFragment = new ShooseLanguageFragment();
        Bundle bundle = shooseLanguageFragment.newInstanceHelper( encodedEmail);
        shooseLanguageFragment.setArguments(bundle);

        return shooseLanguageFragment;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("ShooseLanguageFragment","ONCREATE");
        languageList=new ArrayList<Language>();
        shoosenLanguageList=new ArrayList<Language>();
       // if(savedInstanceState==null){
            Log.d("ShooseLanguageFragment","SAVED NULL");
            Language french=new Language("French", R.drawable.france_512);
            Language england=new Language("English",R.drawable.england);
            languageList.add(french);
            languageList.add(england);
       /* }else{
            Log.d("ShooseLanguageFragment","SAVED"+languageList.size());
            languageList=(ArrayList<Language>) savedInstanceState.getSerializable(LANGUAGE_LIST);
            shoosenLanguageList=(ArrayList<Language>) savedInstanceState.getSerializable(LANGUAGE_SHOSEN_LIST);
            Log.d("ShooseLanguageFragment","SAVED ADTER"+languageList.size());
        }*/



        if (getArguments() != null) {
            mEncodedEmail = getArguments().getString(Constants.KEY_ENCODED_EMAIL);
            Firebase myLanguagesRef = new Firebase(Constants.FIREBASE_URL_LANGAUAGES).child(mEncodedEmail);
            shoosenLanguageQuery = myLanguagesRef.orderByKey();
            languageRef = new Firebase(Constants.FIREBASE_URL_LANGAUAGES).child(mEncodedEmail);
        }
        mAdapter=new ShooseLanguageAdapter(getActivity().getApplicationContext(),R.layout.language_item,languageList);
        shosenAdapter=new ShooseLanguageAdapter(getActivity().getApplicationContext(),R.layout.list_item,shoosenLanguageList);
        shosenLocalAdapter=new MyLanguageAdapter(getActivity(),Language.class,R.layout.language_item,shoosenLanguageQuery);

        childEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();
                Language addLanguages = new Language(data);
                shoosenLanguageList.add(addLanguages);
                shosenAdapter.notifyDataSetChanged();
                shosenLocalAdapter=new MyLanguageAdapter(getActivity(),Language.class,R.layout.language_item,shoosenLanguageQuery);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();
                Language removeLanguage = new Language(data);
                shosenLocalAdapter=new MyLanguageAdapter(getActivity(),Language.class,R.layout.language_item,shoosenLanguageQuery);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        };
        languageRef.addChildEventListener(childEventListener);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shoose_language, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("ShooseLanguageFragment","ON PAUSE");
        shoosenLanguageList.clear();
        /*
        languageRef.removeEventListener(childEventListener);
        shoosenLanguageList=null;
        languageList=null;*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ShooseLanguageFragment","ONDESTROY");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("ShooseLanguageFragment","ON ACTIVTY CREATED");


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList=(ListView)view.findViewById(R.id.language_list);
        mShosenList=(ListView)view.findViewById(R.id.my_langauages);

        mList.setAdapter(mAdapter);
        mShosenList.setAdapter(shosenAdapter);



        mListClickListener=new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Language language = (Language) adapterView.getItemAtPosition(i);

                if(!searchExistenceInList(language,shoosenLanguageList)){
                    // shoosenLanguageList.add(language);
                    addLanguage(language);

                }

            }
        };

        shosenListClickListener=new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Language language = (Language) adapterView.getItemAtPosition(i);
                shosenLocalAdapter.getRef(i).removeValue();
                shoosenLanguageList.remove(i);

                shosenAdapter.notifyDataSetChanged();

            }
        };
        mList.setOnItemClickListener(mListClickListener);

        mShosenList.setOnItemClickListener(shosenListClickListener);




    }

    private void addLanguage(Language language) {

        Firebase firebaseRef = new Firebase(Constants.FIREBASE_URL);
        Firebase languageRef = new Firebase(Constants.FIREBASE_URL_LANGAUAGES).child(mEncodedEmail);

        HashMap<String, Object> updatedItemToAddMap = new HashMap<String, Object>();

            /* Save push() to maintain same random Id */
        Firebase newRef = languageRef.push();
        String languageId = newRef.getKey();
        languageRef.child(languageId).setValue(language);
        languageRef.child(languageId).child(Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED).setValue(ServerValue.TIMESTAMP);


    }
    private void removeLanguage( String languageId ){
        languageRef.child(languageId).removeValue();
    }

    /*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now

       // outState.putSerializable(LANGUAGE_LIST, languageList);
        //outState.putSerializable(LANGUAGE_SHOSEN_LIST,shoosenLanguageList);
    }*/


    public interface Callback {

        void onItemSelected(Language language);
    }

    private boolean searchExistenceInList(Language language,List<Language>LanguageList){

        Iterator itr = LanguageList.iterator();
        while(itr.hasNext()) {
            Language element = (Language)itr.next();
            if(element.getName().equals(language.getName()))
                return true;
        }
        return false;

    }
}
