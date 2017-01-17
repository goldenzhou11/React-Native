package com.binzapp.baiduMap.BitmapDescriptor;

import android.graphics.Bitmap;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.binzapp.R;
import com.binzapp.baiduMap.Utils.BitmapUtil;

/**
 * Created by binz on 2017/1/17.
 */

public class MarkerDescriptor {

    private static MarkerDescriptor instance = new MarkerDescriptor();

    private static BitmapDescriptor attractionsDescriptor;
    private static BitmapDescriptor entertainmDescriptor;
    private static BitmapDescriptor diningDescriptor;
    private static BitmapDescriptor shoppingDescriptor;
    private static BitmapDescriptor restroomsDescriptor;
    private static BitmapDescriptor photopassDescriptor;
    private static BitmapDescriptor toursDescriptor;
    private static BitmapDescriptor guestservicesDescriptor;
    private static BitmapDescriptor resortsDescriptor;
    private static BitmapDescriptor recreationDescriptor;


    public MarkerDescriptor() {
        Bitmap src = BitmapDescriptorFactory.fromResource(R.mipmap.bkg_facility_pin).getBitmap();
        Bitmap inbm = BitmapDescriptorFactory.fromResource(R.mipmap.ic_fac_attractions_dkb).getBitmap();
        this.attractionsDescriptor = this.getBitmapDescriptor(BitmapUtil.drawIntoBitmap(src, inbm));

        inbm = BitmapDescriptorFactory.fromResource(R.mipmap.ic_fac_entertainment_dkb).getBitmap();
        this.entertainmDescriptor = this.getBitmapDescriptor(BitmapUtil.drawIntoBitmap(src, inbm));

        inbm = BitmapDescriptorFactory.fromResource(R.mipmap.ic_fac_dining_dkb).getBitmap();
        this.diningDescriptor = this.getBitmapDescriptor(BitmapUtil.drawIntoBitmap(src, inbm));

        inbm = BitmapDescriptorFactory.fromResource(R.mipmap.ic_fac_shopping_dkb).getBitmap();
        this.shoppingDescriptor = this.getBitmapDescriptor(BitmapUtil.drawIntoBitmap(src, inbm));

        inbm = BitmapDescriptorFactory.fromResource(R.mipmap.ic_fac_restrooms_dkb).getBitmap();
        this.restroomsDescriptor = this.getBitmapDescriptor(BitmapUtil.drawIntoBitmap(src, inbm));

        inbm = BitmapDescriptorFactory.fromResource(R.mipmap.ic_fac_photopass_dkb).getBitmap();
        this.photopassDescriptor = this.getBitmapDescriptor(BitmapUtil.drawIntoBitmap(src, inbm));

        inbm = BitmapDescriptorFactory.fromResource(R.mipmap.ic_fac_tours_dkb).getBitmap();
        this.toursDescriptor = this.getBitmapDescriptor(BitmapUtil.drawIntoBitmap(src, inbm));

        inbm = BitmapDescriptorFactory.fromResource(R.mipmap.ic_fac_guestservices_dkb).getBitmap();
        this.guestservicesDescriptor = this.getBitmapDescriptor(BitmapUtil.drawIntoBitmap(src, inbm));

        inbm = BitmapDescriptorFactory.fromResource(R.mipmap.ic_fac_resorts_dkb).getBitmap();
        this.resortsDescriptor = this.getBitmapDescriptor(BitmapUtil.drawIntoBitmap(src, inbm));

        inbm = BitmapDescriptorFactory.fromResource(R.mipmap.ic_fac_recreation_dkb).getBitmap();
        this.recreationDescriptor = this.getBitmapDescriptor(BitmapUtil.drawIntoBitmap(src, inbm));

    }

    public static MarkerDescriptor getInstance() {
        return instance;
    }

    private BitmapDescriptor getBitmapDescriptor(Bitmap newbm) {
        BitmapDescriptor bd = BitmapDescriptorFactory.fromBitmap(newbm);
        newbm.recycle();
        newbm = null;
        return bd;
    }

    public static BitmapDescriptor getAttractionsDescriptor() {
        return attractionsDescriptor;
    }

    public static BitmapDescriptor getEntertainmDescriptor() {
        return entertainmDescriptor;
    }

    public static BitmapDescriptor getDiningDescriptor() {
        return diningDescriptor;
    }

    public static BitmapDescriptor getShoppingDescriptor() {
        return shoppingDescriptor;
    }

    public static BitmapDescriptor getRestroomsDescriptor() {
        return restroomsDescriptor;
    }

    public static BitmapDescriptor getPhotopassDescriptor() {
        return photopassDescriptor;
    }

    public static BitmapDescriptor getToursDescriptor() {
        return toursDescriptor;
    }

    public static BitmapDescriptor getGuestservicesDescriptor() {
        return guestservicesDescriptor;
    }

    public static BitmapDescriptor getResortsDescriptor() {
        return resortsDescriptor;
    }

    public static BitmapDescriptor getRecreationDescriptor() {
        return recreationDescriptor;
    }

}
