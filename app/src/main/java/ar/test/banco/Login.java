package ar.test.banco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ar.test.banco.Retrofit.IMyService;
import ar.test.banco.Retrofit.RetrofitClient;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class Login extends AppCompatActivity {

    EditText user;
    EditText password;
    Button btnLogin;
    Button btnRegister;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iMyService;


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //init service
        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyService.class);

        //init view


        user = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.logEnter);
        btnRegister = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(v -> {

            Intent next = new Intent(Login.this, Register.class);
            startActivity(next);

        });

        btnLogin.setOnClickListener(v -> {


            Fragment fragment = new Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("username", user.getText().toString());
            fragment.setArguments(bundle);


            loginUser(user.getText().toString(), password.getText().toString(),bundle);





        });

    }

    private void loginUser(String user, String password, Bundle bundle) {

        if(TextUtils.isEmpty(user)){
            Toast.makeText(getApplicationContext(), "Username is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "password is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent next = new Intent(Login.this, StartActivity.class);
        next.putExtras(bundle);
        startActivity(next);

        compositeDisposable.add(iMyService.loginUser(user,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (new Consumer<String>(){
                    @Override
                    public void accept(String response) throws  Exception{
                        Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();
                    }

                    }));

    }


}