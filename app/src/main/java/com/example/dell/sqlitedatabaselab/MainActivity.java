package com.example.dell.sqlitedatabaselab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText nameET;
    EditText phoneNoET;
    private Contact contact;
    private Contact_Manager manager;
    int contactId;
    boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameET = (EditText) findViewById(R.id.inputName);
        phoneNoET = (EditText) findViewById(R.id.inputPhoneNo);
        manager = new Contact_Manager(this);


    }

    public void saveContact(View view) {
        String name = nameET.getText().toString();
        String phoneno = phoneNoET.getText().toString();
        contact = new Contact(name, phoneno);

        if( getEditMode() ){
            boolean updated = manager.updateContact(contactId, contact);
        } else {
            boolean inserted = manager.addContact(contact);
        }
        setEditMode(false);
        Intent intent = new Intent(this, ListActivity.class);
        startActivityForResult(intent, 1);

    }

    public  void showAllContacts(View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == 1 && data != null ){
            contactId = data.getIntExtra("id",0);
            if( contactId != 0 ) {
                Contact contact = manager.getContact(contactId);
                nameET.setText( contact.getName() );
                phoneNoET.setText( contact.getPhoneNo() );
                setEditMode(true);
            }
        }
    }

    private boolean getEditMode(){
        return this.editMode;
    }

    private void setEditMode( boolean editMode ){
        this.editMode = editMode;
    }

}
