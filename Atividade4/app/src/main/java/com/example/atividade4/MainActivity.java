package com.example.atividade4;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button botaoSalvar;
    private Button botaoExcliur;
    private Button botaoSair;
    private Button botaoFoto;
    private EditText campoDeTexto;
    private EditText campoDeSenha;
    private TextView msg;
    private Integer idade;
    private ImageView imagem_nova;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Botão salvar
        botaoSalvar = findViewById(R.id.button5);
        Ouvinte_botaoSalvar ouvinte_botaoSalvar = new Ouvinte_botaoSalvar();
        botaoSalvar.setOnClickListener(ouvinte_botaoSalvar);

        //Campo de texto
        campoDeTexto = findViewById(R.id.editTextTextPersonName);

        // Campo de Senha
        campoDeSenha = findViewById(R.id.editTextTextPersonName3);

        // Mensagem de olá com nome
        msg = findViewById(R.id.textView);

        // Exclir mensagem
        botaoExcliur = findViewById(R.id.button4);
        Ouvinte_botaoExcluir ouvinte_botaoExcluir = new Ouvinte_botaoExcluir();
        botaoExcliur.setOnClickListener(ouvinte_botaoExcluir);

        // Sair do app
        botaoSair = findViewById(R.id.button6);
        Ouvinte_botaoSair ouvinteBotaoSair = new Ouvinte_botaoSair();
        botaoSair.setOnClickListener(ouvinteBotaoSair);

        //imagemView
        imagem_nova = findViewById(R.id.imageView);

        // botão carregar imagem
        botaoFoto = findViewById(R.id.button);
        Ouvinte_botaoFoto ouvinte_botaoFoto = new Ouvinte_botaoFoto();
        botaoFoto.setOnClickListener(ouvinte_botaoFoto);
    }

    private class Ouvinte_botaoSalvar implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String nomeCompleto = campoDeTexto.getText().toString();
            Integer SENHA = Integer.valueOf(campoDeSenha.getText().toString());
            idade = 2021 - SENHA;
            String[] nome = nomeCompleto.split(" ");
            if (idade < 10) {
                msg.setText("Olá " + nome[0] + " Crianca");
            } else if (idade >= 10 && idade < 20) {
                msg.setText("Olá " + nome[0] + " Adolescente");
            } else if (idade >= 20 && idade < 60) {
                msg.setText("Olá " + nome[0] + " Adulto");
            } else if (idade > 59) {
                msg.setText("Olá " + nome[0] + " Idoso");
            } else {
                msg.setText("Dado Invalido");
            }

        }
    }

    private class Ouvinte_botaoExcluir implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            msg.setText("Sem nome");
        }
    }

    private class Ouvinte_botaoSair implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            finish();
        }
    }

    private class Ouvinte_botaoFoto implements View.OnClickListener {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String permissao = (Manifest.permission.READ_EXTERNAL_STORAGE);
                requestPermissions(new String[]{permissao}, 1001);
            } else {
                escolherImagem();
                ;
            }
            
        }
    }

    private void escolherImagem() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult (intent,
                1000);

    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if(resultCode == RESULT_OK && resultCode == 1000){
            imagem_nova.setImageURI(data.getData());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1001: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    escolherImagem();
                } else {
                    Toast.makeText(this, "permissão negada", Toast.LENGTH_LONG).show();
                }

            }
        }
    }
}