package br.santos.anderson.minhacomida.modelo;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.ToggleButton;

public class Pedido {
    StringBuilder gostou;
    String avaliacao;
    String entrega;
    String depoimento;
    String pontualidade, atendimento, sabor;

    public Pedido(StringBuilder gostou, String avaliacao, String entrega, String depoimento, String pontualidade, String atendimento, String sabor) {
        this.gostou = gostou;
        this.avaliacao = avaliacao;
        this.entrega = entrega;
        this.depoimento = depoimento;
        this.pontualidade = pontualidade;
        this.atendimento = atendimento;
        this.sabor = sabor;
    }

    public StringBuilder getGostou() {
        return gostou;
    }

    public void setGostou(StringBuilder gostou) {
        this.gostou = gostou;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public String getDepoimento() {
        return depoimento;
    }

    public void setDepoimento(String depoimento) {
        this.depoimento = depoimento;
    }

    public String getPontualidade() {
        return pontualidade;
    }

    public void setPontualidade(String pontualidade) {
        this.pontualidade = pontualidade;
    }

    public String getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(String atendimento) {
        this.atendimento = atendimento;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "gostou='" + gostou + '\'' +
                ", avaliacao='" + avaliacao + '\'' +
                ", entrega='" + entrega + '\'' +
                ", depoimento='" + depoimento + '\'' +
                ", pontualidade='" + pontualidade + '\'' +
                ", atendimento='" + atendimento + '\'' +
                ", sabor='" + sabor + '\'' +
                '}';
    }
}
