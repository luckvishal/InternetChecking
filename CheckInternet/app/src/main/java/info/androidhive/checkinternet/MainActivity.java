package info.androidhive.checkinternet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    private Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCheck = (Button) findViewById(R.id.btn_check);
       // fab = (FloatingActionButton) findViewById(R.id.fab);

        // Manually checking internet connection
        checkConnection();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Manually checking internet connection
                checkConnection();
            }
        });


    }

    private void checkConnection() {
        ConnectivityReceiver connectivityReceiver = new ConnectivityReceiver(MainActivity.this);
        connectivityReceiver.isConnected();
        //showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {

        if (isConnected) {
            Toast.makeText(getApplicationContext(),"Good! Connected to Internet",Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getApplicationContext(),"Sorry! Not connected to internet",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
     //   MyApplication.getInstance().setConnectivityListener(this);
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
//    @Override
//    public void onNetworkConnectionChanged(boolean isConnected) {
//        showSnack(isConnected);
//    }
}
