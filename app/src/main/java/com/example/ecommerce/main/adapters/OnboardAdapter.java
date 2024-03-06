package com.example.ecommerce.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.ecommerce.R;

public class OnboardAdapter extends PagerAdapter {
    private Context ctx;
    private LayoutInflater Layoutinflater;

    public OnboardAdapter(Context ctx) {
        this.ctx = ctx;
        Layoutinflater=LayoutInflater.from(ctx);
    }

    public OnboardAdapter(Context ctx, LayoutInflater layoutinflater) {
        this.ctx = ctx;
        Layoutinflater = layoutinflater;
    }
    private int titles[]={
            R.string.title1,
            R.string.title2,
            R.string.title3,
    };
    private int images[]={
            R.drawable.onboard1,
            R.drawable.onboardd2,
            R.drawable.onboarding4,

    };
    private int imagesdots[]={
            R.drawable.ellipsis,
            R.drawable.ellipsis2,
            R.drawable.ellipsis3,

    };

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view== (RelativeLayout)object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v=Layoutinflater.inflate(R.layout.onboard_page,container,false);
        ImageView image=v.findViewById(R.id.imagelandingpage);
        ImageView dots=v.findViewById(R.id.dotslayout);
        TextView title =v.findViewById(R.id.landingtitle);
        image.setImageResource(images[position]);
        dots.setImageResource(imagesdots[position]);
        title.setText(titles[position]);
        container.addView(v);
        return v;
    }
}
