package br.santos.anderson.minhacomida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import br.santos.anderson.minhacomida.infra.Conexao;
import br.santos.anderson.minhacomida.modelo.Pedido;

public class MainActivity extends AppCompatActivity  implements ServiceConnection {
    private StringBuilder gostou;
    RatingBar ratingBar;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText depoimento;
    int selectedId;
    ToggleButton pontualidade, atendimento, sabor;
    private Conexao logar;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gostou = new StringBuilder();
        pontualidade =(ToggleButton)findViewById(R.id.toggleButton2);
        pontualidade.setBackgroundColor(Color.LTGRAY);
        atendimento =(ToggleButton)findViewById(R.id.toggleButton3);
        atendimento.setBackgroundColor(Color.LTGRAY);
        sabor =(ToggleButton)findViewById(R.id.toggleButton4);
        sabor.setBackgroundColor(Color.LTGRAY);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);



        depoimento = (EditText)findViewById(R.id.editTextTextMultiLine);
        String avaliacao = "0.0";
    }

    public void onClickAvaliacao(View v){
        Pedido pedido;
        gostou.append("");
        String entrega = "";
        String avaliacao = String.valueOf(ratingBar.getRating());
        Toast.makeText(MainActivity.this, "Avaliação:" + avaliacao, Toast.LENGTH_LONG).show();
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup2);
        selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        if(selectedId==-1){
            Toast.makeText(MainActivity.this,"Não escolheu nada =(", Toast.LENGTH_SHORT).show();
            entrega = "Não escolheu nada =(";
        }
        else{
            Toast.makeText(MainActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
            entrega = radioButton.getText().toString();
        }
        Toast.makeText(getApplicationContext(), gostou.toString(),Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), depoimento.getText().toString(),Toast.LENGTH_LONG).show();
        pedido = new Pedido(gostou, avaliacao, entrega, depoimento.getText().toString(), pontualidade.isChecked()?"Pontualidade":"",
                atendimento.isChecked()?"Atendimento":"", sabor.isChecked()?"Sabor":"");
        System.out.println(pedido);
        //envio para o servidor
        boolean status = logar.cadastrar(pedido.toString());
    }

    public void onSelected(View v){
        ToggleButton botao = (ToggleButton)v;
        if(botao.isChecked()) {
            botao.setBackgroundColor(Color.YELLOW);
            gostou.append("Apertou: ").append(botao.getText());
        }else {
            botao.setBackgroundColor(Color.LTGRAY);
            gostou.append("Liberou: ").append(botao.getText());
        }

    }

    public void Limpar(View v){
        depoimento.setText("");
        radioGroup.clearCheck();
        ratingBar.setRating(0);
        pontualidade.setChecked(false);
        atendimento.setChecked(false);
        atendimento.setChecked(false);
        pontualidade.setBackgroundColor(Color.LTGRAY);
        atendimento.setBackgroundColor(Color.LTGRAY);
        atendimento.setBackgroundColor(Color.LTGRAY);
        gostou.delete(0, gostou.toString().length());
    }

    @Override
    protected void onResume() {
        super.onResume();
        intent= new Intent(this, Conexao.class);
        bindService(intent, this , Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Conexao.MyBinder b = (Conexao.MyBinder) iBinder;
        logar = b.getService();

    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        logar=null;
    }
}