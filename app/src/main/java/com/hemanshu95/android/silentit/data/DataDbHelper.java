package com.hemanshu95.android.silentit.data;

/**
 * Created by hemanshu_sondhi on 4/12/2015.
 */

import android.content.ContentValues;
import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import com.hemanshu95.android.silentit.data.DataContract.DataEntry;


/**
 * Manages a local database for weather data.
 */
public class DataDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "data.db";

    public DataDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold locations.  A location consists of the string supplied in the
        // location setting, the city name, and the latitude and longitude
        final String SQL_CREATE_DATA_TABLE = "CREATE TABLE " + DataEntry.TABLE_NAME + " (" +
                DataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DataEntry.COLUMN_START_HOUR + " INTEGER NOT NULL, " +
                DataEntry.COLUMN_START_MINUTE + " INTEGER NOT NULL, "+
                DataEntry.COLUMN_START_DAY + " INTEGER NOT NULL, "+
                DataEntry.COLUMN_START_MONTH + " INTEGER NOT NULL, "+
                DataEntry.COLUMN_START_YEAR + " INTEGER NOT NULL, "+
                DataEntry.COLUMN_END_HOUR + " INTEGER NOT NULL, " +
                DataEntry.COLUMN_END_MINUTE + " INTEGER NOT NULL, "+
                DataEntry.COLUMN_END_DAY + " INTEGER NOT NULL, "+
                DataEntry.COLUMN_END_MONTH + " INTEGER NOT NULL, "+
                DataEntry.COLUMN_END_YEAR + " INTEGER NOT NULL, "+
                DataEntry.COLUMN_VIBRATION + " INTEGER NOT NULL, "+
                DataEntry.COLUMN_VOLUME + " INTEGER NOT NULL "+
                " );";

        /*final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE " + WeatherEntry.TABLE_NAME + " (" +
                // Why AutoIncrement here, and not above?
                // Unique keys will be auto-generated in either case.  But for weather
                // forecasting, it's reasonable to assume the user will want information
                // for a certain date and all dates *following*, so the forecast data
                // should be sorted accordingly.
                WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

                // the ID of the location entry associated with this weather data
                WeatherEntry.COLUMN_LOC_KEY + " INTEGER NOT NULL, " +
                WeatherEntry.COLUMN_DATETEXT + " TEXT NOT NULL, " +
                WeatherEntry.COLUMN_SHORT_DESC + " TEXT NOT NULL, " +
                WeatherEntry.COLUMN_WEATHER_ID + " INTEGER NOT NULL," +

                WeatherEntry.COLUMN_MIN_TEMP + " REAL NOT NULL, " +
                WeatherEntry.COLUMN_MAX_TEMP + " REAL NOT NULL, " +

                WeatherEntry.COLUMN_HUMIDITY + " REAL NOT NULL, " +
                WeatherEntry.COLUMN_PRESSURE + " REAL NOT NULL, " +
                WeatherEntry.COLUMN_WIND_SPEED + " REAL NOT NULL, " +
                WeatherEntry.COLUMN_DEGREES + " REAL NOT NULL, " +

                // Set up the location column as a foreign key to location table.
                " FOREIGN KEY (" + WeatherEntry.COLUMN_LOC_KEY + ") REFERENCES " +
                LocationEntry.TABLE_NAME + " (" + LocationEntry._ID + "), " +

                // To assure the application have just one weather entry per day
                // per location, it's created a UNIQUE constraint with REPLACE strategy
                " UNIQUE (" + WeatherEntry.COLUMN_DATETEXT + ", " +
                WeatherEntry.COLUMN_LOC_KEY + ") ON CONFLICT REPLACE);";
        */
        sqLiteDatabase.execSQL(SQL_CREATE_DATA_TABLE);
        //sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // Note that this only fires if you change the version number for your database.
        // It does NOT depend on the version number for your application.
        // If you want to update the schema without wiping data, commenting out the next 2 lines
        // should be your top priority before modifying this method.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataEntry.TABLE_NAME);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WeatherEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void createEntry(Integer... params){
        ContentValues cv=new ContentValues();
        cv.put(DataEntry.COLUMN_START_HOUR,params[0]);
        cv.put(DataEntry.COLUMN_START_MINUTE,params[1]);
        cv.put(DataEntry.COLUMN_START_DAY,params[2]);
        cv.put(DataEntry.COLUMN_START_MONTH,params[3]);
        cv.put(DataEntry.COLUMN_START_YEAR,params[4]);
        cv.put(DataEntry.COLUMN_END_HOUR,params[5]);
        cv.put(DataEntry.COLUMN_END_MINUTE,params[6]);
        cv.put(DataEntry.COLUMN_END_DAY,params[7]);
        cv.put(DataEntry.COLUMN_END_MONTH,params[8]);
        cv.put(DataEntry.COLUMN_END_YEAR,params[9]);
        cv.put(DataEntry.COLUMN_VIBRATION,params[10]);
        cv.put(DataEntry.COLUMN_VOLUME,params[11]);
    }
}
