package com.example.dell.sqlitedatabaselab;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    TextView nameView;
    TextView phoneNoView;
    int contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        nameView = (TextView) findViewById(R.id.nameView);
        phoneNoView = (TextView) findViewById(R.id.phoneNoView);

        Intent intent = getIntent();
        if (null != intent) {

            String name = intent.getStringExtra("name");
            nameView.setText(name);

            String phoneNo = intent.getStringExtra("phoneNo");
            phoneNoView.setText(phoneNo);

            contactId = intent.getIntExtra("id",0);

        }
    }

    public void deleteContact(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra( "id", contactId );
        setResult( ListActivity.DELETE, returnIntent);
        finish();
    }

    public void editContact(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra( "id", contactId );
        setResult( ListActivity.EDIT, returnIntent);
        finish();
    }

}
