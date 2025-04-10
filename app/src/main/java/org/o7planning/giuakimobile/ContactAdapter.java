package org.o7planning.giuakimobile;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private List<ContactModel> contactList;
    private IOnChildItemClick listener;

    public ContactAdapter(Context context, List<ContactModel> contactList, IOnChildItemClick listener) {
        this.context = context;
        this.contactList = contactList;
        this.listener = listener;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        }

        // Ánh xạ các thành phần trong item_contact.xml
        ImageView imageViewContact = convertView.findViewById(R.id.imageViewContact);
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewPhone = convertView.findViewById(R.id.textViewPhone);
        ImageButton buttonCall = convertView.findViewById(R.id.buttonCall);
        ImageButton buttonEdit = convertView.findViewById(R.id.buttonEdit);

        // Lấy dữ liệu từ ContactModel
        ContactModel contact = contactList.get(position);
        imageViewContact.setImageResource(contact.getImageResId());
        textViewName.setText(contact.getName());
        textViewPhone.setText(contact.getPhone());

        // Xử lý sự kiện nhấn nút Call
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCallClicked(position);
            }
        });

        // Xử lý sự kiện nhấn nút Edit
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditClicked(position);
            }
        });

        return convertView;
    }
}