package com.example.cardviewfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mNewsList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //REFERENCIANDO BASE DE DATOS FIREBASE
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Global");
        mDatabase.keepSynced(true);
        //INSTANCIANDO RECYCLER VIEW
        mNewsList=(RecyclerView)findViewById(R.id.newsrecyclerview);
        mNewsList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        //Esto permitirá cargar los elementos desde el más reciente al último
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        //Set Layout as Linear Layout
        mNewsList.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<News,NewsViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<News, NewsViewHolder>
                (News.class,R.layout.news_row,NewsViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(NewsViewHolder viewHolder, News model, int position) {
                viewHolder.setFecha(model.getFecha());
                viewHolder.setDescripcion(model.getDescripcion());
                viewHolder.setImage(getApplicationContext(),model.getImage());
            }
        };
        mNewsList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class NewsViewHolder extends  RecyclerView.ViewHolder
    {
        View mView;
        public NewsViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
        }
        public void setFecha(String fecha)
        {
            TextView post_fecha =(TextView)mView.findViewById(R.id.post_fecha);
            post_fecha.setText(fecha);
        }
        public void setDescripcion(String descripcion)
        {
            TextView post_descripcion= (TextView)mView.findViewById(R.id.post_descripcion);
            post_descripcion.setText(descripcion);
        }
        public void setImage(Context ctx, String image)
        {
            ImageView post_Image=(ImageView)mView.findViewById(R.id.post_image);
            Glide.with(ctx).load(image).into(post_Image);
        }
    }


}
