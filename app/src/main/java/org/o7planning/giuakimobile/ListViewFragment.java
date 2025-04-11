package org.o7planning.giuakimobile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class ListViewFragment extends Fragment {

    private ListView listViewContacts;
    private List<ContactModel> contactList;
    private ContactAdapter_listview adapter;

    public ListViewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listview, container, false);


        listViewContacts = view.findViewById(R.id.listViewContacts);


        contactList = new ArrayList<>();
        contactList.add(new ContactModel(R.drawable.ndm_made, "Thanh Trà", "0987654321"));
        contactList.add(new ContactModel(R.drawable.mai_anh, "Mai Anh", "0123456789"));
        contactList.add(new ContactModel(R.drawable.chau_nga, "Châu Nga", "0123456654"));
        contactList.add(new ContactModel(R.drawable.bao_tram, "Bảo Trâm", "0123454321"));
        contactList.add(new ContactModel(R.drawable.ngo_tra, "Ngô Trà", "0989898989"));
        contactList.add(new ContactModel(R.drawable.thanh, "Thanh", "0646464646"));


        adapter = new ContactAdapter_listview(getContext(), contactList);
        listViewContacts.setAdapter(adapter);

        return view;
    }
}