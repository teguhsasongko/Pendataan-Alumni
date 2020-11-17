package pnj.ac.id.uts.teguhtrisasongko.fragment.berita;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import pnj.ac.id.uts.teguhtrisasongko.DetailBeritaActivity;
import pnj.ac.id.uts.teguhtrisasongko.LoginActivity;
import pnj.ac.id.uts.teguhtrisasongko.R;
import pnj.ac.id.uts.teguhtrisasongko.adapter.AdapterBerita;
import pnj.ac.id.uts.teguhtrisasongko.fragment.profile.ProfileFragment;
import pnj.ac.id.uts.teguhtrisasongko.model.BeritaModel;

public class BeritaFragment extends Fragment {
    ListView listView;
    AdapterBerita adapterBerita;
    ProfileFragment profileFragment = new ProfileFragment();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_berita,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);
        adapterBerita = new AdapterBerita(getActivity(), R.layout.item_berita_layout);
        listView.setAdapter(adapterBerita);
        loadDataList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BeritaModel model = (BeritaModel) parent.getAdapter().getItem(position);

                Intent intent =new Intent(getActivity(), DetailBeritaActivity.class);
                intent.putExtra("image", model.getImage());
                intent.putExtra("judul", model.getJudulBerita());
                intent.putExtra("isi",model.getIsiBerita());
                startActivity(intent);

            }
        });

        registerForContextMenu(listView);
    }

    void loadDataList() {
        String[] image = new String[]{"https://borderpnj.or.id/wp-content/uploads/2020/08/Green-and-Orange-Handdrawn-Science-Class-Education-Presentation.jpg",
                "https://borderpnj.or.id/wp-content/uploads/2020/06/PicsArt_06-11-10.16.03-1-1024x682.jpg",
                "https://borderpnj.or.id/wp-content/uploads/2020/05/WhatsApp-Image-2019-02-17-at-12.08.54.jpeg"};

        String[] judul = new String[]{"Satu Persen Lebih Baik Setiap Hari",
                "Apakah Peran Guru Akan Hilang?",
                "MENJADI PEMIMPIN YANG KITA IMPIKAN"};

        String[] isiBerita = new String[]{"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."};

        for(int i=0; i< image.length;i++){
            BeritaModel model = new BeritaModel();
            model.setImage(image[i]);
            model.setJudulBerita(judul[i]);
            model.setIsiBerita(isiBerita[i]);
            adapterBerita.add(model);
        }
        adapterBerita.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actTambahData:
                Toast.makeText(getActivity(),"Menu 1 dipilih", Toast.LENGTH_SHORT).show();
                break;
            case R.id.actDataAlumni:
                Toast.makeText(getActivity(),"Data Alumni Politeknik Negeri Jakarta", Toast.LENGTH_SHORT).show();
                break;
            case R.id.actionLogout:
                Toast.makeText(getActivity(),"Keluar", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actTambahData:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, profileFragment);
                ft.commit();
                break;
            case R.id.actDataAlumni:
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.container, profileFragment);
                ft2.commit();
                break;
            case R.id.actLogout:
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UTS - Teguh Tri Sasongko - 18072421005", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear();
                edit.commit();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                Toast.makeText(getActivity(),"Anda Telah Keluar dari Sesi", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
