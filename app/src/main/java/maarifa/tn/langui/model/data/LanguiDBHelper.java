package maarifa.tn.langui.model.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.lang.Override;
import java.lang.String;

public class LanguiDBHelper extends SQLiteOpenHelper {
  public static final int DATABASE_VERSION = 1;

  public static final String DATABASE_NAME = "langui";

  public LanguiDBHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    final String SQL_CREATE_SHOSENLANGUAGEENTRY_TABLE = "CREATE TABLE " + LanguiContract.ShosenLanguageEntry.TABLE_NAME + " (" +
    LanguiContract.ShosenLanguageEntry._ID + " INTEGER PRIMARY KEY," +
    LanguiContract.ShosenLanguageEntry.COLUMN_NAME + " TEXT," +
    LanguiContract.ShosenLanguageEntry.COLUMN_FLAG + " NUMBER" +
     ")";

     
    sqLiteDatabase.execSQL(SQL_CREATE_SHOSENLANGUAGEENTRY_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +LanguiContract.ShosenLanguageEntry.TABLE_NAME);
  }
}
