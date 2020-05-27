package com.example.morsetorch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.TimeUnit;


public class TorchActivity extends AppCompatActivity{


    private CameraManager mCameraManager;
    private String mCameraId;
    private TextView result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result2 = (TextView) findViewById(R.id.result2);

        boolean isFlashAvailable = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!isFlashAvailable) {
            showNoFlashError();
        }
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void showNoFlashError() {
        AlertDialog alert = new AlertDialog.Builder(this)
                .create();
        alert.setTitle("Oops!");
        alert.setMessage("Flash not available in this device...");
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.show();
    }

    public void switchFlashLight(char c) {

        if (c == '.') {
            try {
               //CameraManager.setTorchMode(mCameraId, true);
               //CameraManager.setTorchMode(mCameraId, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (c == '-') {
            try {
                //mCameraManager.setTorchMode(mCameraId, true);
                TimeUnit.MILLISECONDS.sleep(600);
                //mCameraManager.setTorchMode(mCameraId, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void flashMorse(String morse, String alpha) {
        int i = 0;
        String[] words = morse.split(" ");
        for (String word : words) {
            System.out.println(alpha.toUpperCase().charAt(i++));
            //result2.setText(alpha.toUpperCase().charAt(i++));
            try {
                TimeUnit.MILLISECONDS.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (char c : word.toCharArray()) {
                switchFlashLight(c);
                System.out.println(c);
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}