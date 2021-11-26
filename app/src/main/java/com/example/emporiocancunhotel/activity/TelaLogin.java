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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class TelaLogin extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button btnEntrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        campoEmail = findViewById(R.id.txtEmail);
        campoSenha = findViewById(R.id.editSenha);
        btnEntrar = findViewById(R.id.buttonEntrar);


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();
                if (!textoEmail.isEmpty()){
                    if (!textoSenha.isEmpty()){
                        /*Criando usuário e setando os valores*/

                        usuario = new Usuario();
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);
                        validarLogin();

                    }
                    else { Toast.makeText(TelaLogin.this, "Preencha a Senha", Toast.LENGTH_SHORT).show(); }
                }
                else { Toast.makeText(TelaLogin.this, "Preencha o Email", Toast.LENGTH_SHORT).show(); }
            }
        });

    }
    public void validarLogin (){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){  }

                else{
                    String excecao = "";
                    try {
                        throw task.getException();
                    }
                    catch (FirebaseAuthInvalidUserException e){
                        excecao = "Usuário não cadastrado";
                    }

                    catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Email e senha não correspondem ao um usuário cadastrado";
                    }

                    catch (Exception e){
                        excecao = "Erro ao acessar: " +e.getMessage();
                        e.printStackTrace();;
                    }

                    Toast.makeText(TelaLogin.this, "Erro ao fazer login",Toast.LENGTH_SHORT).show();}
            }
        });





    }

    /*public void abrirTelaPricipal(){
        startActivity(new Intent(this,Cadastrado.class));
        finish();
    }*/
}