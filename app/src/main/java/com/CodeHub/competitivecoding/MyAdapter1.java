package com.CodeHub.competitivecoding;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {
    private String[] mDataset;
    Context mCtx;
    List<Product1> productList;

    public MyAdapter1(Context mCtx, List<Product1> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

//    public MyAdapter(String[] myDataset) {
//        mDataset = myDataset;
//    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView,textView2,textView3,textView4;
        Button b1,b2;
        ImageView imageView;
        public MyViewHolder(View v) {
            super(v);
            textView =  v.findViewById(R.id.textViewTitle);
//            textView2 =  v.findViewById(R.id.textViewPrice);
//            textView3 =  v.findViewById(R.id.textViewRating);
            textView2 =  v.findViewById(R.id.textViewShortDesc);
            imageView=v.findViewById(R.id.imageView);
            b1=v.findViewById(R.id.explore);
            b2=v.findViewById(R.id.share);

        }
    }

//    public MyAdapter(String[] myDataset) {
//        mDataset = myDataset;
//    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter1.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.list_item, parent, false);
//        MyViewHolder vh = new MyViewHolder(v);
//        return vh;

        View view = LayoutInflater.from(mCtx).inflate(R.layout.list_item,
                parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.textView.setText(mDataset[position]);
//        holder.textView2.setText(mDataset[position]);
//        holder.textView3.setText(mDataset[position]);
//        holder.textView4.setText(mDataset[position]);
        final Product1 product = productList.get(position);

        holder.textView.setText(product.getTitle());
        holder.textView2.setText(product.getDetail());
        String s=product.getImage();
//        Picasso.get().load(s).into(holder.imageView);
        Glide.with(mCtx).load(s).thumbnail(Glide.with(mCtx).load(R.drawable.placeholder1)).into(holder.imageView);

        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "An Engineer is a problem solver and competitive programming is the best way to brush up your problem solving skills, learn competitive coding from the very first app of this type on Play Store." +
                        " Study "+product.getTitle()+"  from CodeHub - First Competitive Programming App on Play Store" +
                        " Download Now - "+"https://play.google.com/store/apps/details?" +
                        "id=com.CodeHub.competitivecoding";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                mCtx.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mCtx,Blog.class);
                i.putExtra("b",product.getBlog());
                i.putExtra("title",product.getTitle());
                mCtx.startActivity(i);
            }
        });
//        holder.textView3.setText(product.getDetail());
//        holder.textView4.setText(product.getDetail());

        holder.b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//            Toast.makeText(mCtx, product.getDetail(), Toast.LENGTH_SHORT).show();
                Intent i=new Intent(mCtx,Blog.class);
                i.putExtra("b",product.getBlog());
                i.putExtra("title",product.getTitle());
                mCtx.startActivity(i);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return productList.size();
        //return mDataset.length;
    }


//    class ProductViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView imageView;
//        TextView textViewTitle, textViewShortDesc, textViewRating, textviewPrice;
//
//        public ProductViewHolder(View itemView) {
//            super(itemView);
//
//            imageView = itemView.findViewById(R.id.imageView);
//            textViewTitle = itemView.findViewById(R.id.textViewTitle);
//            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
//            textViewRating = itemView.findViewById(R.id.textViewRating);
//            textviewPrice = itemView.findViewById(R.id.textViewPrice);
//
//        }
//    }

}

