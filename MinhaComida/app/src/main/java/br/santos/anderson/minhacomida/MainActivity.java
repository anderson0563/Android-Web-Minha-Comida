package br.santos.anderson.minhacomida;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private StringBuilder gostou;
    RatingBar ratingBar;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText editTextTextMultiLine;
    int selectedId;
    ToggleButton toggleButton2, toggleButton3, toggleButton4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gostou = new StringBuilder();
        toggleButton2=(ToggleButton)findViewById(R.id.toggleButton2);
        toggleButton2.setBackgroundColor(Color.LTGRAY);
        toggleButton3=(ToggleButton)findViewById(R.id.toggleButton3);
        toggleButton3.setBackgroundColor(Color.LTGRAY);
        toggleButton4=(ToggleButton)findViewById(R.id.toggleButton4);
        toggleButton4.setBackgroundColor(Color.LTGRAY);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup2);
        selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        editTextTextMultiLine = (EditText)findViewById(R.id.editTextTextMultiLine);
    }

    public void onClickAvaliacao(View v){
        String ratingBarString = String.valueOf(ratingBar.getRating());
        Toast.makeText(MainActivity.this, "Avaliação:" + ratingBarString, Toast.LENGTH_LONG).show();
        if(selectedId==-1){
            Toast.makeText(MainActivity.this,"Não escolheu nada =(", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this,radioButton.getText(), Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getApplicationContext(), gostou.toString(),Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), editTextTextMultiLine.getText().toString(),Toast.LENGTH_LONG).show();
    }

    public void onSelected(View v){
        ToggleButton botao = (ToggleButton)v;
        if(botao.isChecked()) {
            botao.setBackgroundColor(Color.YELLOW);
            gostou.append("\nApertou: ").append(botao.getText());
        }else {
            botao.setBackgroundColor(Color.LTGRAY);
            gostou.append("\nLiberou: ").append(botao.getText());
        }

    }

    public void Limpar(View v){
        editTextTextMultiLine.setText("");
        radioGroup.clearCheck();
        ratingBar.setRating(0);
        toggleButton2.setChecked(false);
        toggleButton3.setChecked(false);
        toggleButton4.setChecked(false);
        toggleButton2.setBackgroundColor(Color.LTGRAY);
        toggleButton3.setBackgroundColor(Color.LTGRAY);
        toggleButton4.setBackgroundColor(Color.LTGRAY);
        gostou.delete(0, gostou.toString().length());
    }
}