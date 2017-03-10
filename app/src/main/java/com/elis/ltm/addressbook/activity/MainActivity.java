package com.elis.ltm.addressbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.elis.ltm.addressbook.R;
import com.elis.ltm.addressbook.adapter.ContactAdapter;
import com.elis.ltm.addressbook.db.Databasehandler;
import com.elis.ltm.addressbook.model.Contact;

import static com.elis.ltm.addressbook.activity.AddActivity.*;

/**
 * Created by davide on 09/03/17.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_EDIT = 21;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ContactAdapter adapter;
    FloatingActionButton fab;
    public static final int REQUEST_ADD = 20;
    Databasehandler db;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new ContactAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        db = new Databasehandler(this);
        adapter.setDataset(db.getAllContacts());
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        position = adapter.getPosition();
        Contact contact;
        switch (item.getItemId()) {
            case R.id.delete_menu:
                contact = adapter.getContact(position);
                long i = db.deleteContact(contact);
                if (i != -1) {
                    adapter.removeContact(position);
                }
                break;
            case R.id.edit_menu:
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                contact = adapter.getContact(position);
                intent.putExtra(NAME_KEY, contact.getName());
                intent.putExtra(SURNAME_KEY,contact.getSurname());
                intent.putExtra(MAIL_KEY, contact.getMail());
                intent.putExtra(PHONE_KEY, contact.getPhone());
                startActivityForResult(intent, REQUEST_EDIT);
                break;

        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Contact contact;
        long i;
        switch (resultCode) {
            case RESULT_ADD:
                contact = new Contact();
                contact.setName(data.getStringExtra(NAME_KEY));
//                System.out.println(contact.getName());
                contact.setSurname(data.getStringExtra(SURNAME_KEY));
//                System.out.println(contact.getSurname());
                contact.setMail(data.getStringExtra(MAIL_KEY));
                contact.setPhone(data.getStringExtra(PHONE_KEY));
                i = db.addContact(contact);
                if (i != -1) {adapter.addContact(contact);}
                break;
            case RESULT_EDIT:
                contact = adapter.updateContact(position,
                        data.getStringExtra(NAME_KEY),
                        data.getStringExtra(SURNAME_KEY),
                        data.getStringExtra(PHONE_KEY),
                        data.getStringExtra(MAIL_KEY));
                i = db.updateContact(contact);
                break;
//            case RESULT_CANCELED:
//                break;
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
//            intent.putExtra(ACTION_MODE, CREATE_MODE);
            startActivityForResult(intent, REQUEST_ADD);
        }

    }
}
