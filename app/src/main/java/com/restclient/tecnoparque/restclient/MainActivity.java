package com.restclient.tecnoparque.restclient;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.restclient.tecnoparque.restclient.ClasesAsincronas.GetAsyncrona;
import com.restclient.tecnoparque.restclient.ClasesAsincronas.PostAsyncrona;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity  {

    EditText edtURI, edtData, edtRespuest;
    Spinner spiContent;
    Button btnConsumir, btnNuevo;
    RadioButton radGET, radPOST;
    RadioGroup radioGroup;
    Context cnt;

    private String URI = "https://maps.googleapis.com/maps/api/directions/json?origin=bosconia&destination=valledupar&key=AIzaSyBwuI_lYrCitgDzuaGDX7v77ZKH_8u8e2o";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        cnt = this;
        spiContent = (Spinner) findViewById(R.id.spiContent);
        spiContent = (Spinner) findViewById(R.id.spiContent);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        edtData = (EditText)findViewById(R.id.edtData);
        edtRespuest = (EditText)findViewById(R.id.edtRespuesta);
        edtURI = (EditText)findViewById(R.id.edtURI);
        radGET = (RadioButton)findViewById(R.id.radGET);
        radPOST = (RadioButton)findViewById(R.id.radPOST);
        btnConsumir = (Button)findViewById(R.id.butConsumir);
        btnNuevo = (Button)findViewById(R.id.butNuevo);

        final String[] content = new String[]{"application/json"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, content);
        spiContent.setAdapter(adaptador);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId) {
                    case R.id.radGET:
                        edtData.setEnabled(false);
                        break;
                    case R.id.radPOST:
                        edtData.setEnabled(true);
                        break;
                }

            }
        });
         btnConsumir.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                  URI = edtURI.getText().toString();

                 if (radGET.isChecked()){
                     GetAsyncrona getAsyncrona = (GetAsyncrona) new GetAsyncrona(cnt, new GetAsyncrona.AsyncResponse() {
                         @Override
                         public void processFinish(String output) {
                             Log.e("","1");
                             if (output.equals("")){
                                 Toast.makeText(cnt,"Respuesta no contiene datos",Toast.LENGTH_SHORT).show();
                             }
                             edtRespuest.setText(output);
                         }
                     }).execute(URI);
                 }else if (radPOST.isChecked()){
                     Log.e("","2");
                     String Data = edtData.getText().toString();
                     Log.e("","3");
                     PostAsyncrona postAsyncrona = new PostAsyncrona(Data, cnt, new PostAsyncrona.AsyncResponse() {
                         @Override
                         public void processFinish(String output) {
                             Log.e("","4");
                             if (output.equals("")){
                                 Toast.makeText(cnt,"Respuesta no contiene datos",Toast.LENGTH_SHORT).show();
                             }
                             Log.e("","4");
                             edtRespuest.setText(output);
                         }
                     });
                 }



             }
         });
            btnNuevo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(getIntent());
                }
            });
    }


}
