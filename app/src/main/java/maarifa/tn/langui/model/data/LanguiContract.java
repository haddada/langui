package maarifa.tn.langui.model.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import java.lang.String;

public class LanguiContract {
  public static final String CONTENT_AUTHORITY = " maarifa.tn.langui.data";

  public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
  public static final String SHOSENLANGUAGEENTRY_PATH="myLanguage";

  public static final class ShosenLanguageEntry implements BaseColumns {
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(SHOSENLANGUAGEENTRY_PATH).build();

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/"+ SHOSENLANGUAGEENTRY_PATH;

    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/"+ SHOSENLANGUAGEENTRY_PATH;

    public static final String TABLE_NAME = "shosenlanguageentry";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_FLAG = "flag";

    public static Uri buildShosenLanguageUri(long id) {
      return ContentUris.withAppendedId(CONTENT_URI, id);
    }
  }

}
