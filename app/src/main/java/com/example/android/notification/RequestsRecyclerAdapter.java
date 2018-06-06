package com.example.android.notification;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.io.LineNumberInputStream;
import java.util.List;

/**
 * Created by Riya Khandelwal on 19-05-2018.
 */

 class RequestsRecyclerAdapter extends RecyclerView.Adapter<RequestsRecyclerAdapter.ViewHolder> {
 private List<Requests> requestsList;
 private FirebaseFirestore firebaseFirestore;
 private Context context;
 public RequestsRecyclerAdapter(Context context, List<Requests> requestsList){
     this.requestsList=requestsList;
     this.context=context;

 }
    @Override
    public RequestsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.requests_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RequestsRecyclerAdapter.ViewHolder holder, int position) {

     firebaseFirestore=FirebaseFirestore.getInstance();

     Requests test = new Requests();
     test = requestsList.get(position);
     String msg = test.getMessage();
     if(msg == null){
         Log.e("its null","0");
     }
     Log.e("msg",msg);
    String to_id=requestsList.get(position).getTo();
    Log.e("to-id",to_id);

    holder.user_message_view.setText(requestsList.get(position).getMessage());

    firebaseFirestore.collection("Users").document(to_id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

        @Override
        public void onSuccess(DocumentSnapshot documentSnapshot) {
            String user_name=documentSnapshot.getString("name");
            String user_image=documentSnapshot.getString("image");
            holder.user_name_view.setText(user_name);

            RequestOptions requestOptions=new RequestOptions();
            requestOptions.placeholder(R.drawable.download);

            Glide.with(context).setDefaultRequestOptions(requestOptions).load(user_image).into(holder.user_image_view);
        }
    });

    }

    @Override
    public int getItemCount() {
        return requestsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private View mView;
        private ImageView user_image_view;
        private TextView user_name_view;
        private TextView user_message_view;

        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            user_image_view=(ImageView)mView.findViewById(R.id.requests_list_image);
            user_name_view=(TextView)mView.findViewById(R.id.requests_list_name);
            user_message_view=(TextView)mView.findViewById(R.id.requests_list_message);

        }
    }
}
