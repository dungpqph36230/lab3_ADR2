package com.example.android2_lap1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import DAO.ToDoDAO;
import DTO.ToDo;
import adapter.customAdapTerTodo;
import interfare.Sua;
import interfare.Xoa;
import interfare.onclick;

public class MainActivity extends AppCompatActivity implements Sua, Xoa , onclick {
    private List<ToDo> list=new ArrayList<>();
    private ToDoDAO toDoDAO=new ToDoDAO(MainActivity.this);
    private ToDo ToDoViTri;

    private EditText edt_Titile;
    private EditText edt_Conten;
    private EditText edt_Date;
    private EditText edt_Tyte;
    private RecyclerView rec_ToDo;

    private Button btn_add;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toDoDAO.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layId();
        toDoDAO.open();
        list=toDoDAO.layDanhSach();
        Toast.makeText(this, "đọc dữ liệu", Toast.LENGTH_SHORT).show();

        HienThiDanhSach();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToDo toDoThem = new ToDo();
                toDoThem.setTitle(edt_Titile.getText().toString());
                toDoThem.setConten(edt_Conten.getText().toString());
                toDoThem.setDate(edt_Date.getText().toString());
                toDoThem.setType(edt_Tyte.getText().toString());
                if (toDoDAO.ThemToDo(toDoThem)) {
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list = toDoDAO.layDanhSach();
                   HienThiDanhSach();
                } else {
                    Toast.makeText(MainActivity.this, "Thêm lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        edt_Tyte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] mucDoCongViec={"khó","bình thường","rễ"};
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("chọn mức độ công việc");
                builder.setItems(mucDoCongViec, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        edt_Tyte.setText(mucDoCongViec[i]);
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });


    }

    public void HienThiDanhSach(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        rec_ToDo.setLayoutManager(linearLayoutManager);
        customAdapTerTodo customAdapTerTodos=new customAdapTerTodo(MainActivity.this,MainActivity.this,MainActivity.this,MainActivity.this,list);

        rec_ToDo.setAdapter(customAdapTerTodos);
    }
    public  void CapNhap(ToDo toDo){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater inflater=LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.activity_cap_nhap_to_do,null);
        EditText edt_cnTitle=view.findViewById(R.id.edt_cnTitle);
        EditText edt_cnconten=view.findViewById(R.id.edt_cnContens);
        EditText edt_cndate=view.findViewById(R.id.edt_cnDate);
        EditText edt_cntyte=view.findViewById(R.id.edt_cnType);
        Button btncnupdate=view.findViewById(R.id.btn_cnudate);
        Button btncncaner=view.findViewById(R.id.btn_cncancel);
        builder.setView(view);
        AlertDialog alertDialog =builder.create();
        edt_cnTitle.setText(toDo.getTitle());
        edt_cnconten.setText(toDo.getConten());
        edt_cndate.setText(toDo.getDate());
        edt_cntyte.setText(toDo.getType());
        edt_cntyte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String [] mucDo={"khó","trung bình","rễ"};
               AlertDialog.Builder builder1=new AlertDialog.Builder(MainActivity.this);
               builder1.setTitle("Chọn mức độ công việc");
               builder1.setItems(mucDo, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       edt_cntyte.setText(i);
                   }
               });

            }
        });
        btncnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String titel=edt_cnTitle.getText().toString();
                String conten=edt_cnconten.getText().toString();
                String date=edt_cndate.getText().toString();
                String tyde=edt_cntyte.getText().toString();
                if(!titel.trim().isEmpty()||!conten.trim().isEmpty()||!date.trim().isEmpty()||!tyde.trim().isEmpty()) {
                    toDo.setTitle(titel);
                    toDo.setConten(conten);
                    toDo.setDate(date);
                    toDo.setType(tyde);

                } else {
                    Toast.makeText(MainActivity.this, "vui khồng để trống các trường thông tin", Toast.LENGTH_SHORT).show();
                }
                if(toDoDAO.CapNhapToDo(toDo)){
                    int i=list.lastIndexOf(toDo);
                    rec_ToDo.getAdapter().notifyItemChanged(i);
                    Toast.makeText(MainActivity.this, "cập nhập thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btncncaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }
   public void layId(){
        rec_ToDo=findViewById(R.id.rec_ToDo);
       btn_add = findViewById(R.id.btn_add);
       edt_Titile = findViewById(R.id.edt_Title);
       edt_Conten = findViewById(R.id.edt_Contens);
       edt_Date = findViewById(R.id.edt_Date);
       edt_Tyte = findViewById(R.id.edt_Type);

   }



    @Override
    public void Xoa(int position) {
        ToDo toDo = list.get(position);
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("cảnh báo");
        builder.setIcon(R.drawable.thongbao);
        builder.setMessage("bạn có muốn  xóa không");
        builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (toDoDAO.XoaToDo(toDo)) {
                    list.remove(position);
                    rec_ToDo.getAdapter().notifyItemRemoved(position);
                    Toast.makeText(MainActivity.this, "Xóa thành công phần tử", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Lỗi khi xóa phần tử", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
       AlertDialog alertDialog=builder.create();
       alertDialog.show();

    }


    @Override
    public void onclickItemToDo(int i) {
        ToDo  toDo=list.get(i);
        if(toDo !=null){
            edt_Titile.setText(toDo.getTitle().toString());
            edt_Conten.setText(toDo.getConten().toString());
            edt_Date.setText(toDo.getDate().toString());
            edt_Tyte.setText(toDo.getType().toString());
        }
    }

    @Override
    public void Sua(ToDo toDo) {
        CapNhap(toDo);
//        String title=edt_Titile.getText().toString();
//        String conten=edt_Conten.getText().toString();
//        String date=edt_Date.getText().toString();
//        String tyte=edt_Tyte.getText().toString();
//        if(!title.trim().isEmpty()||!conten.trim().isEmpty()||!date.trim().isEmpty()||!tyte.trim().isEmpty()){
//            toDo.setTitle(title);
//            toDo.setConten(conten);
//            toDo.setDate(date);
//            toDo.setType(tyte);
//        } else {
//            Toast.makeText(this, "vui khồng để trống các trường thông tin", Toast.LENGTH_SHORT).show();
//        }
//        if(toDoDAO.CapNhapToDo(toDo)){
//            int i=list.lastIndexOf(toDo);
//                rec_ToDo.getAdapter().notifyItemChanged(i);
//            }
//        }
    }

    }


