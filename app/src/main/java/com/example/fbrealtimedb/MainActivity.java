package com.example.fbrealtimedb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fbrealtimedb.Model.Users;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG ="Mytags" ;
    EditText edit_name, edit_age;
    Button submit_buttonn;
    RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<Users> usersList;

    private ChildEventListener m_child_event;

    private FirebaseDatabase fbdatebase_ref;
    private DatabaseReference db_Node_Reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializing_fun();
        fbdatebase_ref = FirebaseDatabase.getInstance();
        db_Node_Reference = fbdatebase_ref.getReference("Users");

        usersList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter=new UserAdapter(this,usersList);
        recyclerView.setAdapter(userAdapter);

        m_child_event=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Users users=dataSnapshot.getValue(Users.class);
                Log.d(TAG, "onChildAdded: Name "+users.getName());
                Log.d(TAG, "onChildAdded: Age "+users.getAge());
                usersList.add(users);
                userAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        db_Node_Reference.addChildEventListener(m_child_event);

    }

    private void send_data() {

        String name = edit_name.getText().toString();
        int age = Integer.parseInt(edit_age.getText().toString());

        Users person = new Users(name, age);

        String push_key = db_Node_Reference.push().getKey();

        db_Node_Reference.child(push_key).setValue(person);

    }

    public void initializing_fun() {

        edit_age = (EditText) findViewById(R.id.editage);
        edit_name = (EditText) findViewById(R.id.editname);
        submit_buttonn = (Button) findViewById(R.id.submitbutton);
        recyclerView=(RecyclerView)findViewById(R.id.resviewid);
        submit_buttonn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.submitbutton:
                send_data();
                edit_name.setText("");
                edit_age.setText(" ");
                break;
        }
    }
}
