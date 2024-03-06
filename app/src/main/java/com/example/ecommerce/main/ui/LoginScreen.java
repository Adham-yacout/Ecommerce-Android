package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerce.R;
import com.example.ecommerce.main.util.Helper;
import com.example.ecommerce.main.viewmodel.UsersViewModel;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {
EditText email;
        EditText password;
        Button login;
        UsersViewModel usersmodel;
        TextView emaillogo,passlogo;
        ImageView eye;
    public int passtoggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        email=findViewById(R.id.Emailentered);
        password=findViewById(R.id.passwordentered);
        login=findViewById(R.id.Loginbtn);
        emaillogo=findViewById(R.id.Emaillogo);
        passlogo=findViewById(R.id.passlogo);
        eye=findViewById(R.id.eyebtn);

        passtoggle=1;
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(passtoggle==1)
              {
                  password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                  passtoggle=0;
              }
              else {
                  password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                  passtoggle=1;
              }
            }
        });


//        CategoryViewModel= new ViewModelProvider(this).get(com.example.ecommerce.main.viewmodel.CategoryViewModel.class);
//        CategoryViewModel.getCategories(this).observe(this, Category->{
//            CategoryAdapter adapter=new CategoryAdapter(Redirection.this,Category);
//            myRecycler.setAdapter(adapter);
//            myRecycler.setLayoutManager(new GridLayoutManager(Redirection.this,2));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext=email.getText().toString();
                String passtext=password.getText().toString();
                boolean check;
                usersmodel=new ViewModelProvider(LoginScreen.this).get(UsersViewModel.class);
                usersmodel.getUsers(LoginScreen.this).observe(LoginScreen.this,users->{
                    Helper help=new Helper();
                 int status= help.loginchecker(users,emailtext,passtext,LoginScreen.this);
if (status==1)
{
    savedata();
}
                });
                if(Helper.passwordlogin==1)
                {  passlogo.setTextColor(getResources().getColor(R.color.Red));
//                    password.setBackgroundColor(getResources().getColor(R.color.Red));
                }
                else {
                    passlogo.setTextColor(getResources().getColor(R.color.black));
                }
                if(Helper.emaillogin==1)
                {
                    emaillogo.setTextColor(getResources().getColor(R.color.Red));
//                    email.setBackgroundColor(getResources().getColor(R.color.Red));
                }
                else {
                    emaillogo.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
    }
public  void savedata()
{
    Mainscreen.sharedPreferences=getSharedPreferences(Mainscreen.shared,MODE_PRIVATE);


    Mainscreen.editor.putString(Mainscreen.name,Helper.Activeuser.getName());
    Mainscreen. editor.putString(Mainscreen.emailuser,Helper.Activeuser.getEmail());
    Mainscreen.editor.putString(Mainscreen.id, String.valueOf(Helper.Activeuser.getId()));
    Mainscreen.editor.putString(Mainscreen.role,Helper.Activeuser.getRole());
    Mainscreen. editor.putString(Mainscreen.avatar,Helper.Activeuser.getAvatar());
    Mainscreen.editor.putString(Mainscreen.state,"true");
    Mainscreen. editor.apply();

}
public  void loadData()
{
    Mainscreen.sharedPreferences=getSharedPreferences(Mainscreen.shared,MODE_PRIVATE);


}
}