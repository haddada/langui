package maarifa.tn.langui.ui.langageDetails;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Language;
import maarifa.tn.langui.model.Vocablary;
import maarifa.tn.langui.ui.BaseActivity;
import maarifa.tn.langui.ui.shooseLanguage.LanguageDetailActivity;
import maarifa.tn.langui.ui.shooseLanguage.ShooseLanguageFragment;
import maarifa.tn.langui.utils.Constants;

/**
 * Created by hamza on 24/05/2016.
 */
public class AddWordActivity extends BaseActivity implements AddWordFragment.Callback ,AddSynonymsFragment.Callback,AddContextFragment.Callback
,AddTagsDialog.CallBack
{

    String theWord;
    String context;
    List<String> sentences;
    List<String> syns;
    String languageId;
    Firebase firebaseRef;
    Language language;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_word);
        language = (Language) Parcels.unwrap(getIntent().getParcelableExtra("language"));
        languageId=(String)getIntent().getStringExtra(Constants.LANGUAGE_KEY);
        firebaseRef=new Firebase(Constants.FIREBASE_URL_LANGAUAGES).child(mEncodedEmail).child(languageId);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.container, new AddWordFragment(), "ADD_WORD").
                commit();
    }





    @Override
    public void openSyn(String theWord) {
        this.theWord=theWord;
        getSupportFragmentManager().beginTransaction().
                replace(R.id.container, new AddSynonymsFragment(), "ADD_SYN").
                commit();
    }

    @Override
    public void openContext(String syn1) {
        syns=new ArrayList<String>();
        syns.add(syn1);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.container, new AddContextFragment(), "ADD_CONTEXT").
                commit();

    }

    @Override
    public void openVocablary(String context, String sentence) {
        sentences=new ArrayList<String>();
        sentences.add(sentence);
        this.context=context;
        DialogFragment dialog = new AddTagsDialog();
        dialog.show(getFragmentManager(), "AddTagDialog");

    }

    @Override
    public void addTag(String tag) {

        Vocablary vocab=new Vocablary(theWord,syns,sentences,context,tag);

        Firebase newRef = firebaseRef.push();
        String vocabId = newRef.getKey();
        firebaseRef.child("vocab").child(vocabId).setValue(vocab);
        firebaseRef.child(Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED).setValue(ServerValue.TIMESTAMP);
        Log.d("NUMB",language.getVocabNumb()+"");
        firebaseRef.child(Constants.FIREBASE_PROPERTY_VABNUMBER).setValue(language.getVocabNumb()+1);

        Intent intent =new Intent(AddWordActivity.this, LanguageDetailActivity.class);
        intent.putExtra(Constants.LANGUAGE_KEY,languageId);
        intent.putExtra(Constants.KEY_ENCODED_EMAIL,mEncodedEmail);
        startActivity(intent);


    }
}
