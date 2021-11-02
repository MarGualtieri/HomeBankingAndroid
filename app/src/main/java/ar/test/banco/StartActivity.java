package ar.test.banco;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StartActivity extends AppCompatActivity {

    ImageButton home;
    ImageButton close;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        // conectar boton home
        home = findViewById(R.id.home);
        close = findViewById(R.id.close);

        Button cuenta = findViewById(R.id.btnCuenta);
        Button tarjeta = findViewById(R.id.btnTarjeta);
        Button inversion = findViewById(R.id.btnInversion);
        TextView userName = findViewById(R.id.userName);


        cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment cuentaFragment = new Cuenta();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, cuentaFragment).commit();
            }
        });
        tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment tarjetaFragment = new Tarjeta();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, tarjetaFragment).commit();


            }
        });
        inversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment inversionFragment = new Inversion();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, inversionFragment).commit();
            }
        });


        // assigning ID of the toolbar to a variable
        Toolbar toolbar = findViewById(R.id.toolbar);

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);


        // recuperar el dato desde login
        SharedPreferences sh = getSharedPreferences("data", MODE_PRIVATE);
        String nombre = sh.getString("name", "");
        String apellido = sh.getString("lastname", "");
        int pesos = sh.getInt("pesos", 0);
        int dolares = sh.getInt("dolares", 0);

        userName.setText(nombre);


        // poner a la escucha al boton home
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentContainer init = new FragmentContainer();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, init).commit();

            }

        });


        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(StartActivity.this).create();
                alertDialog.setTitle(nombre);
                alertDialog.setMessage("Desea cerrar la sesion?");

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent i = new Intent(StartActivity.this, Login.class);
                                startActivity(i);
                                finish();
                            }
                        });

                alertDialog.show();

                // Navigation.findNavController(v).navigate(R.id.action_fragmentContainer_to_cuenta2);


            }
        });


    }
}