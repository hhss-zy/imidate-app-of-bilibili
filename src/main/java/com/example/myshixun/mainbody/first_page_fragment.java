package com.example.myshixun.mainbody;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.myshixun.R;
import com.example.myshixun.mainbody.firstpage.Recommend_viewpage_fregment;
import com.example.myshixun.tab_viewpage.Listfragment;
import com.example.myshixun.tab_viewpage.first_viewpage_adapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class first_page_fragment extends Fragment {
    private View view,view1;
    Contentctivity contentctivity;
    FragmentManager fragmentManager;
    ViewPager viewPager;
    TabLayout tabLayout;
    List<Fragment> fragments=new ArrayList<>();
    List<String> titles=new ArrayList<>();
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView imageView;
    private first_page_fragment firstpage_fragment;
    private recommend_fragment recommend_fragment;
    private me_fragment me_fragment;
    private message_frament message_frament;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.first_page_fragment,container,false);
        }
//        User user=new User("s","ss");
//        Event event=new Event("return",user);
//        EventBus.getDefault().post(event);
        tabLayout=view.findViewById(R.id.tablayout_body);
        navigationView=view.findViewById(R.id.tab_NavigationView);
        viewPager=view.findViewById(R.id.tab_viewpage);
        drawerLayout=view.findViewById(R.id.tab_drawerlayout);
        imageView=view.findViewById(R.id.head_sculpture);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        if (fragments.size()==0||titles.size()==0){

            for (int i=0;i<3;i++){
                Recommend_viewpage_fregment recommend_viewpage_fregment=new Recommend_viewpage_fregment();
                Listfragment listFragment=new Listfragment();
                fragments.add(listFragment);
                fragments.add(recommend_viewpage_fregment);
            }
            for (int i=0;i<6;i++){
                titles.add("");
            }
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){
                    case R.id.me:
                        fragmentManager=getActivity().getSupportFragmentManager();
                        if (me_fragment!=null){
                            fragmentManager.beginTransaction().replace(R.id.content_fragment,me_fragment).commitAllowingStateLoss();
                        }else {
                            me_fragment=new me_fragment();
                            fragmentManager.beginTransaction().replace(R.id.content_fragment,me_fragment).commitAllowingStateLoss();
                        }
                        contentctivity=new Contentctivity();
                        contentctivity.changeview(4);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.fristbody:
                        fragmentManager=getActivity().getSupportFragmentManager();
                        if (firstpage_fragment!=null){
                            fragmentManager.beginTransaction().replace(R.id.content_fragment,firstpage_fragment).commitAllowingStateLoss();
                        }else {
                            firstpage_fragment=new first_page_fragment();
                            fragmentManager.beginTransaction().replace(R.id.content_fragment,firstpage_fragment).commitAllowingStateLoss();
                        }
                        contentctivity=new Contentctivity();
                        contentctivity.changeview(1);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.dynamics:
                        fragmentManager=getActivity().getSupportFragmentManager();
                        if (recommend_fragment!=null){
                            fragmentManager.beginTransaction().replace(R.id.content_fragment,recommend_fragment).commitAllowingStateLoss();
                        }else {
                            recommend_fragment=new recommend_fragment();
                            fragmentManager.beginTransaction().replace(R.id.content_fragment,recommend_fragment).commitAllowingStateLoss();
                        }
                        contentctivity=new Contentctivity();
                        contentctivity.changeview(2);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.message:
                        fragmentManager=getActivity().getSupportFragmentManager();
                        if (message_frament!=null){
                            fragmentManager.beginTransaction().replace(R.id.content_fragment,message_frament).commitAllowingStateLoss();
                        }else {
                            message_frament=new message_frament();
                            fragmentManager.beginTransaction().replace(R.id.content_fragment,message_frament).commitAllowingStateLoss();
                        }
                        contentctivity=new Contentctivity();
                        contentctivity.changeview(3);
                        drawerLayout.closeDrawers();
                        break;
                }

                return false;
            }
        });
        viewPager.setAdapter(new first_viewpage_adapter(getChildFragmentManager(),0,fragments,titles));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
