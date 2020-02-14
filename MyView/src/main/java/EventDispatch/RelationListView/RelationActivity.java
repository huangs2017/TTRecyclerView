package EventDispatch.RelationListView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import tt.myview.R;

// 联动的ListView
public class RelationActivity extends Activity {
	private RelationListView lv1;
	private RelationListView lv2;
	
	private String[] data1 = new String[] { "lv1", "lv1", "lv1", "lv1", "lv1", "lv1", "lv1", "lv1", "lv1",
			"lv1", "lv1", "lv1", "lv1", "lv1", "lv1", "lv1", "lv1", "lv1", "lv1", "lv1", "lv1", "lv1"};
	private String[] data2 = new String[] { "lv2", "lv2", "lv2", "lv2", "List2", "lv2", "lv2", "lv2", "lv2",
			"lv2", "lv2", "lv2", "lv2", "lv2", "lv2", "lv2", "lv2", "lv2", "lv2", "lv2", "lv2", "lv2",};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relation_activity);
		
		lv1 = findViewById(R.id.lv1);
		lv2 = findViewById(R.id.lv2);
		
		lv1.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data1));
		lv2.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data2));
		lv1.setRelatedListView(lv2);
//		lv2.setRelatedListView(lv1);
	}
}
