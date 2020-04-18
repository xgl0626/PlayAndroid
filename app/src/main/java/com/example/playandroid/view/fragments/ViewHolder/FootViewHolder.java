package com.example.playandroid.view.fragments.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playandroid.R;

/**
 * @author 徐国林
 * @data 2020/4/4
 * @decription
 */
public class FootViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private boolean loading;
    public FootViewHolder(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.load);
    }
}
