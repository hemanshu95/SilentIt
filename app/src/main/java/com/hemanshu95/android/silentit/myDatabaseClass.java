package com.hemanshu95.android.silentit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by hemanshu_sondhi on 4/13/2015.
 */


public class myDatabaseClass {
    //public static final String TABLE_NAME = "data";
    public static final String COLUMN_DATA_ID = "data_id";
    // Column with the foreign key into the location table.
    public static final String COLUMN_START_HOUR = "start_hour";
    public static final String COLUMN_START_MINUTE = "start_minute";

    public static final String COLUMN_START_DAY = "start_day";
    public static final String COLUMN_START_MONTH = "start_month";
    public static final String COLUMN_START_YEAR = "start_year";
    public static final String _ID = "id";
    public static final String COLUMN_END_HOUR = "end_hour";
    public static final String COLUMN_END_MINUTE = "end_minute";

    public static final String COLUMN_END_DAY = "end_day";
    public static final String COLUMN_END_MONTH = "end_month";
    public static final String COLUMN_END_YEAR = "end_year";
    public static final String COLUMN_START = "start";
    public static final String COLUMN_END = "end";


    public static final String COLUMN_VIBRATION = "vibration";

    // Min and max temperatures for the day (stored as floats)
    public static final String COLUMN_VOLUME = "volume";

    private static final String DATABASE_NAME = "DataDb";
    private static final String DATABASE_TABLE = "DataTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public long createEntry(Integer... params){
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_START_HOUR,params[0]);
        cv.put(COLUMN_START_MINUTE,params[1]);
        cv.put(COLUMN_START_DAY,params[2]);
        cv.put(COLUMN_START_MONTH,params[3]);
        cv.put(COLUMN_START_YEAR,params[4]);
        cv.put(COLUMN_END_HOUR,params[5]);
        cv.put(COLUMN_END_MINUTE,params[6]);
        cv.put(COLUMN_END_DAY,params[7]);
        cv.put(COLUMN_END_MONTH,params[8]);
        cv.put(COLUMN_END_YEAR,params[9]);
        cv.put(COLUMN_VIBRATION,params[10]);
        cv.put(COLUMN_VOLUME,params[11]);
        cv.put(COLUMN_START,1);
        cv.put(COLUMN_END,1);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }



/*    public String gethostel(String mobile) {
        String[] columns = new String[]{KEY_ROWID, KEY_MOBILE, KEY_HOSTEL};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_MOBILE + "=" + mobile,  null, null, null, null);
        if(c!=null){
            c.moveToFirst();
            String hostel = c.getString(2);
            return hostel;
        }
        return "";
    }

*/

