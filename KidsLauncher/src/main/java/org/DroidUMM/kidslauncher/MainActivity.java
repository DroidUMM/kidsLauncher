package org.DroidUMM.kidslauncher;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.DroidUMM.kidslauncher.adapter.ApplicationAdapter;
import org.DroidUMM.kidslauncher.model.AppInfo;
import org.DroidUMM.kidslauncher.model.Applications;

import java.util.ArrayList;

public class MainActivity extends Activity {

    GridView mGridView;
    ArrayList<AppInfo> packageList;
    ApplicationAdapter appAdapter;
    Applications myApps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView=(GridView)findViewById(R.id.gridView);

        packageList = new ArrayList<AppInfo>();
        appAdapter = new ApplicationAdapter(this,
                packageList);

        mGridView.setAdapter(appAdapter);

        PackageManager manager  = getPackageManager();
        ComponentName compName = new ComponentName("org.DroidUMM.kidslauncher", "org.DroidUMM.kidslauncher.MainActivityAlias");
//ComponentName compName = new ComponentName("<Package Name>", "<Package Name.Alias Name>");
        manager.setComponentEnabledSetting(compName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);


       getApps();

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Utilities.launchApp(MainActivity.this,getPackageManager(), packageList.get(i).getPackageName());
            }
        });

    }


    private void getApps(){

            myApps = new Applications(this);
            packageList = myApps.getPackageList();

        this.runOnUiThread(returnRes);
    }


    private Runnable returnRes = new Runnable(){
        public void run(){
            if(packageList != null && packageList.size() > 0){
                appAdapter.notifyDataSetChanged();

                for(int i = 0; i < packageList.size(); ++i){
                    appAdapter.add(packageList.get(i));
                }
            }

            appAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:

                    return true;
                case KeyEvent.KEYCODE_HOME:
                    return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }

}
