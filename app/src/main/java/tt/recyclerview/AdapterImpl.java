package tt.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AdapterImpl implements Adapter {

    Context context;

    public AdapterImpl(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int row) {
        return row % 2; // 0或1
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public View onCreateViewHolder(int position, View convertView, ViewGroup parent) {
        if (position % 2 == 0) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_table1, parent, false);
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_table2, parent, false);
        }
        return onBinderViewHolder(position, convertView, parent);
    }

    @Override
    public View onBinderViewHolder(int position, View convertView, ViewGroup parent) {
        if (position % 2 == 0) {
            TextView textView = convertView.findViewById(R.id.text1);
            textView.setText("第 " + position + "行");
            return convertView;
        } else {
            TextView textView = convertView.findViewById(R.id.text2);
            textView.setText("第 " + position + "行的图标");
            return convertView;
        }
    }


    @Override
    public int getCount() {
        return 30000;
    }

    @Override
    public int getHeight(int index) {
        return 200;
    }


}