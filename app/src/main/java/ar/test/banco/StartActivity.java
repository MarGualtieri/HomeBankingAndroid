package ar.test.banco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    ImageButton home;
    ImageButton close;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // conectar boton home
        home =  findViewById(R.id.home);
        close = findViewById(R.id.close);


        // assigning ID of the toolbar to a variable
       Toolbar toolbar = findViewById(R.id.toolbar);

        // using toolbar as ActionBar
       setSupportActionBar(toolbar);

       // recuperar el dato desde login
        String username = getIntent().getStringExtra("username");
        Toast.makeText(getApplicationContext(), "Nombreen start es: " + username , Toast.LENGTH_SHORT).show();


        Fragment fragment = new Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("username",username);
        fragment.setArguments(bundle);

        // poner a la escucha al boton home
        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


               FragmentContainer init = new FragmentContainer();
               getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,init,username).commit();


               // Navigation.findNavController(v).navigate(R.id.action_fragmentContainer_to_cuenta2);


            }
            });
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(StartActivity.this).create();
                alertDialog.setTitle(username);
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

                                Intent i= new Intent(StartActivity.this,Login.class);
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