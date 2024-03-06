package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ecommerce.R;
import com.example.ecommerce.main.repository.models.remotedata.Category;
import com.example.ecommerce.main.repository.models.remotedata.Users;
import com.example.ecommerce.main.retrofit.API;
import com.example.ecommerce.main.retrofit.RetrofitClient;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
Button signup;
EditText email,userup,pass1,pass2;
CheckBox check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup=findViewById(R.id.signupbtn);
        email=findViewById(R.id.signupemail);
        userup=findViewById(R.id.signUsername);
        pass1=findViewById(R.id.signuppass1);
        pass2=findViewById(R.id.signuppass2);
        check=findViewById(R.id.signupcheckbox);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String avatar = "https://cdn-icons-png.flaticon.com/512/1053/1053244.png?w=360";
                if (!(userup.getText().toString().isEmpty() || email.getText().toString().isEmpty()
                        || pass1.getText().toString().isEmpty() ||
                        pass2.getText().toString().isEmpty()))
                {
                    if (check.isChecked()) {
                        if (pass1.getText().toString().equals(pass2.getText().toString())) {
                            Users user = new Users(userup.getText().toString(),
                                    email.getText().toString(),
                                    pass1.getText().toString(),
                                    avatar);
                            Createuser(user);
                        }
                        else
                        {
                            Toast.makeText(SignUp.this, "Passwords doesn't match", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {

                        Toast.makeText(SignUp.this, "Please agree to our terms", Toast.LENGTH_LONG).show();
                    }


                }
                else {
                    Toast.makeText(SignUp.this, "Please Fill all the fields", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void Createuser(Users user)
    {
        Toast.makeText(SignUp.this,"enter",Toast.LENGTH_SHORT);



        Call<Users>  call=RetrofitClient.getInstance().getMyApi().adduseer(user);
        call.enqueue(new Callback<Users> () {
            @Override
            public void onResponse(Call<Users>  call, Response<Users>  response) {
                if(!response.isSuccessful()){

                    Toast.makeText(SignUp.this,"Failed to sing up try again",Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(SignUp.this,"Success Signup",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SignUp.this,LoginScreen.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(SignUp.this,"failure",Toast.LENGTH_SHORT).show();
            }
        });

    }
}