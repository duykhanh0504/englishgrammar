package com.Tornado.englishgrammar.lesson_recycler_view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import com.Tornado.englishgrammar.R;
import com.Tornado.englishgrammar.lesson.LessonActivity;
import com.Tornado.englishgrammar.home_page.TenseLessonActivity;
import com.Tornado.englishgrammar.home_page.WordTypeLessonActivity;

public class LessonAdapter extends RecyclerView.Adapter{
    private List<Lesson> lessonList;
    private int[] listImage;
    private Context mContext;

    public LessonAdapter(Context context, List<Lesson> ll) {
        lessonList = ll;
        mContext = context;
        listImage = new int[46];
        listImage[0] = R.drawable.i1;
        listImage[1] = R.drawable.i5;
        listImage[2] = R.drawable.i3;
        listImage[3] = R.drawable.i4;
        listImage[4] = R.drawable.i10;
        listImage[5] = R.drawable.i6;
        listImage[6] = R.drawable.i7;
        listImage[7] = R.drawable.i8;
        listImage[8] = R.drawable.i9;
        listImage[9] = R.drawable.i2;
        listImage[10] = R.drawable.i11;
        listImage[11] = R.drawable.i12;
        listImage[12] = R.drawable.i13;
        listImage[13] = R.drawable.i14;
        listImage[14] = R.drawable.i15;
        listImage[15] = R.drawable.i16;
        listImage[16] = R.drawable.i17;
        listImage[17] = R.drawable.i18;
        listImage[18] = R.drawable.i19;
        listImage[19] = R.drawable.i20;
        listImage[20] = R.drawable.i21;
        listImage[21] = R.drawable.i22;
        listImage[22] = R.drawable.i23;
        listImage[23] = R.drawable.i24;
        listImage[24] = R.drawable.i25;
        listImage[25] = R.drawable.i26;
        listImage[26] = R.drawable.i27;
        listImage[27] = R.drawable.i28;
        listImage[28] = R.drawable.i29;
        listImage[29] = R.drawable.i30;
        listImage[30] = R.drawable.i31;
        listImage[31] = R.drawable.i32;
        listImage[32] = R.drawable.i33;
        listImage[33] = R.drawable.i34;
        listImage[34] = R.drawable.i35;
        listImage[35] = R.drawable.i36;
        listImage[36] = R.drawable.i37;
        listImage[37] = R.drawable.i1;
        listImage[38] = R.drawable.i2;
        listImage[39] = R.drawable.i3;
        listImage[40] = R.drawable.i4;
        listImage[41] = R.drawable.i5;
        listImage[42] = R.drawable.i6;
        listImage[43] = R.drawable.i7;
        listImage[44] = R.drawable.i8;
        listImage[45] = R.drawable.i9;
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
        lessonHolder.imageView.setImageResource(listImage[position]);
        if (lessonList.get(position).getUuid() == 1) {
            lessonHolder.lessonCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, WordTypeLessonActivity.class);
                    mContext.startActivity(i);
                }
            });
        }
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
        }
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }
}
