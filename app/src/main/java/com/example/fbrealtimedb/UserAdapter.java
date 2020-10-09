package com.example.fbrealtimedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fbrealtimedb.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context mcontext;
    private List<Users> users_list;

    public UserAdapter(Context mcontext, List<Users> users_list) {
        this.mcontext = mcontext;
        this.users_list = users_list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root_view = LayoutInflater.from(mcontext).inflate(R.layout.viewlayout, parent, false);

        return new MyViewHolder(root_view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Users users = users_list.get(position);

        holder.tv_name.setText("Name " + users.getName());
        holder.tv_age.setText("Age " + users.getAge());

         holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View view) {
                 String id=users.getUid();
                 FirebaseDatabase.getInstance().getReference("Users").child(id)
                         .removeValue()
                         .addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {
                                 Toast.makeText(mcontext, "Data deleted", Toast.LENGTH_SHORT).show();
                             }
                         });
//                 Toast.makeText(mcontext, "UID "+id, Toast.LENGTH_SHORT).show();
                 return true;
             }
         });

    }

    @Override
    public int getItemCount() {
        return users_list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_age;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.res_view_name);
            tv_age = itemView.findViewById(R.id.res_view_age);

        }
    }
}
