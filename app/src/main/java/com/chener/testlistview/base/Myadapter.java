package com.chener.testlistview.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chener.testlistview.R;

import java.util.List;

public class Myadapter extends BaseAdapter {
    private  List<PackageInfo> mData;
    private Context context;
    private Activity mActivity;
    PackageInfo packageInfo;
    ApplicationInfo applicationInfo;
    PackageManager pManager;

    public Myadapter(List<PackageInfo> data, Context context, Activity mActivity) {
        mData = data;
        this.context = context;
        this.mActivity=mActivity;
        pManager = context.getPackageManager();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup viewGroup) {
        packageInfo= mData.get(postion);
        applicationInfo= packageInfo.applicationInfo;
        ViewHolder holder = ViewHolder.getViewHolder(convertView, context, R.layout.apk_item); //获取ViewHolder对象

        ImageView imageView= holder.getView(R.id.apk_img);
        imageView.setImageDrawable(pManager.getApplicationIcon(applicationInfo));
        TextView textView=holder.getView(R.id.apk_name);
        textView.setText(pManager.getApplicationLabel(applicationInfo).toString());

        return holder.getConvertView();
    }


}
