package com.example.hp.l_sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //定义一个常量
    private static final String USER_INFO = "user_info";
    private Button btn_login,btn_logout;
    private CheckBox cb_rem;
    private EditText et_UserName,et_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_UserName=(EditText) findViewById(R.id.et_UserName);
        et_Password=(EditText)findViewById(R.id.et_Password);
        cb_rem=(CheckBox)findViewById(R.id.cb_rem);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_logout=(Button)findViewById(R.id.btn_logout);
        //监听按钮
        btn_login.setOnClickListener(this);
        btn_logout.setOnClickListener(this);

        init();
    }

    //如果存在用户信息就自动输入
    private void init() {
        //获取SharedPreferences对象
        SharedPreferences sp = getSharedPreferences(USER_INFO,MODE_PRIVATE);
        String strUserName = sp.getString("USER","");
        String strPassword = sp.getString("PASSWORD","");
        et_UserName.setText(strUserName);
        et_Password.setText(strPassword);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_logout:
                logout();
                break;
        }
    }

    //存信息
    private void login() {
        //判断是否勾选记住账号
        if(cb_rem.isChecked()){
            String strUserName=et_UserName.getText().toString();//toString() 方法可把一个逻辑值转换为字符串，并返回结果。
            String strPassword=et_Password.getText().toString();
            SharedPreferences sp=getSharedPreferences(USER_INFO,MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("USER",strUserName);
            editor.putString("PASSWORD",strPassword);
            //保存
            editor.commit();

            Toast.makeText(this,"User infomation has been saved!",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"User infomation is not saved!",Toast.LENGTH_LONG).show();
        }
    }
    //删除数据
    private void logout() {
        SharedPreferences sp=getSharedPreferences(USER_INFO,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(this,"User infomation has been delete!",Toast.LENGTH_LONG).show();
    }



}
