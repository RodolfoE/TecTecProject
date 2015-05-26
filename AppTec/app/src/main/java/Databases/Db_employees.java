package Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

import db_Lines.Employee;

/**
 * Created by Rodolfo on 26/05/2015.
 */
public class Db_employees {
    public static final String DB_NAME = "TecTecDB";
    public static final String TABLE_NAME = "Employee";

    //defining the column names
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_AREA = "Area";
    public static final String COLUMN_PASSWORD = "Password";

    SQLiteDatabase mDb;
    Context mContext;

    public Db_employees(Context context){
        mContext = context;
        mDb = context.openOrCreateDatabase(DB_NAME, mContext.MODE_WORLD_WRITEABLE, null);
        mDb.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        COLUMN_NAME + " varchar(100)," +
                        COLUMN_AREA + " varchar(100)," +
                        COLUMN_PASSWORD + " varchar(100)," +
                        "PRIMARY KEY ('" + COLUMN_NAME + "') );"
                 );
    }

    public ArrayList<Employee> getEmployeeByArea(String Area){
        ArrayList<Employee> retorner = new ArrayList<Employee>();
        Cursor cursor = mDb.query(TABLE_NAME, null, Area, null, null, null, null, null);

        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                Employee employee = new Employee(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                retorner.add(employee);
            }
        }
        return retorner;
    }

    public Employee getEmployeeByName(String name){
        Cursor cursor = mDb.query(TABLE_NAME, null, name, null, null, null, null, null);
        cursor.moveToFirst();

        Employee retorner = new Employee(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        return retorner;
    }

    public boolean existsEmployee(String name){
        Cursor cursor = mDb.query(TABLE_NAME, new String[]{COLUMN_NAME}, COLUMN_NAME + "='" + name + "'", null, null, null, null);
        return (cursor.moveToFirst());
    }

    public boolean insertEmployee(Employee employee){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, employee.getName());
        values.put(COLUMN_AREA, employee.getArea());
        values.put(COLUMN_PASSWORD, employee.getPassword());
        mDb.insert(TABLE_NAME, null, values);
        return true;
    }

    public void close(){
        mDb.close();
    }
}
