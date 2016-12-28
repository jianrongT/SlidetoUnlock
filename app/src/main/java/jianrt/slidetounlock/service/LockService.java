package jianrt.slidetounlock.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import jianrt.slidetounlock.activity.LockMainActivity;

public class LockService extends Service {

  @Override public void onCreate() {
    // TODO Auto-generated method stub
    super.onCreate();
    LockService.this.registerReceiver(mScreenOffReceiver,
        new IntentFilter(Intent.ACTION_SCREEN_OFF));
  }

  @Override public void onDestroy() {
    super.onDestroy();
    unregisterComponent();
  }

  @Override public IBinder onBind(Intent arg0) {
    return null;
  }

  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    return Service.START_STICKY;
  }

  private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() {
    @Override public void onReceive(Context context, Intent intent) {
      if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF) || intent.getAction()
          .equals(Intent.ACTION_SCREEN_ON)) {
        Intent intent1 = new Intent(LockService.this, LockMainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent1);
      }
    }
  };

  public void unregisterComponent() {
    // TODO Auto-generated method stub
    if (mScreenOffReceiver != null) {
      LockService.this.unregisterReceiver(mScreenOffReceiver);
    }
  }
}
