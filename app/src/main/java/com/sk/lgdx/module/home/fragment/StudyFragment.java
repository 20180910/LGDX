package com.sk.lgdx.module.home.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.github.customview.MyLinearLayout;
import com.sk.lgdx.GetSign;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseFragment;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.home.adapter.StudyFragmentAdapter;
import com.sk.lgdx.module.study.activity.SearchActivity;
import com.sk.lgdx.module.study.network.ApiRequest;
import com.sk.lgdx.module.study.network.response.MajorTypeObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/4.
 */

public class StudyFragment extends BaseFragment {
    @BindView(R.id.stl_study)
    SlidingTabLayout stl_study;
    @BindView(R.id.vp_sudy)
    ViewPager vp_sudy;
    List<Fragment> list = new ArrayList<>();
    StudyFragmentAdapter adapter;
    @BindView(R.id.ll_study_search)
    MyLinearLayout ll_study_search;

    @Override
    protected int getContentView() {
        return R.layout.frag_study;
    }

    @Override
    protected void initView() {


    }


    @Override
    protected void initData() {
        showProgress();
        getMajorType();


    }

    private void getMajorType() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getMajorType(map, new MyCallBack<List<MajorTypeObj>>(mContext, pcfl, pl_load) {
            @Override
            public void onSuccess(List<MajorTypeObj> obj) {
                initTabLayout(obj);


            }
        });

    }

    private void initTabLayout(List<MajorTypeObj> obj) {
        for (int i = 0; i < obj.size(); i++) {
            list.add(StudyDetailFragment.newInstance(obj.get(i).getCourse_type_id()));
        }
        adapter = new StudyFragmentAdapter(getChildFragmentManager());
        adapter.setList(list);
        adapter.setListBeen(obj);

        vp_sudy.setAdapter(adapter);
        vp_sudy.setOffscreenPageLimit(list.size() - 1);
        vp_sudy.setAdapter(adapter);

        stl_study.setViewPager(vp_sudy);

    }



    @OnClick({R.id.ll_study_search})
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.ll_study_search:
                STActivity(SearchActivity.class);

            break;
        }
    }
}
