package EventDispatch.ListView_ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import tt.myview.R;

// ListView嵌套ViewPager滑动冲突解决（ViewPager区域上下滑动不影响ListView）
public class SlideActivity extends Activity {

	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_activity);

		lv = findViewById(R.id.lv);

		View header = LayoutInflater.from(this).inflate(R.layout.viewpage_layout, null);
		MyViewPager vp = header.findViewById(R.id.vp);
		vp.setNestedParent((ViewGroup)vp.getParent());
//		vp.setNestedParent((ViewGroup)header);

		MyPagerAdapter pagerAdapter = new MyPagerAdapter(this);
		vp.setAdapter(pagerAdapter);
		lv.addHeaderView(header);

		// lv的数据
		ArrayList<String> arrayList = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			arrayList.add(i + "  -------------");
		}
		ArrayAdapter adapter = new ArrayAdapter(this, R.layout.one_txt, R.id.textView1, arrayList);
		lv.setAdapter(adapter);
	}

}
