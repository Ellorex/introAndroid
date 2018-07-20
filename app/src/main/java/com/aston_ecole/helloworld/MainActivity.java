package com.aston_ecole.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "main";
    private EditText editTextFar;
    private EditText editTextCel;
    private Button buttonSave;
    private ListView listViewTemperature;
    private List<String> stringList;
    private ArrayAdapter<String> adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.burger, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemDeleteAll:
                editTextFar.getText().clear();
                editTextCel.getText().clear();
                stringList.clear();
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        stringList = new ArrayList<>();
        editTextFar = findViewById(R.id.editTextFar);
        editTextCel = findViewById(R.id.editTextCel);
        buttonSave = findViewById(R.id.buttonSave);
        listViewTemperature = findViewById(R.id.listViewTemperature);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);
        listViewTemperature.setAdapter(adapter);

        editTextFar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String valeur = editable.toString();
                if(editTextFar.hasFocus()) {
                    if (valeur.length() > 0 && isNumeric(valeur)) {
                        editTextCel.setText(TemperatureConverter.celFromFar(Double.valueOf(valeur)));
                    } else {
                        editTextCel.setText("");
                    }
                }

            }
        });
        editTextCel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String valeur = editable.toString();
                if(editTextCel.hasFocus()) {
                    if(valeur.length() > 0 && isNumeric(valeur)) {
                        editTextFar.setText(TemperatureConverter.farFromCel(Double.valueOf(valeur)));
                    } else {
                        editTextFar.setText("");
                    }
                }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: sur le bouton");
                stringList.add(editTextCel.getText().toString()+
                "°C = " +editTextFar.getText().toString()+"°F");
                adapter.notifyDataSetChanged();
            }
        });
        listViewTemperature.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                stringList.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Welcome");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: Welcome");
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
