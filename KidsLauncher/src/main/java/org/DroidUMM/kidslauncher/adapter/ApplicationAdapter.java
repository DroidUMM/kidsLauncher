package org.DroidUMM.kidslauncher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.DroidUMM.kidslauncher.R;
import org.DroidUMM.kidslauncher.model.AppInfo;

import java.util.ArrayList;

/**
 * Created by andika on 5/31/13.
 */
public class ApplicationAdapter extends ArrayAdapter<AppInfo> {
    private ArrayList<AppInfo> items;
    private Context mContext;
    public ApplicationAdapter(Context context,
                              ArrayList<AppInfo> items){
        super(context, R.layout.grid_item, items);
        this.items = items;
        this.mContext=context;
    }

    public View getView(int position, View convertView,
                        ViewGroup parent){
        View view = convertView;

        if(view == null){
            LayoutInflater layout = (LayoutInflater)mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = layout.inflate(R.layout.grid_item, null);
        }

        AppInfo appInfo = items.get(position);
        if(appInfo != null){
            TextView appName = (TextView) view.findViewById(
                    R.id.textView);
            ImageView appIcon = (ImageView) view.findViewById(
                    R.id.imageView);

            if(appName != null){
                appName.setText(appInfo.getAppName());
            }
            if(appIcon != null){
                appIcon.setImageDrawable(appInfo.getIcon());
            }
        }

        return view;
    }
}



