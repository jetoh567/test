package com.example.gurufinalproject.activity.administrator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.bean.NoteBean;
import com.example.gurufinalproject.db.FileDB;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteAdminActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    private TextView writer, location, content, regDate, finDate, checkWho;
    private ImageView detailImg;
    private NoteBean note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_admin);

        writer = findViewById(R.id.detailWriterA);
        location = findViewById(R.id.detailLocationA);
        content = findViewById(R.id.detailContentA);
        regDate = findViewById(R.id.detailRegDateA);
        finDate = findViewById(R.id.detailFinDateA);
        checkWho = findViewById(R.id.detailCheckWhoA);
        detailImg = findViewById(R.id.detailImgA);

        note  = (NoteBean) getIntent().getSerializableExtra("detailA");

        writer.setText("신고자 : "+note.userName);
        location.setText(note.location);
        content.setText(note.detail);

        regDate.setText("신고 날짜 : "+note.regdate);

        if(note.imgName != " " && note.imgUri != " "){

            try {
                    new DownloadImgTask(detailImg).execute(new URL(note.imgUri));
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        Button btnDone = findViewById(R.id.btn_checkDoneA);
        if(note.check == true){
            btnDone.setEnabled(false);
            finDate.setText("완료 날짜 : " + note.findate);
            checkWho.setText("담당자 : "+note.checkWho);
        }else{
            finDate.setText(" ");
            checkWho.setText(" ");
        }

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("정말 완료하시겠습니까?");
        builder.setMessage("완료를 누르신 후에는 완료 되었다는 사실과 함께 담당자의 이름이 저장됩니다.");

        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                note.check = true;

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                note.findate = sdf.format(new Date());

                MemberBean login = FileDB.getLoginAdmin(getBaseContext());
                note.checkWho = login.name;
                note.checkId = login.userid;

                DatabaseReference dbRef = mFirebaseDB.getReference();
                dbRef.child("Note").child(note.id).setValue(note);
                finish();
            }
        });

        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();
    }

    public class DownloadImgTask extends AsyncTask<URL,Void, Bitmap> {

        private WeakReference<ImageView> mImageView;

        //생성자
        public DownloadImgTask( ImageView imageView) {
            mImageView = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(URL... params) {
            URL imageUrl = params[0];
            Bitmap downloadedBitmap = null;
            try{
                InputStream inputStream = imageUrl.openStream();
                downloadedBitmap = BitmapFactory.decodeStream(inputStream);
            }catch(Exception e){
                e.printStackTrace();
            }

            return downloadedBitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap !=null) {
                //이미지 다운로드 성공
                mImageView.get().setImageBitmap(bitmap);

            }
        }
    }

}
