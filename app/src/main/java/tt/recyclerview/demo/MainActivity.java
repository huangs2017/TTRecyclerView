package tt.recyclerview.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

import tt.recyclerview.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    WordListAdapter adapter;
    private List<String> words = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        recyclerView = findViewById(R.id.recyclerview);

        words.add("Hello");
        adapter = new WordListAdapter(this, words);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    int i;
    public void insertWord(View view) {
        Log.e("hunter", "insertWord------------------");

//        i++;
//        words.add("word" + i);
//        adapter.setWords(words);
    }

}
