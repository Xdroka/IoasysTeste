package pedro.com.ioasysteste.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

        mSiginButton = findViewById(R.id.siginButtonId);
        mEmailEditText = findViewById(R.id.emailEditTextId);
        mPasswordEditText = findViewById(R.id.passwordEditTextId);

        BindWithActivities.sContext = getApplicationContext();
        BindWithActivities.sEmailEditText = mEmailEditText;
        BindWithActivities.sPasswordEditText = mPasswordEditText;

        mSiginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmailEditText.setError(null);
                if (isEmail(mEmailEditText.getText().toString())) {
                    callingAuthenticationAPI();
                } else {
                    mEmailEditText.setError("Email Ínvalido");
                }

            }
        });

    }

    private void callingAuthenticationAPI() {
        ApiController apiController = new ApiController();
        apiController.setUser(mEmailEditText.getText().toString(),
                mPasswordEditText.getText().toString()
        );
//        apiController.setUser("testeapple@ioasys.com.br", "12341234");
        apiController.callingAuth();
    }

    private boolean isEmail(String email) {
        String regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).find();
    }

}
