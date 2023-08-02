package DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.ToDo;
import SQLiteHelper.TaoDaTabaseToDo;

public class ToDoDAO {

    TaoDaTabaseToDo taoDaTabaseToDo;
    SQLiteDatabase sqLiteDatabase;

    public ToDoDAO(Context context) {
     taoDaTabaseToDo=new TaoDaTabaseToDo(context);
    }
    public void open(){
        sqLiteDatabase=taoDaTabaseToDo.getWritableDatabase();
    }
    public void close(){
        taoDaTabaseToDo.close();
    }
//    public List<NhanVienDTO> layDanhSachNHanVien(){
//        List<NhanVienDTO> list=new ArrayList<>();
//        String cautruyvan=("select * from " + taoDaTabaseNhanVien.TABLE_NHANVIEN);
//        Cursor cursor=sqLiteDatabase.rawQuery(cautruyvan,null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            int id= cursor.getInt(cursor.getColumnIndex(taoDaTabaseNhanVien.ID_NHANVIEN));
//            String ten= cursor.getString(cursor.getColumnIndex(taoDaTabaseNhanVien.TEN_NHANVIEN));
//            NhanVienDTO nhanVienDTO=new NhanVienDTO();
//            nhanVienDTO.setId(id);
//            nhanVienDTO.setTen(ten);
//            list.add(nhanVienDTO);
//            cursor.moveToNext();
//        }
//        return list;
//    }
    public List<ToDo> layDanhSachToDo(){
        List<ToDo> list=new ArrayList<>();
        String ctv_LayTable=("select * from"+taoDaTabaseToDo.TABLE_TODO);
        Cursor cursor=sqLiteDatabase.rawQuery(ctv_LayTable,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
        int id=cursor.getInt(cursor.getColumnIndex(taoDaTabaseToDo.ID_TODO));

        }
    }

}
