package com.example.plusorderfinal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "plusorder1.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "usuarios";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableQuery);

        insertPredefinedUser(db, "admin", "admin@example.com", "admin123");
    }

    private void insertPredefinedUser(SQLiteDatabase db, String username, String email, String password) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        db.insert(TABLE_NAME, null, values);
    }

    @SuppressLint("Range")
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        // Query para seleccionar todos los usuarios de la tabla
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Iterar a través de todas las filas y agregar usuarios a la lista
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return userList;
    }

    public boolean authenticateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID},
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{username, password}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    public boolean isUsernameTaken(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID},
                COLUMN_USERNAME + "=?",
                new String[]{username}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    public long insertUser(String username, String email, String password) {
        if (isUsernameTaken(username)) {
            return -1;
        }
        if (isPredefinedUser(username, email, password)) {
            return -2;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    private boolean isPredefinedUser(String username, String email, String password) {
        // Definir los datos de los usuarios predefinidos
        String[] predefinedUsernames = {"admin"};
        String[] predefinedEmails = {"admin@example.com"};
        String[] predefinedPasswords = {"admin123"};

        // Verificar si los datos coinciden con algún usuario predefinido
        for (int i = 0; i < predefinedUsernames.length; i++) {
            if (username.equals(predefinedUsernames[i]) && email.equals(predefinedEmails[i]) && password.equals(predefinedPasswords[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Método llamado cuando se actualiza la base de datos, no necesitamos implementarlo por ahora
    }
}
