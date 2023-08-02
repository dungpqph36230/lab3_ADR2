package SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TaoDaTabaseToDo extends SQLiteOpenHelper {
    public static final String DB_QUANLITODO="DB_QUANLITODO";
    public static final int DB_VERSION=1;
    public static final String TABLE_TODO="TABLE_TODO";
    public static final String ID_TODO="ID_TODO";
    public static final String TEN_TODO="TEN_TODO";
    public static final String CONTEN_TODO="CONTEN_TODO";
    public static final String DATE_TODO="DATE_TODO";
    public TaoDaTabaseToDo(@Nullable Context context) {
        super(context,DB_QUANLITODO,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TaoBang= "CREATE TABLE " + TABLE_TODO + " (" + ID_TODO + " INTEGER PRIMARY KEY ," +
                TEN_TODO + "TEXT ," + CONTEN_TODO + "TEXT ," + DATE_TODO + "TEXT);";
        sqLiteDatabase.execSQL(TaoBang);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(sqLiteDatabase);
    }
}
