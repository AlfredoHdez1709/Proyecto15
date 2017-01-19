package com.javaya.proyecto015;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends Activity {
	private EditText et1, et2, et3, et4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		et3 = (EditText) findViewById(R.id.editText3);
		et4 = (EditText) findViewById(R.id.editText4);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void alta(View v) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
				"administracion", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String dni = et1.getText().toString();
		String nombre = et2.getText().toString();
		String colegio = et3.getText().toString();
		String nromesa = et4.getText().toString();
		ContentValues registro = new ContentValues();
		registro.put("dni", dni);
		registro.put("nombre", nombre);
		registro.put("colegio", colegio);
		registro.put("nromesa", nromesa);
		bd.insert("votantes", null, registro);
		bd.close();
		et1.setText("");
		et2.setText("");
		et3.setText("");
		et4.setText("");
		Toast.makeText(this, "Se cargaron los datos de la materia",
				Toast.LENGTH_SHORT).show();
	}

	public void consulta(View v) {
		Intent i = new Intent(this, MainActivityA.class);
        i.putExtra("direccion", et1.getText().toString());
        startActivity(i);
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
				"administracion", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String dni = et1.getText().toString();
		Cursor fila = bd.rawQuery(
				"select nombre,colegio,nromesa  from votantes where dni=" + dni
						+ "", null);
		
		if (fila.moveToFirst()) {
			et2.setText(fila.getString(0));
			et3.setText(fila.getString(1));
			et4.setText(fila.getString(2));
		} else
			Toast.makeText(this, "No existe una materia con esa clave de grupo",
					Toast.LENGTH_SHORT).show();
		bd.close();

	}

	public void baja(View v) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
				"administracion", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String dni = et1.getText().toString();
		int cant = bd.delete("votantes", "dni=" + dni + "", null);
		bd.close();
		et1.setText("");
		et2.setText("");
		et3.setText("");
		et4.setText("");
		if (cant == 1)
			Toast.makeText(this, "Se cargaron los datos de la materia",
					Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, "No existe una materia con esa clave de grupo",
					Toast.LENGTH_SHORT).show();
	}

	public void modificacion(View v) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
				"administracion", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String dni = et1.getText().toString();
		String nombre = et2.getText().toString();
		String colegio = et3.getText().toString();
		String nromesa = et4.getText().toString();
		ContentValues registro = new ContentValues();
		registro.put("nombre", nombre);
		registro.put("colegio", colegio);
		registro.put("nromesa", nromesa);
		int cant = bd.update("votantes", registro, "dni=" + dni, null);
		bd.close();
		if (cant == 1)
			Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
					.show();
		else
			Toast.makeText(this, "No existe una materia con esa clave de grupo",
					Toast.LENGTH_SHORT).show();
	}

}
