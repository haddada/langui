package maarifa.tn.langui.model.data;


import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import java.lang.Override;
import java.lang.String;

public class LanguiProvider extends ContentProvider {
  private static final UriMatcher sUriMatcher = buildUriMatcher();

  static final int SHOSENLANGUAGEENTRY = 100;

  private LanguiDBHelper mOpenHelper;

  static UriMatcher buildUriMatcher() {
    final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    final String authority =LanguiContract.CONTENT_AUTHORITY;
    matcher.addURI(authority,LanguiContract.SHOSENLANGUAGEENTRY_PATH,SHOSENLANGUAGEENTRY);
    return matcher;
  }

  @Override
  public boolean onCreate() {
    mOpenHelper = new LanguiDBHelper(getContext());
    return true;
  }

  @Override
  public String getType(Uri uri) {
    final int match = sUriMatcher.match(uri);
    switch (match)  {
      case SHOSENLANGUAGEENTRY:
      return LanguiContract.ShosenLanguageEntry.CONTENT_TYPE;
       default:
           throw new UnsupportedOperationException("Unknown uri: " + uri);
    }
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    Cursor retCursor;
    switch (sUriMatcher.match(uri)) {
      case SHOSENLANGUAGEENTRY:
      {retCursor = mOpenHelper.getReadableDatabase().query( 
      LanguiContract.ShosenLanguageEntry.TABLE_NAME,
      projection,
      selection,
      selectionArgs,
      null,
       null,
       sortOrder
      );
      break;
      }
       default: 
           throw new UnsupportedOperationException("Unknown uri: " + uri);
    }
    retCursor.setNotificationUri(getContext().getContentResolver(), uri);
    return retCursor;
  }

  @Override
  public Uri insert(Uri uri,  ContentValues values) {
    final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
    final int match = sUriMatcher.match(uri);
    Uri returnUri;
    switch (match) {
      case SHOSENLANGUAGEENTRY: {
        long _id = db.insert(LanguiContract.ShosenLanguageEntry.TABLE_NAME,null, values);
        if ( _id > 0 )
        returnUri =LanguiContract.ShosenLanguageEntry.buildShosenLanguageUri(_id);
        else 
         throw new SQLException("Failed to insert row into " + uri);
        break;
      }
      default: 
           throw new UnsupportedOperationException("Unknown uri: " + uri);
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return returnUri;
  }

  @Override
  public int bulkInsert(Uri uri,  ContentValues[] values) {
    final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
    final int match = sUriMatcher.match(uri);
    switch (match) {
      case SHOSENLANGUAGEENTRY: {
        db.beginTransaction();
        int returnCountShosenLanguageEntry= 0;
        try {
          for (ContentValues value : values) {
            long _id = db.insert(LanguiContract.ShosenLanguageEntry.TABLE_NAME,null, value);
            if (_id != -1)
            returnCountShosenLanguageEntry++;
          }
          db.setTransactionSuccessful();
        }
        finally {
          db.endTransaction();
          db.close();
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnCountShosenLanguageEntry;
      }
      default: 
           return super.bulkInsert(uri, values);
    }
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
    final int match = sUriMatcher.match(uri);
    int rowsDeleted;
    if ( null == selection ) selection = "1";
    switch (match) {
      case SHOSENLANGUAGEENTRY: {
        rowsDeleted = db.delete(LanguiContract.ShosenLanguageEntry.TABLE_NAME,selection, selectionArgs);
        break;
      }
      default: 
           throw new UnsupportedOperationException("Unknown uri: " + uri);
    }
    if(rowsDeleted != 0)
    getContext().getContentResolver().notifyChange(uri, null);
    return rowsDeleted;
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
    final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
    final int match = sUriMatcher.match(uri);
    int rowsUpdated;
    switch (match) {
      case SHOSENLANGUAGEENTRY: {
        rowsUpdated = db.update(LanguiContract.ShosenLanguageEntry.TABLE_NAME, values, selection,selectionArgs);
        break;
      }
      default: 
           throw new UnsupportedOperationException("Unknown uri: " + uri);
    }
    if(rowsUpdated != 0)
    getContext().getContentResolver().notifyChange(uri, null);
    return rowsUpdated;
  }

  @Override
  @TargetApi(11)
  public void shutdown() {
    mOpenHelper.close();
    super.shutdown();
  }
}
