package com.example.android.notification.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.notification.R;
import com.example.android.notification.UsersRecyclerAdapter;
import com.example.android.notification.models.Users;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UsersListActivity extends AppCompatActivity {
    private RecyclerView mUsersListView;
    private List<Users> usersList;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_5);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getColor(R.color.toolbar));

        mFirestore=FirebaseFirestore.getInstance();
        mUsersListView=(RecyclerView) findViewById(R.id.users_list_view);
        usersList=new ArrayList<>();
        usersRecyclerAdapter=new UsersRecyclerAdapter(UsersListActivity.this, UsersListActivity.this,usersList);
        mUsersListView.setHasFixedSize(true);
        mUsersListView.setLayoutManager(new LinearLayoutManager(UsersListActivity.this));
        mUsersListView.setAdapter(usersRecyclerAdapter);



    }
    @Override
    public void onStart() {
        super.onStart();
        usersList.clear();
        mFirestore.collection("Users").addSnapshotListener(UsersListActivity.this,new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                for(DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){
                    if(doc.getType()==DocumentChange.Type.ADDED){

                        String user_id=doc.getDocument().getId();

                        Users users=doc.getDocument().toObject(Users.class).withId(user_id);
                        usersList.add(users);
                        usersRecyclerAdapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               // usersRecyclerAdapter.getFilter
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
