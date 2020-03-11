package widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import tt.myview.R;

public class ExpandableListView_Activity extends Activity {

	ExpandableListView lv;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable_listview_activity);

		final ExpandableListAdapter adapter = new ExpandableListView_Adapter(this);
		lv = findViewById(R.id.list);
		lv.setAdapter(adapter);

		lv.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				Toast.makeText(ExpandableListView_Activity.this, "你点击了" + adapter.getChild(groupPosition, childPosition), Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
	}

}
