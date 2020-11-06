package com.example.cardgames;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CardGameMaker {

    private static final String KEY_USERID = "_id";
    private static final String KEY_NAME = "Users_Name";
    private static final String KEY_EMAIL = "Users_Email";
    private static final String KEY_PASSWORD = "Users_Password";


    private static final String DATABASE_NAME = "User";
    private static final String DATABASE_TABLE = "userTable";
    private static final int DATABASE_VERISON = 1;

    private DBhelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    private static class DBhelper extends SQLiteOpenHelper{

        public DBhelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERISON);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + //Creates a data table called User
                KEY_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + //First row is to the user id
                KEY_NAME + " TEXT NOT NULL, " + //second is their name
                KEY_EMAIL + " TEXT NOT NULL, " + //third is their email
                KEY_PASSWORD + " TEXT NOT NULL);" //forth is their password (ENCRYPTED)
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }
    public CardGameMaker(Context c){
        ourContext = c;
    }
    public long createUser(String name, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_EMAIL, email);
        cv.put(KEY_PASSWORD, password);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }
    public void updateUser(long id, String name, String email, String pass){
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_EMAIL, email);
        cv.put(KEY_PASSWORD, pass);
        ourDatabase.update(DATABASE_TABLE, cv, KEY_USERID+ "=" + id, null);

    }
    public String getName(String name){
        String str = null;
        String[] col = new String[]{KEY_USERID, KEY_NAME, KEY_EMAIL, KEY_PASSWORD};
        Cursor c = ourDatabase.query(DATABASE_TABLE, col, KEY_NAME + " LIKE " + name, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
            str = c.getString(1);
        }
        return str;
    }
    public String getEmail(String name){
        String str = null;
        String[] col = new String[]{KEY_USERID, KEY_NAME, KEY_EMAIL, KEY_PASSWORD};
        Cursor c = ourDatabase.query(DATABASE_TABLE, col, KEY_NAME + " LIKE " + name, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
            str = c.getString(2);
        }
        return str;
    }
    public String getPassword(String name){
        String str = null;
        String[] col = new String[]{KEY_USERID, KEY_NAME, KEY_EMAIL, KEY_PASSWORD};
        Cursor c = ourDatabase.query(DATABASE_TABLE, col, KEY_NAME + " LIKE " + name, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
            str = c.getString(3);
        }
        return str;
    }
    public void deleteUser(String name){
        ourDatabase.delete(DATABASE_TABLE, KEY_NAME + " LIKE " + name, null);
    }
    public String getData(){
        String[] col = new String[]{KEY_USERID, KEY_NAME, KEY_EMAIL, KEY_PASSWORD};
        Cursor c = ourDatabase.query(DATABASE_TABLE, col, null, null, null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_USERID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iEmail = c.getColumnIndex(KEY_EMAIL);
        int iPass = c.getColumnIndex(KEY_PASSWORD);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(iRow) + ".) " + c.getString(iName) + " " + c.getString(iEmail) + " " + c.getString(iPass) + "\n";
        }
        return result;
    }
    public CardGameMaker open()throws SQLException {
        ourHelper = new DBhelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        ourHelper.close();
    }
}
