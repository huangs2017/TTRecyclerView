package widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ExpandableListView_Adapter extends BaseExpandableListAdapter {

    Context context;
    public ExpandableListView_Adapter(Context context) {
        this.context = context;
    }

    private String[] country = new String[] {"中国", "美国"};
    private String[][] city = new String[][] { { "北京", "上海"}, {"纽约", "洛杉矶"} };

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setTextSize(30);
        textView.setText(getGroup(groupPosition).toString());
        return textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setTextSize(20);
        textView.setText(getChild(groupPosition, childPosition).toString());
        return textView;
    }


    @Override
    public int getGroupCount() {
        return country.length;
    }
    @Override
    public Object getGroup(int groupPosition) {
        return country[groupPosition];
    }
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return city[groupPosition].length;
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return city[groupPosition][childPosition];
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
    @Override
    public boolean isChildSelectable(int groupPosition,int childPosition) {
        return true;
    }
}
