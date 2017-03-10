package com.elis.ltm.addressbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elis.ltm.addressbook.R;

import static com.elis.ltm.addressbook.activity.AddActivity.*;

/**
 * Created by davide on 10/03/17.
 */

public class EditActivity extends AppCompatActivity implements View.OnClickListener{
    EditText name, surname, mail, phone;
    Button edit;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editadd);
        intent = getIntent();

        name = (EditText) findViewById(R.id.name_et);
        name.setText(intent.getStringExtra(NAME_KEY));
        surname = (EditText) findViewById(R.id.surname_et);
        surname.setText(intent.getStringExtra(SURNAME_KEY));
        mail = (EditText) findViewById(R.id.email_et);
        mail.setText(intent.getStringExtra(MAIL_KEY));
        phone = (EditText) findViewById(R.id.telephone_et);
        phone.setText(intent.getStringExtra(PHONE_KEY));
        edit = (Button) findViewById(R.id.add_btn);
        edit.setText(R.string.edit);
        edit.setOnClickListener(this);
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
            setResult(RESULT_EDIT, intent);
            finish();
        }

    }
}
