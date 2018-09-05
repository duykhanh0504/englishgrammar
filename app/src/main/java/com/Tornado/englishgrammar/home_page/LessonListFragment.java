package com.Tornado.englishgrammar.home_page;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.Tornado.englishgrammar.fast_scroller.FastScroller;
import com.Tornado.englishgrammar.lesson_recycler_view.Lesson;
import com.Tornado.englishgrammar.lesson_recycler_view.LessonAdapter;
import com.Tornado.englishgrammar.lesson_recycler_view.LessonList;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import com.Tornado.englishgrammar.R;
import com.Tornado.englishgrammar.lesson_recycler_view.*;

public class LessonListFragment extends Fragment implements MainActivity.MyInterface {

   // public static FloatingActionMenu mFab;
    private RecyclerView mLessonRecyclerView;

    private TabLayout taplayout;
    LessonAdapter mLessonAdapter;
    List<Lesson> listLessons;
    LessonList lessonList;
    int cutrrentype =0;

    public LessonListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lesson_list, container, false);
        mLessonRecyclerView = (RecyclerView) v.findViewById(R.id.lesson_list);
        taplayout = (TabLayout)v.findViewById(R.id.taplayout);
        mLessonRecyclerView.setHasFixedSize(true);




        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mLessonRecyclerView.setLayoutManager(layoutManager);


         lessonList = LessonList.get(getContext());
        listLessons = new ArrayList<>();
        for (Lesson lesson : lessonList.getLessonsSentence()) {
            //if (lesson.getUuid() <= 32)
                listLessons.add(lesson);
        }
        mLessonAdapter = new LessonAdapter(getContext(), listLessons);
        mLessonRecyclerView.setAdapter(mLessonAdapter);

        FastScroller fastScroller = (FastScroller) v.findViewById(R.id.fastscroll);
        fastScroller.setRecyclerView(mLessonRecyclerView);
        cutrrentype =0;
      //  mFab = (FloatingActionMenu) v.findViewById(R.id.menu_red);
        taplayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                cutrrentype = tab.getPosition();
                if(tab.getPosition() ==0)
                {
                //    LessonList lessonList = LessonList.get(getContext());
                    listLessons.clear();
                    for (Lesson lesson : lessonList.getLessonsSentence()) {
                        //if (lesson.getUuid() <= 32)
                        listLessons.add(lesson);
                    }
                    mLessonAdapter.setdata(listLessons);
                }
                else
                {
                    listLessons.clear();
                    for (Lesson lesson : lessonList.getLessonsWord()) {
                        //if (lesson.getUuid() <= 32)
                        listLessons.add(lesson);
                    }
                    mLessonAdapter.setdata(listLessons);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }

    public static void hideFloatingMenu() {
       // mFab.hideMenuButton(true);
    }

    public static void showFloatingMenu() {
       // mFab.showMenuButton(true);
    }

    private static final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null)
        {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            mLessonRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, mLessonRecyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onResume() {
        ((MainActivity) getActivity()).setToolbarColor(R.color.colorPrimary);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        }
        super.onResume();
    }

    @Override
    public void myAction(String text) {
        if(!text.isEmpty())
        {
            listLessons.clear();
            for (Lesson lesson : lessonList.searchLesson(text)) {
                //if (lesson.getUuid() <= 32)
                listLessons.add(lesson);
            }
            mLessonAdapter.setdata(listLessons);
        }
        else
        {
            if(cutrrentype ==0)
            {
                listLessons.clear();
                for (Lesson lesson : lessonList.getLessonsSentence()) {
                    //if (lesson.getUuid() <= 32)
                    listLessons.add(lesson);
                }
                mLessonAdapter.setdata(listLessons);
            }
                else
            {
                listLessons.clear();
                for (Lesson lesson : lessonList.getLessonsWord()) {
                    //if (lesson.getUuid() <= 32)
                    listLessons.add(lesson);
                }
                mLessonAdapter.setdata(listLessons);
            }
        }
    }
}
