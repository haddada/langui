package maarifa.tn.langui.utils;

import maarifa.tn.langui.BuildConfig;
import maarifa.tn.langui.model.data.LanguiContract;

/**
 * Created by hamza on 10/04/2016.
 */
public final class Constants {

    /**
     * Constants related to locations in Firebase, such as the name of the node
     * where active lists are stored (ie "activeLists")
     */
    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_LOCATION_LANGUAGES="languages";



    /**
     * Constants for Firebase URL
     */
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USERS;
    public static final String FIREBASE_URL_LANGAUAGES=FIREBASE_URL+"/"+FIREBASE_LOCATION_LANGUAGES;

    /**
     * Constants for Firebase login
     */
    public static final String PASSWORD_PROVIDER = "password";
    public static final String GOOGLE_PROVIDER = "google";
    public static final String PROVIDER_DATA_DISPLAY_NAME = "displayName";


    /**
     * extras for Shared Preferences
     */

    public static final String KEY_PROVIDER = "PROVIDER";
    public static final String KEY_ENCODED_EMAIL = "ENCODED_EMAIL";
    public static final String KEY_SIGNUP_EMAIL = "SIGNUP_EMAIL";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";
    public static final String FIREBASE_PROPERTY_VABNUMBER="vocabNumb";
    public static final String FIREBASE_PROPERTY_GRAMNUMBER="gramNumb";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_PROPERTY_EMAIL="email";
    public static final String FIREBASE_PROPERTY_USER_HAS_LOGGED_IN_WITH_PASSWORD="hasLoggedInWithPassword";
    public static final String KEY_NBR_VOCAB="vocab_nbr";
    public static final String KEY_NBR_GRAM="gram_nbr";

    public static final String LANGUAGE_KEY="language_key";


    /**
     *  My Language dataBase
     */

    public static final String[] MY_LANGUAGE_COLUMNS = {
            LanguiContract.ShosenLanguageEntry.TABLE_NAME + "." + LanguiContract.ShosenLanguageEntry._ID,
            LanguiContract.ShosenLanguageEntry.COLUMN_NAME,
            LanguiContract.ShosenLanguageEntry.COLUMN_FLAG
    };

    public static final int COL_LANGUAGE_ID = 0;
    public static final int COL_LANGUAGE_NAME = 1;
    public static final int COL_LAGUAGE_FLAG = 2;


}
