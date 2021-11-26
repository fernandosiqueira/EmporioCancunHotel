package com.example.emporiocancunhotel.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.emporiocancunhotel.R;
import com.example.emporiocancunhotel.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class Cadastrado extends AppCompatActivity {
    Button btnScan, btnoff;
    TextView txtnome, txtcpf, txtturno, txtmatricula, txtemail;
    String nome;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    DatabaseReference referencia2 = referencia.child("users");
    DatabaseReference referenciaNome = referencia.child("users").child("279414");
    FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrado);
        btnScan = findViewById(R.id.buttonQR);
        btnoff = findViewById(R.id.buttonLogof);
        txtnome = findViewById(R.id.txtNome);
        txtcpf = findViewById(R.id.txtCPF);
        txtcpf = findViewById(R.id.txtCPF);
        txtturno = findViewById(R.id.txtTurno);
        txtmatricula = findViewById(R.id.txtMatricula);
        txtemail = findViewById(R.id.txtEmail);
        nome = referenciaNome.get().toString();

        referencia2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtnome.setText("Fernando Siqueira");
                txtcpf.setText("43509422899");
                txtturno.setText("Primeiro");
                txtmatricula.setText("279414");
                txtemail.setText("f@f.com");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoff();
            }
        });





        /*btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Camera Scanner");
                integrator.initiateScan();

            }
        });*/

    }

    public void logoff(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signOut();
        finish();
    }
}