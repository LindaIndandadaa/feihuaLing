package com.example.dell.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(this);
    }
    @Override
    public  void onClick(View v) {


        EditText input=(EditText)findViewById(R.id.editText);
        String str=input.getText().toString();

        TextView textView=(TextView)findViewById(R.id.textView);
        InputStream context = getClass().getClassLoader().getResourceAsStream("assets/shi.txt");

        BufferedReader br=new BufferedReader(new InputStreamReader(context));

        String ans="",line;
        int count=0;

        try {
            while((line=br.readLine())!=null) {
                if(line.length()!=0) {
                    char s = line.charAt(0);
                    if (s >= '0' && s <= '9')
                        continue;
                    else {
                        if (line.contains(str)) {
                            ans += line + "\n";
                            count += 1;
                        }
                    }
                }
            }
            SpannableString s = new SpannableString(ans);
            Pattern p=Pattern.compile(str);
            Matcher m=p.matcher(s);
            while(m.find()) {
                int start=m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(Color.RED),start,end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            }

            textView.setText(s);
            textView.setHorizontallyScrolling(true);
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
            Toast.makeText(SecondActivity.this,"一共有"+count+"句",Toast.LENGTH_SHORT).show();


        }catch(IOException e) {

            ans="bug了？再来一遍吧！";
            textView.setText(ans);
        }

    }

}
