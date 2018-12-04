package com.example.georky.ejemplofirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText txtTema;
    Spinner spinAreas, spinSecciones;
    Button btnRegistrar;
    private DatabaseReference Clases;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Clases = FirebaseDatabase.getInstance().getReference("Clases");

        txtTema=(EditText) findViewById(R.id.txttema);
        spinAreas=(Spinner) findViewById(R.id.spinarea);
        spinSecciones=(Spinner) findViewById(R.id.spinseccion);
        btnRegistrar=(Button) findViewById(R.id.btnregistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarClase();
            }
        });
    }

    public void registrarClase(){
        String seccion=spinSecciones.getSelectedItem().toString();
        String area=spinAreas.getSelectedItem().toString();
        String tema = txtTema.getText().toString();

        if (!TextUtils.isEmpty(tema)){
            String id = Clases.push().getKey();
            Clases leccion =new Clases(id, seccion,area,tema);
            Clases.child("Lecciones").child("Clase registrada Id: "+id).setValue(leccion);

            Toast.makeText(this, "Clase registrada", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Debe introducir un tema", Toast.LENGTH_LONG).show();

        }
    }
}