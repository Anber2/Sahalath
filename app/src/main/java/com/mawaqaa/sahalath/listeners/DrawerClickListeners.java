package com.mawaqaa.sahalath.listeners;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aacustomer.fragments.AboutUsFragment;
import com.mawaqaa.sahalath.aacustomer.fragments.ContactUsFragment;
import com.mawaqaa.sahalath.aacustomer.fragments.CustomerAccountPageFragment;
import com.mawaqaa.sahalath.aacustomer.fragments.HomeFragment;
import com.mawaqaa.sahalath.aacustomer.fragments.MyCartFragment;
import com.mawaqaa.sahalath.aacustomer.fragments.NotificationFragments;
import com.mawaqaa.sahalath.aacustomer.fragments.OffersFragment;
import com.mawaqaa.sahalath.aacustomer.fragments.OrderHistoryFragment;
import com.mawaqaa.sahalath.aacustomer.fragments.SignInFragment;
import com.mawaqaa.sahalath.aadriver.fragment.AcceptedOrdersFragment;
import com.mawaqaa.sahalath.aadriver.fragment.CancelledOrdersFragment;
import com.mawaqaa.sahalath.aadriver.fragment.DriverAccountDetails;
import com.mawaqaa.sahalath.aadriver.fragment.NavigationFragment;
import com.mawaqaa.sahalath.aadriver.fragment.PendingOrdersFragment;
import com.mawaqaa.sahalath.aaserviceboy.fragment.ServiceBoyAcceptedWorksListFragment;
import com.mawaqaa.sahalath.aaserviceboy.fragment.ServiceBoyAccountFragments;
import com.mawaqaa.sahalath.aaserviceboy.fragment.ServiceBoyAssignedWorkListFragment;
import com.mawaqaa.sahalath.aaserviceboy.fragment.ServiceBoyCanceledWorksListFragments;
import com.mawaqaa.sahalath.aaserviceboy.fragment.ServiceBoyCompletedWorksListFragments;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.utils.DrawerUtilities;

/**
 * Created by anson on 3/9/2017.
 */

public class DrawerClickListeners implements View.OnClickListener {
    public static String TAG = "DrawerClickListeners";
    Context mcontext;
    DrawerLayout mDrawerLayout;
    TextView textView;
    Activity mActivity;

