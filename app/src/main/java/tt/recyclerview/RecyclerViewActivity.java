package tt.recyclerview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity);
        recyclerView = findViewById(R.id.recycler_view);
        Adapter adapter = new AdapterImpl(this);
        recyclerView.setAdapter(adapter);
    }

}
