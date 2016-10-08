package com.example.androidstudio.firstandroid_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OnTouch extends AppCompatActivity {

    TextView x,y,differ,Quad,motion,motion1;
    String coorx, coory, differ1, differ2, moti,moti1,Quad1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_touch);

        final ImageView Bulls = (ImageView)findViewById(R.id.Bulls);

        x = (TextView)findViewById(R.id.Xcoordinates);
        y = (TextView)findViewById(R.id.Ycoordinates);
        differ = (TextView)findViewById(R.id.tvdiff);
        motion = (TextView)findViewById(R.id.tvmotion);
        motion1 = (TextView)findViewById(R.id.tvmotion1);
        Quad = (TextView)findViewById(R.id.tvQuad);

        Bulls.setOnTouchListener(new View.OnTouchListener() {
            float initX = 0, initY = 0, finalX = 0, finalY = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        initX = motionEvent.getX();
                        initY = motionEvent.getY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        finalX = motionEvent.getX();
                        finalY = motionEvent.getY();
                        displayAction(initX, finalX, initY, finalY);
                        return false;
                }
                return false;
            }
        });

    }
    public void displayAction(float x1, float x2, float y1, float y2){
        coorx = String.format("x1:%.2f,x2:%.2f",x1,x2);
        coory = String.format("y1:%.2f,y2:%.2f",y1,y2);
        differ1 = String.format("x1-x2:%.2f",x1-x2);
        differ2 = String.format("y1-y2:%.2f",y1-y2);

        x.setText(coorx);
        y.setText(coory);
        differ.setText(differ1 + " " + differ2);

        if(x1 == x2) {

        }else if(x1 > x2){
            moti = String.format("SWIPED RIGHT TO LEFT");
        }else if(x1 < x2){
            moti = String.format("SWIPED LEFT TO RIGHT");
        }motion.setText(moti);

        if(y1 == y2) {

        }else if(y1 > y2){
            moti1 = String.format("SWIPED DOWN TO UP");
        }else if(y1 < y2){
            moti1 = String.format("SWIPED UP TO DOWN");
        }motion1.setText(moti1);


        if(x1-x2 > 0 && y1-y2 > 0) {
            Quad1 = String.format("Second Quadrant");
        }else if(x1-x2 < 0 && y1-y2 > 0){
            Quad1 = String.format("First Quadrant");
        }else if(x1-x2 < 0 && y1-y2 < 0){
            Quad1 = String.format("Fourth Quadrant");
        }else if(x1-x2 > 0 && y1-y2 < 0){
            Quad1 = String.format("Third Quadrant");
        }else{
            Quad1 = String.format("Origin, No Quadrant");
        }Quad.setText(Quad1);

    }


}
