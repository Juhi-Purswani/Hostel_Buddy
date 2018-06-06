package com.example.android.notification;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.notification.Activities.Word;
import com.example.android.notification.Activities.WordAdapter;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {
    private Button button;
    int schoolName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        final ArrayList<Word> word = new ArrayList<>();
        word.add(new Word("XYZ", "Hostel Committee 2016-IPG", R.drawable.profile, 785461212));
        word.add(new Word("LMN", "Hostel Committee 2015-IPG", R.drawable.profile, 725468915));
        word.add(new Word("XYZ", "Mess Committee 2016-IPG", R.drawable.profile,785445212));
        word.add(new Word("XYZ", "Hostel Committee 2015-IPG", R.drawable.profile,785462125));
        WordAdapter adapter = new WordAdapter(ContactsActivity.this, word);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position, long id)
            {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                Word words=word.get(position);
                int p=words.getAudioResourceId();
                callIntent.setData(Uri.parse("tel:"+p));

                if (ActivityCompat.checkSelfPermission(ContactsActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }

        });
        // add PhoneStateListener
//        button = (Button) findViewById(R.id.phone_call);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View arg0) {
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:0377778888"));
//
//                if (ActivityCompat.checkSelfPermission(ContactsActivity.this,
//                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    return;
//                }
//                startActivity(callIntent);
//            }
//        });
    }
}



