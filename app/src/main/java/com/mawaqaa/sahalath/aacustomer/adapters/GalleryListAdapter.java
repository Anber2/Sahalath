package com.mawaqaa.sahalath.aacustomer.adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.GalleryViewPagerAdapter;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aacustomer.Data.GalleryData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by anson on 4/12/2017.
 */

public class GalleryListAdapter extends RecyclerView.Adapter<GalleryListAdapter.ViewHolder> {
    private ArrayList<GalleryData> galleryDatas;
    private Context context;
    private ViewPager viewPager;

    public GalleryListAdapter(Context context, ArrayList<GalleryData> galleryDatas, ViewPager viewPager) {
        this.context = context;
        this.galleryDatas = galleryDatas;
        this.viewPager = viewPager;
    }

    @Override
    public GalleryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_customer_gallery_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(GalleryListAdapter.ViewHolder holder, final int position) {
        Picasso.with(context).load(galleryDatas.get(position).imageUrl).placeholder(R.drawable.bg_loading).into(holder.img_gallery);
        holder.img_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setVisibility(View.VISIBLE);
                SahalathMainActivity.isGalleryViewPagerVisible = true;
                GalleryViewPagerAdapter adapter = new GalleryViewPagerAdapter(context, galleryDatas, position);
                viewPager.setAdapter(adapter);
                viewPager.setCurrentItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_gallery;

        public ViewHolder(View view) {
            super(view);
            img_gallery = (ImageView) view.findViewById(R.id.img_gallery_list_item);
        }
    }
}