    public DrawerClickListeners(Activity activity, Context context, DrawerLayout drawerLayout, TextView textView) {
        this.mcontext = context;
        this.textView = textView;
        this.mDrawerLayout = drawerLayout;
        mActivity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cst_drawer_home:
                Log.e(TAG, ">>>> drawer home clicked.");
                // if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new HomeFragment().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same HomeFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment mHomFragment = new HomeFragment();

                    SahalathBaseActivity.getSahalathBaseActivity().pushFragments(mHomFragment, false, true);
                }
                break;
            case R.id.tv_cst_drawer_about:
                Log.e(TAG, ">>>> drawer About clicked.");
                //    if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new AboutUsFragment().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same AboutUsFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment aboutUsFragment = new AboutUsFragment();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(aboutUsFragment.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(aboutUsFragment, false, true);
                    } else {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(aboutUsFragment, false, false);
                    }
                }
                break;
            case R.id.tv_cst_drawer_cart:
                Log.e(TAG, ">>>> drawer cart clicked.");
                // if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new MyCartFragment().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same CartFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment myCartFragment = new MyCartFragment();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(myCartFragment.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(myCartFragment, false, true);
                    } else {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(myCartFragment, false, true);
                    }
                }
                break;
            case R.id.tv_cst_drawer_account:
                Log.e(TAG, ">>>> drawer Account clicked.");
                //if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new CustomerAccountPageFragment().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same AccountFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment customerAccountPageFragment = new CustomerAccountPageFragment();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(customerAccountPageFragment.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(customerAccountPageFragment, false, true);
                    } else {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(customerAccountPageFragment, false, false);
                    }
                }
                break;
            case R.id.tv_cst_drawer_history:
                Log.e(TAG, ">>>> drawer Order History clicked.");
                // if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new OrderHistoryFragment().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same OrderHistoryFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment orderPageFragment = new OrderHistoryFragment();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(orderPageFragment.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(orderPageFragment, false, true);
                    } else {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(orderPageFragment, false, false);
                    }
                }
                break;
            case R.id.tv_cst_drawer_registration:
                Log.e(TAG, ">>>> drawer SigninRegister clicked.");
                // if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new SignInFragment().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same SigninFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment signInFragment = new SignInFragment();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(signInFragment.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(signInFragment, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(signInFragment, false, false);
                    }
                }
                break;
            case R.id.tv_cst_drawer_contact_us:
                Log.e(TAG, ">>>> drawer OContact Us clicked.");
                // if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new ContactUsFragment().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same ContactUSFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment contactUsFragment = new ContactUsFragment();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(contactUsFragment.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(contactUsFragment, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(contactUsFragment, false, false);
                    }
                }
                break;
            case R.id.tv_cst_drawer_notification:
                Log.e(TAG, ">>>> drawer Notification clicked.");
                //   if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new NotificationFragments().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same NOtification >> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment notificationFragments = new NotificationFragments();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(notificationFragments.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(notificationFragments, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(notificationFragments, false, false);
                    }
                }
                break;
            case R.id.tv_cst_drawer_offers:
                //  if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new OffersFragment().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same HomeFragment>> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment mOffersFragment = new OffersFragment();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(mOffersFragment.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(mOffersFragment, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(mOffersFragment, false, false);
                    }
                }
                break;

            case R.id.tv_cst_drawer_logout:
                break;

            /*****************************************************************************************************************************/

            case R.id.tv_driver_drawer_pending_order:
                Log.e(TAG, ">>>> drawer Driver pending order clicked.");
                // if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new PendingOrdersFragment().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same  Driver pending order  >> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment pendingOrdersFragment = new PendingOrdersFragment();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(pendingOrdersFragment.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(pendingOrdersFragment, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(pendingOrdersFragment, false, false);
                    }
                }
                break;
            case R.id.tv_driver_drawer_navigation_orders:
                Log.e(TAG, ">>>> drawer Driver pending order clicked.");
                //   if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new com.mawaqaa.sahalath.aadriver.fragment.NavigationFragment().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same  Driver pending order  >> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment navigationOrdersFragment = new NavigationFragment();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(navigationOrdersFragment.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(navigationOrdersFragment, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(navigationOrdersFragment, false, false);
                    }
                }
                break;
            case R.id.tv_driver_drawer_accepted_orders:
                Log.e(TAG, ">>>> drawer  Driver Accepted order  clicked.");
                //   if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new AcceptedOrdersFragment().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same  Driver Accepted order  >> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment acceptedOrdersFragment = new AcceptedOrdersFragment();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(acceptedOrdersFragment.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(acceptedOrdersFragment, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(acceptedOrdersFragment, false, false);
                    }
                }
                break;
            case R.id.tv_driver_drawer_canceled_orders:
                Log.e(TAG, ">>>> drawer  Driver Cancelled order  clicked.");
                //  if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new CancelledOrdersFragment().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same  Driver Canelled order  >> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment cancelledOrdersFragment = new CancelledOrdersFragment();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(cancelledOrdersFragment.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(cancelledOrdersFragment, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(cancelledOrdersFragment, false, false);
                    }
                }
                break;
            case R.id.tv_driver_drawer_my_account:
                Log.e(TAG, ">>>> drawer  Driver Account  clicked.");
                //  if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new DriverAccountDetails().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same  DriverAccount  >> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment driverAccountDetails = new DriverAccountDetails();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(driverAccountDetails.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(driverAccountDetails, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(driverAccountDetails, false, false);
                    }
                }
                break;
            /**************************************************************************************************************/
            case R.id.tv_sboy_drawer_home:
                Log.e(TAG, ">>>> drawer  Driver Accepted order  clicked.");
                //   if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new ServiceBoyAssignedWorkListFragment().getClass().getName()) {
                /**In same  Driver Accepted order  >> closing drawer*/
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment serviceBoyHomeFragment = new ServiceBoyAssignedWorkListFragment();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(serviceBoyHomeFragment.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(serviceBoyHomeFragment, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(serviceBoyHomeFragment, false, false);
                    }
                }
                break;
         /*   case R.id.tv_sboy_pending_request:
                Log.e(TAG, ">>>> drawer  Driver Accepted order  clicked.");
                if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new PendingRequestsFragment().getClass().getName()) {
                    *//**In same  Driver Accepted order  >> closing drawer*//*
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    *//**Starting fragment transaction*//*
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment serviceRequestsFragment = new PendingRequestsFragment();
                    SahalathBaseActivity.getSahalathBaseActivity().pushFragments(serviceRequestsFragment, false, true);
                }
                break;
*/
            case R.id.tv_sboy_drawer_accepted_works:
                Log.e(TAG, ">>>> drawer  Driver Accepted order  clicked.");
                //   if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new ServiceBoyAcceptedWorksListFragment().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same  Driver Accepted order  >> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment acceptedWorksListFragment = new ServiceBoyAcceptedWorksListFragment();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(acceptedWorksListFragment.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(acceptedWorksListFragment, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(acceptedWorksListFragment, false, false);
                    }
                }
                break;

            case R.id.tv_sboy_drawer_complete_works:
                Log.e(TAG, ">>>> drawer  Serviceboy completed drawer clicked.");
                // if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new ServiceBoyCompletedWorksListFragments().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same  Driver Accepted order  >> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment completedWorksListFragments = new ServiceBoyCompletedWorksListFragments();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(completedWorksListFragments.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(completedWorksListFragments, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(completedWorksListFragments, false, false);
                    }
                }
                break;

            case R.id.tv_sboy_drawer_canceled_works:
                Log.e(TAG, ">>>> drawer Serviceboy CAnceled Drawer  clicked.");
                // if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new ServiceBoyCanceledWorksListFragments().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same  Driver Accepted order  >> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment canceledWorksListFragments = new ServiceBoyCanceledWorksListFragments();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(canceledWorksListFragments.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(canceledWorksListFragments, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(canceledWorksListFragments, false, false);
                    }
                }
                break;
            case R.id.tv_sboy_drawer_my_account:
                Log.e(TAG, ">>>> drawer Serviceboy CAnceled Drawer  clicked.");
                //   if (SahalathMainActivity.getSahalathBaseActivity().getCurrentFragment() == new ServiceBoyAccountFragments().getClass().getName()) {
                if (AppConstants.CURRENT_FRAGMENT.getClass().getName() == new HomeFragment().getClass().getName()) {
                    /**In same  Driver Accepted order  >> closing drawer*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Log.e(TAG, "Same Fragment");
                } else {
                    /**Starting fragment transaction*/
                    DrawerUtilities.closeDrawerVeiw(mcontext, mDrawerLayout);
                    Fragment serviceBoyAccountFragments = new ServiceBoyAccountFragments();
                    android.app.FragmentManager mFragmentManager = mActivity.getFragmentManager();
                    android.app.Fragment mFragment = mFragmentManager.findFragmentByTag(serviceBoyAccountFragments.getClass().getName());
                    if (mFragment == null) {
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(serviceBoyAccountFragments, false, true);
                    }else{
                        SahalathBaseActivity.getSahalathBaseActivity().pushFragments(serviceBoyAccountFragments, false, false);
                    }
                }
                break;

            default:
                break;
        }
    }
}
