package org.DroidUMM.kidslauncher.model;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by andika on 5/31/13.
 */
public class Applications{
    private ArrayList<AppInfo> packageList = null;
    private List<ResolveInfo> activityList = null;
    private Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
    private PackageManager packMan = null;
    private Context mContext;
    public Applications(Context mContext){
        this.mContext=mContext;
        packMan = mContext.getPackageManager();
        packageList = createPackageList(false);
        activityList = createActivityList();
        this.addClassNamesToPackageList();
    }

    public ArrayList<AppInfo> getPackageList(){
        return packageList;
    }

    public List<ResolveInfo> getActivityList(){
        return activityList;
    }

    private ArrayList<AppInfo> createPackageList(boolean getSysPackages){
        ArrayList<AppInfo> pList = new ArrayList<AppInfo>();

        List<PackageInfo> packs = mContext.getPackageManager(
        ).getInstalledPackages(0);

        for(int i = 0; i < packs.size(); i++){
            PackageInfo packInfo = packs.get(i);

            if((!getSysPackages) && (packInfo.versionName == null)){
                continue ;
            }

            AppInfo newInfo = new AppInfo();

            newInfo.appName = packInfo.applicationInfo.loadLabel(
                    mContext.getPackageManager()).toString();
            newInfo.packageName = packInfo.packageName;
            newInfo.versionName = packInfo.versionName;
            newInfo.versionCode = packInfo.versionCode;
            newInfo.icon = packInfo.applicationInfo.loadIcon(
                    mContext.getPackageManager());

            pList.add(newInfo);
        }
        return pList;
    }

    private List<ResolveInfo> createActivityList(){
        List<ResolveInfo> aList = packMan.queryIntentActivities(mainIntent, 0);

        Collections.sort(aList,
                new ResolveInfo.DisplayNameComparator(packMan));

        return aList;
    }

    private void packageDebug(){
        if(null == packageList){
            return;
        }

        for(int i = 0; i < packageList.size(); ++i){
            Log.v("PACKINFO: ", "\t" +
                    packageList.get(i).appName + "\t" +
                    packageList.get(i).packageName + "\t" +
                    packageList.get(i).className + "\t" +
                    packageList.get(i).versionName + "\t" +
                    packageList.get(i).versionCode);
        }
    }

    private void activityDebug(){
        if(null == activityList){
            return;
        }

        for(int i = 0; i < activityList.size(); i++){
            ActivityInfo currentActivity = activityList.get(
                    i).activityInfo;
            Log.v("ACTINFO",
                    "pName="
                            + currentActivity.applicationInfo.packageName +
                            " cName=" + currentActivity.name);
        }
    }

    private void addClassNamesToPackageList(){
        if(null == activityList || null == packageList){
            return;
        }

        String tempName = "";

        for(int i = 0; i < packageList.size(); ++i){
            tempName = packageList.get(i).packageName;

            for(int j = 0; j < activityList.size(); ++j){
                if(tempName.equals(activityList.get(
                        j).activityInfo.applicationInfo.packageName)){
                    packageList.get(i).className = activityList.get(
                            j).activityInfo.name;
                }
            }
        }
    }
}
