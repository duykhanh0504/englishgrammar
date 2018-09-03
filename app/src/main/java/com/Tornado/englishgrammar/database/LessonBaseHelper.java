package com.Tornado.englishgrammar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.Tornado.englishgrammar.lesson_recycler_view.Lesson;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.Tornado.englishgrammar.database.LessonDatabase.LessonTable.UUID;

public class LessonBaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH;
    private static String DB_NAME = "lessonBase.db";
    private SQLiteDatabase myDataBase;
    static LessonBaseHelper  database;
    private final Context myContext;


    public static LessonBaseHelper getDatabase(Context context)
    {
        if (database == null)
        {
            database = new LessonBaseHelper(context);
        }
        return database;
    }

    public void insertDb(Lesson data)
    {
       SQLiteDatabase mDatabase =  getDatabase(myContext).getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(LessonDatabase.LessonTable.UUID, data.getUuid());
        contentValues.put(LessonDatabase.LessonTable.lessonName, data.getLessonName());
        contentValues.put(LessonDatabase.LessonTable.lessonDescription, data.getLessonDescription());
        contentValues.put(LessonDatabase.LessonTable.ISFAVORITE, data.isFavorite());
        contentValues.put(LessonDatabase.LessonTable.ISlEANING, data.isLearning());
        contentValues.put(LessonDatabase.LessonTable.TYPE, data.isType());

        mDatabase.insert(LessonDatabase.LessonTable.NAME, null, contentValues);
        mDatabase.close();


    }

    public LessonBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.myContext = context;
        this.createDataBase();
    }

    private void createDataBase(){
        try {
            boolean dbExist = checkDataBase();
            if(!dbExist){
                SQLiteDatabase db = this.getWritableDatabase();
                db.execSQL(
                        "CREATE TABLE IF NOT EXISTS " + LessonDatabase.LessonTable.NAME +
                                "(" + LessonDatabase.LessonTable.UUID + " INTEGER, " /*INTEGER PRIMARY KEY,*/  +
                                LessonDatabase.LessonTable.lessonName + " TEXT, " /*INTEGER PRIMARY KEY,*/  +
                                LessonDatabase.LessonTable.lessonDescription + " TEXT, " +
                                LessonDatabase.LessonTable.ISFAVORITE + " INTEGER, " +
                                LessonDatabase.LessonTable.ISlEANING + " INTEGER, " +
                                LessonDatabase.LessonTable.TYPE + " INTEGER)"


                );
                db.close();
                this.getReadableDatabase();
               // copyDataBase();
                String s = loadJSONFromAsset();
                List<Lesson> lesson = new ArrayList<>();
                JsonArray jsonArray = (new JsonParser()).parse(s).getAsJsonArray();
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<List<Lesson>>(){}.getType();
                lesson = gson.fromJson(jsonArray, type);
                for (Lesson temp: lesson
                     ) {
                    insertDb(temp);

                }
            }
        }
        catch (Exception e) {
            e.getMessage();
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){

        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = myContext.getAssets().open("lessondb.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void copyDataBase(){

        try{
            InputStream myInput = myContext.getAssets().open(DB_NAME);
            String outFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception e) {
        }
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        return myDataBase;
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
        { myDataBase.close();}
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}