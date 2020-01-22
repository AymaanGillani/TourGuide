package com.example.android.tourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<List> {

    public ListAdapter(Context context, ArrayList<List> array){
        super(context,0,array);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        List l=getItem(position);
        ImageView image = (ImageView) listItemView.findViewById(R.id.ListImageView);
        if(l.hasImage()) {
            image.setImageResource(l.getImageId());
        }
        else {
            image.setVisibility(View.GONE);
        }
        TextView TitleTextView=(TextView)listItemView.findViewById(R.id.ListTextView);
        TitleTextView.setText(""+l.getTitle());
        TitleTextView.setBackgroundColor(getContext().getColor(l.getColorId()));
        TitleTextView.setTextColor(getContext().getColor(R.color.White));

        return listItemView;
    }
}
