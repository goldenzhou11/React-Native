package com.binzapp.baiduMap.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

/**
 * Created by binz on 2017/1/16.
 */

public class BitmapUtil {

    /**
     * 合并2个Bitmap
     * @param src
     * @param inbm
     * @return
     */
    public static Bitmap drawIntoBitmap(Bitmap src, Bitmap inbm){

        // 另外创建一张组合后的图片
        Bitmap newb =  Bitmap.createBitmap(src);
        // 缩小图片
        Matrix matrix = new Matrix();
        matrix.postScale(0.8f,0.8f);
        Bitmap resizeBmp = Bitmap.createBitmap(inbm,0,0,inbm.getWidth(),inbm.getHeight(),matrix,true);
        Canvas canvas = new Canvas(newb);
        //组合图片
        canvas.drawBitmap(resizeBmp, (src.getWidth() - resizeBmp.getWidth())/2 + 1, 10, null);
        inbm.recycle();
        inbm = null;
        return newb;
    }
}
