package ar.test.banco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Informacion extends Activity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion);
    btn= (Button)findViewById(R.id.aceptar);


    }



    public void aceptar(View v){
    Intent i= new Intent(this, MainActivity.class);
   startActivity(i);

}
}
