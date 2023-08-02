package adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android2_lap1.R;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import DAO.ToDoDAO;
import DTO.ToDo;
import interfare.Sua;
import interfare.Xoa;
import interfare.onclick;

public class customAdapTerTodo  extends RecyclerView.Adapter<customAdapTerTodo.viewholder>{
    private Sua sua;
    private Xoa xoa;
  private  Context  context;
    private ToDoDAO toDoDAO=new ToDoDAO(context);
    private onclick onclicks;
    private List<ToDo> list;

    public customAdapTerTodo(Context context,Sua sua, Xoa xoa,onclick onclicks, List<ToDo> list) {
        this.context=context;
        this.onclicks=onclicks;
        this.sua = sua;
        this.xoa = xoa;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_to_do_adapter, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {
        ToDo todo = list.get(position);

        holder.tv_Ten.setText(todo.getTitle());
        holder.tv_ThoiGian.setText(todo.getDate());

//        if (todo.getStatur() == 1) {
//            holder.chk_MonHoc.setChecked(true);
//            holder.tv_Ten.setPaintFlags(holder.tv_Ten.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//        } else {
//            holder.chk_MonHoc.setChecked(false);
//            holder.tv_Ten.setPaintFlags(holder.tv_Ten.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
//        }

        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa.Xoa(position);
            }
        });
        holder.chk_MonHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean cheks=holder.chk_MonHoc.isChecked();
                if (cheks) {
                    holder.tv_Ten.setPaintFlags(holder.tv_Ten.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    holder.tv_Ten.setPaintFlags(holder.tv_Ten.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }

            }
        });
        holder.btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sua.Sua(todo);
            }
        });
        holder.layout_item_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclicks.onclickItemToDo(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView tv_Ten, tv_ThoiGian;
        Button btn_sua, btn_xoa;
        LinearLayout layout_item_todo;
        CheckBox chk_MonHoc;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_Ten = itemView.findViewById(R.id.tv_Ten);
            tv_ThoiGian = itemView.findViewById(R.id.tv_ThoiGian);
            btn_sua = itemView.findViewById(R.id.btn_sua);
            btn_xoa = itemView.findViewById(R.id.btn_xoa);
            chk_MonHoc = itemView.findViewById(R.id.chk_MonHoc);
            layout_item_todo=itemView.findViewById(R.id.layout_item_Todo);
        }
    }
}
