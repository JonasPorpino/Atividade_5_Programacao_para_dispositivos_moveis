package com.example.telas_intents;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DirectAction;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Outra_Tela extends AppCompatActivity {
    private Button bt_tela2;
    private TextView tv_tela2;
    private String msg_outra_tela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra_tela);


        Intent intent = getIntent();
        Bundle msg = intent.getExtras();

        bt_tela2 = findViewById(R.id.button2);
        bt_tela2.setOnClickListener(new Ouvinte_tela2());


        // textView da tela 2
        tv_tela2 = findViewById(R.id.textView3);

        if (msg != null){
            String nome = msg.getString("m");
            int idade = msg.getInt("a");
            tv_tela2.setText("nome: "+nome+" idade: "+idade);

            if (idade < 10){
                msg_outra_tela = ("Olá "+nome+" você é Crianca");
            }else if (idade >=10 && idade < 20){
                msg_outra_tela = ("Olá "+nome+" você é Adolescente");
            }else if (idade >=20 && idade < 60){
                msg_outra_tela = ("Olá "+nome+" você é Adulto");
            }else if (idade > 59){
                msg_outra_tela = ("Olá "+nome+" você é Idoso");
            }else {
                msg_outra_tela = ("Dado Invalido");
            }
        }

    }

    private class Ouvinte_tela2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Outra_Tela.this, MainActivity.class);

            String classificacao;
            classificacao = msg_outra_tela;

            intent.putExtra("c",classificacao);

            startActivity(intent);

            finish();
        }
    }
}