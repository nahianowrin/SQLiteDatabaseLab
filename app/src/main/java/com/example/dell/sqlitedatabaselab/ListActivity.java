package com.example.dell.sqlitedatabaselab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private Contact_Manager manager;
    ArrayList<Contact> contactList;
    static final int EDIT = 1;
    static final int DELETE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        manager = new Contact_Manager(this);
        this.showContactList();
    }

    private void showContactList(){
        contactList = manager.getAllContacts();
        List<String> ls=new ArrayList<String>();
        for ( Contact contact : contactList ){
            ls.add(contact.getName());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.name_row,ls);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ListActivity.this, ViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", contactList.get(position).getId());
                bundle.putString("name", contactList.get(position).getName());
                bundle.putString("phoneNo", contactList.get(position).getPhoneNo());
                intent.putExtras(bundle);
                startActivityForResult(intent, 1000);
            }

            @SuppressWarnings("unused")
            public void onClick(View v) {
            }

            ;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == 1000 && data != null ){
            int contactId=data.getIntExtra("id",0);
            if( contactId != 0 ) {
                if( this.DELETE == resultCode ){
                    manager.deleteContact(contactId);
                    this.showContactList();
                } else if ( this.EDIT == resultCode ){
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra( "id", contactId );
                    setResult( RESULT_OK, returnIntent);
                    finish();
                }
            }
        }
    }
}
