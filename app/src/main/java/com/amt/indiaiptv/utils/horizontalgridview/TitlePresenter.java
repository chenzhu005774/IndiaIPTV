package com.amt.indiaiptv.utils.horizontalgridview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v17.leanback.widget.Presenter;
import android.support.v4.view.ViewCompat;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amt.indiaiptv.R;
import com.amt.indiaiptv.utils.bean.DataEntry;

public class TitlePresenter extends Presenter {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_title, viewGroup, false);
        view.findViewById(R.id.im).setAlpha(0.4f);
        view.findViewById(R.id.tv_title).setAlpha(0.4f);
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
//                    zoomIn(v,1.2f);
                    v.findViewById(R.id.im).setAlpha(1.0f);
                    v.findViewById(R.id.tv_title).setAlpha(1.0f);
                }else {
//                     zoomOut(v);
                     v.findViewById(R.id.im).setAlpha(0.4f);
                     v.findViewById(R.id.tv_title).setAlpha(0.4f);
//                    System.out.println("chenzhu--->:lose focus");
                }
            }
        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object o) {
        if (o instanceof DataEntry) {
            DataEntry dataEntry = ((DataEntry) o);
            ViewHolder vh = (ViewHolder) viewHolder;
            vh.tvTitle.setText(dataEntry.title);
            if (!dataEntry.icon.equals("")){
                byte[] decodedString = Base64.decode(dataEntry.icon.split(",")[1], Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                vh.im.setImageBitmap(zoomBitmap(decodedByte,240, 240));
            }
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }

    static class ViewHolder extends Presenter.ViewHolder {
        TextView tvTitle;
        ImageView im;

        public ViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tv_title);
            im=view.findViewById(R.id.im);
        }

    }

    /**
     * 放大动画效果
     *
     * @param itemView
     */
    public void zoomIn(View itemView, float scale) {
        if (itemView == null) {
            return;
        }
        //抬高Z轴
        ViewCompat.animate(itemView).scaleX(scale).scaleY(scale).translationZ(1).setDuration(500).start();

    }


    public void zoomOut(View itemView) {
        System.out.println("chenzhu--->:zoomOut");
        if (itemView == null) {
            System.out.println("chenzhu--->:zoomOut is null ");
            return;
        }
        ViewCompat.animate(itemView).scaleX(1.0f).scaleY(1.0f).translationZ(0).setDuration(500).start();

    }

    /**
     *  图片缩放
     * @param bitmap 对象
     * @param w 要缩放的宽度
     * @param h 要缩放的高度
     * @return newBmp 新 Bitmap对象
     */
    public   Bitmap zoomBitmap(Bitmap bitmap, int w, int h){
        int width = bitmap.getWidth();
        System.out.println("zoomBitmap:"+width);
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newBmp;
    }
}
