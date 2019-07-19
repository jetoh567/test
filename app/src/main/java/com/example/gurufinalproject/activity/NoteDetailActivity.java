package com.example.gurufinalproject.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.NoteBean;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;

import static com.example.gurufinalproject.activity.NoteWriteActivity.STORAGE_DB_URL;

public class NoteDetailActivity extends AppCompatActivity {

    private TextView writer,access, location, content, regDate, finDate, checkWho;
    private ImageView detailImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        writer = findViewById(R.id.detailWriter);
        location = findViewById(R.id.detailLocation);
        content = findViewById(R.id.detailContent);
        regDate = findViewById(R.id.detailRegDate);
        finDate = findViewById(R.id.detailFinDate);
        checkWho = findViewById(R.id.detailCheckWho);
        detailImg = findViewById(R.id.detailImg);
        access = findViewById(R.id.detailAccess);

        NoteBean note  = (NoteBean) getIntent().getSerializableExtra("detail");

        writer.setText("신고자 : "+note.userName);
        location.setText("신고 위치 : " +note.location);
        content.setText("신고 내용 : " + note.detail);
        if(note.access == true){
            access.setText("비공개 글");
            access.setTextColor(Color.rgb(255,0,0));
        }else{
            access.setText("공개 글");
        }
        if(note.check == true){
            regDate.setText("신고 날짜 : "+note.regdate);
            finDate.setText("완료 날짜 : " + note.findate);
            checkWho.setText("담당자 : "+note.checkWho);
        }else{
            regDate.setText("신고 날짜 : "+note.regdate);
            finDate.setText("완료 안됨");
            checkWho.setText(" ");
        }

        if(note.imgName != " " && note.imgUri != " "){
            try {
                    new DownloadImgTask(detailImg).execute(new URL(note.imgUri));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public class DownloadImgTask extends AsyncTask<URL,Void, Bitmap> {

        private WeakReference<ImageView> mImageView = null;

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
