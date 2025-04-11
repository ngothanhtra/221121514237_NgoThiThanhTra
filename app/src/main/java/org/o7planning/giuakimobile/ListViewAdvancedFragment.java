package org.o7planning.giuakimobile;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class ListViewAdvancedFragment extends Fragment implements IOnChildItemClick {

    private ListView listViewContacts;
    private ImageView imageViewProfile;
    private TextView textViewUsername;
    private List<ContactModel> contactList;
    private ContactAdapter adapter;
    private static final int REQUEST_CALL_PHONE = 1;

    public ListViewAdvancedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listview_advanced, container, false);


        listViewContacts = view.findViewById(R.id.listViewContacts);
        imageViewProfile = view.findViewById(R.id.imageViewProfile);
        textViewUsername = view.findViewById(R.id.textViewUsername);

        // Khởi tạo danh sách liên hệ (dữ liệu mẫu)
        contactList = new ArrayList<>();
        contactList.add(new ContactModel(R.drawable.ndm_made, "Thanh Trà", " 0987654321"));
        contactList.add(new ContactModel(R.drawable.mai_anh, "Mai Anh", "0123456789"));
        contactList.add(new ContactModel(R.drawable.chau_nga, "Châu Nga", "0123456654"));
        contactList.add(new ContactModel(R.drawable.bao_tram, "Bảo Trâm", "0123454321"));
        contactList.add(new ContactModel(R.drawable.ngo_tra, "Ngô Trà", "0989898989"));
        contactList.add(new ContactModel(R.drawable.thanh, "Thanh", "0646464646"));


        adapter = new ContactAdapter(getContext(), contactList, this);
        listViewContacts.setAdapter(adapter);


        textViewUsername.setText(" Thanh Trà");
        imageViewProfile.setImageResource(R.drawable.tra);

        return view;
    }

    @Override

    public void onCallClicked(int position) {

        String phoneNumber = contactList.get(position).getPhone();

        Toast.makeText(getContext(), "Calling " + phoneNumber, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditClicked(int position) {

        ContactModel contact = contactList.get(position);


        imageViewProfile.setImageResource(contact.getImageResId());
        textViewUsername.setText(contact.getName());

        Toast.makeText(getContext(), "Updated profile with " + contact.getName(), Toast.LENGTH_SHORT).show();
    }
}