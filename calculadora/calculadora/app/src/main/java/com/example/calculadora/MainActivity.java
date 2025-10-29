package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fathzer.soft.javaluator.DoubleEvaluator;



public class MainActivity extends AppCompatActivity {
    private TextView tvRes; // mostrar el resultat
    private StringBuilder expressio = new StringBuilder(); // ex: "33+5+15")

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

        tvRes = findViewById(R.id.tvRes);
        Button btn0 = findViewById(R.id.button0);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);
        Button btn7 = findViewById(R.id.button7);
        Button btn8 = findViewById(R.id.button8);
        Button btn9 = findViewById(R.id.button9);

        Button btnPlus = findViewById(R.id.buttonPlus);
        Button btnMinus = findViewById(R.id.buttonMinus);
        Button btnEquals = findViewById(R.id.buttonEquals);
        Button btnTimes = findViewById(R.id.buttonX);
        Button btnBetween = findViewById(R.id.buttonSlash);
        Button btnDeleteOne = findViewById(R.id.buttonBack);
        Button btnDeleteAll = findViewById(R.id.buttonDeleteAll);
        Button btnParentesisObert = findViewById(R.id.buttonParentesis1);
        Button btnParentesisTancat = findViewById(R.id.buttonParentesis2);
        Button btnDot = findViewById(R.id.buttonDecimal);


        // Listeners
        btn0.setOnClickListener(v -> afegirNum("0"));
        btn1.setOnClickListener(v -> afegirNum("1"));
        btn2.setOnClickListener(v -> afegirNum("2"));
        btn3.setOnClickListener(v -> afegirNum("3"));
        btn4.setOnClickListener(v -> afegirNum("4"));
        btn5.setOnClickListener(v -> afegirNum("5"));
        btn6.setOnClickListener(v -> afegirNum("6"));
        btn7.setOnClickListener(v -> afegirNum("7"));
        btn8.setOnClickListener(v -> afegirNum("8"));
        btn9.setOnClickListener(v -> afegirNum("9"));

        btnPlus.setOnClickListener(v -> operacio());
        btnEquals.setOnClickListener(v -> evaluar());
        btnMinus.setOnClickListener(v -> negativa());
        btnDeleteAll.setOnClickListener(v -> deleteAll());
        btnParentesisObert.setOnClickListener(v -> parentesiObert());
        btnParentesisTancat.setOnClickListener(v -> parentesiTancat());
        btnTimes.setOnClickListener(v->multiplicacio());
        btnBetween.setOnClickListener(v->divisio());
        btnDeleteOne.setOnClickListener(v->eliminarUnCaracter());
        btnDot.setOnClickListener(v->decimal());


        actualitzar();
    }

    private boolean isLastCharSymbol() {
        if (expressio.length() < 0){
            return true;
        }

        char[] symbols = {'+','-','*','(',')','/','.'};
        for (char c: symbols) {
            if (expressio.charAt(expressio.length()-1) == c){
                return true;
            }
        }

        return false;
    }


    private void  deleteAll(){
        expressio.delete(0,expressio.length());
        actualitzar();
    }

    private void afegirNum(String c) {
        expressio.append(c);
        tvRes.setText(expressio.toString());
        actualitzar();
    }

    private void operacio() {
        if (!isLastCharSymbol()){
            expressio.append("+");
            tvRes.setText(expressio.toString());
            actualitzar();
        }

    }

    private void decimal() {
        if (!isLastCharSymbol()) {
            expressio.append(".");
            tvRes.setText(expressio.toString());
            actualitzar();
        }
    }

    private void negativa() {
        if (!isLastCharSymbol()) {
            expressio.append("-");
            tvRes.setText(expressio.toString());
            actualitzar();
        }
    }

    private void divisio() {
        if (!isLastCharSymbol()) {
            expressio.append("/");
            tvRes.setText(expressio.toString());
            actualitzar();
        }
    }

    private void multiplicacio() {
        if (!isLastCharSymbol()) {
            expressio.append("*");
            tvRes.setText(expressio.toString());
            actualitzar();
        }
    }

    private void parentesiObert() {

            expressio.append("(");
            tvRes.setText(expressio.toString());
            actualitzar();

    }

    private void parentesiTancat() {

            expressio.append(")");
            tvRes.setText(expressio.toString());
            actualitzar();

    }

    private void eliminarUnCaracter() {
        if (!isLastCharSymbol()) {
                tvRes.setText(expressio.deleteCharAt(expressio.length() - 1).toString());
                actualitzar();
        }
    }



    private void evaluar() {
        // https://mvnrepository.com/artifact/com.fathzer/javaluator
        // https://github.com/fathzer/javaluator  3.0.6
        // "(2^3-1)*sin(pi/4)/ln(pi^2)" = 2.1619718020347976

        try {
            // evaluem
            DoubleEvaluator evaluator = new DoubleEvaluator();
            Double result = evaluator.evaluate(expressio.toString());

            // mostrem resultat
            // try/catch
            tvRes.setText(result.toString());
            expressio.setLength(0);

        } catch (Exception e) {
            deleteAll();
            actualitzar();
            tvRes.setText("Syntax Error");
        }


    }

    private void actualitzar() {
        tvRes.setText(expressio.toString());
    }
}