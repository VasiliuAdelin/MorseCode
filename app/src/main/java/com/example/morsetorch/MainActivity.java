package com.example.morsetorch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txt;
    private TextView result;
    private Button toMorseBtn;
    private Button toAlphaBtn;
    private Button flashBtn;
    private Button msgBtn;
    static String lastStringAlph= "";
    static String lastStringMorse= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.txt);
        result = (TextView) findViewById(R.id.result);
        toMorseBtn = (Button) findViewById(R.id.toMorseBtn);
        toAlphaBtn = (Button) findViewById(R.id.toAlphaBtn);
        flashBtn = (Button) findViewById(R.id.flash);
        msgBtn = (Button) findViewById(R.id.msg);

        flashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TorchActivity torch = new TorchActivity();
                if(lastStringMorse != "")
                    torch.flashMorse(lastStringMorse,lastStringAlph);
                else
                {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "You need to first encrypt a message.", duration);
                    toast.show();
                }

            }
        });

        toMorseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtToConvert = txt.getText().toString();
                lastStringAlph = txtToConvert;
                String convertedTxt = MorseCode.alphaToMorse(txtToConvert);
                if(convertedTxt != null)
                    lastStringMorse = convertedTxt;
                result.setText(convertedTxt);
            }
        });

        toAlphaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtToConvert = txt.getText().toString();
                String convertedTxt = MorseCode.morseToAlpha(txtToConvert);
                lastStringAlph = convertedTxt;
                result.setText(convertedTxt);
            }
        });

        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:"));
                intent.putExtra("sms_body", "MorseCode encrypted: \n" +lastStringMorse);
                startActivity(intent);
            }
        });
    }
}
