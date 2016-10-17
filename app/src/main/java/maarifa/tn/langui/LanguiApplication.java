package maarifa.tn.langui;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by seif on 10/04/2016.
 */
public class LanguiApplication extends Application {

    public static int nbrVcab;
    public static int nbrGrammar;

    @Override
    public void onCreate() {
        super.onCreate();
         /* Initialize Firebase */
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
