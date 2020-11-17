package pnj.ac.id.uts.teguhtrisasongko;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import pnj.ac.id.uts.teguhtrisasongko.database.DatabaseHelper;

public class UpdateAlumniActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtNIM,edtName,edtTempatLahir,edtTanggalLahir,edtAlamat,edtAgama,edtNomorTelp,edtTahunMasuk,edtTahunKeluar,edtPekerjaan,edtJabatan;
    Button actionSimpan;
    Calendar calendar = Calendar.getInstance();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("UTS - Teguh Tri Sasongko", Context.MODE_PRIVATE);
        edtNIM = findViewById(R.id.edtNIM);
        edtName = findViewById(R.id.edtName);
        edtTempatLahir = findViewById(R.id.edtTempatLahir);
        edtTanggalLahir = findViewById(R.id.edtTanggalLahir);
        edtAlamat =findViewById(R.id.edtAlamat);
        edtAgama = findViewById(R.id.edtAgama);
        edtNomorTelp =findViewById(R.id.edtNomorTelp);
        edtTahunMasuk = findViewById(R.id.edtTahunMasuk);
        edtTahunKeluar =findViewById(R.id.edtTahunKeluar);
        edtPekerjaan = findViewById(R.id.edtPekerjaan);
        edtJabatan =findViewById(R.id.edtJabatan);
        actionSimpan = findViewById(R.id.actionSimpan);

        edtTanggalLahir.setOnClickListener(this);
        actionSimpan.setOnClickListener(this);

        SQLiteDatabase database = new DatabaseHelper(this).getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM tb_user WHERE id = ? ", new String[]{""+sharedPreferences.getInt("id",0)});
        edtNIM.setText(cursor.getString(1));
        edtName.setText(cursor.getString(2));
        edtTempatLahir.setText(cursor.getString(3));
        edtTanggalLahir.setText(cursor.getString(4));
        edtAlamat.setText(cursor.getString(5));
        edtAgama.setText(cursor.getString(6));
        edtNomorTelp.setText(cursor.getString(7));
        edtTahunMasuk.setText(cursor.getString(8));
        edtTahunKeluar.setText(cursor.getString(9));
        edtPekerjaan.setText(cursor.getString(10));
        edtJabatan.setText(cursor.getString(11));

        cursor.moveToFirst();
        cursor.close();
        database.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edtTanggalLahir:
                //event click BOD
                new DatePickerDialog(this,dateSetListener,calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.actionSimpan:
                if (edtNIM.getText().toString().length() > 0 && edtName.getText().toString().length() > 0 && edtTempatLahir.getText().toString().length()>0
                        && edtTanggalLahir.getText().toString().length()>0 && edtAlamat.getText().toString().length() > 0 && edtAgama.getText().toString().length() > 0
                        && edtNomorTelp.getText().toString().length()>0 && edtTahunMasuk.getText().toString().length()>0 && edtTahunKeluar.getText().toString().length() > 0
                        && edtPekerjaan.getText().toString().length() > 0 && edtJabatan.getText().toString().length()>0 ){
                    simpan();
                } else {
                    Toast.makeText(this, "Mohon Lengkapi Data", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    void simpan(){
        SQLiteDatabase database = new DatabaseHelper(this).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nim",edtNIM.getText().toString());
        contentValues.put("nama",edtName.getText().toString());
        contentValues.put("tempat lahir",edtTempatLahir.getText().toString());
        contentValues.put("tanggal lahir",edtTanggalLahir.getText().toString());
        contentValues.put("alamat",edtAlamat.getText().toString());
        contentValues.put("agama",edtAgama.getText().toString());
        contentValues.put("tlp/hp",edtNomorTelp.getText().toString());
        contentValues.put("tahun masuk",edtTahunMasuk.getText().toString());
        contentValues.put("tahun keluar",edtTahunKeluar.getText().toString());
        contentValues.put("pekerjaan",edtPekerjaan.getText().toString());
        contentValues.put("jabatan",edtJabatan.getText().toString());

        long update = database.update("tb_user",contentValues,"id=?", new String[]{""+sharedPreferences.getInt("id",0)});
        if (update != -1){
            Toast.makeText(this,"Update Berhasil",Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this,"Update Gagal",Toast.LENGTH_SHORT).show();
        }

        database.close();
    }


    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            edtTanggalLahir.setText(dateFormat.format(calendar.getTime()));

        }
    };
}