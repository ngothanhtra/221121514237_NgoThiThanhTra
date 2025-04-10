package org.o7planning.giuakimobile;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ContactAdapter_listview extends BaseAdapter {

    private Context context;
    private List<ContactModel> contactList;

    public ContactAdapter_listview(Context context, List<ContactModel> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact_listview, parent, false);
        }

        // Ánh xạ các thành phần trong item_contact_listview.xml
        ImageView imageViewContact = convertView.findViewById(R.id.imageViewContact);
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewPhone = convertView.findViewById(R.id.textViewPhone);

        // Lấy dữ liệu từ ContactModel
        ContactModel contact = contactList.get(position);
        imageViewContact.setImageResource(contact.getImageResId());
        textViewName.setText(contact.getName());
        textViewPhone.setText(contact.getPhone());

        return convertView;
    }
}