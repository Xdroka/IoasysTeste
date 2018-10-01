package pedro.com.ioasysteste.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.regex.Pattern;

import pedro.com.ioasysteste.R;
import pedro.com.ioasysteste.controllers.ApiController;
import pedro.com.ioasysteste.controllers.connector.BindWithActivities;

public class LoginActivity extends AppCompatActivity {
    private Button mSiginButton;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        BindWithActivities.sContext = getApplicationContext();

        //remover isso abaixo
        callingAuthenticationAPI();

        mSiginButton = findViewById(R.id.siginButtonId);
        mEmailEditText = findViewById(R.id.emailEditTextId);
        mPasswordEditText = findViewById(R.id.passwordEditTextId);

        mPasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    mPasswordEditText.clearFocus();
                    mSiginButton.requestFocus();
                }
            }
        });

        mSiginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmailEditText.setError(null);
//                if (isEmail(mEmailEditText.getText().toString()) && isPassword()) {

                if (!callingAuthenticationAPI()) {//pega o resultado da autenticação
                    mEmailEditText.setError("Verifique se o email está correto");
                    mPasswordEditText.setError("Verifique se a senha está correta");
                }

//                }

            }
        });//fechando o Listener do botão entrar

    }

    private boolean callingAuthenticationAPI() {
        ApiController apiController = new ApiController();
//        apiController.setUser(mEmailEditText.getText().toString(),
//                                mPasswordEditText.getText().toString()
//                        );
        apiController.setUser("testeapple@ioasys.com.br", "12341234");
        apiController.callingAuth();
        return apiController.ismAuthenticationResponse();
    }


    private boolean isPassword() {
        boolean isPassword = !mPasswordEditText.getText().toString().isEmpty();
        if (!isPassword) {
            mPasswordEditText.setError("Senha Vazia");
        }
        return isPassword;
    }

    private boolean isEmail(String email) {
        String regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        mEmailEditText.setError("Email Ínvalido");
        return pattern.matcher(email).find();
    }

}
