package com.elis.ltm.addressbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elis.ltm.addressbook.R;

/**
 * Created by davide on 09/03/17.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int RESULT_ADD = 10;
    public static final int RESULT_EDIT = 11;
    EditText name, surname, mail, phone;
    Button add;
    public final static String NAME_KEY = "name";
    public final static String SURNAME_KEY = "surname";
    public final static String MAIL_KEY = "mail";
    public final static String PHONE_KEY = "phone";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editadd);
        name = (EditText) findViewById(R.id.name_et);
        surname = (EditText) findViewById(R.id.surname_et);
        mail = (EditText) findViewById(R.id.email_et);
        phone = (EditText) findViewById(R.id.telephone_et);
        add = (Button) findViewById(R.id.add_btn);
        add.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (name.getText().toString().isEmpty() ||
                surname.getText().toString().isEmpty() ||
                mail.getText().toString().isEmpty() ||
                phone.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.compile, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(NAME_KEY, name.getText().toString());
            intent.putExtra(SURNAME_KEY, surname.getText().toString());
            intent.putExtra(MAIL_KEY, mail.getText().toString());
            intent.putExtra(PHONE_KEY, phone.getText().toString());
            setResult(RESULT_ADD, intent);
            finish();
        }
    }
}
