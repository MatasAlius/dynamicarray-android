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

        // Show data in array
        Button bCreate = findViewById(R.id.bCreate);
        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.clear();
                for (int i=0; i<laptops.size(); i++)
                    arrayList.add(laptops.get(i).toString());
                adapter.notifyDataSetChanged();

            }
        });

        // Show array size
        Button bSize = findViewById(R.id.button2);
        bSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tx = findViewById(R.id.textView);

                tx.setText("Size: " + laptops.size());
            }
        });

        // Show real array size
        Button bSize2 = findViewById(R.id.button3);
        bSize2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tx = findViewById(R.id.textView2);

                tx.setText("Real size: " + laptops.realSize());
            }
        });

        // Clear array
        Button bClear = findViewById(R.id.button5);
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                laptops.clearAll();
                Toast.makeText(MainActivity.this, "Array is empty", Toast.LENGTH_SHORT).show();
            }
        });

        // Removes first element
        Button bRemove = findViewById(R.id.button6);
        bRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                laptops.removeAt(0);
                Toast.makeText(MainActivity.this, "First element removed", Toast.LENGTH_SHORT).show();
            }
        });

        // Add new elements in array
        Button bAdd = findViewById(R.id.button4);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] makes = {"Lenovo","HP","Asus","Dell","Acer"};
                int[] screen = {10,11,12,14,15,16};
                int[] drive = {120,250,500,1000};
                Random rnd = new Random();
                int makeIndex = rnd.nextInt(makes.length);
                int screenIndex = rnd.nextInt(screen.length);
                int driveIndex = rnd.nextInt(drive.length);
                laptops.add(new Laptop(makes[makeIndex],
                        rnd.nextInt(4),
                        screen[screenIndex],
                        drive[driveIndex],
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
