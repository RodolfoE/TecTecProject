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

    SQLiteDatabase mDb_enterprises;

    public static final String TABLE_ENTERPRISES = "report";
    public static final String COLUMN_NAME_ENTERPRISE = "Name_enterprise";

    public static final String TABLE_REPORTS = "reports";
    public static final String COLUMN_PAT_NUMBER = "Pat_number";
    public static final String COLUMN_REPORT_NUMBER = "Name_enterprise";
    public static final String CODE_ENTERPRISE = "codeEnterprise";

    public static final String TABLE_PHOTOS = "PathToPhotos";
    public static final String COLUMN_PATH = "reportPaths";
    public static final String CODE_REPORT = "codigoRelatorio";
    Context mCtx;

    public Db_phot_report(Context ctx){
        this.mCtx = ctx;

        mDb_enterprises = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);

        mDb_enterprises.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_ENTERPRISES +
                " ( '" + COLUMN_NAME_ENTERPRISE + "' varchar(100), " +
                " '" + CODE_ENTERPRISE + "' ," +
                "Primary key('" + COLUMN_NAME_ENTERPRISE + "' );");

        mDb_enterprises.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_REPORTS +
                " ( '" + COLUMN_REPORT_NUMBER + "' varchar(100), '" +
                 COLUMN_PAT_NUMBER + "' varchar(100) );");

        mDb_enterprises.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PHOTOS  + " ('" + COLUMN_PATH + "' varchar(200)," +
                "'" + CODE_REPORT);

    }

    public boolean addReport(PhotReport photReport){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ENTERPRISE, photReport.getmNomeEmpresa());
        values.put(COLUMN_REPORT_NUMBER, photReport.getmNumOrdemManutencao());
        values.put(COLUMN_PAT_NUMBER, photReport.getmNumPat());
        mDb_enterprises.insert(TABLE_ENTERPRISES, null, values);
        return true;
    }

    public boolean deleteReport(){
        return true;
    }

}
