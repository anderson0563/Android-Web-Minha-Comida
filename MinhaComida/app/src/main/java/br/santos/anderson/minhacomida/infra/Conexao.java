package br.santos.anderson.minhacomida.infra;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.StrictMode;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import br.santos.anderson.minhacomida.modelo.Situacao;

public class Conexao extends Service {
    public Conexao() {
    }

    private final IBinder mBinder = new MyBinder();

    public class MyBinder extends Binder {
        public Conexao getService() {
            return Conexao.this;
        }
    }

    public boolean validar(String pedido) throws IOException {
        return cadastrar(pedido);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public boolean cadastrar(String pedido) {
        HttpURLConnection conn = null;

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            String url = "http://192.168.0.9:8080/MinhaComidaServer/webresources/pedido/minhacomida/" + URLEncoder.encode(pedido, "UTF-8");
            System.out.println(url);
            URL _url = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) _url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                //readStream(in);
            } finally {
                urlConnection.disconnect();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }
}