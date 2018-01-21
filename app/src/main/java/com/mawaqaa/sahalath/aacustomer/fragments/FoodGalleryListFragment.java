package com.mawaqaa.sahalath.aacustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aacustomer.Data.GalleryData;
import com.mawaqaa.sahalath.aacustomer.adapters.GalleryListAdapter;
import com.mawaqaa.sahalath.contants.AppConstants;

import java.util.ArrayList;

/**
 * Created by anson on 4/10/2017.
 */

public class FoodGalleryListFragment extends SahalathBaseFragment {
    private String TAG = "FoodGalleryListFragment";
    ArrayList<GalleryData> galleryDatas = new ArrayList<GalleryData>();
    RecyclerView recyclerView;
    ViewPager viewPager;
    ImageButton imageButtonGalleryClose;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();
        galleryDatas.add(new GalleryData("1", "http://www.mostluxuriouslist.com/wp-content/uploads/2015/11/Pizza.jpg"));
        galleryDatas.add(new GalleryData("2", "http://www3.pictures.zimbio.com/mp/wwj1hrnad01x.jpg"));
        galleryDatas.add(new GalleryData("3", "http://www.got-it.in/blog/wp-content/uploads/2016/05/samosa-gotit.jpg"));
        galleryDatas.add(new GalleryData("4", "https://i.ytimg.com/vi/R6ztX7C7YR0/maxresdefault.jpg"));
        galleryDatas.add(new GalleryData("5", "https://media-cdn.tripadvisor.com/media/photo-s/05/7e/18/e0/kfc.jpg"));
        galleryDatas.add(new GalleryData("6", "https://www.skymetweather.com/themes/skymet/images/gallery/toplists/20-Must-Try-Food-Items-in-Delhi/13.jpg"));
        galleryDatas.add(new GalleryData("7", "http://amazingindiablog.in/wp-content/uploads/2016/01/Aaloo-Ka-Paratha.jpg"));
        galleryDatas.add(new GalleryData("8", "http://i.imgur.com/wntKchU.jpg"));
        galleryDatas.add(new GalleryData("9", "http://s3.india.com/travel/wp-content/uploads/2015/04/Nihari-kulcha.jpg"));
        galleryDatas.add(new GalleryData("10", "http://im.hunt.in/cg/thane/City-Guide/seafoodinthane.jpg"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_food_gallery, container, false);
        SahalathMainActivity.showUpButton();
        initView(v);
        loadGalleryItems();
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void loadGalleryItems() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        GalleryListAdapter adapter = new GalleryListAdapter(getContext(), galleryDatas, viewPager);
        recyclerView.setAdapter(adapter);
    }

    private void initView(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.gallery_list);
        viewPager = (ViewPager) v.findViewById(R.id.gallery_view_pager);

    }
}
