package com.example.myshixun.tab_viewpage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myshixun.mainbody.firstpage.Recommend_viewpage_fregment;

import java.util.ArrayList;
import java.util.List;

public class first_viewpage_adapter extends FragmentPagerAdapter {

    List<Fragment> fragments=new ArrayList<>();
    List<String> titles=new ArrayList<>();

    public first_viewpage_adapter(FragmentManager fm, int behavior, List<Fragment> fragments, List<String> titles) {
        super(fm, behavior);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        titles.set(0,"直播");
        titles.set(1,"推荐");
        titles.set(2,"热门");
        titles.set(3,"追番");
        titles.set(4,"影视");
        titles.set(5,"建党百年");
        return titles.get(position);
    }
}
