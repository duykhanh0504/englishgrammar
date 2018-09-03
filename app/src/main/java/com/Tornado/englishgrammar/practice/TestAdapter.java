package com.Tornado.englishgrammar.practice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.Tornado.englishgrammar.R;

public class TestAdapter extends RecyclerView.Adapter{

    private ListQuestion test;
    private Context mContext;

    public TestAdapter(ListQuestion l, Context c) {
        test = l;
        mContext = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testcard,parent, false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final TestHolder testHolder = (TestHolder) holder;
        String temp = Integer.valueOf(position + 1).toString();
        testHolder.title.setText("Bài kiểm tra số " + temp);
        temp = "Dễ";
        testHolder.level.setText("Mức độ: " + temp);
        testHolder.testCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TestActivity.class);
                intent.putExtra("testIndex", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return test.getSize();
    }

    private class TestHolder extends RecyclerView.ViewHolder {
        CardView testCard;
        TextView title;
        TextView level;
        TestHolder(View itemView) {
            super(itemView);
            testCard = (CardView) itemView.findViewById(R.id.test_card);
            title = (TextView) itemView.findViewById(R.id.test_name);
            level = (TextView) itemView.findViewById(R.id.test_level);
        }
    }
}
