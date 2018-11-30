package fr.utt.if26.brainn_back;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class
PersistancePartie extends SQLiteOpenHelper {

    public static final int DATABASE_version = 1;
    public static final String DATABASE_NAME = "historique.db";
    public static final String TABLE_HISTORIQUE = "historique";
    public static final String  ATTRIBUT_ID ="id";
    public static final String  ATTRIBUT_DATE ="date";
    public static final String  ATTRIBUT_NIVEAU ="niveau";
    public static final String ATTRIBUT_COULEUR = "couleur";
    public static final String ATTRIBUT_SON = "son";
    public static final String ATTRIBUT_SCORE = "score";
    public static final String TABLE_HISTORIQUE_CREATE =
            "CREATE TABLE " + TABLE_HISTORIQUE + "(" +
                    ATTRIBUT_ID + " TEXT primary key," +
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


}
