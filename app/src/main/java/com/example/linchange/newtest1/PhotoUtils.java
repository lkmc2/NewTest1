package com.example.linchange.newtest1;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lin Change on 2016-08-20.
 */
public class PhotoUtils {
    private static final String TAG = "PhotoUtils";

    public static final int[] PHOTO_COUNTS = {62, 200, 292, 17, 22, 403, 18, 195, 96, 10, 30};
    public static final String[] PHOTO_NAMES = {"junxun", "jiaoliu", "shimen", "hongmao",
            "jinglao", "qingxiu", "dasheng", "pumiao", "nongjia", "leifeng", "zhishu"};
    public static final String[] PHOTO_PATHS = {
            "junxun/junxun", "jiaoliu/jiaoliu","shimen/shimen", "hongmao/hongmao",
            "jinglao/jinglao", "qingxiu/qingxiu", "dasheng/dasheng", "pumiao/pumiao",
            "nongjia/nongjia", "leifeng/leifeng", "zhishu/zhishu"};
    public static final String LAST_NAME = ".jpg";
    //数据源
    private List<Photo> photoList = new ArrayList<Photo>();
    private Photo photo;


    public List<Photo> loadPicByNum(int num) {
        //photos为当前相册内照片的张数
        int photos = PHOTO_COUNTS[num];

        Log.i(TAG, "loadPicByNum: photos=" + photos);

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
            Log.i(TAG, "loadPicByNum: picName=" + picName);
            Log.i(TAG, "loadPicByNum: picPath=" + picPath);


            photo = new Photo(picName, picPath);
            photoList.add(photo);
        }

        return photoList;
    }
}
