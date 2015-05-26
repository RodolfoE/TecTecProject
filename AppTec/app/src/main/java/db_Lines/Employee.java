package db_Lines;

/**
 * Created by Rodolfo on 26/05/2015.
 */
public class Employee {
    String mName, mArea, mPassword;

    public Employee(String name, String area, String password){
        this.mName = name;
        this.mArea = area;
        this.mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public void semName(String mName) {
        this.mName = mName;
    }

    public String getArea() {
        return mArea;
    }

    public void setArea(String mArea) {
        this.mArea = mArea;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
