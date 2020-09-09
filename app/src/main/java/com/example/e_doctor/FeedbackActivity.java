package com.example.e_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.System.in;

public class FeedbackActivity extends AppCompatActivity {

    EditText editTextname,editTextfeed;
    Button btnsend;

    DatabaseFeedback DH = new DatabaseFeedback(this);
    DatabaseDoctor databaseDoctor=new DatabaseDoctor(this);

    int p = 0, n = 0,r;


    String review;

    String[] stopwordslist = new String[]{ "tell", "a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any", "are", "aren't", "as", "at", "be", "based", "because", "been", "before", "being", "below", "between", "both", "but", "by", "can't", "cannot", "could",
            "couldn't", "did", "didn't", "do", "does", "doesn't", "doing", "don't", "down", "during", "each", "few",
            "for", "from", "further", "get", "had", "hadn't", "has", "hasn't", "have", "haven't", "having", "he", "he'd", "he'll", "he's", "her", "here", "here's", "hers", "herself", "him", "himself", "his", "how", "how's", "i", "i'd", "i'll", "i'm", "i've", "if", "in", "into", "is", "isn't",
            "it", "it's", "its", "itself", "let's", "me", "more", "most", "mustn't", "my", "myself", "no", "nor", "not", "of", "off", "on", "once", "only", "or", "other", "ought", "our", "ours	ourselves", "out", "over", "own", "same", "shan't", "she",
            "she'd", "she'll", "she's", "should", "shouldn't", "so", "some", "such", "than", "that", "that's", "the", "their", "theirs", "them", "themselves", "then", "there", "there's", "these", "they", "they'd", "they'll", "they're", "they've", "this", "those", "through", "to", "too", "under", "until", "up", "very",
            "was", "wasn't", "we", "we'd", "we'll", "we're", "we've", "were", "weren't", "what", "what's", "when", "when's", "where", "where's", "which", "while", "who", "who's", "whom", "why", "why's", "with", "won't", "would", "wouldn't", "you", "you'd", "you'll",
            "you're", "you've", "your", "yours", "yourself", "yourselves", "I", "have", "and" };

    String finaldata ;

    String[] pos = new String[] { "good", "awesome", "nice", "better" };
    String[] neg = new String[] { "bad", "worst" };

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        editTextname = findViewById(R.id.editTextname);
        editTextfeed = findViewById(R.id.editTextfeedback);
        btnsend = findViewById(R.id.buttonsend);

        textView=findViewById(R.id.textrate);

        Intent intent = getIntent();

        String text = intent.getStringExtra("docname");
        editTextname.setText(text);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextname.getText().toString()) ||
                        TextUtils.isEmpty(editTextfeed.getText().toString())) {

                    Toast.makeText(FeedbackActivity.this, "Pls fill the fields",
                            Toast.LENGTH_LONG).show();

                } else {
                    boolean res = DH.Feedback_Data(editTextname.getText().toString(),
                            editTextfeed.getText().toString());
                    if (res) {
                        feeback();

                        Toast.makeText(FeedbackActivity.this, "Submitted Successfully",
                                Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(FeedbackActivity.this, "Try Again",
                                Toast.LENGTH_LONG).show();
                    }

                    editTextname.setText("");
                    editTextfeed.setText("");

                }
            }


        });
    }

    public void feeback() {


        String str = editTextfeed.getText().toString().toLowerCase();
        String[] stringlist =str.split(" ");

        for(int i=0;i<stringlist.length;i++)
        {

            for(int j=0;j< stopwordslist.length;j++)
            {
                if (!stopwordslist[j].contains(stringlist[i]))
                {
                    finaldata=stringlist[i].replace(stopwordslist[i],"");

                }
            }

        }

      //  t1.setText(finaldata);


        for(int i=0;i<finaldata.length();i++)
        {
            for(int j=0;j<pos.length;j++)
            {
                if(finaldata.contains(pos[j]))
                {
                    p++;
                  //  tpos.setText(finaldata);
                    break;
                }
            }
        }
        for (int i = 0; i < finaldata.length(); i++)
        {
            for (int j = 0; j < neg.length; j++)
            {
                if (finaldata.contains(neg[j]))
                {
                    n++;
                  //  tneg.setText(finaldata);
                    break;
                }
            }
        }

        if(p>n)
        {
            textView.setText("Thank you for Positive Feedback");
            Toast.makeText(this, "Thank you for Positive Feedback", Toast.LENGTH_SHORT).show();
        }
        else if (p == n)
        {
            textView.setText("Thank you for Netural Feedback");
            Toast.makeText(this, "Thank you for Netural Feedback", Toast.LENGTH_SHORT).show();
        }
        else if(p < n)
        {
            textView.setText("Hey, negative feedback. We are sorry for the inconvinience caused");
            Toast.makeText(this, "Hey, negative feedback. We are sorry for the inconvinience caused.", Toast.LENGTH_SHORT).show();
        }






    }
}
