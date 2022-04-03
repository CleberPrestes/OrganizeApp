package cleberprestes.utfpr.com.organizaaula;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class AulasViewAdapter extends ArrayAdapter<Aula> {

    public AulasViewAdapter(@NonNull Context context, ArrayList<Aula> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        }

        Aula currentNumberPosition = getItem(position);

        ImageView numbersImage = currentItemView.findViewById(R.id.imageView);
        assert currentNumberPosition != null;
        numbersImage.setImageResource(R.drawable.logo_utfpr);

        TextView textView1 = currentItemView.findViewById(R.id.textView1);
        textView1.setText(currentNumberPosition.getDia());

        TextView textView2 = currentItemView.findViewById(R.id.textView2);
        textView2.setText(currentNumberPosition.getPeriodo());

        TextView textView5 = currentItemView.findViewById(R.id.textView5);
        textView5.setText(currentNumberPosition.getAula());

        TextView textView6 = currentItemView.findViewById(R.id.textView6);
        textView6.setText(currentNumberPosition.getDisciplina());

        return currentItemView;
    }
}
