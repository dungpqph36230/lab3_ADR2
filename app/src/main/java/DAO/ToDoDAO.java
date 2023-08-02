package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.ToDo;
import SQLiteHeHelper.TaoDaTaBaseToDo;

public class ToDoDAO {
    SQLiteDatabase  sqLiteDatabase;
    TaoDaTaBaseToDo taoDaTaBaseToDo;

    public ToDoDAO(Context context) {
        taoDaTaBaseToDo=new TaoDaTaBaseToDo(context);
    }
    public void open(){
        sqLiteDatabase=taoDaTaBaseToDo.getWritableDatabase();
    }
    public void close(){
        taoDaTaBaseToDo.close();
    }

    public boolean ThemToDo(ToDo toDo){
        ContentValues contentValues=new ContentValues();
        contentValues.put(taoDaTaBaseToDo.TITLE_TODO,toDo.getTitle());
        contentValues.put(taoDaTaBaseToDo.CONTEN_TODO,toDo.getConten());
        contentValues.put(taoDaTaBaseToDo.DATE_TODO,toDo.getDate());
        contentValues.put(taoDaTaBaseToDo.TYPE_TODO,toDo.getType());
        long id_ToDo=sqLiteDatabase.insert(taoDaTaBaseToDo.TABLE_QUANLITODO,null,contentValues);
        if(id_ToDo!=0){
           return true;
        } else {
            return false;
        }
    }
    public List<ToDo> layDanhSach(){
        List<ToDo> list=new ArrayList<>();
        String truyvan=("select*from " + taoDaTaBaseToDo.TABLE_QUANLITODO);
        Cursor cursor=sqLiteDatabase.rawQuery(truyvan,null);
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() >0){
            while (!cursor.isAfterLast()){
                int id=cursor.getInt(cursor.getColumnIndex(taoDaTaBaseToDo.ID_TODO));
                String title=cursor.getString(cursor.getColumnIndex(taoDaTaBaseToDo.TITLE_TODO));
                String conten=cursor.getString(cursor.getColumnIndex(taoDaTaBaseToDo.CONTEN_TODO));
                String date=cursor.getString(cursor.getColumnIndex(taoDaTaBaseToDo.DATE_TODO));
                String tyde=cursor.getString(cursor.getColumnIndex(taoDaTaBaseToDo.TYPE_TODO));
                ToDo toDo=new ToDo();
                toDo.setID(id);
                toDo.setTitle(title);
                toDo.setConten(conten);
                toDo.setDate(date);
                toDo.setType(tyde);
                list.add(toDo);
                cursor.moveToNext();
            }
        }
        return list;
    }

    public  boolean CapNhapToDo(ToDo toDomoi){
        if(toDomoi != null){
            ContentValues contentValues=new ContentValues();
            contentValues.put(taoDaTaBaseToDo.TITLE_TODO,toDomoi.getTitle());
            contentValues.put(taoDaTaBaseToDo.CONTEN_TODO,toDomoi.getConten());
            contentValues.put(taoDaTaBaseToDo.DATE_TODO,toDomoi.getDate());
            contentValues.put(taoDaTaBaseToDo.TYPE_TODO,toDomoi.getType());
            int kt=sqLiteDatabase.update(taoDaTaBaseToDo.TABLE_QUANLITODO, contentValues,
                    taoDaTaBaseToDo.ID_TODO + " = " + toDomoi.getID(),null);
            if(kt !=0 ){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public boolean XoaToDo(ToDo td) {
        int kt = sqLiteDatabase.delete(taoDaTaBaseToDo.TABLE_QUANLITODO, taoDaTaBaseToDo.ID_TODO + " = " +
                td.getID(), null);
        if (kt != 0) {
            return true;
        } else {
            return false;
        }
    }


}
