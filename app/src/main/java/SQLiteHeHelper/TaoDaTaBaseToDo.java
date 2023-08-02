package SQLiteHeHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;

public class TaoDaTaBaseToDo extends SQLiteOpenHelper {
    public static final String QUANLITODO = "QUANLITODO";
    public static final int version = 1;
    public static final String TABLE_QUANLITODO = "TABLE_QUANLITODO";
    public static final String ID_TODO="ID_TODO";
    public static final String TITLE_TODO = "TITLE_TODO";
    public static final String CONTEN_TODO = "CONTEN_TODO";
    public static final String DATE_TODO = "DATE_TODO";
    public static final String TYPE_TODO = "TYPE_TODO";
    public static final String STATUR="STATUR";

    public TaoDaTaBaseToDo(@Nullable Context context) {
        super(context, "QUANLITODO", null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TaoBang = "CREATE TABLE " + TABLE_QUANLITODO + "(" + ID_TODO + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + TITLE_TODO + " TEXT ," + CONTEN_TODO + " TEXT ," + DATE_TODO + " TEXT ," + TYPE_TODO +
                " TEXT ," + STATUR + " INTEGER " + ");";
        sqLiteDatabase.execSQL(TaoBang);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_QUANLITODO);
        onCreate(sqLiteDatabase);
    }
}
