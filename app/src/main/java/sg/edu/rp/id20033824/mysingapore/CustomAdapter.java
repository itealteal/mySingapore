package sg.edu.rp.id20033824.mysingapore;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Island> versionList;


    public CustomAdapter(Context context, int resource, ArrayList<Island> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitleDisplay = rowView.findViewById(R.id.tvTitleDisplay);
        TextView tvYearDisplay = rowView.findViewById(R.id.tvYearDisplay);
        RatingBar ratingBar = rowView.findViewById(R.id.ratingBar);
        TextView tvSingerDisplay = rowView.findViewById(R.id.tvSingerDisplay);
        ImageView imageView = rowView.findViewById(R.id.imageView);

        // Obtain the Android Version information based on the position
        Island currentItem = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitleDisplay.setText(currentItem.getName());
        tvYearDisplay.setText(String.valueOf(currentItem.getSquare())+"KM");
        tvSingerDisplay.setText(currentItem.getDescription());
        ratingBar.setRating(currentItem.getStars());

        return rowView;
    }


}
