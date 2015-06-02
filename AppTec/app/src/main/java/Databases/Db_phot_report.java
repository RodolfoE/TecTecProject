package Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import db_Lines.PhotReport;

/**
 * Created by Rodolfo on 29/05/2015.
 */
public class Db_phot_report {
    public static final String DB_NAME = Db_employees.DB_NAME;
    public static final String TABLE_ENTERPRISE_NAME = "PhotoReport"; // enterpriseId auto-increments - COLUMN NAME ENTERPRISE
    public static final String COLUMN_NAME_ENTERPRISE = "NameEnterprise";
    public static final String COLUMN_ENTERPRISE_ID = "EnterpriseId";

    public static final String TABLE_REPORTS = "Photograph_reports"; // id auto-increment - foreing key from Table_Enterprise_name - COLUM_PAT_NUMBER - COLUMN_REPORT_NUMBER
    public static final String COLUMN_PAT_NUMBER = "Pat_number";
    public static final String COLUMN_REPORT_NUMBER = "Report_Number";
    public static final String COLUMN_REPORT_ID = "ReportId";


    public static final String TABLE_PHOTOS = "Photos_from_reports"; // foreing key from TABLE_REPORTs - Photos from the report
    public static final String COLUMN_PHOTOS = "Photos";

    SQLiteDatabase mDatabase;
    Context mCtx;

    public Db_phot_report(Context ctx){
        this.mCtx = ctx;

        mDatabase = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);

        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_ENTERPRISE_NAME +
                " ( " + COLUMN_ENTERPRISE_ID + " int AUTO_INCREMENT, " + COLUMN_NAME_ENTERPRISE + " varchar(100)," +
                "PRIMARY KEY( " + COLUMN_ENTERPRISE_ID + " ));");

        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_REPORTS + " (" + COLUMN_REPORT_ID + " int AUTO_INCREMENT, " +
                "FOREIGN KEY ("+ COLUMN_ENTERPRISE_ID +") REFERENCES " + TABLE_ENTERPRISE_NAME + "("+ COLUMN_ENTERPRISE_ID +"), " +
                COLUMN_REPORT_NUMBER + " varchar(100), " + COLUMN_PAT_NUMBER + " varchar(100), " +
                "PRIMARY KEY(" + COLUMN_REPORT_ID + ") );");

        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PHOTOS + "( FOREIGN KEY (" + COLUMN_REPORT_ID + ") REFERENCES " + TABLE_REPORTS + "(R_ID), " +
                COLUMN_PHOTOS + " varchar(200) );");
    }

    //If the name of the enterprise already exists, It's not going to be inserted.
    public boolean insertEnterprise(String enterpriseName){
        if (!containsEnterprise(enterpriseName)) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_ENTERPRISE, enterpriseName);
            mDatabase.insert(TABLE_ENTERPRISE_NAME, "", values);
            return true;
        }
        else
            return false;
    }
    public boolean containsEnterprise(String enterpriseName) {
        Cursor cursor = mDatabase.query(TABLE_ENTERPRISE_NAME, new String[]{COLUMN_NAME_ENTERPRISE}, COLUMN_NAME_ENTERPRISE + " = '" + enterpriseName + "'", null, null, null, null, null);
        return cursor.moveToFirst();
    }

    public int getEnterpriseId(String enterpriseName){
        Cursor cursor = mDatabase.query(TABLE_ENTERPRISE_NAME, null , COLUMN_NAME_ENTERPRISE + " = '" + enterpriseName + "'", null, null, null, null, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public int getReportId(PhotReport photReport) {
        Cursor cursor = mDatabase.query(TABLE_REPORTS, null, COLUMN_PAT_NUMBER + " = " + photReport.getmNumPat() + " AND " + COLUMN_REPORT_NUMBER + " = " + photReport.getmNumOrdemManutencao(), null, null, null, null);
        cursor.moveToFirst();
        return cursor.getInt(0);

    }

    public boolean addReport(PhotReport photReport){

        if (!containsEnterprise(photReport.getmNomeEmpresa())) {
            insertEnterprise(photReport.getmNomeEmpresa());
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ENTERPRISE, photReport.getmNomeEmpresa());
        values.put(COLUMN_REPORT_NUMBER, photReport.getmNumOrdemManutencao());
        values.put(COLUMN_PAT_NUMBER, photReport.getmNumPat());
        //mDatabase.insert(TABLE_ENTERPRISES, null, values);
        return true;
    }

    public boolean deleteReport(){
        //values.put();

        return true;
    }
    public void close(){
        mDatabase.close();
    }

    public Cursor getAllEnterprise() {
        Cursor cursor = mDatabase.query(TABLE_ENTERPRISE_NAME, null, null, null, null, null, null, null);
        return cursor;
    }

}
