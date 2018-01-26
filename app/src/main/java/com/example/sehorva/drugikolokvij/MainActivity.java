package com.example.sehorva.drugikolokvij;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = new DBAdapter(this);

        //---donji kod je zakomentiran jer su oni vec dodani u bazu---
        //---dodavanje zaposlenika---
        /*db.open();
        long id = db.insertIntoZaposlenik("Mirko", "mirko34@gmail.com", 2500);
        id = db.insertIntoZaposlenik("Janko", "janac@hotmail.com", 3560);
        id = db.insertIntoZaposlenik("Ivana", "ivana.maric@gmail.com", 5000);
        id = db.insertIntoZaposlenik("Branko", "brahg@hotmail.com", 2800);
        id = db.insertIntoZaposlenik("Hrvoje", "hrvojko123@gmail.com", 4200);
        db.close();

        //---dodavanje radnih mjesta---
        db.open();
        long id_mjesta = db.insertIntoRadnoMjesto(1, 2800);
        id_mjesta = db.insertIntoRadnoMjesto(5, 6000);
        id_mjesta = db.insertIntoRadnoMjesto(3, 4600);
        id_mjesta = db.insertIntoRadnoMjesto(4, 9000);
        id_mjesta = db.insertIntoRadnoMjesto(2, 3400);
        db.close();*/

        int najvecaPlaca = 0;
        String najplaceniji =  "";

        //--dohvati sve zaposlenike---
        db.open();
        Cursor c = db.getSviZaposlenici();
        if (c.moveToFirst())
        {
            do {
                DisplayZaposlenika(c);
                if(c.getInt(3)>najvecaPlaca)
                {
                    najvecaPlaca = c.getInt(3);
                    najplaceniji = c.getString(1);
                }
            } while (c.moveToNext());
        }
        db.close();

        EditText myText = (EditText) this.findViewById(R.id.najplaceniji);
        myText.setText( "Najvisu placu ima: " + najplaceniji );

        //--dohvati sva radna mjesta---
        db.open();
        Cursor c2 = db.getSvaRadnaMjesta();
        if (c2.moveToFirst())
        {
            do {
                DisplayRadnogMjesta(c2);
            } while (c2.moveToNext());
        }
        db.close();



        //---dohvati zaposlenika---
        /*db.open();
        Cursor cu = db.getZaposlenik(2);
        if (cu.moveToFirst())
            DisplayZaposlenika(cu);
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
        db.close();

        //---dohvati radno mjesto---
        db.open();
        Cursor cu2 = db.getRadnoMjesto(2);
        if (cu2.moveToFirst())
            DisplayRadnogMjesta(cu2);
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
        db.close();*/



        //---update zaposlenika---
        /*db.open();
        if (db.updateZaposlenik(1, "Wei-Meng Lee", "weimenglee@gmail.com", 2000))
            Toast.makeText(this, "Update successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();
        db.close();

        //---update radnog mjesta---
        db.open();
        if (db.updateRadnoMjesto(2, 3, 5000))
            Toast.makeText(this, "Update successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();
        db.close();*/

        /*

        //---obrisi radno mjesto---
        db.open();
        if (db.deleteRadnoMjesto(1))
            Toast.makeText(this, "Delete successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
        db.close();*/
    }

    private void DisplayZaposlenika(Cursor c) {
        /*Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Ime" + c.getString(1) + "\n" +
                        "Mail:  " + c.getString(2) + "\n" +
                        "Placa: " + c.getString(3),
                Toast.LENGTH_LONG).show();*/
        TextView textView = (TextView) findViewById(R.id.ispisBaze1);
        textView.append("\n" + "id: " + c.getString(0) + "\t" +
                "Ime" + c.getString(1) + "\t" +
                "Mail:  " + c.getString(2) + "\t" +
                "Placa: " + c.getString(3));
    }

    private void DisplayRadnogMjesta(Cursor c) {
        /*Toast.makeText(this,
                "id_mjesta: " + c.getString(0) + "\n" +
                        "Nivo_odgovornosti: " + c.getString(1) + "\n" +
                        "Minimalna_placa:  " + c.getString(2),
                Toast.LENGTH_LONG).show();*/
        TextView textView = (TextView) findViewById(R.id.ispisBaze2);
        textView.append("\n" + "id_mjesta: " + c.getString(0) + "\t" +
                "Nivo_odgovornosti: " + c.getString(1) + "\t" +
                "Minimalna_placa:  " + c.getString(2));
    }

    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }

    public void brisanjeZaposlenika()
    {
        /*EditText editText = (EditText) findViewById(R.id.editBaza);
        int value = Integer.parseInt(editText.getText().toString());


        //---obrisi zaposlenika---
        db.open();
        if (db.deleteZaposlenik(value))
            Toast.makeText(this, "Delete successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
        db.close();*/

    }

    public void brisanjeRadnogMjesta()
    {
        /*EditText editText = (EditText) findViewById(R.id.editBaza);
        int value = Integer.parseInt(editText.getText().toString());

        //---obrisi radno mjesto---
        db.open();
        if (db.deleteRadnoMjesto(value))
            Toast.makeText(this, "Delete successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
        db.close();*/
    }
}
