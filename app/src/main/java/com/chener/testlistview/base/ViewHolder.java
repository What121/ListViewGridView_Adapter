package com.chener.testlistview.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class ViewHolder {

    private View mConvertView;
    private Map<Integer, View> viewMap;


    public ViewHolder(Context context, int layoutId) {
        viewMap = new HashMap<Integer, View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, null);
        mConvertView.setTag(this);
    }


    /**
     * 获取ViewHolder对象
     *
     * @param convertView
     * @return
     */
    public static ViewHolder getViewHolder(View convertView, Context context, int layoutId) {
        //先判断convertView组件是否存在，存在的话，说明ViewHolder已经创建
        if (convertView == null) {
            return new ViewHolder(context, layoutId);
        }
        return (ViewHolder) convertView.getTag();
    }

    /**
     * 获取控件
     * 由于布局不一样，控件是未知的，但是，无论是哪一个控件，他的父类都是View
     */
    public <T extends View> T getView(int viewId) {
        View view = viewMap.get(viewId); //从Map中去出控件
        if (view == null) { //说明，没有有这个控件，到布局中找
            view = mConvertView.findViewById(viewId);
            viewMap.put(viewId, view);
        }
        return (T) view;
    }


//    /**
//     * 给TextView设置值
//     */
//    public ViewHolder setText(int viewId, String text) {
//        if (viewId > 0 && text != null) {
//            View tv_Text = getView(viewId);
//            tv_Text.setText(text);
//        }
//        return this;
//    }


    public View getConvertView() {
        return mConvertView;
    }
}
