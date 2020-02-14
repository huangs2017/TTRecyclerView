package widget;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import tt.myview.R;

public class SearchView_Activity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView sv;
    private ListView lv;
    private final String[] mStrings = {"aaaaa", "bbbbbb", "cccccc"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchview_activity);
        lv = findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStrings));
        lv.setTextFilterEnabled(true);
        sv = findViewById(R.id.sv);
        sv.setIconifiedByDefault(false);//
        sv.setOnQueryTextListener(this);
        sv.setSubmitButtonEnabled(true);//
        sv.setQueryHint("查找");
    }

    @Override
    public boolean onQueryTextChange(String newText) {
//        if (TextUtils.isEmpty(newText)) {
//            lv.clearTextFilter();
//        } else {
//            lv.setFilterText(newText);
//        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(this, "您的选择是:" + query, Toast.LENGTH_SHORT).show();
        return false;
    }

}