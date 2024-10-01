package com.example.calculadora_imc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

class MainActivity2 extends AppCompatActivity { // Adicionado "public" à classe
    private Button buttonsobreposicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonsobreposicao = findViewById(R.id.btn_proximo); // Verifique se o ID está correto
        buttonsobreposicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarSobreposicao();
            }
        });
    }

    private void mostrarSobreposicao() {
        // Aqui vai o código que você quer executar ao clicar no botão
    }
}
