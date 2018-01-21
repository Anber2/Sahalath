package com.mawaqaa.sahalath.aaactivities;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aacustomer.Data.GalleryData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by anson on 4/12/2017.
 */

public class GalleryViewPagerAdapter extends PagerAdapter {
    private String TAG = "GalleryViewPagerAdapter";
    private ArrayList<GalleryData> galleryDatas;
    private Context context;
    LayoutInflater inflater;
    ImageView imageView;
    private int position;

    public GalleryViewPagerAdapter(Context context, ArrayList<GalleryData> galleryDatas, int position) {
        this.context = context;
        this.galleryDatas = galleryDatas;
        this.position = position;
    }



    @Override
    public int getCount() {
        return galleryDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.customer_gallery_item, null);
        imageView = (ImageView) itemView.findViewById(R.id.img_gallery);
        Picasso.with(context).load(galleryDatas.get(position).imageUrl).placeholder(R.drawable.bg_loading).into(imageView);
        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((LinearLayout) object);

    }

}
