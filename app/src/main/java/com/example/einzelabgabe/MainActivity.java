package com.example.einzelabgabe;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    EditText nr;
    TextView txt;
    Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         btn = (Button) findViewById(R.id.click);
         txt = (TextView) findViewById(R.id.Textfeld);
         nr = (EditText) findViewById(R.id.number);
         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(Internetaktiv() && v== btn){

                     senden(nr.getText().toString());

                 } else{
                     Toast.makeText(getApplicationContext()," Keine Verbindung",Toast.LENGTH_SHORT).show();
                 }

             }
         });


    }

    public void senden(String Text){
        Socket s;
        DataOutputStream dataOutputStream;
        PrintWriter printWriter;
        String message= "text";


        try {

            s = new Socket("se2-isys.aau.at", 53212);
            printWriter = new PrintWriter(s.getOutputStream());
            printWriter.write(message);
            printWriter.flush();
            printWriter.close();
            s.close();
            InputStream inputStream=s.getInputStream();

        }catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getTextfromInputStream(InputStream inputStream){
        BufferedReader reader= new BufferedReader(new InputStreamReader(inputStream));

        String message= "";

        return message;


    }

    public boolean Internetaktiv(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }






    }




