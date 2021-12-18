package com.geekcommunity.geekplay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListOfVideos extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_videos);
        reference = FirebaseDatabase.getInstance().getReference("videos");
        recyclerVideo = findViewById(R.id.recycler_video_list);
        recyclerVideo.setLayoutManager(new LinearLayoutManager(this));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getModel();
    }

    private void getModel() {
        List<Modal> modalList = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snap) {
                for(DataSnapshot ds : snap.getChildren()) {
                    modalList.add(ds.getValue(Modal.class));
                }
                VideoAdapter adapter = new VideoAdapter(modalList);
                recyclerVideo.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}