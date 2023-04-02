package com.example.autobackup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private VideoAdapter mAdapter;

    private DatabaseReference databaseRef;
    private List<Upload> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mRecyclerView=findViewById(R.id.recycler_viewv);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads= new ArrayList<>();

        databaseRef= FirebaseDatabase.getInstance().getReference("Videos");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot: snapshot.getChildren()){
                    Upload upload= postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }
                mAdapter= new VideoAdapter(VideoActivity.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(VideoActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}