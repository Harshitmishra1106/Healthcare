package com.example.healthcare;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class History extends AppCompatActivity {


    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    StorageReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ImageView downLd = findViewById(R.id.downLd);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Dashboard.class);
                startActivity(i);
            }
        });
        HistModel[] histModel = new HistModel[] {
                new HistModel("12/10/22","2:40","96.4°C","76","96"),
                new HistModel("13/10/22","8:15","97.1°C","79","95.2"),
                new HistModel("16/10/22","10:20","95.3°C","89","99"),
                new HistModel("19/10/22","9:45","98.7°C","93","98"),
                new HistModel("20/10/22","7:12","106.2°C","85","97.3"),
                new HistModel("24/10/22","11:13","99.5°C","94","97"),
                new HistModel("27/10/22","13:04","98.3°C","78","98"),
                new HistModel("05/11/22","17:15","100.2°C","83","96"),
                new HistModel("08/11/22","6:41","101.8°C","68","94.4"),
                new HistModel("13/11/22","19:18","97.6°C","72","97.5"),
                new HistModel("16/11/22","12:40","98.3°C","91","95"),
                new HistModel("17/11/22","21:35","104.7°C","98","96.3"),
        };
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        HistAdapter adapter = new HistAdapter(histModel);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        downLd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download();
            }
        });


    }
    public  void download()
    {

        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("health.pdf");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadfile(History.this,"health",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(History.this, "File download failed 🚨", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void downloadfile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {

        DownloadManager downloadManager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory,fileName +fileExtension);

        downloadManager.enqueue(request);

    }




}