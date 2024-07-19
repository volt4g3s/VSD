package com.volt4g3s.vsd;

import android.content.*;

public class Reboot extends BroadcastReceiver {

    @Override
    public void onReceive(Context myContext, Intent myIntent) {
        
        if (Intent.ACTION_BOOT_COMPLETED.equals(myIntent.getAction())) {
            Intent intent = new Intent(myContext, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            myContext.startActivity(intent);
        }
        
    }
}