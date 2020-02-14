package widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.StackView;
import androidx.appcompat.app.AppCompatActivity;
import tt.myview.R;

public class StackView_Activity extends AppCompatActivity {

    StackView stackView;
    int[] imageIds = new int[] {R.mipmap.chunv, R.mipmap.sheshou, R.mipmap.juxie,  R.mipmap.shizi, R.mipmap.jinniu};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stackview_activity);
        stackView = findViewById(R.id.mStackView);

        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return imageIds.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView = new ImageView(StackView_Activity.this);
                imageView.setImageResource(imageIds[position]);
                return imageView;
            }
        };

        stackView.setAdapter(adapter);
    }

    public void prev(View view) {
        stackView.showPrevious();
    }

    public void next(View view) {
        stackView.showNext();
    }

}