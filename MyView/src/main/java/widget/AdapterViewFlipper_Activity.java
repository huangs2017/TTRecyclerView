package widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import tt.myview.R;

public class AdapterViewFlipper_Activity extends AppCompatActivity {

    int[] imageIds = new int[] {R.mipmap.chunv, R.mipmap.sheshou, R.mipmap.juxie, R.mipmap.shizi, R.mipmap.jinniu};
    AdapterViewFlipper flipper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter_view_flipper_activity);
        flipper = findViewById(R.id.flipper);
        flipper.setAdapter(adapter);
    }

    public void prev(View source) {
        flipper.showPrevious();
        flipper.stopFlipping();
    }

    public void next(View source) {
        flipper.showNext();
        flipper.stopFlipping();
    }

    public void auto(View source) {
        flipper.startFlipping();
    }


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
            ImageView imageView = new ImageView(AdapterViewFlipper_Activity.this);
            imageView.setImageResource(imageIds[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            return imageView;
        }
    };

}
