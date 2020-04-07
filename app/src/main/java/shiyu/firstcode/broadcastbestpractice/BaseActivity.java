package shiyu.firstcode.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private ForceOfflineReceiver mForceOfflineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("shiyu.firstcode.broadcastbestpractice.FORCE_OFFLINE");
        mForceOfflineReceiver=new ForceOfflineReceiver();
        registerReceiver(mForceOfflineReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mForceOfflineReceiver!=null)
        {
            unregisterReceiver(mForceOfflineReceiver);
            mForceOfflineReceiver=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(context);
            alertDialog.setTitle("Warning");
            alertDialog.setMessage("You are forced to be offline,please try to login again");
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();
                    Intent intent=new Intent(context,LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            alertDialog.show();

        }
    }
}
