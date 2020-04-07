package shiyu.firstcode.broadcastbestpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText mEditText_account;
    private EditText mEditText_password;
    private Button mButton_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEditText_account = findViewById(R.id.editText_account);
        mEditText_password = findViewById(R.id.editText_password);
        mButton_login = findViewById(R.id.button_login);
        mButton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=mEditText_account.getText().toString();
                String password=mEditText_password.getText().toString();
                //如果账号是admin，且密码是123456，就认为登录成功
                if(account.equals("admin")&&password.equals("123456"))
                {
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else
                {
                    Toast.makeText(LoginActivity.this,"account or password is invalid",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
