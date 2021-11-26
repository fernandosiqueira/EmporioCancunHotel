package com.example.emporiocancunhotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.emporiocancunhotel.R;
import com.example.emporiocancunhotel.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class MainActivity extends IntroActivity {
    FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);*/

        /*Removendo Botões de Nvagação*/
        setButtonBackVisible(false);
        setButtonNextVisible(false);
        /*Criando Slide*/
        addSlide(new SimpleSlide.Builder()
                .title("Aplicativo Hotel Emporio")
                .description("Uso somente para Funcionários")
                .image(R.drawable.hotel01)
                .background(android.R.color.holo_blue_light)
                .build()
        );
        addSlide(new SimpleSlide.Builder()
                .title("Aplicativo Hotel Emporio")
                .description("Qualquer duvida busque o TI")
                .image(R.drawable.hotel03)
                .background(android.R.color.holo_blue_light)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_dark)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)
                .build());


    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void verificarUsuarioLogado() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser() != null){
            abrirTelaPricipal();
        }

    }

    public void abrirTelaPricipal(){
        startActivity(new Intent(this, Cadastrado.class));
    }

    public void btEntrar (View view){
        startActivity(new Intent(this, Login.class));

    }

    public void btCadastrar (View view){
        startActivity(new Intent(this, Cadastro.class));

    }

    public void btLogin (View view){
        startActivity(new Intent(this, Login.class));
    }


}