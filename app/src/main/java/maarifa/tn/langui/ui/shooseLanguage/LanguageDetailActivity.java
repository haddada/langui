package maarifa.tn.langui.ui.shooseLanguage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.firebase.client.Firebase;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Language;
import maarifa.tn.langui.ui.BaseActivity;
import maarifa.tn.langui.ui.MainActivity;
import maarifa.tn.langui.ui.langageDetails.AddGrammarRuleActivity;
import maarifa.tn.langui.ui.langageDetails.AddWordActivity;
import maarifa.tn.langui.ui.langageDetails.GrammarFragment;
import maarifa.tn.langui.ui.langageDetails.VocablaryFragment;
import maarifa.tn.langui.utils.Constants;

/**
 * Created by seif on 23/04/2016.
 */
public class LanguageDetailActivity extends BaseActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener,VocablaryFragment.CallBack ,GrammarFragment.CallBack {

    MyPageAdapter pageAdapter;
    private ViewPager mViewPager;
    private TabHost mTabHost;
    private String languageId;
    private String mEncodedEmail;
    private Firebase firebaseRef;
    Language language;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoose_language_detail);
        language = (Language) Parcels.unwrap(getIntent().getParcelableExtra("language"));
        mEncodedEmail=(String)getIntent().getStringExtra(Constants.KEY_ENCODED_EMAIL);
        languageId=(String)getIntent().getStringExtra(Constants.LANGUAGE_KEY);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseRef=new Firebase(Constants.FIREBASE_URL_LANGAUAGES).child(mEncodedEmail).child(languageId);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        // Tab Initialization
        initialiseTabHost();


        // Fragments and ViewPager Initialization
        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(pageAdapter);
        mViewPager.setOnPageChangeListener(LanguageDetailActivity.this);



        /*
        // Now later we can lookup the fragment by tag
        LanguageDetailFragment fragmentDemo = (LanguageDetailFragment)
                getSupportFragmentManager().findFragmentByTag("SOMETAG");*/
    }

    private static void AddTab(MainActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec) {
        tabSpec.setContent(new MyTabFactory(activity));
        tabHost.addTab(tabSpec);
    }

    // Manages the Tab changes, synchronizing it with Pages
    public void onTabChanged(String tag) {
        int pos = this.mTabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    // Manages the Page changes, synchronizing it with Tabs
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        int pos = this.mViewPager.getCurrentItem();
        this.mTabHost.setCurrentTab(pos);
    }

    @Override
    public void onPageSelected(int arg0) {
    }


    private List<Fragment> getFragments(){
        List<Fragment> fList = new ArrayList<Fragment>();

        // TODO Put here your Fragments
       VocablaryFragment f1 = VocablaryFragment.newInstance(mEncodedEmail,languageId);
        GrammarFragment f2=GrammarFragment.newInstance(mEncodedEmail,languageId);
       /* MySampleFragment f2 = MySampleFragment.newInstance("Sample Fragment 2");
        MySampleFragment f3 = MySampleFragment.newInstance("Sample Fragment 3");*/
        fList.add(f1);
        fList.add(f2);

        return fList;
    }

    private void initialiseTabHost() {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        // TODO Put here your Tabs
        setupTab(new TextView(this),getString(R.string.vocablary) );
        setupTab(new TextView(this),getString(R.string.grammar));
        setupTab(new TextView(this),getString(R.string.rules));

        mTabHost.setOnTabChangedListener(this);
    }

    private void setupTab(final View view, final String tag) {
        View tabview = createTabView(mTabHost.getContext(), tag);

        TabHost.TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {return view;}
        });
        mTabHost.addTab(setContent);

    }
    private static View createTabView(final Context context, final String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        tv.setText(text);
        return view;
    }


    @Override
    public void openAddWord() {
        Intent intent=new Intent(LanguageDetailActivity.this, AddWordActivity.class);
        intent.putExtra(Constants.LANGUAGE_KEY,languageId);
        intent.putExtra("language",Parcels.wrap(language));
        startActivity(intent);
    }

    @Override
    public void openAddGrammar() {
        Intent intent=new Intent(LanguageDetailActivity.this, AddGrammarRuleActivity.class);
        intent.putExtra(Constants.LANGUAGE_KEY,languageId);
        intent.putExtra("language",Parcels.wrap(language));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
