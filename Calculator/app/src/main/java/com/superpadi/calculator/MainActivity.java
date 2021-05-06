package com.superpadi.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.mainactivity.MESSAGE";

    public String operador;
    public float num1, num2, resultado;
    private TextView textViewCuenta;
    private TextView textViewNum;
    private boolean pulsadoNumero = false, pulsadoOperador = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //Botones:
    public void boton0(View v){
        pasarNumero(0);
    }
    public void boton1(View v){
        pasarNumero(1);
    }
    public void boton2(View v){
        pasarNumero(2);
    }
    public void boton3(View v){
        pasarNumero(3);
    }
    public void boton4(View v){
        pasarNumero(5);
    }
    public void boton5(View v){
        pasarNumero(5);
    }
    public void boton6(View v){
        pasarNumero(6);
    }
    public void boton7(View v){
        pasarNumero(7);
    }
    public void boton8(View v){
        pasarNumero(8);
    }
    public void boton9(View v){
        pasarNumero(9);
    }
    public void botonPunto(View v){
        if (!pulsadoNumero) { //Si no se ha pulsado antes ningún número no se puede introducir un punto
            vaciarTextViews();
        } else {
            textViewCuenta =  (TextView) findViewById(R.id.textViewCuenta);
            textViewCuenta.setText(textViewCuenta.getText() + ".");
            textViewNum =  (TextView) findViewById(R.id.textViewNum);
            textViewNum.setText(textViewNum.getText() + ".");
        }

    }

    public void botonSuma(View v){
        if (!pulsadoNumero) { //Si no se ha pulsado ningún número antes, no se puede pulsar la suma
            vaciarTextViews();

        } else {
            pasarOperador("+");
            cambioNumero(v);
        }
    }

    public void botonResta(View v){
        if (!pulsadoNumero) { //Si no se ha pulsado ningún número antes, no se puede pulsar la resta
            vaciarTextViews();

        } else {
            pasarOperador("-");
            cambioNumero(v);
        }
    }

    public void botonMultiplicar(View v){
        if (!pulsadoNumero) { //Si no se ha pulsado ningún número antes,no se puede pulsar la multiplicación
            vaciarTextViews();

        } else {
            pasarOperador("x");
            cambioNumero(v);
        }
    }

    public void botonDividir(View v){
        if (pulsadoNumero == false) {// Si no se ha pulsado ningún número, no se puede pulsar la división
            vaciarTextViews();

        } else {
            pasarOperador("/");
            cambioNumero(v);
        }
    }

    /**
     * Método ocupado de realizar la operación una vez que los dos números han sido escritos
     * @param v
     */
    public void botonIgual(View v){
        if (!pulsadoOperador) { //Si no se ha pulsado un operador antes, no se puede pulsar igual
            vaciarTextViews();

        } else {
            textViewNum = (TextView)findViewById(R.id.textViewNum);
            num2 = Float.parseFloat(textViewNum.getText().toString());
            //Hay que detectar cuál es el operador para realizar la cuenta deseada
            if(operador.equals("+")){
                resultado =  num1 + num2;
            }   else  if(operador.equals("-")){
                resultado =  num1 - num2;
            }   else  if(operador.equals("x")){
                resultado =  num1 * num2;
            }   else  if(operador.equals("/")){
                resultado =  num1 / num2;
            }
            textViewNum.setText("" + resultado);

            //Mandar el resultado a la segunda actividad
            Intent intent = new Intent(this, ResultActivity.class);
            TextView textView = findViewById(R.id.textViewNum);
            String result = textView.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, result);
            startActivity(intent);
        }

    }

    /**
     * Método encargado de concatenar los TextViews el número que se pulse
     * @param n número que nos pasen
     */
    public void pasarNumero(int n){
        String num = "" + n;
        textViewCuenta =  (TextView) findViewById(R.id.textViewCuenta);
        textViewCuenta.setText(textViewCuenta.getText() + num);
        textViewNum =  (TextView) findViewById(R.id.textViewNum);
        textViewNum.setText(textViewNum.getText() + num);
        pulsadoNumero = true;
    }

    /**
     * Método utilizado para poner el operador en el TextViewCuenta
     * @param op
     */
    public void pasarOperador(String op){
        operador = op;
        textViewCuenta.setText(textViewCuenta.getText() + " " + op + " ");
    }

    /**
     * Método encargado de asignar lo que haya en pantalla a la variable num1 al pulsar un operador
     * @param view
     */
    public void cambioNumero(View view){
        textViewNum = (TextView) findViewById(R.id.textViewNum);
        num1 = Float.parseFloat(textViewNum.getText().toString());
        textViewNum.setText("");
        pulsadoOperador = true;
    }

    /**
     * Método encargado de vaciar los TextViews en el caso de que se pulse en principio un botón operador
     * Esto es una medida preventiva para evitar que de fallo y se salga de la app
     */
    public void vaciarTextViews(){
        textViewCuenta =  (TextView) findViewById(R.id.textViewCuenta);
        textViewCuenta.setText("");
        textViewNum =  (TextView) findViewById(R.id.textViewNum);
        textViewNum.setText("");
    }

}