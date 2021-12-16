package com.example.judeforever;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private EditText etLogin, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textTest);
        Button btnSubmit = findViewById(R.id.buttonSubmitSignIn);
        etLogin = findViewById(R.id.editTextLogin);
        etPassword = findViewById(R.id.editTextPassword);
        btnSubmit.setOnClickListener(e ->
        {
            String url = "http://cars.areas.su/login";
            try
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                JSONObject data = new JSONObject();
                data.put("username", etLogin.getText());
                data.put("password", etPassword.getText());
                try(OutputStream os = connection.getOutputStream()) {
                    byte[] input = data.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String line;
                String text = "";
                while((line = reader1.readLine()) != null)
                {
                    text = line;
                }
                reader1.close();
                Log.d("Bred", text);
                JSONObject jsonObj = new JSONObject(text);
                Log.d("Bred", jsonObj.toString());
                JSONObject token = (JSONObject) jsonObj.get("notice");
                String str_token = token.get("token").toString();
                Intent intent = new Intent(this, MapsActivity.class);
                intent.putExtra("token", str_token);
                startActivity(intent);
                finish();
            } catch (IOException | JSONException d) {
                Log.d("Bred", "Ты обосрался");
            }
        });



            //textView.setText(connection.getResponseMessage());
            /*OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            int character = reader.read();
            char[] buffer = new char[4096];
            reader.read(buffer);*/
            /*connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");*/
            /*textView.setText(connection.getResponseCode());
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            String text = "";
            while((line = reader1.readLine()) != null)
            {
                text = line;
            }
            reader1.close();
            JSONArray jsonArray = new JSONArray(text);
            JSONObject jsonObj = jsonArray.getJSONObject(0);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            for()
            Car a_car = gson.fromJson(String.valueOf(jsonObj), Car.class);*/
            //textView.setText(a_car.model);
            /*for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                System.out.println(jsonObj);
            }*/
            /*JSONObject jsonObject = new JSONObject(text);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                System.out.println(explrObject);
            }*/



    }
}