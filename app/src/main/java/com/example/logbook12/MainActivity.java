package com.example.logbook12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    Button add_url_image_btn;
    Button back_btn;
    Button forward_btn;
    ArrayList<String> imageList ;
    EditText inputImageURl;
    int currentImage = 0;

    private static final String CHECK_IMG_URL = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpeg|jpg|gif|png)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputImageURl = findViewById(R.id.urlText);
        imageView = findViewById(R.id.imageView);
        back_btn = findViewById(R.id.prevBtn);
        forward_btn = findViewById(R.id.nextBtn);

        back_btn.setOnClickListener(this::renderImageWhenOnclick);
        forward_btn.setOnClickListener(this::renderImageWhenOnclick);

        imageList = new ArrayList<String>();
        imageList.add("https://wikiceleb.net/wp-content/uploads/2022/04/ngoai-hinh-cua-zoro.jpg");
        imageList.add("https://image.winudf.com/v2/image/Y29tLmJhbGVmb290Lk1vbmtleURMdWZmeVdhbGxwYXBlcl9zY3JlZW5fMF8xNTI0NTE5MTEwXzAyOA/screen-0.jpg?fakeurl=1&type=.webp");
        imageList.add("https://gamek.mediacdn.vn/133514250583805952/2022/5/18/photo-1-16528608926331302726659.jpg");
        imageList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTC52Qb5GJXhY2l68Pc15aiV_wO22KHNOaUBg&usqp=CAU");



        add_url_image_btn = findViewById(R.id.addBtn);

        add_url_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageURl = inputImageURl.getText().toString();


                if (imageURl.matches(CHECK_IMG_URL)){
                    if (imageURl.isEmpty()) {

                        Toast.makeText(getApplicationContext(), "Please insert URl to add !!!", Toast.LENGTH_SHORT).show();
                    } else {

                        imageList.add(imageURl);
                        inputImageURl.setText("");
                        Toast.makeText(getApplicationContext(), "Completed!!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Invalid link", Toast.LENGTH_SHORT).show();
                }

//                if (imageURl.isEmpty()) {
//
//                    Toast.makeText(getApplicationContext(), "Please insert URl to add !!!", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    imageList.add(imageURl);
//
//
//                    inputImageURl.setText("");
//                    Toast.makeText(getApplicationContext(), "Completed!!!", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }



    private void renderImageWhenOnclick(View view) {
        if (view == forward_btn) {
            currentImage++;
            if (currentImage == imageList.size()) {
                currentImage = 0;
            }
        } else {
            if (currentImage == 0) {
                currentImage = imageList.size();
            }
            currentImage--;
        }
        loadImage(currentImage);
    }

    private void loadImage(int item) {
        Glide.with(MainActivity.this)
                .load(imageList.get(item))
                .into(imageView);
    }
}