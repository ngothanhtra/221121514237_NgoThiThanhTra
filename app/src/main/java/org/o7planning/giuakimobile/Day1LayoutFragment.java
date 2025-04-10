package org.o7planning.giuakimobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class Day1LayoutFragment extends Fragment {

    public Day1LayoutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_day1_layout, container, false);


        TextView buttonLike = view.findViewById(R.id.buttonLike);
        TextView buttonComment = view.findViewById(R.id.buttonComment);
        TextView buttonShare = view.findViewById(R.id.buttonShare);


        buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Bạn đã nhấn Like!", Toast.LENGTH_SHORT).show();
            }
        });


        buttonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Bạn đã nhấn Comment!", Toast.LENGTH_SHORT).show();
            }
        });


        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Bạn đã nhấn Share!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}