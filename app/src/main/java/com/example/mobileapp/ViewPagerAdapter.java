package com.example.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {

    // Context object
    Context context;

    // Array of images
    int[] images;

    // Layout Inflater
    LayoutInflater mLayoutInflater;

    // Viewpager Constructor
    public ViewPagerAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // return the number of images
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.item, container, false);
        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);

        // setting the image in the imageView
        imageView.setImageResource(images[position]);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==0){
                    Toast.makeText(context, "Image 1 clicked", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context,Staff.class);
                        context.startActivity(intent);

                }else if (position==1){
                    Toast.makeText(context, "Image 2 clicked", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,Staff.class);
                    context.startActivity(intent);
                }else if (position==2){
                    Toast.makeText(context, "Image 3 clicked", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,Staff.class);
                    context.startActivity(intent);
                }else if (position==3){
                    Toast.makeText(context, "Image 4 clicked", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,Staff.class);
                    context.startActivity(intent);
                }else if (position==4){
                    Toast.makeText(context, "Image 5 clicked", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,Staff.class);
                    context.startActivity(intent);
                }else if (position==5){
                    Toast.makeText(context, "Image 6 clicked", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,Staff.class);
                    context.startActivity(intent);
                }else if (position==6){
                    Toast.makeText(context, "Image 7 clicked", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,Staff.class);
                    context.startActivity(intent);
                }else if (position==7){
                    Toast.makeText(context, "Image 8 clicked", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,Staff.class);
                    context.startActivity(intent);
                }else if (position==8){
                    Toast.makeText(context, "Image 9 clicked", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,Staff.class);
                    context.startActivity(intent);
                }else if (position==9){
                    Toast.makeText(context, "Image 10 clicked", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,Staff.class);
                    context.startActivity(intent);
                }
            }
        });
        Objects.requireNonNull(container).addView(itemView);

        return itemView;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }
}