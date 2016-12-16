package com.yyxk.socketdemo;

import android.Manifest;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.IBinder;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;


/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * 项目名称：SocketDemo
 * 包名:com.yyxk.socketdemo
 * 类描述：
 * 创建人：Random
 * 创建时间：12:05
 * 修改人：Random
 * 修改时间：12:05
 * 修改备注：
 */
public class MyBroadcast extends BroadcastReceiver {
    private Context mContext;
    private String incoming_number;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        TelephonyManager tm =
                (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);

        switch (tm.getCallState()) {
            case TelephonyManager.CALL_STATE_RINGING:
                incoming_number = intent.getStringExtra("incoming_number");
                Log.i("tagggg", "RINGING :" + incoming_number);
                if (incoming_number != null && incoming_number.equals("13666666666")) {
                    rejectCall();//挂断电话
                }
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                //在这里可以添加删除一个最近通话的方法
                break;
        }
    }

    /**
     * 挂断电话
     */
    public void rejectCall() {
        try {
            Method method = Class.forName("android.os.ServiceManager")
                    .getMethod("getService", String.class);
            IBinder binder = (IBinder) method.invoke(null, new Object[]{Context.TELEPHONY_SERVICE});
            ITelephony telephony = ITelephony.Stub.asInterface(binder);
            telephony.endCall();
            Log.i("tagggg","RINGING :"+ incoming_number);

        } catch (NoSuchMethodException e) {
            Log.i("tagggg",e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.i("tagggg",e.getMessage());
        } catch (Exception e) {
            Log.i("tagggg",e.getMessage());
        }
    }

}
