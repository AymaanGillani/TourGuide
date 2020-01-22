package com.example.android.tourguide;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DetailsFragment extends Fragment implements Serializable {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static int[] IMAGES ;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private static boolean alreadyInitialized;
    Timer swipeTimer = new Timer();
    private boolean playpause=true;

    private void init(View rootView) {
        alreadyInitialized = true;
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);
        mPager = (ViewPager) rootView.findViewById(R.id.imgPager);
        mPager.setAdapter(new SlidingImage_Adapter(getContext(), ImagesArray));

        CirclePageIndicator indicator = (CirclePageIndicator)
                rootView.findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        startTimer(handler, Update);
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 1500, 1500);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        swipeTimer = new Timer();
        Intent i = getActivity().getIntent();
        DataSetter data=(DataSetter) i.getSerializableExtra("actData");
        IMAGES=data.getImages();
        final View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        TextView details = (TextView) rootView.findViewById(R.id.details);
        details.setText(Html.fromHtml(getString(data.getDetails())));
        details.setMovementMethod(LinkMovementMethod.getInstance());
        if (!alreadyInitialized) init(rootView);
        else {
            mPager = (ViewPager) rootView.findViewById(R.id.imgPager);
            mPager.setAdapter(new SlidingImage_Adapter(getContext(), ImagesArray));
            CirclePageIndicator indicator = (CirclePageIndicator)
                    rootView.findViewById(R.id.indicator);
            indicator.setViewPager(mPager);
            final float density = getResources().getDisplayMetrics().density;
            indicator.setRadius(5 * density);
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == NUM_PAGES) {
                        currentPage = 0;
                    }
                    mPager.setCurrentItem(currentPage++, true);
                }
            };
            startTimer(handler, Update);
            indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;
                }

                @Override
                public void onPageScrolled(int pos, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int pos) {

                }
            });
        }
        setRetainInstance(true);
        ImageView play = rootView.findViewById(R.id.playImg);
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playSlider(rootView);
            }
        });
        ImageView pause = rootView.findViewById(R.id.pauseImg);
        pause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pauseSlider(rootView);
            }
        });
        return rootView;
    }

    private void startTimer(final Handler handler, final Runnable Update) {
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1500, 1500);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (swipeTimer != null) {
            swipeTimer.cancel();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        alreadyInitialized=false;
    }

    public void pauseSlider(View view){
        if (playpause) {
            if (swipeTimer != null) {
                swipeTimer.cancel();
            }
            ImageView pause = view.findViewById(R.id.pauseImg);
            pause.setImageResource(R.drawable.pause1);
            ImageView play = view.findViewById(R.id.playImg);
            play.setImageResource(R.drawable.play);
            playpause=false;
        }
    }

    public void playSlider(View view) {
        if (!playpause) {
            mPager = (ViewPager) view.findViewById(R.id.imgPager);
            mPager.setAdapter(new SlidingImage_Adapter(getContext(), ImagesArray));
            CirclePageIndicator indicator = (CirclePageIndicator)
                    view.findViewById(R.id.indicator);
            indicator.setViewPager(mPager);
            final float density = getResources().getDisplayMetrics().density;
            indicator.setRadius(5 * density);
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == NUM_PAGES) {
                        currentPage = 0;
                    }
                    mPager.setCurrentItem(currentPage++, true);
                }
            };
            startTimer(handler, Update);
            indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;
                }

                @Override
                public void onPageScrolled(int pos, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int pos) {

                }
            });
            ImageView pause = view.findViewById(R.id.pauseImg);
            pause.setImageResource(R.drawable.pause);
            ImageView play = view.findViewById(R.id.playImg);
            play.setImageResource(R.drawable.play1);
            playpause=true;
        }
    }
}
