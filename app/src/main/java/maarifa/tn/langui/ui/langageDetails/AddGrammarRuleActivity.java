package maarifa.tn.langui.ui.langageDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;

import org.parceler.Parcels;

import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Grammar;
import maarifa.tn.langui.model.Language;
import maarifa.tn.langui.model.Vocablary;
import maarifa.tn.langui.ui.BaseActivity;
import maarifa.tn.langui.ui.shooseLanguage.LanguageDetailActivity;
import maarifa.tn.langui.utils.Constants;

/**
 * Created by seif on 24/05/2016.
 */
public class AddGrammarRuleActivity extends BaseActivity implements AddGrammarFragment.CallBack{


    String languageId;
    Firebase firebaseRef;
    Language language;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_grammar);
        language = (Language) Parcels.unwrap(getIntent().getParcelableExtra("language"));
        languageId=(String)getIntent().getStringExtra(Constants.LANGUAGE_KEY);
        firebaseRef=new Firebase(Constants.FIREBASE_URL_LANGAUAGES).child(mEncodedEmail).child(languageId);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.container, new AddGrammarFragment(), "ADD_GRAMMAR").
                commit();
    }

    @Override
    public void returToDetail(String title, String content) {

        Grammar gram=new Grammar(title,content);

        Firebase newRef = firebaseRef.push();
        String grammarId = newRef.getKey();
        firebaseRef.child("grammar").child(grammarId).setValue(gram);
        firebaseRef.child(Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED).setValue(ServerValue.TIMESTAMP);
        firebaseRef.child(Constants.FIREBASE_PROPERTY_GRAMNUMBER).setValue(language.getGramNumb()+1);
        Intent intent =new Intent(AddGrammarRuleActivity.this, LanguageDetailActivity.class);
        intent.putExtra(Constants.LANGUAGE_KEY,languageId);
        intent.putExtra(Constants.KEY_ENCODED_EMAIL,mEncodedEmail);
        startActivity(intent);
    }
}
