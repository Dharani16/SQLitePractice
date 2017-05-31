package com.dharani.sqlitepractice;

import android.database.Cursor;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextInputLayout tvName,tvSurName, tvMarks,tvId;
    EditText etName,etSurName,etMarks,etID;
    Button btInsert,btRetrieve,btUpdate,btDelete;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        tvName = (TextInputLayout)findViewById(R.id.textInputLayout4);
        tvSurName = (TextInputLayout)findViewById(R.id.textInputLayout5);
        tvMarks = (TextInputLayout)findViewById(R.id.textInputLayout6);
        tvId = (TextInputLayout)findViewById(R.id.textInputLayout7);

        etName = (EditText)findViewById(R.id.etName);
        etSurName = (EditText)findViewById(R.id.etSurname);
        etMarks = (EditText)findViewById(R.id.etMarks);
        etID = (EditText)findViewById(R.id.etID);

        btInsert = (Button)findViewById(R.id.button6);
        btRetrieve = (Button)findViewById(R.id.button7);
        btUpdate = (Button)findViewById(R.id.button8);
        btDelete = (Button)findViewById(R.id.button9);

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(etName.getText().toString(),etSurName.getText().toString(),etMarks.getText().toString());
                Log.e("Testing ","Values are "+isInserted);
                if (isInserted == true){
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this, "Not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this, "Nothing found", Toast.LENGTH_SHORT).show();
                }
                while (res.moveToNext()){
                    Toast.makeText(MainActivity.this, "ID :"+res.getString(0)+"\n"+"Name : "+res.getString(1)+"\n"+"Surname :"+res.getString(2)+"\n"+"Marks : "+res.getString(3), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = myDb.deleteData(etID.getText().toString());
                if (deleteRows > 0){
                    Toast.makeText(MainActivity.this, "Row deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String surName = etSurName.getText().toString();
                String mar = etMarks.getText().toString();
                String id = etID.getText().toString();

                boolean isUpdate = myDb.updateData(id,name,surName,mar);
               // boolean isUpdate = myDb.updateData(etName.getText().toString(),etSurName.getText().toString(),etMarks.getText().toString(),etID.getText().toString());
                if (isUpdate == true){
                    Toast.makeText(MainActivity.this, "Updated row", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
