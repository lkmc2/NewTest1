package com.example.linchange.newtest1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private List<Event> datas;
    private GridView mGridView;
    private MyAdapter mAdapter;
    private TextView titleText;
    private Bitmap bitmap;
    private ImageLoader imageLoader;
    private int[] lastFirstView = new int[12];
//    private int[] lastOffsest = new int[11];


    public static final int EVENT_PICS[] = {
            R.drawable.pic01,R.drawable.pic02,R.drawable.pic03,
            R.drawable.pic04,R.drawable.pic05,R.drawable.pic06,
            R.drawable.pic07, R.drawable.pic08
    };
    public static final String PLACES[] = {"风景1","风景2","景致1", "景致2","风光1",
            "风光2","良辰1", "良辰2"};
    public static final String DATES[] = {"2014-09-28","2014-10-28","2014-12-06","2015-03-27","2015-05-16",
            "2015-10-17","2015-11-21","2015-12-16"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();
        initEvents();
    }

    private void initEvents() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailViewActivity.class);

                Event event = datas.get(position);

                //传递当前选中的相册的索引
                intent.putExtra("current_index",position);
                //传递当前选中的相册的标题
                intent.putExtra("title", event.getPlace());
                //上一次该相册gridview滑动到的第一项
                intent.putExtra("firstView", lastFirstView[position]);
                //上一次该相册gridview滑动到的偏移距离
//                intent.putExtra("offset", lastOffsest[position]);

                Log.i(TAG, "lastFirstView[" + position + "]" +  "= " + lastFirstView[position]);
//                Log.i(TAG, "lastOffsest[" + position + "]" +  "= " + lastOffsest[position]);

                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        titleText.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/hanyi.ttf"));
//        // 获取最大内存
//        int maxMemorySize = (int) (Runtime.getRuntime().maxMemory());
//
//        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
//                this);
//        config.threadPriority(Thread.NORM_PRIORITY - 2);
//        config.denyCacheImageMultipleSizesInMemory();// 不会在内存中缓存多个大小的图片
//        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());// 为了保证图片名称唯一
//        config.diskCacheSize(maxMemorySize / 10);// 内存缓存大小默认是：app可用内存的1/8,这里设为1/10
//
//        config.tasksProcessingOrder(QueueProcessingType.LIFO);
//        config.writeDebugLogs(); // Remove for release app
//
//        // Initialize ImageLoader with configuration.
//        ImageLoader.getInstance().init(config.build());

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);

        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);


        imageLoader = ImageLoader.getInstance();

        datas = new ArrayList<Event>();

        int picName;

        for (int i = 0; i < EVENT_PICS.length; i++) {
            picName = EVENT_PICS[i];
//            Log.i(TAG, "loadPicByNum: picName=" + picName);

            Event event = new Event(picName, PLACES[i],DATES[i]);
            datas.add(event);
        }
        mAdapter = new MyAdapter(this,datas);
        mGridView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //firstView为第一个选项的位置
        int firstView = data.getIntExtra("firstView", 0);
//        float offset = data.getFloatExtra("offset", 0);
        //此处请求码返回相册序号，
        lastFirstView[resultCode] = firstView;
//        lastOffsest[resultCode] = (int) offset;

//        Log.i(TAG, "onActivityResult: resultCode = " + resultCode);

//        Log.i(TAG, "onActivityResult lastFirstView[" + resultCode + "]" +  "= " + lastFirstView[resultCode]);
//        Log.i(TAG, "onActivityResult lastOffsest[" + resultCode + "]" +  "= " + lastOffsest[resultCode]);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Log.i(TAG, "MainActivity onDestroy: ");
        imageLoader.clearMemoryCache();
        imageLoader.clearDiskCache();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.i(TAG, "MainActivity onStop: ");
        imageLoader.clearMemoryCache();
        imageLoader.clearDiskCache();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        mGridView = (GridView) findViewById(R.id.gridView);
        titleText = (TextView) findViewById(R.id.tv_title);
    }



}
