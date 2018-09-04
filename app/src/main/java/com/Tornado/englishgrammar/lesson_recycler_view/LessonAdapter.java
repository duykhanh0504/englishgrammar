package com.Tornado.englishgrammar.lesson_recycler_view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import com.Tornado.englishgrammar.GrammarActivity;
import com.Tornado.englishgrammar.R;
import com.Tornado.englishgrammar.home_page.MainActivity;
import com.Tornado.englishgrammar.lesson.GrammarFragment;
import com.Tornado.englishgrammar.lesson.LessonActivity;
import com.Tornado.englishgrammar.home_page.TenseLessonActivity;
import com.Tornado.englishgrammar.home_page.WordTypeLessonActivity;

public class LessonAdapter extends RecyclerView.Adapter{
    private List<Lesson> lessonList;
    private Context mContext;

    public LessonAdapter(Context context, List<Lesson> ll) {
        lessonList = ll;
        mContext = context;
    }

    public  void setdata(List<Lesson> ll) {
        lessonList = ll;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_card,parent, false);
        return new LessonHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final LessonHolder lessonHolder = (LessonHolder) holder;
        lessonHolder.lessonName.setText(lessonList.get(position).getLessonName());
        lessonHolder.lessonName.setTextColor(Color.parseColor("#0B8E46"));
        lessonHolder.lessonDescription.setText(lessonList.get(position).getLessonDescription());
        lessonHolder.lessonDescription.setTextColor(Color.parseColor("#686868"));
        //lessonHolder.imageView.setImageResource(listImage[position]);

            lessonHolder.lessonCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent i = new Intent(mContext, GrammarActivity.class);
                   i.putExtra("id" , lessonList.get(position).getUuid());
                    mContext.startActivity(i);
                   // mContext.getApplicationContext().
                   // ((MainActivity) mContext).ShowFragmentGrammer("");
                }
            });
     /*   }
        if (lessonList.get(position).getUuid() == 2) {
            lessonHolder.lessonCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, TenseLessonActivity.class);
                    mContext.startActivity(i);
                }
            });
        }
        if (lessonList.get(position).getUuid() > 2) {
            lessonHolder.lessonCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, LessonActivity.class);
                    i.putExtra("lesson_id", lessonList.get(position).getUuid());
                    mContext.startActivity(i);
                }
            });
        }*/
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }
}
