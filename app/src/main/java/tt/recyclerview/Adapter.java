package tt.recyclerview;

import android.view.View;
import android.view.ViewGroup;

interface Adapter {
    View onCreateViewHolder(int position, View convertView, ViewGroup parent);
    View onBinderViewHolder(int position, View convertView, ViewGroup parent);

    int getItemViewType(int row); // Item的类型
    int getViewTypeCount();       // Item的类型数量
    int getCount();

    int getHeight(int index);
}