package Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import db_Lines.PhotReport;

/**
 * Created by Rodolfo on 29/05/2015.
 */
public class Db_phot_report {
    public static final String DB_NAME = Db_employees.DB_NAME;
    public static final String TABLE_NAME= "Photo report";
    public static final String COLUMN_NAME_ENTERPRISE = "Name enterprise";
    public static final String COLUMN_PAT_NUMBER = "Name enterprise";
    public static final String COLUMN_REPORT_NUMBER = "Name enterprise";

    SQLiteDatabase mDatabase;
    Context mCtx;

    public Db_phot_report(Context ctx){
        this.mCtx = ctx;
        mDatabase = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " ( " + COLUMN_NAME_ENTERPRISE + " varchar(100)," +
                " ( " + COLUMN_REPORT_NUMBER + " varchar(100), " +
                " ( " + COLUMN_PAT_NUMBER + " varchar(100) )" );
    }

    public boolean addReport(PhotReport photReport){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ENTERPRISE, photReport.getmNomeEmpresa());
        values.put(COLUMN_REPORT_NUMBER, photReport.getmNumOrdemManutencao());
        values.put(COLUMN_PAT_NUMBER, photReport.getmNumPat());
        mDatabase.insert(TABLE_NAME, null, values);
        return true;
    }

}
