package com.elis.ltm.addressbook.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.elis.ltm.addressbook.R;
import com.elis.ltm.addressbook.activity.AddActivity;
import com.elis.ltm.addressbook.activity.MainActivity;
import com.elis.ltm.addressbook.activity.ViewActivity;
import com.elis.ltm.addressbook.model.Contact;

import java.util.ArrayList;

/**
 * Created by davide on 09/03/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactVH> {
    ArrayList<Contact> dataset = new ArrayList<>();
    int position;

    @Override
    public ContactVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactVH(v);
    }

    @Override
    public void onBindViewHolder(ContactVH holder, int position) {
        holder.name.setText(dataset.get(position).getName());
        holder.surname.setText(dataset.get(position).getSurname());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public Contact getContact(int position){
        return dataset.get(position);
    }

    public void setDataset(ArrayList<Contact> dataset) {
        this.dataset = dataset;
    }

    public ArrayList<Contact> getDataset() {
        return dataset;
    }

    public void addContact(Contact contact){
        dataset.add(contact);
        notifyDataSetChanged();
    }
    public void removeContact(int position){
        dataset.remove(position);
        notifyItemRemoved(position);
    }
    public Contact updateContact(int position, String name, String surname, String phone, String mail){
        Contact contact = dataset.get(position);
        contact.setName(name);
        contact.setSurname(surname);
        contact.setPhone(phone);
        contact.setMail(mail);
        notifyItemChanged(position);
        return contact;


    }

    public class ContactVH extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnClickListener{
        TextView name, surname;

        public ContactVH(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_tv);
            surname = (TextView) itemView.findViewById(R.id.surname_tv);
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MainActivity activity = (MainActivity) v.getContext();
            MenuInflater menuInflater =  activity.getMenuInflater();
            menuInflater.inflate(R.menu.context_menu, menu);
            setPosition(getAdapterPosition());
        }


        @Override
        public void onClick(View v) {
            MainActivity activity = (MainActivity) v.getContext();
            Toast.makeText(activity , "Funziona", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(activity , ViewActivity.class);
//            intent.putExtra(AddActivity.NAME_KEY, )
//            activity.startActivity(intent);
        }
    }
}
