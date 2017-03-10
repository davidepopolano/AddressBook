package com.elis.ltm.addressbook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.elis.ltm.addressbook.model.Contact;

import java.util.ArrayList;

/**
 * Created by davide on 04/03/17.
 */

public class Databasehandler extends SQLiteOpenHelper {

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_MAIL = "mail";


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "dbcontacts";

    private static final String TABLE_CONTACTS = "contacts";


    public Databasehandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_SURNAME + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_MAIL + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        onCreate(db);
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contactlist = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setSurname(cursor.getString(2));
                contact.setPhone(cursor.getString(3));
                contact.setMail(cursor.getString(4));

                contactlist.add(contact);
            } while (cursor.moveToNext());
        }
        return contactlist;
    }

    public long addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_SURNAME, contact.getSurname());
        values.put(KEY_PHONE, contact.getPhone());
        values.put(KEY_MAIL, contact.getMail());

        long insert = db.insert(TABLE_CONTACTS, null, values);
        contact.setId((int) insert);
        db.close();
        return insert;
    }

    public int deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        int x = db.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[]{String.valueOf(contact.getId())});
        db.close();
        return x;
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_SURNAME, contact.getSurname());
        values.put(KEY_PHONE, contact.getPhone());
        values.put(KEY_MAIL, contact.getMail());

        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
    }
}
