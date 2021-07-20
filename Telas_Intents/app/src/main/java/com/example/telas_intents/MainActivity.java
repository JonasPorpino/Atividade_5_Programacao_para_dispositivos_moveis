package com.example.telas_intents;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private Button bt_tela1;
    private EditText Nome_da_Pessoa;
    private EditText data_ano;
    private Integer idade;
    private TextView texto_idade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // botão
        bt_tela1 = findViewById(R.id.button);
        bt_tela1.setOnClickListener(new Ouvinte_bt_tela1());

        // Texto com o nome
        Nome_da_Pessoa = findViewById(R.id.editTextTextPersonName);

        //
        data_ano = findViewById(R.id.editTextNumber);

        texto_idade = findViewById(R.id.textView4);

        String msg = getIntent().getStringExtra("c");

        if (msg != null){
            texto_idade.setText(msg);
        }


    }
    private class Ouvinte_bt_tela1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            
            Intent intent = new Intent(MainActivity.this, Outra_Tela.class);

            Integer ano = Integer.valueOf(data_ano.getText().toString());
            idade = 2021 - ano;
            String nome = Nome_da_Pessoa.getText().toString();

            Bundle bundle = new Bundle();

            bundle.putString("m",nome);
            bundle.putInt("a",idade);
            intent.putExtras(bundle);

            startActivity(intent);
        }
    }
}