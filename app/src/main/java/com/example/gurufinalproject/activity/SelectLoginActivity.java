package com.example.gurufinalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.db.FileDB;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class SelectLoginActivity extends AppCompatActivity {
    //구글 로그인 클라이언트 제어자
    private GoogleSignInClient mGoogleSignInClient;
    private String account = "";
    private MemberBean findAdmin, findmember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login);

        findViewById(R.id.btnGoogleSignIn).setOnClickListener(mBtnClick);
        findViewById(R.id.fake_btnSignIn).setOnClickListener(mBtnClick);
        findViewById(R.id.btnAdmin).setOnClickListener(mBtnClick);
        findViewById(R.id.btnMember).setOnClickListener(mBtnClick);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);

    }

    View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btnGoogleSignIn:
                    googleSignIn();
                    break;
                case R.id.fake_btnSignIn:
                    googleSignIn();
                    break;
                case R.id.btnAdmin:
                    if(account == ""){
                        Toast.makeText(getBaseContext(),"Google 로그인 인증 후 회원가입 가능합니다.",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    findAdmin = FileDB.getFindAdmin(getBaseContext(),account);
                    findmember= FileDB.getFindMember(getBaseContext(),account);

                    if(findAdmin != null || findmember != null){
                        Toast.makeText(getBaseContext(),"이미 가입되어 있는 아이디입니다.",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent i = new Intent(getBaseContext(),AdminJoinActivity.class);
                    i.putExtra("AdminID",account);
                    startActivity(i);
                    finish();
                    break;

                case R.id.btnMember:
                    if(account == ""){
                        Toast.makeText(getBaseContext(),"Google 로그인 인증 후 회원가입 가능합니다.",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    findAdmin = FileDB.getFindAdmin(getBaseContext(),account);
                    findmember= FileDB.getFindMember(getBaseContext(),account);

                    if(findAdmin != null || findmember != null){
                        Toast.makeText(getBaseContext(),"이미 가입되어 있는 아이디입니다.",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent i2 = new Intent(getBaseContext(),MemberJoinActivity.class);
                    i2.putExtra("MemberID",account);
                    startActivity(i2);
                    finish();
                    break;
            }
        }
    };

    private void googleSignIn() {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,1004);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //구글 로그인 버튼 응답
        if(requestCode == 1004){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                //구글 로그인 성공
                GoogleSignInAccount signInAccount = task.getResult(ApiException.class);
                Toast.makeText(getBaseContext(),"로그인에 성공하셨습니다.",Toast.LENGTH_SHORT).show();
                account = signInAccount.getEmail();

            }catch(ApiException e){
                e.printStackTrace();
            }
        }
    }

}
