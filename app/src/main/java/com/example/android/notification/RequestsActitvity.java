package com.example.android.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.android.notification.models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class RequestsActitvity extends AppCompatActivity {
    private RecyclerView mRequestsListView;
    private List<Requests> requestsList;

    private RequestsRecyclerAdapter requestsRecyclerAdapter;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_actitvity);
        mFirestore = FirebaseFirestore.getInstance();


        mRequestsListView = (RecyclerView) findViewById(R.id.requests_list_view);
        requestsList = new ArrayList<>();
        requestsRecyclerAdapter = new RequestsRecyclerAdapter(RequestsActitvity.this, requestsList);
        mRequestsListView.setHasFixedSize(true);
        mRequestsListView.setLayoutManager(new LinearLayoutManager(RequestsActitvity.this));
        mRequestsListView.setAdapter(requestsRecyclerAdapter);

        mFirestore = FirebaseFirestore.getInstance();

        String current_user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.e("Requests",current_user_id);


        mFirestore.collection("Users").document(current_user_id).collection("Notifications To").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {

                      //  String user_id = doc.getDocument().getId();
                       // Requests requests = new Requests();
                       // Log.e("user_id",user_id);

                      //  requests = doc.getDocument().toObject(requests.getClass());
                    Requests requests=doc.getDocument().toObject(Requests.class);
                    String msg = requests.getMessage();
                    Log.e("msg",msg);
                    requestsList.add(requests);

                        requestsRecyclerAdapter.notifyDataSetChanged();


                }

            }
        });

    }

}