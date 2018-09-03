package com.Tornado.englishgrammar.practice;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.Tornado.englishgrammar.home_page.MainActivity;

import com.Tornado.englishgrammar.R;

public class PracticeFragment extends Fragment {

    public PracticeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_practice, container, false);

        ((MainActivity) getActivity()).setToolbarColor(R.color.toolbar_practice);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.toolbar_practice));
        }
        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_practice_fragment);
        mRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        TestAdapter adapter = new TestAdapter(new ListQuestion(), getActivity());
        mRecyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onResume() {
        getActivity().setTitle("Luyện tập tổng hợp");
        super.onResume();
    }
}
