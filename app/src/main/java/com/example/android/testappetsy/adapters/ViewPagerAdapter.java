package com.example.android.testappetsy.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.testappetsy.fragments.FragmentSaved;
import com.example.android.testappetsy.fragments.search.mvp.FragmentSearch;

/**Adapter to display two tabs: a fragment for the search and a fragment with the list of saved products*/
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] tabTitles = {"Search", "Saved Products"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return (position == 0) ? new FragmentSearch() : new FragmentSaved();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
