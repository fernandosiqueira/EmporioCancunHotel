package com.example.emporiocancunhotel.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.emporiocancunhotel.R;
import com.example.emporiocancunhotel.config.ConfiguracaoFirebase;
import com.example.emporiocancunhotel.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastro extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha, campoMatricula, campoTurno, campoCPF;
    private Button botaoCadastrar;
    /*Criando conexões com firebase*/
    private FirebaseAuth autenticacao;
    private Usuario usuario;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    DatabaseReference referencia2 = referencia.child("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        /*Instanciando Variaveis*/
        campoNome = findViewById(R.id.txtNome);
        campoEmail = findViewById(R.id.txtEmail);
        campoSenha = findViewById(R.id.editSenha);
        campoCPF = findViewById(R.id.editCpf);
        campoMatricula = findViewById(R.id.txtMatricula);
        campoTurno = findViewById(R.id.txtTurno);
        botaoCadastrar = findViewById(R.id.buttonEntrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoNome = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();
                String textoCPF = campoCPF.getText().toString();
                String textoMatricula = campoMatricula.getText().toString();
                String textoTurno = campoTurno.getText().toString();



                /*Validar campos preenchidos*/

                if (!textoNome.isEmpty()) {
                    if (!textoEmail.isEmpty()){
                        if (!textoSenha.isEmpty()){
                            /*Criando usuário e setando os valores*/
                            usuario =  new Usuario();
                            usuario.setNome(textoNome);
                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textoSenha);
                            usuario.setCpf(textoCPF);
                            usuario.setMatricula(textoMatricula);
                            usuario.setTurno(textoTurno);
                            referencia2.child(textoEmail).setValue(usuario);
                            cadastrarUsuario();
                        }
                        else { Toast.makeText(Cadastro.this, "Preencha a Senha", Toast.LENGTH_SHORT).show(); }
                    }
                    else { Toast.makeText(Cadastro.this, "Preencha o Email", Toast.LENGTH_SHORT).show(); }
                }
                else { Toast.makeText(Cadastro.this, "Preencha o Nome", Toast.LENGTH_SHORT).show(); }
        }});

    }

    public void cadastrarUsuario (){
        /**/
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                    {
                        finish();
                    }



            }
        });



    }
}