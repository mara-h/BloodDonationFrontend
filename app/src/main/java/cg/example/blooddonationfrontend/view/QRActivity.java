package cg.example.blooddonationfrontend.view;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import cg.example.blooddonationfrontend.R;

public class QRActivity extends AppCompatActivity {

    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    UUID id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_qr_generator);

        ImageView qrCodeIV = findViewById(R.id.qrCodeImageView);
        //Button generateQrCode = findViewById(R.id.generateQRButton);
        ImageButton backButton = findViewById(R.id.back_button);
        Button saveQr = findViewById(R.id.generateQRButton);

        if (getIntent().getExtras() != null) {
            String stringId = getIntent().getStringExtra("id");
            id = UUID.fromString(stringId);
        }

        generateQR();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QRActivity.this, HomeActivity.class));
            }
        });
        saveQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean saveImage(Bitmap bitmap) throws IOException{
        boolean saved;
        OutputStream fos=null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentValues contentValues =new  ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, System.currentTimeMillis());
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/" + "QR");
            Uri imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            try {
                fos = getContentResolver().openOutputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            String imagesDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM).toString() + File.separator + "QR";

            File file =new File(imagesDir);

            if (!file.exists()) {
                file.mkdir();
            }

            File image =new File(imagesDir, "${System.currentTimeMillis()}.png");
            try {
                fos =new FileOutputStream(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        saved = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        assert fos != null;
        fos.flush();
        fos.close();

        return saved;
    }


    public void generateQR() {
        ImageView qrCodeIV = findViewById(R.id.qrCodeImageView);
        if (id == null) {
            Toast.makeText(QRActivity.this, "Ne pare rău, a apărut o problemă cu generarea codului QR.", Toast.LENGTH_LONG).show();
        } else {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();

            Point point = new Point();
            display.getSize(point);

            int width = point.x;
            int height = point.y;

            int dimen = width < height ? width : height;
            dimen = dimen * 3 / 4;

            qrgEncoder = new QRGEncoder(id.toString(), null, QRGContents.Type.TEXT, dimen);
            try {
                // getting our qrcode in the form of bitmap.
                bitmap = qrgEncoder.encodeAsBitmap();
                // the bitmap is set inside our image
                // view using .setimagebitmap method.
                qrCodeIV.setImageBitmap(bitmap);
            } catch (WriterException e) {
                // this method is called for
                // exception handling.
                Log.e("Tag", e.toString());
            }
        }
    }
}
