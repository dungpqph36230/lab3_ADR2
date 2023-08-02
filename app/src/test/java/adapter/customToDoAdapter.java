package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.example.android2_lap1.R;

import java.util.List;

import DTO.ToDo;

public class customToDoAdapter extends BaseAdapter {
  Context  context;
  List<ToDo> list;

    public customToDoAdapter(Context context, List<ToDo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        view=inflater.inflate(R.layout.activity_item_to_do_adapter,viewGroup,false);
        EditText edt_lvtitle=view.findViewById(R.id.edt_lvTitle);
        EditText edt_lvconten=view.findViewById(R.id.edt_lvConten);
        EditText edt_lvDate=view.findViewById(R.id.edt_lvDate);
        edt_lvtitle.setText(list.get(i).getTitle());
        edt_lvconten.setText(list.get(i).getContent());
        edt_lvDate.setText(list.get(i).getDate());
        return view;
    }
}
