package edu.temple.mikem.checkerboardmaker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mikem on 9/17/2015.
 */
public class CheckerboardAdapter extends BaseAdapter {

    Context c;
    int count;
    int columnCount;
    int color;
    int changeColor;
    String[] numbers;

    public CheckerboardAdapter(){

    }

    public CheckerboardAdapter(Context c, int count, String[] numbers){
        this.numbers = numbers;
        this.c = c;
        this.count = count * count;
        this.columnCount = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null)
            convertView = new FrameLayout(c);
        TextView tv = new TextView(c);
        tv.setText(numbers[position]);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(10);
        tv.setMinimumHeight(80);
        tv.setPadding(5,5,5,5);
        if(((position)) == 0){
            tv.setBackgroundColor(Color.BLACK);
            tv.setTextColor(Color.WHITE);
            color= Color.BLACK;
            changeColor = Color.BLACK;
        }
        else
        {
            if ((position ) % columnCount ==0){
                if (changeColor == Color.BLACK)
                {
                    changeColor = Color.WHITE;
                    color = Color.BLACK;
                }
                else{
                    changeColor = Color.BLACK;
                    color = Color.WHITE;
                }


            }
            if (color == Color.BLACK) {
                tv.setBackgroundColor(Color.WHITE);
                tv.setTextColor(Color.BLACK);
                color= Color.WHITE;
            }else
            {
                tv.setBackgroundColor(Color.BLACK);
                tv.setTextColor(Color.WHITE);
                color= Color.BLACK;
            }
        }

        ((FrameLayout)convertView).addView(tv);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, String.valueOf(position+1), Toast.LENGTH_SHORT).show();
            }
        });



        return convertView;
    }
}
