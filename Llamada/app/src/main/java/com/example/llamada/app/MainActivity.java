package com.example.llamada.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    EditText editTelefono;
    private Button botonLlamar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTelefono = (EditText) findViewById(R.id.txtTelefono);
        botonLlamar = (Button) findViewById(R.id.btLlamar);

        botonLlamar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //Mostrar un mensaje de confirmación antes de realizar la llamada
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage("¿Desea realizar la llamada al contacto?");
                alertDialog.setTitle("Llamar a contacto...");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        try
                        {
                            EditText num=(EditText)findViewById(R.id.txtTelefono);
                            String number = "tel:" + num.getText().toString().trim();
                            Toast.makeText(getApplicationContext(),
                                    "Llamando al " + num.getText().toString().trim(), Toast.LENGTH_LONG).show();
                            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                            startActivity(callIntent);
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),
                                    "No se ha podido realizar la llamada", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(getApplicationContext(),
                                "Llamada cancelada", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.show();
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
