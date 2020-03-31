package com.blq.ssnb.baseconfigure.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blq.ssnb.baseconfigure.R;

import java.util.ArrayList;
import java.util.List;

import blq.ssnb.baseconfigure.pinchimage.IImageBean;
import blq.ssnb.baseconfigure.pinchimage.ImageWallActivity;
import blq.ssnb.baseconfigure.pinchimage.PinchImageVPAdapter;
import blq.ssnb.snbview.SnbPinchImageView;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2020-01-08
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class MyImageWallActivity extends ImageWallActivity<MyImageWallActivity.SnbImage> {

    @Override
    protected void onVpBindView(PinchImageVPAdapter<SnbImage> vpAdapter, SnbPinchImageView picImage, int position) {
        IImageBean iImageBean = vpAdapter.getData().get(position);
        Integer integer = Integer.valueOf(iImageBean.getImgUrl());
        picImage.setImageResource(integer);
    }

    @Override
    protected List<SnbImage> bindImageData() {
        List<SnbImage> snbImages = new ArrayList<>();
        snbImages.add(new SnbImage(R.drawable.vateral_1));
        snbImages.add(new SnbImage(R.drawable.vateral_2));
        snbImages.add(new SnbImage(R.drawable.vateral_3));
        snbImages.add(new SnbImage(R.drawable.vateral_4));
        snbImages.add(new SnbImage(R.drawable.vateral_5));
        snbImages.add(new SnbImage(R.drawable.vateral_6));
        snbImages.add(new SnbImage(R.drawable.vateral_7));
        return snbImages ;
    }


    public static class SnbImage implements IImageBean {
        private int id;

        public SnbImage(int id) {
            this.id = id;
        }

        @Override
        public String getImgUrl() {
            return id + "";
        }
    }
}
