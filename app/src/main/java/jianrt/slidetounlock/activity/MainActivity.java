package jianrt.slidetounlock.activity;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import jianrt.slidetounlock.R;
import jianrt.slidetounlock.service.LockService;

public class MainActivity extends Activity {
  public KeyguardManager keyguardManager = null;
  public KeyguardManager.KeyguardLock keyguardLock = null;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initKeyguardManager();
  }

  private void initKeyguardManager() {
    keyguardManager =
        (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
    keyguardLock = keyguardManager.newKeyguardLock("");
    keyguardLock.disableKeyguard();//取消系统锁屏
    startService(new Intent(MainActivity.this, LockService.class));
  }
}
