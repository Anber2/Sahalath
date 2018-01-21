package com.mawaqaa.sahalath.aacustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aacustomer.Data.RestaurantData;
import com.mawaqaa.sahalath.aacustomer.adapters.RestaurantListAdapter;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.interfaces.BottomBarHandlingUtility;

import java.util.ArrayList;

/**
 * Created by anson on 3/9/2017.
 */

public class DynamicSearchFragment extends SahalathBaseFragment implements View.OnClickListener {
    public SahalathBaseActivity Activity;
    Button btnSearch;
    TextView textViewSearchResultCount;
    ListView listViewDynamicSearch;
    EditText editTextSearchKeyWord;
    ArrayList<RestaurantData> restaurantDataArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_dynamic_search_page, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View v) {
        btnSearch = (Button) v.findViewById(R.id.btn_dynamic_search);
        textViewSearchResultCount = (TextView) v.findViewById(R.id.tv_result_count);
        editTextSearchKeyWord = (EditText) v.findViewById(R.id.et_search_keyword);
        listViewDynamicSearch = (ListView) v.findViewById(R.id.list_view_dynamic_search);

        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((SahalathBaseActivity) getActivity()).BaseFragment = this;
        BottomBarHandlingUtility bottomBarHandlingUtility = (BottomBarHandlingUtility) Activity;
        bottomBarHandlingUtility.searchPage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dynamic_search:
                viewSearchResults();
                break;
            default:
                break;
        }
    }

    private void viewSearchResults() {

        RestaurantListAdapter adapter = new RestaurantListAdapter(Activity, restaurantDataArrayList);
        listViewDynamicSearch.setAdapter(adapter);
        textViewSearchResultCount.setVisibility(View.VISIBLE);
        listViewDynamicSearch.setVisibility(View.VISIBLE);
    }
}
