package com.example.linchange.newtest1;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import utils.CommonAdapter;
import utils.ViewHolder;

/**
 * Created by Lin Change on 2016/7/27.
 */
public class MyAdapter extends CommonAdapter<Event> {



    public MyAdapter(Context context, List<Event> datas) {
        super(context,datas,R.layout.listview_item_main);
    }

    /**
     * 此方法用来设置组件上的内容
     * @param holder
     * @param event
     */
    @Override
    public void convert(ViewHolder holder, Event event) {
//        ((TextView)holder.getView(R.id.id_title)).setText(bean.getTitle());
//        ((TextView)holder.getView(R.id.id_desc)).setText(bean.getDesc());
//        ((TextView)holder.getView(R.id.id_time)).setText(bean.getTime());
//        ((TextView)holder.getView(R.id.id_phone)).setText(bean.getPhone());

       //这里的setText方法为ViewHolder里面另外写的
//        holder.setText(R.id.id_title,bean.getTitle())
//                .setText(R.id.id_desc,bean.getDesc())
//                .setText(R.id.id_time,bean.getTime())
//                .setText(R.id.id_phone,bean.getPhone());


        holder.setImageFromAssets(R.id.iv_pic_item, "drawable://" + event.getImgName())
                .setText(R.id.tv_date, event.getDate())
                .setText(R.id.tv_place, event.getPlace());
    }

    /**
     * 从assets文件夹中异步加载图片
     *
     * @param imageName
     *            图片名称，带后缀的，例如：1.png
     * @param imageView
     */
    public void dispalyFromAssets(String imageName, ImageView imageView) {
        // String imageUri = "assets://image.png"; // from assets
        ImageLoader.getInstance().displayImage("assets://" + imageName,
                imageView);
    }


}
