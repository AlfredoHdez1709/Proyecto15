package com.javaya.proyecto015;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class lista extends Activity {
	TextView et1;
	String cadenaObtenida;
	@Override
public void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.activity_main_activity_l);
	 et1 = (EditText)findViewById(R.id.textView3);
	 
	 Bundle recogerDatos = getIntent().getExtras();
	 cadenaObtenida = recogerDatos.getString("resultado");
	 
	 et1.setText(cadenaObtenida);
	}
}
