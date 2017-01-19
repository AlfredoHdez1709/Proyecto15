package com.javaya.proyecto015;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelperA extends SQLiteOpenHelper{
	public AdminSQLiteOpenHelperA(Context context, String nombre, CursorFactory factory, int version) {
		super(context, nombre, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table alumnos(dni integer primary key, nombre text, colegio text, nromesa integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
		db.execSQL("drop table if exists alumnos");
		db.execSQL("create table alumnos(dni integer primary key, nombre text, colegio text, nromesa integer)");		
	}	

}
