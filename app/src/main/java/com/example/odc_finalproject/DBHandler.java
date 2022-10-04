package com.example.odc_finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "FoodHub";
    private static final String t="cart";
    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "users";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String EmailCol = "Email";
    // below variable is for our course name column
    private static final String FnameCol = "FullName";

    private static final String UserID = "UserID";
    private static final String FOOD = "FOOD";
    private static final String IItemTYPE = "IItemTYPE";


    // below variable for our course description column.
    private static final String passCol = "pass";



    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EmailCol + " String,"
                + FnameCol + " String,"
                + passCol + " String)";

        // at last we are calling a exec sql
        // method to execute above sql query

       db.execSQL(query);
        String query1 = "CREATE TABLE " + t + " ("
                + UserID + " INTEGER , "
                + FOOD + " INTEGER,"

                + IItemTYPE + " INTEGER)";
        db.execSQL(query1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME+"'");

        db.execSQL("DROP TABLE IF EXISTS '" + t+"'");
        onCreate(db);
    }
    // this method is use to add new course to our sqlite database.
    public void addNewCourse(String FullName, String E_MAIL, String Password) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(FnameCol, FullName);
        values.put(EmailCol, E_MAIL);
        values.put(passCol, Password);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
    public void AddToCart(int userID,int food,int type) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("IItemTYPE ", type);
        values.put("FOOD" , food);
        values.put("UserID", userID);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
public String getUserName(String Email,String pass){
    String userName="";
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE "+EmailCol+"="+"'"+Email+"'"+" AND "+passCol+"="+"'"+pass+"'", null);

    if (cursor.moveToFirst()) {
        do {
            userName=cursor.getString(2);
        }while (cursor.moveToNext());}
        return userName;
}

public boolean CheckUser(String Email,String pass){
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE "+EmailCol+"="+"'"+Email+"'"+" AND "+passCol+"="+"'"+pass+"'", null);
    int counter=0;
    if (cursorCourses.moveToFirst()) {
        do {
            counter++;
        }while (cursorCourses.moveToNext());}


    if (counter==0){
        return false;
    }else{
        return  true;
    }


}
public int getUserID(String Email,String pass){
    int id=0;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE "+EmailCol+"="+"'"+Email+"'"+" AND "+passCol+"="+"'"+pass+"'", null);
    if (cursorCourses.moveToFirst()) {
        do {
            // on below line we are adding the data from cursor to our array list.


            id=   cursorCourses.getInt(0);
        } while (cursorCourses.moveToNext());

}
    return id;
}


    // we have created a new method for reading all the courses.
    public ArrayList<CourseModal> readCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<CourseModal> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new CourseModal(cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(2)
                        ));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }
    public ArrayList<CartModel> readCart() {
        // on below line we are creating a ArrayList<CartModel> return courseModalArrayList;
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + t, null);

        // on below line we are creating a new array list.
        ArrayList<CartModel> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.

       if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new CartModel(cursorCourses.getInt(1),
                        cursorCourses.getInt(2),
                        cursorCourses.getInt(2)
                ));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }


}


