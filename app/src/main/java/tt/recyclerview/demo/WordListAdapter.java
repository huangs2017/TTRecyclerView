package tt.recyclerview.demo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import tt.recyclerview.R;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private Context context;
    private List<String> words;

    WordListAdapter(Context context, List<String> words) {
        this.context = context;
        this.words = words;
    }

    @Override
    public int getItemCount() {
        Log.i("hunter", "getItemCount: " + words.size());
        return words.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        TextView txt_word;
        private WordViewHolder(View itemView) {
            super(itemView);
            txt_word = itemView.findViewById(R.id.textView);
        }
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter, parent, false);
        Log.i("hunter", "onCreateViewHolder");
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        String word = words.get(position);
        holder.txt_word.setText(word);
        Log.i("hunter", "onBindViewHolder");
    }

    void setWords(List<String> words) {
        this.words = words;
        notifyDataSetChanged();
    }

}


