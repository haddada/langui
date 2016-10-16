package maarifa.tn.langui.ui;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.fasterxml.jackson.databind.deser.Deserializers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import maarifa.tn.langui.R;
import maarifa.tn.langui.model.Language;
import maarifa.tn.langui.ui.login.CreateAccountActivity;
import maarifa.tn.langui.ui.login.LoginActivity;
import maarifa.tn.langui.ui.progress.ProgressFragment;
import maarifa.tn.langui.ui.shooseLanguage.MyLanguagesFragment;
import maarifa.tn.langui.ui.shooseLanguage.ShooseLanguageActivity;
import maarifa.tn.langui.ui.shooseLanguage.ShooseLanguageAdapter;
import maarifa.tn.langui.ui.shooseLanguage.ShooseLanguageFragment;
import maarifa.tn.langui.utils.Constants;

public class MainActivity extends BaseActivity {

    public DrawerLayout drawerLayout;
    public NavigationView linear;
    Toolbar toolbar;
    public ListView drawerList;
    public List listContent=new ArrayList<Language>();
    public ShooseLanguageAdapter mItemAdapter;
    public String[] mTitles;
    public CharSequence title="langui";
    private ActionBarDrawerToggle drawerToggle;
    private Map map;

    protected void onCreateDrawer()
    {
        mTitles=getResources().getStringArray(R.array.titles);
        // R.id.drawer_layout should be in every activity with exactly the same id.
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        linear=(NavigationView) findViewById(R.id.navigation_view);
        toolbar= (Toolbar) findViewById(R.id.app_bar);
        drawerToggle = new ActionBarDrawerToggle((Activity) this, drawerLayout, toolbar, 0, 0)
        {
            public void onDrawerClosed(View view)
            {
                getSupportActionBar().setTitle(title);
            }

            public void onDrawerOpened(View drawerView)
            {
                //getSupportActionBar().setTitle(R.string.menu);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
/*
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
*/
       // layers = getResources().getStringArray(R.array.layers_array);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        View header = getLayoutInflater().inflate(R.layout.drawer_list_header, null);
        drawerList.addHeaderView(header, null, false);
        Language item=new Language(getResources().getString(R.string.all_languages) ,R.drawable.ic_language_black);
        Language alarm=new Language(getResources().getString(R.string.alarm),R.drawable.ic_alarm_black);
        Language chat=new Language(getResources().getString(R.string.chat),R.drawable.ic_chat_black);
        Language setting =new Language(getResources().getString(R.string.settings),R.drawable.ic_settings_black);
        Language progress=new Language(getResources().getString(R.string.progress),R.drawable.ic_show_chart_black);
        Language myLanguage=new Language(getResources().getString(R.string.my_language),R.drawable.ic_library_books_black);
        listContent.add(item);
        listContent.add(myLanguage);
        listContent.add(progress);
        listContent.add(alarm);
        listContent.add(setting);
        listContent.add(chat);
        mItemAdapter=new ShooseLanguageAdapter((Activity) this,R.layout.list_item,listContent);
        drawerList.setAdapter(mItemAdapter);
      /*  View footerView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.drawer_list_footer, null, false);
        drawerList.addFooterView(footerView);*/

        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);


    }




    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment ;
        /*
       */

        // Insert the fragment by replacing any existing fragment

        switch (position){
            case 1:
                fragment=ShooseLanguageFragment.newInstance(mEncodedEmail);
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, fragment, "ALL_LANGUAGES").
                        commit();
                break;
            case 2:
                fragment= MyLanguagesFragment.newInstance(mEncodedEmail);
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, fragment, "MY_LANGUAGES").
                        addToBackStack("my_languages").
                        commit();
                break;
            case 3:
                fragment= ProgressFragment.newInstance(mEncodedEmail);
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, fragment, "MY_PROGRESS").
                        addToBackStack("my_languages").
                        commit();
                break;
            default:
                    fragment=ShooseLanguageFragment.newInstance(mEncodedEmail);
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.container, fragment, "ALL_LANGUAGES").
                            commit();
                break;
        }


        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        setTitle(mTitles[position-1]);
        drawerLayout.closeDrawer(linear);
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title=title;
        getSupportActionBar().setTitle(title);
    }
}


