package com.example.wangjun.demoapk.WidgetDemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wangjun.demoapk.R;



public class CWidgetActivity extends AppCompatActivity {

    private static String sLogcatTAG = "ActivityDemoActivity";

    private Button m_proDialogBtn;
    private Button m_editTextBtn;
    private Button m_imageViewBtn;
    private Button m_proBarBtn;
    private Button m_alertDialogBtn;

    private EditText m_editText;
    private ImageView m_imageView;
    private ProgressBar m_progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widgetdemo_cwidget);



        m_editText = (EditText) findViewById(R.id.edit_text);
        m_imageView  = (ImageView) findViewById(R.id.image_view);
        m_progressBar = (ProgressBar) findViewById(R.id.m_progressBar);



        m_proDialogBtn = (Button) findViewById(R.id.m_proDialogBtn);
        m_proDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.m_proDialogBtn:
                        ProgressDialog progressDialog = new ProgressDialog(CWidgetActivity.this);
                        progressDialog.setTitle("This is ProgressDialog");
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(true);
                        progressDialog.show();
                        break;
                    default:
                        break;
                }
            }
        });

        m_editTextBtn = (Button) findViewById(R.id.m_editTextBtn);
        m_editTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.m_editTextBtn:
                        String inputText = m_editText.getText().toString();
                        Toast.makeText(CWidgetActivity.this, inputText, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

        m_imageViewBtn = (Button) findViewById(R.id.m_imageViewBtn);
        m_imageViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.m_imageViewBtn:
                        m_imageView.setImageResource(R.drawable.listviewdemo_i2);
                        break;
                    default:
                        break;
                }
            }
        });

        m_proBarBtn = (Button) findViewById(R.id.m_proBarBtn);
        m_proBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.m_proBarBtn:
                        if (m_progressBar.getVisibility() == View.GONE) {
                            m_progressBar.setVisibility(View.VISIBLE);
                            int progress = m_progressBar.getProgress();
                            progress = progress + 10;
                            m_progressBar.setProgress(progress);
                        } else {
                            m_progressBar.setVisibility(View.GONE);
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        m_alertDialogBtn = (Button) findViewById(R.id.m_alertDialogBtn);
        m_alertDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.m_alertDialogBtn:
                        AlertDialog.Builder dialog = new AlertDialog.Builder(CWidgetActivity.this);
                        dialog.setTitle("This is Dialog");
                        dialog.setMessage("Something important.");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        dialog.show();
                        break;
                    default:
                        break;
                }
            }
        });

    }
}