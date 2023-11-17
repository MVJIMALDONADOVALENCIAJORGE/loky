package com.jorge_mauricio.loky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
   FirebaseFirestore firestore;
   EditText Name,Usuario,Password,Edad;
   Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Name = findViewById(R.id.r_Name);
        Usuario = findViewById(R.id.r_Usuario);
        Password = findViewById(R.id.r_Password);
        Edad = findViewById(R.id.r_Edad);
        boton = findViewById(R.id.r_Button);

        // creando base de datos

       boton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               firestore = FirebaseFirestore.getInstance();
               Map<String, Object> datos = new HashMap<>();
               datos.put("NOMBRE", Name.getText().toString());
               datos.put("USUARIO",Usuario.getText().toString());
               datos.put("PASSWORD",Password.getText().toString());
               datos.put("EDAD",Edad.getText().toString());

               firestore.collection("USUARIOS_TABLA")
                       .add(datos)
                       .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                           @Override
                           public void onSuccess(DocumentReference documentReference) {
                               Toast.makeText(getApplicationContext(),"Registrado", Toast.LENGTH_LONG).show();
                               Intent irChat = new Intent(Registro.this, chat.class);
                                startActivity(irChat);
                           }
                       })
                       .addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(getApplicationContext(),"Fallo registro", Toast.LENGTH_LONG).show();

                           }
                       });
               //traer datos
              
           }
       });

    }

}