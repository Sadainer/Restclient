package com.restclient.tecnoparque.restclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.restclient.tecnoparque.restclient.ClasesAsincronas.GetAsyncrona;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText edtURI, edtData, edtRespuest;
    Spinner spiContent;
    Button btnConsumir, btnNuevo;
    RadioButton radGET, radPOST;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        spiContent = (Spinner) findViewById(R.id.spiContent);
        spiContent = (Spinner) findViewById(R.id.spiContent);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        edtData = (EditText)findViewById(R.id.edtData);
        edtRespuest = (EditText)findViewById(R.id.edtRespuesta);
        edtURI = (EditText)findViewById(R.id.edtURI);
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

                 GetAsyncrona getAsyncrona = new GetAsyncrona();
                 try {
                     String Resultado = getAsyncrona.execute("http://190.109.185.138/codigoQR/api/miembro").get();
                     edtRespuest.setText(Resultado.toString());
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 } catch (ExecutionException e) {
                     e.printStackTrace();
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
