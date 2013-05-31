package org.DroidUMM.kidslauncher.model;

import android.graphics.drawable.Drawable;

/**
 * Created by andika on 5/31/13.
 */
public class AppInfo{
     String appName = "";
     String packageName = "";
     String className = "";
     String versionName = "";
     Integer versionCode = 0;
     Drawable icon = null;


    public String getAppName(){
        return appName;
    }

    public String getPackageName(){
        return packageName;
    }

    public String getClassName(){
        return className;
    }

    public String getVersionName(){
        return versionName;
    }

    public Integer getVersionCode(){
        return versionCode;
    }

    public Drawable getIcon(){
        return icon;
    }
}