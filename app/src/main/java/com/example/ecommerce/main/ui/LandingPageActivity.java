package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ecommerce.R;
import com.example.ecommerce.main.adapters.OnboardAdapter;

public class LandingPageActivity extends AppCompatActivity {
private ViewPager viewPager;
private ImageView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        viewPager=findViewById(R.id.viewpager);
        next=findViewById(R.id.nextpage);
        OnboardAdapter adapter=new OnboardAdapter(this);
        viewPager.setAdapter(adapter);
   next.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
//           viewPager.setCurrentItem(1,true);
           Intent intent=new Intent(LandingPageActivity.this,Mainscreen.class);
           startActivity(intent);
       }
   });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                 }



            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}