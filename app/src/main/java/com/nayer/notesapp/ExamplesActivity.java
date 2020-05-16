package com.nayer.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamplesActivity extends AppCompatActivity {
    private static final String TAG = "ExamplesActivity";
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examples);
    }


    public void createaDocument(View view) {
       // Toast.makeText(this,"createaDocument",Toast.LENGTH_LONG).show();
       // FirebaseStorage.getInstance();

      /*  Map<String,Object>map = new HashMap<>();
        map.put("text","I wanna watch captain marvel again and again");
        map.put("isCompleted","false");
        map.put("created",new Timestamp(new Date()));

        firestore.collection("notes")
                .add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "onSuccess: Task is successful");
                        Log.d(TAG, "onSuccess: "+documentReference.getId());

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: Task is unsuccessful");

                    }
                });*/

      Map<String,Object>map = new HashMap<>();
      map.put("name","iPhone 11");
      map.put("price", 699);
      map.put("isAvailable",true);

      FirebaseFirestore.getInstance()
              .collection("products")
              .add(map)
              .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                  @Override
                  public void onSuccess(DocumentReference documentReference) {
                      Log.d(TAG, "onSuccess: Product is added succeccfully");
                      Log.d(TAG, "onSuccess: "+documentReference.getId());

                  }
              })
              .addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Log.e(TAG, "onFailure: ",e );
                  }
              });
    }

    public void readDocument(View view) {

        FirebaseFirestore.getInstance()
                .collection("products")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d(TAG, "onSuccess: We are getting the data");
                        List<DocumentSnapshot>snapshotList=queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot :snapshotList){
                            Log.d(TAG, "onSuccess: "+snapshot.getData().toString());
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ", e);

                    }
                });
    }

    public void updateDocument(View view) {
        Toast.makeText(this,"updateDocument",Toast.LENGTH_LONG).show();
    }

    public void deleteDocument(View view) {
        Toast.makeText(this,"deleteDocument",Toast.LENGTH_LONG).show();
    }

    public void getAllDocuments(View view) {
        Toast.makeText(this,"getAllDocuments",Toast.LENGTH_LONG).show();
    }

    public void getAllDocumentsWithRealtimeUpdates(View view) {
        Toast.makeText(this,"getAllDocumentsWithRealtimeUpdates",Toast.LENGTH_LONG).show();
    }
}
