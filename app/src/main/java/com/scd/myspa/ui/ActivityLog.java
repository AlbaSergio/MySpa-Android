package com.scd.myspa.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scd.myspa.R;
import com.scd.myspa.commons.MySPACommons;
import com.systemComunity.myspa.model.Empleado;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityLog extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        if (Build.VERSION.SDK_INT >= 23)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},1);
        else
            initComponents();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        // Invocamos al metodo de la super-clase
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Revisamos que los resultados de los permisos NO sean null:
        if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            // Este seria el equivalente al System.out.println() en Android:
            Log.i("info", "Permisos Concedidos");
            initComponents();
        }
        else {
            mostrarMensaje("No ha concedido los permisos necesarios. Esta aplicación se cerrará");
            System.exit(0);
        }
    }

    private void initComponents()
    {
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {validarUsuario();}
        });
    }

    private void validarUsuario()
    {
        final ActivityLog activityLogin = this;
        String url = MySPACommons.MYSPA_SERVER +"/api/login/validate";
        RequestQueue rq = Volley.newRequestQueue(this);

        Response.Listener<String> rl = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JsonObject jso = JsonParser.parseString(response).getAsJsonObject();
                if (jso.get("error") != null)
                {
                    mostrarMensaje(jso.get("error").getAsString());
                    return;
                }
                else
                {
                    //Generamos un Intent para navegar a la siguiente Activity
                    Intent intent = new Intent(activityLogin, ActivityMain.class);
                    //Establecemos los datos que va a recibir el ActivityMain:
                    intent.putExtra("datosEmpleado", response);
                    //Cargamos el ActivityMain
                    startActivity(intent);
                }
            }
        };

        Response.ErrorListener rel = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Imprimimos el error en la consolola de Android Studio
                error.printStackTrace();
                mostrarMensaje("Por el momento no se pudo validar su identidad. Intente más tarde.");
            }
        };

        StringRequest sr = new StringRequest(Request.Method.POST,url,rl,rel)
        {
            @Override
           public Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("nombreUsuario", txtUsuario.getText().toString());
                params.put("contrasenia", txtPassword.getText().toString());
                return params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        rq.add(sr);
    }

    private void mostrarMensaje(String mensaje)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje).setTitle("");
        AlertDialog alert = builder.create();
        alert.show();
    }
}