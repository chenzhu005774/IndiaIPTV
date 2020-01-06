package com.amt.indiaiptv.utils.horizontalgridview;

import android.support.v17.leanback.widget.Presenter;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amt.indiaiptv.R;

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
        if (o instanceof Title) {
            ViewHolder vh = (ViewHolder) viewHolder;
            vh.tvTitle.setText(((Title) o).getName());
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
}
