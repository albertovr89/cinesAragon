package com.vrodriguez.cinesaragon;

import android.renderscript.ScriptGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FuncionesJSON {

    public static JSONObject ObtenerParam (String url, String param) {
        JSONObject jsonObject = null;

        try{
            URL jsonurl = new URL(url);
            try{
                HttpURLConnection conexion = (HttpURLConnection) jsonurl.openConnection();
                conexion.setUseCaches(false);
                conexion.setRequestMethod("POST");
                conexion.setDoOutput(true);
                conexion.setDoInput(true);
                conexion.connect();

                OutputStream outputStream = new BufferedOutputStream(conexion.getOutputStream());
                outputStream.write(param.getBytes());
                outputStream.flush();

                InputStream inputStream = new BufferedInputStream(conexion.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String line;
                while((line=bufferedReader.readLine())!=null) {
                    stringBuffer.append(line).append("/n");
                }
                inputStream.close();

                try {
                    jsonObject = new JSONObject(stringBuffer.toString());
                }catch (JSONException e){
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
