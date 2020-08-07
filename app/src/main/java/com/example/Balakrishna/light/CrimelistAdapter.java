package com.example.Balakrishna.light;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CrimelistAdapter extends RecyclerView.Adapter<CrimelistAdapter.ViewHolder>{
    private ArrayList<String> main = new ArrayList<>();
    private ArrayList<String> sub = new ArrayList<>();
    public static int VALUE=0;
    private Context Con;
    public CrimelistAdapter(Context c, ArrayList<String> m,ArrayList<String> s){
        Con = c;
        main = m;
        sub = s;
    }
    public void removeItem(int position) {
        main.remove(position);
        sub.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclercrimelist,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.Main.setText(main.get(position));
        holder.Sub.setText(sub.get(position));
        holder.Par.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICK","Position:"+main.get(position));
                if(GLOBAL.KEY==1 && CrimeListActivity.flag==1){
                VALUE = position;
                Intent intent = new Intent(Con,CrimeDetActivity.class);
                intent.putExtra("Position",position);
                Con.startActivity(intent);}
                else if(GLOBAL.KEY==2){
                    VALUE = position;
                    Log.d("TAG","CALLED");
                    Intent intent = new Intent(Con,ForceListActivity.class);
                    intent.putExtra("Position",sub.get(position));
                    Con.startActivity(intent);
                }
                else if(GLOBAL.KEY==0){
                    VALUE = position;
                    Intent intent = new Intent(Con,CrimeDetFavActivity.class);
                    intent.putExtra("Position",position);
                    Con.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return main.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Main, Sub;
        LinearLayout Par;
        public ViewHolder(View itemView){
            super(itemView);
            Main = itemView.findViewById(R.id.textView);
            Sub = itemView.findViewById(R.id.textView2);
            Par = itemView.findViewById(R.id.parent_layout);

        }
    }
}
