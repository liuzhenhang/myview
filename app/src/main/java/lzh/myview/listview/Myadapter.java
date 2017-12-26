package lzh.myview.listview;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import lzh.myview.R;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class Myadapter extends BaseAdapter{
    private Context context;
    private List<String> list = new ArrayList<>();

    public Myadapter(Context context, List<String> list) {
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
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
      ViewHolder viewHolder;
        if (convertView==null){
            //convertView = View.inflate(context, R.layout.listview_itemlayout, null);
            convertView = LayoutInflater.from(context).inflate( R.layout.listview_itemlayout,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
       viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText("123");

        return convertView;
    }

   class ViewHolder{
     TextView textView;
    }

}
