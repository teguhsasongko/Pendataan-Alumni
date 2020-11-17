package pnj.ac.id.uts.teguhtrisasongko.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  String _NAMA_DATABASE="db_user";
    public static int _VERSION = 1;
    public static String _CREATE_TABLE = "create table tb_user (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nim TEXT,name TEXT,tempatlahir TEXT,alamat TEXT,tlp/hp TEXT,tahun masuk TEXT,tahun keluar TEXT,pekerjaan TEXT,jabatan TEXT)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, _NAMA_DATABASE, null, _VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_user");
        onCreate(db);
    }
}
