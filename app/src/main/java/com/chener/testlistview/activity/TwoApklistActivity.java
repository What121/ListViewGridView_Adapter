package com.chener.testlistview.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.chener.testlistview.R;
import com.chener.testlistview.base.Myadapter;
import com.chener.testlistview.general.apkadapter;
import com.chener.testlistview.utils.PackageInfoManager;

import java.util.List;

public class TwoApklistActivity extends AppCompatActivity implements ListView.OnItemClickListener,ListView.OnItemLongClickListener{
    private Activity mActivity;
    private Context mContext;

    PackageManager pManager;
    private PackageInfo packageInfo;

    private ListView apklist;

    private List<PackageInfo> mlistPackage;
    private Myadapter mApkadapter;

    private PackageInfoManager mPackageInfoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apklist);

        init();
        initdata();
        initview();

    }

    private void init(){
        mActivity=this;
        mContext=this;
        mPackageInfoManager=PackageInfoManager.getInstance(mContext);
        pManager=mActivity.getPackageManager();
    }

    private void initdata(){
        mlistPackage=mPackageInfoManager.getAllApps();
        Log.i("----------",mlistPackage.size()+"");
    }

    private void initview(){
        apklist=findViewById(R.id.list_apk);
        mApkadapter= new Myadapter(mlistPackage,mContext,mActivity);
        apklist.setAdapter(mApkadapter);
        apklist.setOnItemClickListener(this);
        apklist.setOnItemLongClickListener(this);
        mApkadapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        packageInfo= mlistPackage.get(i);
        //打开第三方应用
        mPackageInfoManager.startThridApp(packageInfo.packageName);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
        packageInfo= mlistPackage.get(i);
        //卸载选中的第三方应用
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        //    指定下拉列表的显示数据
        final String[] cities = {"信息", "卸载"};
        //    设置一个下拉的列表选择项
        builder.setItems(cities, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which){
                    case 0:
                        Toast.makeText(mContext,packageInfo.applicationInfo.toString(),Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(mContext,"test unistall apk",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        builder.show();

        return false;
    }
}
