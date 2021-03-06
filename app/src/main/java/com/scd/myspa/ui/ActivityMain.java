package com.scd.myspa.ui;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.scd.myspa.commons.MySPACommons;
import com.scd.myspa.databinding.ActivityMainBinding;
import com.scd.myspa.R;
import com.systemComunity.myspa.model.Empleado;

public class ActivityMain extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    ImageView imgvFotoEmpleado;
    TextView lblNombreEmpleado;
    TextView lblUsuario;
    Empleado empleado = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityMainBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarActivityMain.toolbar);
        binding.appBarActivityMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_productos, R.id.nav_clientes, R.id.nav_empleados)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        initComponents();
        mostrarInformacionEmpleado();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_activity_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void initComponents()
    {
        imgvFotoEmpleado = binding.navView.getHeaderView(0).findViewById(R.id.imgvFotoEmpleado);
        lblNombreEmpleado = binding.navView.getHeaderView(0).findViewById(R.id.lblNombreEmpleado);
        lblUsuario = binding.navView.getHeaderView(0).findViewById(R.id.lblUsuario);
    }
    private void mostrarInformacionEmpleado()
    {
        try
        {
            empleado = new Gson().fromJson(getIntent().getStringExtra("datosEmpleado"), Empleado.class);

            if (empleado.getFoto() != null && empleado.getFoto().trim().length() > 64)
                imgvFotoEmpleado.setImageBitmap(MySPACommons.fromBase64(empleado.getFoto()));
            else
                imgvFotoEmpleado.setImageResource(R.drawable.man_worker);
            lblUsuario.setText(empleado.getUsuario().getNombreUsuario());
            lblNombreEmpleado.setText(empleado.getPersona().getNombre() + " " +
                                      empleado.getPersona().getApellidoPaterno() + " "+
                                      empleado.getPersona().getApellidoMaterno());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void mostrarMensaje(String titulo, String mensaje)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje).setTitle(titulo);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public  Empleado getEmpleado()
    {
        return  empleado;
    }
}