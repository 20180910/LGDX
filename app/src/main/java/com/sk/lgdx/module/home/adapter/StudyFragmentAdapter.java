package com.sk.lgdx.module.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sk.lgdx.module.study.network.response.MajorTypeObj;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */

public class StudyFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list;
    private List<MajorTypeObj> listBeen;

    public List<MajorTypeObj> getListBeen() {
        return listBeen;
    }

    public void setListBeen(List<MajorTypeObj> listBeen) {
        this.listBeen = listBeen;
    }

    public void setList(List<Fragment> list) {
        this.list = list;
    }

    public StudyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return listBeen.get(position).getCourse_type_name();
    }
}
