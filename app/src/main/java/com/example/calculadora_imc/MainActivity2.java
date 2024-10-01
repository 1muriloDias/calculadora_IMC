package com.example.calculadora_imc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculadora_imc.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void calcularImc(View view) {
        TextInputEditText campoNome = findViewById(R.id.inputNome);
        TextInputEditText campoAltura = findViewById(R.id.inputAltura);
        TextInputEditText campoPeso = findViewById(R.id.inputPeso);
        TextView resultado1 = findViewById(R.id.resultado);   // Campo de erro
        TextView resultado2 = findViewById(R.id.textView2);   // Campo de resultado

        String nome = campoNome.getText().toString();
        String peso = campoPeso.getText().toString();
        String altura = campoAltura.getText().toString();

        // Verifica se os campos estão preenchidos e válidos
        if (peso.isEmpty() || altura.isEmpty() || !isNumeric(peso) || !isNumeric(altura)) {
            resultado1.setText("Erro: Entrada inválida.");
            resultado2.setText("");
            return;
        }

        // Converte os valores de peso e altura
        Double numPeso = Double.parseDouble(peso);
        Double numAltura = Double.parseDouble(altura) / 100;  // Altura em metros

        // Verifica se peso e altura são maiores que zero
        if (numAltura <= 0 || numPeso <= 0) {
            resultado1.setText("Erro: Valores de peso e altura devem ser positivos.");
            resultado2.setText("");
            return;
        }

        // Cálculo do IMC
        Double numImc = numPeso / (numAltura * numAltura);

        // Formatação do valor do IMC
        DecimalFormat df = new DecimalFormat("##.##");
        String imc = df.format(numImc);

        // Determinar a classificação do IMC conforme OMS
        String classificacao;

        if (numImc < 18.5) {
            classificacao = "Baixo peso";
        } else if (numImc >= 18.5 && numImc <= 24.9) {
            classificacao = "Peso normal";
        } else if (numImc >= 25 && numImc <= 29.9) {
            classificacao = "Sobrepeso";
        } else if (numImc >= 30 && numImc <= 34.9) {
            classificacao = "Obesidade Grau 1";
        } else if (numImc >= 35 && numImc <= 39.9) {
            classificacao = "Obesidade Grau 2";
        } else {
            classificacao = "Obesidade Grau 3 (Extrema)";
        }

        // Exibir o resultado e a classificação
        resultado1.setText("IMC: " + imc);
        resultado2.setText("Classificação: " + classificacao);
    }

    // Função auxiliar para verificar se a string é um número válido
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void limpaDados(View view) {
        TextInputEditText campoNome = findViewById(R.id.inputNome);
        TextInputEditText campoAltura = findViewById(R.id.inputAltura);
        TextInputEditText campoPeso = findViewById(R.id.inputPeso);
        TextView resultado1 = findViewById(R.id.resultado);
        TextView resultado2 = findViewById(R.id.textView2);

        // Limpar os campos
        campoNome.setText("");
        campoPeso.setText("");
        campoAltura.setText("");
        resultado1.setText("");
        resultado2.setText("");
    }
}