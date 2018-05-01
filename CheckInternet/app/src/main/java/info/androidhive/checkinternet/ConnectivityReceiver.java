package info.androidhive.checkinternet;

/**
 * Created by Lincoln on 18/03/16.
 */

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class ConnectivityReceiver
        extends BroadcastReceiver {

    public static ConnectivityReceiverListener connectivityReceiverListener;
    public static Context context;
    public boolean returnCheckValue = false;
    public ConnectivityReceiver() {
        super();
    }

    public ConnectivityReceiver(MainActivity mainActivity) {
        this.context = mainActivity;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (connectivityReceiverListener != null) {
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }
    }

    @Override
    public void onReceive(Context context, Intent arg1) {

    }

    public void isConnected() {

        ConnectivityManager
                cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();


        if (isConnected){
            new RunToCheckInternet().execute("www.google.com");

        }
        else {
            Toast.makeText(context,"Internet not connected",Toast.LENGTH_SHORT).show();
        }

        //return returnCheckValue;
    }


    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }


    private  class RunToCheckInternet extends AsyncTask<String, Integer, Boolean> {

        ProgressDialog progressDialog;
        protected Boolean doInBackground(String...params ) {
            publishProgress(5);
            try {
                //make a URL to a known source
                URL url = new URL("http://www.google.com");

                //open a connection to that source
                HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();

                //trying to retrieve data from the source. If there
                //is no connection, this line will fail

                if(urlConnect.getResponseMessage().equals("OK")){
                   return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
                return  false;
            }
            return false;
        }

            @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            checkNetAccess(result);
            if(result)
            Toast.makeText(context,"Internet connected",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context,"Internet not connected",Toast.LENGTH_SHORT).show();
            //return false;

        }
        //        @Override
//        protected boolean onPostExecute(boolean result) {
//           //progressDialog.dismiss();
//           checkNetAccess(result);
//           return result;
//        }
    }


    private boolean checkNetAccess(boolean result){
        returnCheckValue = result;
        return result;
    }

}