package cs499.cpp.edu.l09_data_storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yusun on 2/15/16.
 */
public class StudentDbHelper extends SQLiteOpenHelper {

    private static final String TYPE_TEXT = " TEXT ";

    private static final String CREATE_STUDENT_TABLE =
            "CREATE TABLE " + StudentEntry.TABLE_NAME + " ( "
                    + StudentEntry.STUDENT_COLUMN_NAME_ID + " INTEGER PRIMARY KEY, "
                    + StudentEntry.STUDENT_COLUMN_NAME_NAME + TYPE_TEXT + " , "
                    + StudentEntry.STUDENT_COLUMN_NAME_AGE + " INTEGER, "
                    + StudentEntry.STUDENT_COLUMN_NAME_MAJOR + TYPE_TEXT + ")";

    public StudentDbHelper(Context context) {
        super(context, "student-db", null, 1);
    }

    public StudentDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // delete db first
        db.execSQL("DROP TABLE " + StudentEntry.TABLE_NAME);
        // re-create the db
        onCreate(db);
    }
}
