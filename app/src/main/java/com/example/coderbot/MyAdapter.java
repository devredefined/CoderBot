package com.example.coderbot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataset;
    Context mCtx;
    List<Product> productList;

    public MyAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

//    public MyAdapter(String[] myDataset) {
//        mDataset = myDataset;
//    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView,textView2,textView3,textView4;
        public MyViewHolder(View v) {
            super(v);
            textView =  v.findViewById(R.id.textViewShortDesc);
            textView2 =  v.findViewById(R.id.textViewPrice);
            textView3 =  v.findViewById(R.id.textViewRating);
            textView4 =  v.findViewById(R.id.textViewTitle);
        }
    }

//    public MyAdapter(String[] myDataset) {
//        mDataset = myDataset;
//    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
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
        Product product = productList.get(position);

        holder.textView.setText(product.getTitle());
        holder.textView2.setText(product.getDetail());
        holder.textView3.setText(product.getImage());
        holder.textView4.setText(product.getDetail());


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