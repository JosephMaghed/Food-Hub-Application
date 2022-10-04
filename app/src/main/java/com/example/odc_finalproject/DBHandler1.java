package com.example.odc_finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler1 extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "FoodHub1";
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
    public DBHandler1(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.


        // at last we are calling a exec sql
        // method to execute above sql query
        String query3 = "CREATE TABLE reviews ("
                + "UserName" + " String , "
                + "review" + " String , "
                + "rating" + " Float , "

                + "FoodId" + " INTEGER)";
        db.execSQL(query3);
        String query2 = "CREATE TABLE address ("
                + UserID + " INTEGER , "


                + "IItemTYPE" + " String)";
        db.execSQL(query2);
        String query = "CREATE TABLE orders ("
                + UserID + " INTEGER , "
                + FOOD + " INTEGER,"

                + IItemTYPE + " INTEGER)";
        db.execSQL(query);

        String query1 = "CREATE TABLE " + t + " ("
                + UserID + " INTEGER , "
                + FOOD + " INTEGER,"

                + IItemTYPE + " INTEGER)";
        db.execSQL(query1);
        String query4 = "CREATE TABLE " + "FavoriteFood" + " ("
                + UserID + " INTEGER , "
                + FOOD + " INTEGER,"

                + IItemTYPE + " INTEGER)";
        db.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.


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
        db.insert(t, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
    public void AddToFavorite(int userID,int food,int type) {

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
        db.insert("FavoriteFood", null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
    public void AddToReview(String userName,int foodID,float rating,String review) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("UserName ", userName);
        values.put("FoodId" , foodID);
        values.put("rating", rating);
        values.put("review", review);

        // after adding all values we are passing
        // content values to our table.
        db.insert("reviews", null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
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
    public ArrayList<CartModel> readCart(int id) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM cart WHERE userID= "+"'"+id+"'", null);

        // on below line we are creating a new array list.
        ArrayList<CartModel> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.

       if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new CartModel(cursorCourses.getInt(0),
                        cursorCourses.getInt(1),
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
    public ArrayList<CartModel> readFavorite(int id,int type) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM FavoriteFood WHERE userID= "+"'"+id+"'"+"AND IItemTYPE= '"+type+"'", null);

        // on below line we are creating a new array list.
        ArrayList<com.example.odc_finalproject.CartModel> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.

        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new com.example.odc_finalproject.CartModel(cursorCourses.getInt(0),
                        cursorCourses.getInt(1),
                        cursorCourses.getInt(2)
                ));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;}

    public void ADD_ADDRESS(int Userid,String Address){
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("IItemTYPE ", Address);

        values.put("UserID", Userid);

        // after adding all values we are passing
        // content values to our table.
        db.insert("address", null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
    public ArrayList<AdressModel> readAdress(int id) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM address WHERE userID= "+"'"+id+"'", null);

        // on below line we are creating a new array list.
        ArrayList<AdressModel> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.

        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new AdressModel(cursorCourses.getString(1)

                ));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }
    public ArrayList<ReviewModel> readReviews(int FoodId) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM reviews WHERE FoodId= "+"'"+FoodId+"'", null);

        // on below line we are creating a new array list.
        ArrayList<ReviewModel> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.

        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new ReviewModel(cursorCourses.getString(0),
                        cursorCourses.getString(1),
                                cursorCourses.getFloat(2),
                                        cursorCourses.getInt(3)

                ));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }
    public float Averagereview(int FoodId) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM reviews WHERE FoodId= "+"'"+FoodId+"'", null);

       float AVGReview=0;
       int counter=1;

        // moving our cursor to first position.

        if (cursorCourses.moveToFirst()) {
            counter=0;
            do {
                // on below line we are adding the data from cursor to our array list.
                AVGReview+=cursorCourses.getFloat(2);
                counter++;


            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return AVGReview/counter;
    }
    public ArrayList<CartModel> readOrders(int id) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM orders WHERE UserID= '"+id+"'", null);

        // on below line we are creating a new array list.
        ArrayList<CartModel> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.

        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new CartModel(cursorCourses.getInt(0),
                        cursorCourses.getInt(1),
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
    public void deleteFromCart(int id) {
        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor cursorCourses = db1.rawQuery("SELECT * FROM cart WHERE UserID= '"+id+"'", null);
        if (cursorCourses.moveToFirst()) {
            do {

                SQLiteDatabase db2 = this.getWritableDatabase();


                ContentValues values = new ContentValues();

                // on below line we are passing all values
                // along with its key and value pair.
                values.put("IItemTYPE ", cursorCourses.getInt(2));
                values.put("FOOD" , cursorCourses.getInt(1));
                values.put("UserID", cursorCourses.getInt(0));

                // after adding all values we are passing
                // content values to our table.
                db2.insert("orders", null, values);

                // at last we are closing our
                // database after adding database.
                db2.close();
                // on below line we are adding the data from cursor to our array list.


            } while (cursorCourses.moveToNext());

        // on below line we are creating
        // a variable to write our database.

        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(t, "UserID=  '"+id+"'",null);
        db.close();
    }


  /*  public boolean readcart() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();
        int c=0;

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + t, null);

        // on below line we are creating a new array list.
        ArrayList<CartModel> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.

        if (cursorCourses.moveToFirst()) {
            do {
                c++;
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new CartModel(cursorCourses.getInt(0),
                        cursorCourses.getInt(1),
                        cursorCourses.getInt(2)
                ));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        if(c==0){
            return false;
    }else {
            return true;
        }


}*/}}


