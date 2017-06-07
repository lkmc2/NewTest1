package com.example.linchange.newtest1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import java.util.ArrayList;
import java.util.List;

public class DetailViewActivity extends Activity {

    private static final String TAG = "DetailViewActivity";

    private TextView mDetailTVTitle;
    //用于展示图片的网格列表
    private GridView mDetailGridView;

    public static final int[] PHOTO_COUNTS = {20, 20, 20, 20, 20, 20, 20, 20};
    public static final String[] PHOTO_NAMES = {"fengguang", "fengjing", "huafang", "jingzhi",
            "liangchen", "meijing", "meitu", "tupian"};
    public static final String[] PHOTO_PATHS = {
            "fengguang/fengguang", "fengjing/fengjing","huafang/huafang", "jingzhi/jingzhi",
            "liangchen/liangchen", "meijing/meijing", "meitu/meitu", "tupian/tupian"};
    public static final String LAST_NAME = ".jpg";
    private ImageLoader imageLoader;
    //当前相册的索引
    public static int current_index = 0;
    //记录上一次滑动到gridview的第一项的位置
    private int firstView = 0;
    //记录上一次点击gridview的位置
    private int lastPosition = 0;

    //数据源
    private List<Photo> photoList;
    private Photo photo;

    private DetailAdapter mDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        initViews();
        initDatas();
        initEvents();
    }

    private void initEvents() {

        mDetailGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PicViewActivity.class);

                Photo photo = photoList.get(position);

                intent.putExtra("current_index",current_index);
                intent.putExtra("position", position);
                intent.putExtra("photoPath", photo.getPhotoPath());

                lastPosition = position;

                startActivityForResult(intent, 0);
            }
        });

        mDetailGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                firstView = firstVisibleItem;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if ((resultCode > firstView + 14) || ((resultCode > lastPosition + 1) && (lastPosition >= firstView + 13))) {
            mDetailGridView.setSelection(resultCode - 6);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("firstView", firstView);
//        float offset = mDetailGridView.getTop();

//        Log.i(TAG, "onBackPressed: getTop = " + mDetailGridView.getTop()
//                + "getY = " + mDetailGridView.getY()
//                + "getPivotY = " + mDetailGridView.getPivotY()
//                + "getScrollY = " + mDetailGridView.getScrollY()
//                + "getTranslationY = " + mDetailGridView.getTranslationY());


//        intent.putExtra("offset", offset);
        setResult(current_index, intent);
        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        imageLoader.clearMemoryCache();
        imageLoader.clearDiskCache();
    }

    private void initDatas() {
        imageLoader = ImageLoader.getInstance();

        photoList = new ArrayList<Photo>();

        Intent intent = getIntent();

        String title;
        title = intent.getStringExtra("title");

        mDetailTVTitle.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/hanyi.ttf"));
        mDetailTVTitle.setText(title);

        int num;
        num = current_index = intent.getIntExtra("current_index", 0);

        Log.i(TAG, "initDatas: intent传递的数字为" + num);

        loadPicByNum(num); //根据序号加载图片


        int firstView = intent.getIntExtra("firstView", 0);
//        int offset = intent.getIntExtra("offset", 0);

//        Log.i(TAG, "initDatas: currentIndex = " + current_index);
//        Log.i(TAG, "DetailViewActivity initDatas: firstView = " + firstView);
        
//        mDetailGridView.smoothScrollToPositionFromTop(firstView, 0);
        mDetailGridView.setSelection(firstView);

    }

    /**
     * 通过传入的相册序号加载相册图片
     * @param num 传入的相册序号
     */
    private void loadPicByNum(int num) {
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

        mDetailAdapter = new DetailAdapter(this, photoList);

        mDetailGridView.setAdapter(mDetailAdapter);
        //第一个参数就是ImageLoader, 第二个是控制是否在滑动过程中暂停加载图片，如果需要暂停传true就行了，第三个参数控制猛的滑动界面的时候图片是否加载
        mDetailGridView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, true));
    }

    private void initViews() {
        mDetailTVTitle = (TextView) findViewById(R.id.tv_detail_title);
        mDetailGridView = (GridView) findViewById(R.id.gv_detail);
    }

}
