package com.matasalius.dynamicarray;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    DynamicArray laptops = new DynamicArray();

    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create();

        list = (ListView) findViewById(R.id.list);
        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

        list.setAdapter(adapter);

        Button bCreate = findViewById(R.id.bCreate);
        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.clear();
                for (int i=0; i<laptops.size(); i++)
                    arrayList.add(laptops.get(i).toString());
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();

            }
        });

        Button bSize = findViewById(R.id.button2);
        bSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tx = findViewById(R.id.textView);

                tx.setText("Size: " + laptops.size());
            }
        });

        Button bSize2 = findViewById(R.id.button3);
        bSize2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tx = findViewById(R.id.textView);

                tx.setText("Real size: " + laptops.realSize());
            }
        });

        Button bClear = findViewById(R.id.button5);
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                laptops.clearAll();

                Toast.makeText(MainActivity.this, "Masyvas tuscias", Toast.LENGTH_SHORT).show();
            }
        });

        Button bAdd = findViewById(R.id.button4);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] makes = {"Lenovo","HP","Asus","Dell","Acer"};
                Random rnd = new Random();
                int makeIndex = rnd.nextInt(makes.length);
                laptops.add(new Laptop(makes[makeIndex],
                        rnd.nextInt(2_6),
                        14,
                        rnd.nextInt(250),
                        500 + rnd.nextInt(500) + rnd.nextDouble()));

                Toast.makeText(MainActivity.this, laptops.get(laptops.size()-1).toString(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    void create() {
        Laptop l1 = new Laptop("Lenovo", 2, 14, 250, 500);
        Laptop l2 = new Laptop("Asus", 4, 14, 500, 800);
        Laptop l3 = new Laptop("Acer", 2, 12, 250, 600);
        Laptop l4 = new Laptop("Dell", 8, 14, 500, 900);

        laptops.add(l1);
        laptops.add(l2);
        laptops.add(l3);
        laptops.add(l4);
    }
}
