package com.example.linchange.newtest1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PicViewActivity extends Activity {

    private static final String TAG = "PicViewActivity";

    private ZoomImageView picShowImageView;
    private ViewPager mViewPager;
//    private RelativeLayout tooBar;
//    private RelativeLayout picView;
    AssetManager assetManager;
    private List<ImageView> mImageViews = new ArrayList<ImageView>();
    private List<Photo> photoList = new ArrayList<Photo>();
    public static final int[] PHOTO_COUNTS = {20, 20, 20, 20, 20, 20, 20, 20};
    public static final String[] PHOTO_NAMES = {"fengguang", "fengjing", "huafang", "jingzhi",
            "liangchen", "meijing", "meitu", "tupian"};
    public static final String[] PHOTO_PATHS = {
            "fengguang/fengguang", "fengjing/fengjing","huafang/huafang", "jingzhi/jingzhi",
            "liangchen/liangchen", "meijing/meijing", "meitu/meitu", "tupian/tupian"};
    public static final String LAST_NAME = ".jpg";
    //数据源
    private Photo photo;
    private PagerAdapter pagerAdapter;
//    private ImageLoader imageLoader;
    private int currentPosition = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_view);

        initViews();
        initDatas();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        imageLoader.clearMemoryCache();
//        imageLoader.clearDiskCache();
    }

    @Override
    public void onBackPressed() {
        setResult(currentPosition);

        super.onBackPressed();
    }

    private void initDatas() {
        assetManager = getAssets();
        Intent intent = getIntent();
        String picPath = intent.getStringExtra("photoPath");
        int currentIndex = intent.getIntExtra("current_index", 0);
        int position = intent.getIntExtra("position", 0);

        loadPicByNum(currentIndex);

//        imageLoader = ImageLoader.getInstance();


//        Log.i(TAG, "initDatas: PHOTO_COUNTS[currentIndex] = " + PHOTO_COUNTS[currentIndex]);


        for(int i = 0;i < PHOTO_COUNTS[currentIndex];i++) {
            ZoomImageView ziv = new ZoomImageView(getApplicationContext());

//            ImageLoader.getInstance().displayImage("assets://" + photoList.get(i).getPhotoPath(),
//                            ziv);

            mImageViews.add(ziv);
        }

        pagerAdapter = new PagerAdapter() {
            public boolean isViewFromObject(View arg0, Object arg1) {

                return arg0 == arg1;
            }

            public int getCount() {

                return mImageViews.size();
            }

            public void destroyItem(ViewGroup container, int position,
                                    Object object) {

                mImageViews.get(position).setImageBitmap(null);


                container.removeView(mImageViews.get(position));
            }

            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mImageViews.get(position));

//                ImageLoader.getInstance().displayImage("assets://" + photoList.get(position).getPhotoPath(),
//                        mImageViews.get(position));
                try {
                    InputStream is = assetManager.open(photoList.get(position).getPhotoPath());
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    mImageViews.get(position).setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return mImageViews.get(position);
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }
        };


        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(pagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPosition = position;

                // 获取占用最大内存
//                int maxMemory = (int) Runtime.getRuntime().maxMemory();
//
//                if (maxMemory < 40 * 1024 * 1024) {
//                    ImageLoader.getInstance().clearMemoryCache();
//                    ImageLoader.getInstance().clearDiskCache();
//                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(position);


    }

    public List<Photo> loadPicByNum(int num) {
        //photos为当前相册内照片的张数
        int photos = PHOTO_COUNTS[num];

//        Log.i(TAG, "loadPicByNum: photos=" + photos);

        String picName;
        String picPath;

        for (int i = 1; i <= photos; i++) {
            picName = PHOTO_NAMES[num];
            picPath = PHOTO_PATHS[num];

            if (i < 10) {
                picName += "00" + i;
                picPath += "00" + i;
            } else if (i < 100) {
                picName += "0" + i;
                picPath += "0" + i;
            } else {
                picName += i;
                picPath += i;
            }

            picName += LAST_NAME;
            picPath += LAST_NAME;
//            Log.i(TAG, "loadPicByNum: picName=" + picName);
//            Log.i(TAG, "loadPicByNum: picPath=" + picPath);


            photo = new Photo(picName, picPath);
            photoList.add(photo);
        }

        return photoList;
    }



    private void initViews() {
//        picShowImageView = (ZoomImageView) findViewById(R.id.iv_pic_show);
//        tooBar = (RelativeLayout) findViewById(R.id.toobar);
        mViewPager = (ViewPager) findViewById(R.id.pic_view_viewpager);
//        picView = (RelativeLayout) findViewById(R.id.pic_view);

        //隐藏导航栏
//        tooBar.setVisibility(View.INVISIBLE);
//        picView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //隐藏状态栏
//        picView.setSystemUiVisibility(View.INVISIBLE);
    }

}
