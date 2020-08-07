package com.example.Balakrishna.light;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ForcelistAdapter extends RecyclerView.Adapter<ForcelistAdapter.ViewHolder>{
    private ArrayList<String> main = new ArrayList<>();
    private ArrayList<String> sub = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    public static int VALUE=0;
    private Context Con;
    public ForcelistAdapter(Context c, ArrayList<String> m,ArrayList<String> s,ArrayList<String> x){
        Con = c;
        main = m;
        sub = s;
        title = x;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_forcelistadapter,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.Main.setText("Engagement URL: "+main.get(position));
        holder.Sub.setText("Type: "+sub.get(position));
        holder.Title.setText("Title: "+title.get(position));
    }

    @Override
    public int getItemCount() {
        return main.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Main, Sub, Title;
        LinearLayout Par;
        public ViewHolder(View itemView){
            super(itemView);
            Main = itemView.findViewById(R.id.FLA_URL);
            Sub = itemView.findViewById(R.id.FLA_TYPE);
            Title = itemView.findViewById(R.id.FLA_title);
            Par = itemView.findViewById(R.id.flalay);
        }
    }
}
