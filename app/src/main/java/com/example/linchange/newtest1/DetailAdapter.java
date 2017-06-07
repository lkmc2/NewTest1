package com.example.linchange.newtest1;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import utils.CommonAdapter;
import utils.ViewHolder;

/**
 * Created by Lin Change on 2016/7/27.
 */
public class DetailAdapter extends CommonAdapter<Photo> {

    private Context context;


    public DetailAdapter(Context context, List<Photo> datas) {
        super(context,datas,R.layout.gridview_item_detail);
        this.context = context;
    }

    /**
     * 此方法用来设置组件上的内容
     * @param holder
     * @param photo
     */
    @Override
    public void convert(ViewHolder holder, Photo photo) {
//        ((TextView)holder.getView(R.id.id_title)).setText(bean.getTitle());
//        ((TextView)holder.getView(R.id.id_desc)).setText(bean.getDesc());
//        ((TextView)holder.getView(R.id.id_time)).setText(bean.getTime());
//        ((TextView)holder.getView(R.id.id_phone)).setText(bean.getPhone());

       //这里的setText方法为ViewHolder里面另外写的
//        holder.setText(R.id.id_title,bean.getTitle())
//                .setText(R.id.id_desc,bean.getDesc())
//                .setText(R.id.id_time,bean.getTime())
//                .setText(R.id.id_phone,bean.getPhone());

//        Bitmap bitmap = dispalyFromAssets(photo.getPhotoPath(), R.id.iv_detail);

//        holder.setImageResource(R.id.iv_detail, bitmap);



        holder.setImageFromAssets(R.id.iv_detail, "assets://" + photo.getPhotoPath());


    }

    public void dispalyFromAssets(String imageName, ImageView imageView) {
        // String imageUri = "assets://image.png"; // from assets
        ImageLoader.getInstance().displayImage("assets://" + imageName,
                imageView);
    }


    public Bitmap getImageFromAssetsFile(String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
