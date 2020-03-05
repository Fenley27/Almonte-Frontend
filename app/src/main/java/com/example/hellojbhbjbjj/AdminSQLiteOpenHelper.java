package com.example.hellojbhbjbjj;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    private static final String SQL_CREATE_ClIENTS =
            "CREATE TABLE Clients ("+"idClient INTEGER  PRIMARY KEY AUTOINCREMENT,"+"id TEXT UNIQUE,"+ "firstname TEXT NOT NULL,"+
                    "lastname TEXT NOT NULL,"+"identification TEXT NOT NULL UNIQUE,"+"phone TEXT NOT NULL,"+
                    "adress TEXT NOT NULL,"+"city TEXT NOT NULL,"+"description TEXT NOT NULL,"+
                    "points INTEGER DEFAULT 500,"+"upTOdate TEXT DEFAULT 'false',"+"sincronized TEXT DEFAULT 'false'"+")";

    private static final String SQL_CREATE_USERS =
            "CREATE TABLE Users ("+ "idlocal INTEGER  PRIMARY KEY AUTOINCREMENT,"+
                    "id TEXT UNIQUE,"+"name TEXT NOT NULL,"+"username TEXT UNIQUE NOT NULL,"+
                    "password TEXT NOT NULL,"+"status NUMERIC DEFAULT 0,"+
                    "upTOdate TEXT DEFAULT 'false',"+"sincronized TEXT DEFAULT 'false'"+")";

    private static final String SQL_CREATE_PLAN =
            "CREATE TABLE Plans("+ "idPlan INTEGER  PRIMARY KEY AUTOINCREMENT,"+
                    "id TEXT UNIQUE,"+"name TEXT UNIQUE,"+"numberCuotes REAL NOT NULL,"+"rateOfInterest REAL NOT NULL,"+
                    "date TEXT NOT NULL"+")";

    private static final String SQL_CREATE_LOANS =
            "CREATE TABLE Loans ("+"idLoan INTEGER  PRIMARY KEY AUTOINCREMENT,"+"id TEXT UNIQUE,"+
                    "amount REAL NOT NULL,"+"payStatus NUMERIC DEFAULT 0,"+
                    "status TEXT DEFAULT 'unpaid',"+"date TEXT NOT NULL,"+"upTOdate TEXT DEFAULT 'false',"+
                    "client TEXT,"+"plane TEXT,"+
                    "sincronized TEXT DEFAULT 'false',"+"FOREIGN KEY(client) REFERENCES Clients(id),"+"FOREIGN KEY(plane) REFERENCES Plans(id)"+")";

    private static final String SQL_CREATE_PREVIEW_LOANS =
            "CREATE TABLE PreviewLoans ("+ "idPreview INTEGER  PRIMARY KEY AUTOINCREMENT,"+"idLoan TEXT,"+
                    "idPreviewsLoan TEXT,"+
                    "FOREIGN KEY(idLoan) REFERENCES Loans(id),"+
                    "FOREIGN KEY(idPreviewsLoan) REFERENCES Loans(id)"+")";

    private static final String SQL_CREATE_INTEREST =
            "CREATE TABLE Interest ("+ "idInterest INTEGER  PRIMARY KEY AUTOINCREMENT,"+"idLoan TEXT,"+
                    "amount REAL NOT NULL,"+"date TEXT NOT NULL,"+
                    "FOREIGN KEY(idLoan) REFERENCES Loans(id)"+")";

    private static final String SQL_CREATE_DUES =
            "CREATE TABLE Dues ("+"idDue INTEGER  PRIMARY KEY AUTOINCREMENT,"+"idLoan TEXT NOT NULL,"+
                    "amount TEXT NOT NULL,"+"date TEXT ,"+
                    "FOREIGN KEY(idLoan) REFERENCES Loans(id)"+ ")";

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ClIENTS);
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_LOANS);
        db.execSQL(SQL_CREATE_PREVIEW_LOANS);
        db.execSQL(SQL_CREATE_INTEREST);
        db.execSQL(SQL_CREATE_DUES);
        db.execSQL(SQL_CREATE_PLAN);
    }


    public long insertDues(String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idLoan",name);
        contentValues.put("amount","");
        contentValues.put("date",marks);
        long result = db.insert("Dues",null ,contentValues);
        return result;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteUsers(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS  USERS");
    }


}
