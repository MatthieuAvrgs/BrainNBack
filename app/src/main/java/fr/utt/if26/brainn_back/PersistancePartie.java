package fr.utt.if26.brainn_back;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;

import fr.utt.if26.brainn_back.Game.Partie;

public class
PersistancePartie extends SQLiteOpenHelper {

    public static final int DATABASE_version = 1;
    public static final String DATABASE_NAME = "historique.db";
    public static final String TABLE_HISTORIQUE = "historique";
    public static final String  ATTRIBUT_ID ="_id";
    public static final String  ATTRIBUT_DATE ="date";
    public static final String  ATTRIBUT_NIVEAU ="niveau";
    public static final String ATTRIBUT_COULEUR = "couleur";
    public static final String ATTRIBUT_SON = "son";
    public static final String ATTRIBUT_SCORE = "score";
    public static final String TABLE_HISTORIQUE_CREATE =
            "CREATE TABLE " + TABLE_HISTORIQUE + "(" +
                    ATTRIBUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ATTRIBUT_DATE + " TEXT, " +
                    ATTRIBUT_NIVEAU + " INTEGER, " +
                    ATTRIBUT_COULEUR + " TEXT," +
                    ATTRIBUT_SON + " TEXT," +
                    ATTRIBUT_SCORE + " INTEGER " +
                    ")";
    public static final String TABLE_HISTORIQUE_DROP = "DROP TABLE IF EXISTS " + DATABASE_NAME + ";";

    public PersistancePartie(Context context) {

        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_HISTORIQUE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_HISTORIQUE_DROP);
        onCreate(db);
    }

    public void initData() {
        /*ContentValues values_test = new ContentValues();

        //Date du jour
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        String date = (""+mDay+"-"+mMonth+"-"+mYear);

        //remplissage du bundle
        values_test.put(ATTRIBUT_DATE, date);
        values_test.put(ATTRIBUT_NIVEAU, 3);
        values_test.put(ATTRIBUT_SON, String.valueOf(
                true));
        values_test.put(ATTRIBUT_COULEUR, String.valueOf(false));
        values_test.put(ATTRIBUT_SCORE,100);

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_HISTORIQUE, null, values_test);
        db.close();*/
    }

    //ajout des parties dans la base de donnée
    public void addPartie(Partie p){
        ContentValues values = new ContentValues();

        //remplissage du bundle
        values.put(ATTRIBUT_DATE, getDateDuJour());
        values.put(ATTRIBUT_NIVEAU, p.getSettingPartie().getNiveau());
        values.put(ATTRIBUT_SON, String.valueOf(
                p.getSettingPartie().isSon()));
        values.put(ATTRIBUT_COULEUR, String.valueOf(p.getSettingPartie().isCouleur()));
        values.put(ATTRIBUT_SCORE, p.getScorePoint());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_HISTORIQUE, null, values);
        db.close();

    }

    //récupérer la liste des parties
    public Cursor getListeParties() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_HISTORIQUE,null);
        return cursor;
    }

    public int getMeilleurScore(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX("+ATTRIBUT_SCORE+") FROM " + TABLE_HISTORIQUE,null);
        cursor.moveToNext();
        int result = cursor.getInt(0);
        return result;
    }
    public int getMeilleurNiveau(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX("+ATTRIBUT_NIVEAU+") FROM " + TABLE_HISTORIQUE,null);
        cursor.moveToNext();
        int result = cursor.getInt(0);
        return result;
    }
    public int getNombrePartiesJouees(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_HISTORIQUE + " WHERE "+ ATTRIBUT_DATE +"=='"+getDateDuJour()+"'",null);
        cursor.moveToNext();
        int result = cursor.getInt(0);
        return result;
    }

    public String getDateDuJour (){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        String date = (""+mDay+"-"+mMonth+"-"+mYear);
        return date;
    }


}