/*
    public void modify(long l, String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_YEAR, s1);
        cvUpdate.put(KEY_NAME, s2);
        cvUpdate.put(KEY_PASSWORD, s3);
        cvUpdate.put(KEY_HOSTEL, s4);
        cvUpdate.put(KEY_ROOMNO, s5);
        cvUpdate.put(KEY_WHATSAPP, s6);
        cvUpdate.put(KEY_EMAIL, s7);
        ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + l, null);
    }
*/









    // public void delete_entry(String mobile) {
    //ourDatabase.delete(DATABASE_TABLE, , null);
    //}


    private static class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            final String SQL_CREATE_DATA_TABLE = "CREATE TABLE " + DATABASE_TABLE + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_START_HOUR + " INTEGER NOT NULL, " +
                    COLUMN_START_MINUTE + " INTEGER NOT NULL, "+
                    COLUMN_START_DAY + " INTEGER NOT NULL, "+
                    COLUMN_START_MONTH + " INTEGER NOT NULL, "+
                    COLUMN_START_YEAR + " INTEGER NOT NULL, "+
                    COLUMN_END_HOUR + " INTEGER NOT NULL, " +
                    COLUMN_END_MINUTE + " INTEGER NOT NULL, "+
                    COLUMN_END_DAY + " INTEGER NOT NULL, "+
                    COLUMN_END_MONTH + " INTEGER NOT NULL, "+
                    COLUMN_END_YEAR + " INTEGER NOT NULL, "+
                    COLUMN_VIBRATION + " INTEGER NOT NULL, "+
                    COLUMN_VOLUME + " INTEGER NOT NULL, "+
                    COLUMN_START+" INTEGER NOT NULL, "+
                    COLUMN_END+" INTEGER NOT NULL"+
                    " );";
            db.execSQL(SQL_CREATE_DATA_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }

            // looping through all rows and adding to list
        /*if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }*/

            // return contact list


    }
    public Cursor getAllData() {
        //List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + DATABASE_TABLE+" ORDER BY "+COLUMN_START_YEAR+","+COLUMN_START_MONTH+","+COLUMN_START_DAY+","+COLUMN_START_HOUR+","+COLUMN_START_MINUTE+" ASC";

        Cursor cursor = ourDatabase.rawQuery(selectQuery, null);
        return cursor;
    }
    public void deletedata(){
        String deleteQuery = "DELETE FROM " + DATABASE_TABLE+" WHERE "+COLUMN_START+"= 0 AND "+COLUMN_END+" = 0 ";
        ourDatabase.execSQL(deleteQuery);
        //ourDatabase.delete(DATABASE_TABLE,COLUMN_START_YEAR+"= ? AND "+COLUMN_END_YEAR+" = ? ",);
        //Cursor cursor = ourDatabase.rawQuery(selectQuery, null);
    }
    public void updatedata(int id,int flag){

        String updateQuery;
        if(flag==10)
            updateQuery = "UPDATE " + DATABASE_TABLE+" SET "+COLUMN_END+" = 0 WHERE "+_ID+" = "+id;
        else
            updateQuery = "UPDATE " + DATABASE_TABLE+" SET "+COLUMN_START+" = 0 WHERE "+_ID+" = "+id;

        ourDatabase.execSQL(updateQuery);
    }
    public Bundle next_time()
    {
        Bundle bundle=new Bundle();
        int flag=0;
        String selectQuery = "SELECT * FROM " + DATABASE_TABLE+" WHERE "+COLUMN_START+" <> 0 ORDER BY "+COLUMN_START_YEAR+","+COLUMN_START_MONTH+","+COLUMN_START_DAY+","+COLUMN_START_HOUR+","+COLUMN_START_MINUTE+" ASC LIMIT 1";
        Toast.makeText(ourContext,selectQuery,Toast.LENGTH_LONG).show();
        Cursor cursor1 = ourDatabase.rawQuery(selectQuery, null);

        selectQuery = "SELECT * FROM " + DATABASE_TABLE+" WHERE "+COLUMN_END+" <> 0 ORDER BY "+COLUMN_END_YEAR+","+COLUMN_END_MONTH+","+COLUMN_END_DAY+","+COLUMN_END_HOUR+","+COLUMN_END_MINUTE+" ASC LIMIT 1";
        Cursor cursor2 = ourDatabase.rawQuery(selectQuery, null);
        Toast.makeText(ourContext,"im"+Integer.toString(cursor1.getCount()),Toast.LENGTH_LONG).show();
        Toast.makeText(ourContext,"imx"+Integer.toString(cursor2.getCount()),Toast.LENGTH_LONG).show();

        if(cursor1.getCount()==0 && cursor2.getCount()==0)
            return null;
        else if(cursor1.getCount()==0)
            flag=-1;
        else if(cursor2.getCount()==0)
            flag=1;
        else if(cursor2.getCount()>0 && cursor1.getCount()>0 && cursor1.moveToFirst() && cursor2.moveToFirst())
        {
            Toast.makeText(ourContext," heelo world ",Toast.LENGTH_LONG).show();
            if(cursor1.getInt(5)<cursor2.getInt(10))
                flag=1;
            else if(cursor1.getInt(5)>cursor2.getInt(10))
                flag=-1;
            else
            {
                if(cursor1.getInt(4)<cursor2.getInt(9))
                    flag=1;
                else if(cursor1.getInt(4)>cursor2.getInt(9))
                    flag=-1;
                else
                {
                    if(cursor1.getInt(3)<cursor2.getInt(8))
                        flag=1;
                    else if(cursor1.getInt(3)>cursor2.getInt(8))
                        flag=-1;
                    else
                    {
                        if(cursor1.getInt(1)<cursor2.getInt(6))
                            flag=1;
                        else if(cursor1.getInt(1)>cursor2.getInt(6))
                            flag=-1;
                        else
                        {
                            if(cursor1.getInt(2)<cursor2.getInt(7))
                                flag=1;
                            else if(cursor1.getInt(2)>cursor2.getInt(7))
                                flag=-1;

                        }
                    }
                }
            }
        }
        if(flag<0)
        {
            bundle.putInt("year",cursor2.getInt(10));
            bundle.putInt("month",cursor2.getInt(9));
            bundle.putInt("day",cursor2.getInt(8));
            bundle.putInt("hour",cursor2.getInt(6));
            bundle.putInt("minute",cursor2.getInt(7));
            bundle.putInt("volume",7);
            bundle.putBoolean("vibrate",false);
            bundle.putInt("id",cursor2.getInt(0));
            bundle.putInt("flag",10);
        }
        else
        {
            bundle.putInt("year",cursor1.getInt(5));
            bundle.putInt("month",cursor1.getInt(4));
            bundle.putInt("day",cursor1.getInt(3));
            bundle.putInt("hour",cursor1.getInt(1));
            bundle.putInt("minute",cursor1.getInt(2));
            bundle.putInt("volume",cursor1.getInt(12));
            Boolean x=false;
            if(cursor1.getInt(11)>0)
                x=true;
            bundle.putBoolean("vibrate",x);
            bundle.putInt("id",cursor1.getInt(0));
            bundle.putInt("flag",5);
        }
        return bundle;
    }

    public myDatabaseClass(Context c) {

        ourContext = c;
    }

    public myDatabaseClass open(){
        //try {
            ourHelper = new DbHelper(ourContext);
            ourDatabase = ourHelper.getWritableDatabase();
        /*}
        catch(Exception e)
        {
            Log.e("hellobrother","errrr",e);
        }*/
        return this;
    }

    public void close(){

        ourHelper.close();
    }
}
