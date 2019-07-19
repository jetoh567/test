package com.example.gurufinalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.activity.administrator.AdminCheckWritingActivity;
import com.example.gurufinalproject.activity.administrator.AdminNoticeActivity;
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.db.FileDB;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.File;

public class LoginActivity extends AppCompatActivity {
    //구글 로그인 클라이언트 제어자
    private GoogleSignInClient mGoogleSignInClient;
    private Boolean student = true;
    //FireBase 인증 객체
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btnJoin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),SelectLoginActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignIn();
            }
        });

        findViewById(R.id.fake_btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignIn();
            }
        });

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
    }
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
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //Toast.makeText(getBaseContext(),"구글로그인에 성공하셨습니다.",Toast.LENGTH_SHORT).show();

                MemberBean findMember = FileDB.getFindMember(this,account.getEmail());
                MemberBean findAdmin = FileDB.getFindAdmin(this,account.getEmail());
                if(findMember != null ){
                    //FireBase 인증하러가기
                    fireBaseAuthWithGoogle(account);
                    //Toast.makeText(getBaseContext(),"firebase 학생 성공",Toast.LENGTH_SHORT).show();
                    FileDB.setLoginMember(this, findMember);
                }else{
                    if(findAdmin != null){
                        //FireBase 인증하러가기
                        fireBaseAuthWithGoogle(account);
                      //  Toast.makeText(getBaseContext(),"firebase 관리자 성공",Toast.LENGTH_SHORT).show();
                        FileDB.setLoginAdmin(this, findAdmin);
                        student =false;
                    }else{
                        Toast.makeText(getBaseContext(),"해당 아이디는 가입이 되어있지 않습니다.",Toast.LENGTH_SHORT).show();
                    }
                }
            }catch(ApiException e){
                e.printStackTrace();
            }
        }
    }
    //FireBase 인증
    private void fireBaseAuthWithGoogle(GoogleSignInAccount account){
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //FireBase 로그인 성공
                    //Toast.makeText(getBaseContext(),"FireBase 로그인 성공",Toast.LENGTH_SHORT).show();
                    if(student == false){
                        MemberBean memberBean = FileDB.getLoginAdmin(getApplicationContext());

                        if(memberBean.userNum == 0){
                            Intent i = new Intent(getBaseContext(), AdminNoticeActivity.class);
                            startActivity(i);
                        }
                        else{
                            Intent i = new Intent(getBaseContext(), AdminCheckWritingActivity.class);
                            startActivity(i);
                        }

                        student = true;
                    }else{
                        Intent i = new Intent(getBaseContext(),MemberNavigationActivity.class);
                        startActivity(i);
                    }
                }else{
                    //로그인 실패
                    Toast.makeText(getBaseContext(),"FireBase 로그인 실패",Toast.LENGTH_SHORT).show();
                    Log.w("TEST","인증실패 : "+ task.getException());

                }
            }
        });
    }
}
