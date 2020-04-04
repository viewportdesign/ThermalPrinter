package com.viewport.cordova.plugin;
// The native Toast API
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.imagpay.PrnStrFormat;
import com.imagpay.Settings;
import com.imagpay.SwipeListener;
import com.imagpay.enums.PrnTextFont;
import com.imagpay.mpos.MposHandler;

import android.content.Context;
import android.util.Log;
import android.os.Message;
import android.os.Handler;



public class ThermalPrinterPlugin extends CordovaPlugin {
	
  private static final String DURATION_LONG = "long";
   private static String TAG = "PosDemo";
   private MposHandler handler;
   private Settings setting;
   private Context context;


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 101:
                    /*
                    Toast.makeText(cordova.getActivity(),
                            "Printing now,pls wait for a moment", Toast.LENGTH_LONG)
                            .show();

                     */

                    break;

                default:
                    break;
            }
        };
    };



    @Override
  public boolean execute(String action, JSONArray args,
    final CallbackContext callbackContext) {



      String message;
      String duration;
      try {
        JSONObject options = args.getJSONObject(0);
        message = options.getString("message");
        duration = options.getString("duration");
      } catch (JSONException e) {
        callbackContext.error("Error encountered: " + e.getMessage());
        return false;
      }


      if( action.equals("init") )
      {
          initSDK();
      }


      if( action.equals("print") )
      {
          sendMessage("Trying to Print this message:"+message);
          print(message);
      }


        // Send a positive result to the callbackContext
      PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
      callbackContext.sendPluginResult(pluginResult);
      return true;


    }



    /**** SDK ***/
    private void initSDK() {

        context = this.cordova.getActivity();

        // Init SDK,call singleton function,so that you can keeping on the
        // connect in the whole life cycle
        handler = MposHandler.getInstance(context);
        handler.setShowLog(true);

        sendMessage("Handler is:" +handler);

        setting = Settings.getInstance(handler);
        // power on the device when you need to read card or print
        setting.mPosPowerOn();

        sendMessage("Setting is:" +setting);

        try {
            // for 90,delay 1S and then connect
            // Thread.sleep(1000);
            // connect device via serial port
            if (!handler.isConnected()) {
                sendMessage("Connect Res:" + handler.connect());
            } else {
                handler.close();
                sendMessage("ReConnect Res:" + handler.connect());
            }
        } catch (Exception e) {
            sendMessage(e.getMessage());

        }

        // add linstener for connection
        //handler.addSwipeListener((SwipeListener) context);

        // add linstener for read IC chip card
        // handler.addEMVListener(this);
    }



    protected void sendMessage(String string) {
        Log.i(TAG, "==>:" + string);
    }




    private void print(String message) {


        StringBuffer receipts = new StringBuffer();

        receipts.append(message);


        setting.prnStr(receipts.toString());
        setting.prnStart();

        sendMessage("Done Printing");

    }








}