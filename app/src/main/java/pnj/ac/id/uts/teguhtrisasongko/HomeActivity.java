package pnj.ac.id.uts.teguhtrisasongko;


import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import pnj.ac.id.uts.teguhtrisasongko.database.DatabaseHelper;
import pnj.ac.id.uts.teguhtrisasongko.fragment.berita.BeritaFragment;
import pnj.ac.id.uts.teguhtrisasongko.fragment.home.HomeFragment;
import pnj.ac.id.uts.teguhtrisasongko.fragment.profile.ProfileFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button actionHome,actionProfile,actionBerita;
    EditText edtNIM, edtNama, edtTglhr, edtTmplhr, edtThnlulus, edtThnmsk, edtPekerjaan, edtJabatan,edtNotlp,edtAgama,edtAlamat;
    HomeFragment homeFragment = new HomeFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    BeritaFragment beritaFragment = new BeritaFragment();
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferences = getSharedPreferences("UTS - Teguh Tri Sasongko - 1807421005", MODE_PRIVATE);
        actionHome = findViewById(R.id.actionHome);
        actionProfile = findViewById(R.id.actionProfile);
        actionBerita = findViewById(R.id.actionBerita);


        actionHome.setOnClickListener(this);
        actionProfile.setOnClickListener(this);
        actionBerita.setOnClickListener(this);
//        setTitle(sharedPreferences.getString("nama",""));

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, homeFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (v.getId()){
            case R.id.actionHome:
                fragmentTransaction.replace(R.id.container, homeFragment);
                fragmentTransaction.commit();
                break;
            case R.id.actionBerita:
                fragmentTransaction.replace(R.id.container, beritaFragment);
                fragmentTransaction.commit();
                break;
            case R.id.actionProfile:
                fragmentTransaction.replace(R.id.container, profileFragment);
                fragmentTransaction.addToBackStack("Profile");
                fragmentTransaction.commit();
                break;
        }
    }

    void simpan(){
        SQLiteDatabase database = new DatabaseHelper(this).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NIM",edtNIM.getText().toString());
        contentValues.put("nama_alumni",edtNama.getText().toString());
        contentValues.put("tgl_lhr",edtTglhr.getText().toString());
        contentValues.put("tempat_lhr",edtTmplhr.getText().toString());
        contentValues.put("alamat",edtAlamat.getText().toString());
        contentValues.put("agama",edtAgama.getText().toString());
        contentValues.put("no_tlp",edtNotlp.getText().toString());
        contentValues.put("thn_masuk",edtThnmsk.getText().toString());
        contentValues.put("thn_lulus",edtThnlulus.getText().toString());
        contentValues.put("pekerjaan",edtPekerjaan.getText().toString());
        contentValues.put("jabatan",edtJabatan.getText().toString());

        long insert = database.insert("tb_user",null,contentValues);
    }

}