package com.matasalius.dynamicarray;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

        list = findViewById(R.id.list);
        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

        list.setAdapter(adapter);

        Show();

        // Show data in array
        Button bCreate = findViewById(R.id.bLoad);
        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Laptop l1 = new Laptop("Lenovo", 2, 14, 250, 500);
                Laptop l2 = new Laptop("Asus", 4, 14, 500, 800);
                Laptop l3 = new Laptop("Acer", 2, 12, 250, 600);
                Laptop l4 = new Laptop("Dell", 8, 14, 500, 900);
                laptops.add(l1);
                laptops.add(l2);
                laptops.add(l3);
                laptops.add(l4);

                Show();
            }
        });

        // Clear array
        Button bClear = findViewById(R.id.bClear);
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                laptops.clearAll();
                Toast.makeText(MainActivity.this, "Array is empty", Toast.LENGTH_SHORT).show();
                Show();
            }
        });

        // Removes first element
        Button bRemove = findViewById(R.id.bRem2);
        bRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (laptops.size() != 0) {
                    laptops.removeAt(0);
                    Toast.makeText(MainActivity.this, "First element removed", Toast.LENGTH_SHORT).show();
                    Show();
                }
            }
        });

        // Add new random elements in array
        Button bAdd = findViewById(R.id.bAdd);
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
                Show();
            }

        });

        // Add new element in array
        Button bAdd2 = findViewById(R.id.bAdd2);
        bAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                Context context = alert.getContext();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText edittext = new EditText(context);
                edittext.setHint("Brand");
                layout.addView(edittext);
                final EditText edittext2 = new EditText(context);
                edittext2.setRawInputType(2);
                edittext2.setHint("Core number");
                layout.addView(edittext2);
                final EditText edittext3 = new EditText(context);
                edittext3.setRawInputType(2);
                edittext3.setHint("Screen size");
                layout.addView(edittext3);
                final EditText edittext4 = new EditText(context);
                edittext4.setRawInputType(2);
                edittext4.setHint("Drive size");
                layout.addView(edittext4);
                final EditText edittext5 = new EditText(context);
                edittext5.setRawInputType(2);
                edittext5.setHint("Price");
                layout.addView(edittext5);

                alert.setMessage("");
                alert.setTitle("Add new value");

                alert.setView(layout);

                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String text = edittext.getText().toString();
                        String text2 = edittext2.getText().toString();
                        String text3 = edittext3.getText().toString();
                        String text4 = edittext4.getText().toString();
                        String text5 = edittext5.getText().toString();

                        if (!text.isEmpty() && !text2.isEmpty() && !text3.isEmpty() && !text4.isEmpty() && !text5.isEmpty()) {
                            laptops.add(new Laptop(text, Integer.parseInt(text2), Double.parseDouble(text3), Integer.parseInt(text4), Double.parseDouble(text5)));
                            Toast.makeText(getApplicationContext(), "Value added", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Value not added", Toast.LENGTH_SHORT).show();
                        Show();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();
            }
        });

        // Removes i element
        Button bRemove2 = findViewById(R.id.bRem);
        bRemove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                Context context = alert.getContext();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText edittext = new EditText(context);
                edittext.setHint("Index");
                edittext.setRawInputType(2);
                layout.addView(edittext);

                alert.setMessage("Array indexing start with 0");
                alert.setTitle("Remove index value");

                alert.setView(layout);

                alert.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String text = edittext.getText().toString();

                        if (laptops.size() != 0 && laptops.size()-1 >= Integer.parseInt(text)) {
                            laptops.removeAt(Integer.parseInt(text));
                            Toast.makeText(MainActivity.this, Integer.parseInt(text)+ " element removed", Toast.LENGTH_SHORT).show();
                            Show();
                        }
                        else
                            Toast.makeText(MainActivity.this, " Bad index", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();
            }
        });

        // Get i element
        Button bGet = findViewById(R.id.bGet);
        bGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                Context context = alert.getContext();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText edittext = new EditText(context);
                edittext.setHint("Index");
                edittext.setRawInputType(2);
                layout.addView(edittext);

                alert.setMessage("Array indexing start with 0");
                alert.setTitle("Get index value");

                alert.setView(layout);

                alert.setPositiveButton("Get", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String text = edittext.getText().toString();
                        if (laptops.size() != 0 && laptops.size()-1 >= Integer.parseInt(text)) {
                            String string = laptops.get(Integer.parseInt(text)).toString();
                            Toast.makeText(MainActivity.this, string, Toast.LENGTH_LONG).show();
                            Show();
                        }
                        else
                            Toast.makeText(MainActivity.this, " Bad index", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();
            }
        });

        // Set element in index position of array
        Button bSet = findViewById(R.id.bSet);
        bSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                Context context = alert.getContext();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText edittext0 = new EditText(context);
                edittext0.setHint("Index");
                edittext0.setRawInputType(2);
                layout.addView(edittext0);
                final EditText edittext = new EditText(context);
                edittext.setHint("Brand");
                layout.addView(edittext);
                final EditText edittext2 = new EditText(context);
                edittext2.setRawInputType(2);
                edittext2.setHint("Core number");
                layout.addView(edittext2);
                final EditText edittext3 = new EditText(context);
                edittext3.setRawInputType(2);
                edittext3.setHint("Screen size");
                layout.addView(edittext3);
                final EditText edittext4 = new EditText(context);
                edittext4.setRawInputType(2);
                edittext4.setHint("Drive size");
                layout.addView(edittext4);
                final EditText edittext5 = new EditText(context);
                edittext5.setRawInputType(2);
                edittext5.setHint("Price");
                layout.addView(edittext5);

                alert.setMessage("Array indexing start with 0");
                alert.setTitle("Set value in i position");

                alert.setView(layout);

                alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String text0 = edittext0.getText().toString();
                        String text = edittext.getText().toString();
                        String text2 = edittext2.getText().toString();
                        String text3 = edittext3.getText().toString();
                        String text4 = edittext4.getText().toString();
                        String text5 = edittext5.getText().toString();

                        if (!text.isEmpty() && !text2.isEmpty() && !text3.isEmpty() && !text4.isEmpty() && !text5.isEmpty()) {
                            if (laptops.size() != 0 && laptops.size()-1 >= Integer.parseInt(text0)) {
                                laptops.set(Integer.parseInt(text0), new Laptop(text, Integer.parseInt(text2), Double.parseDouble(text3), Integer.parseInt(text4), Double.parseDouble(text5)));
                                Toast.makeText(getApplicationContext(), "Value added in " +  Integer.parseInt(text0), Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(getApplicationContext(), "Value not added", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Value not added", Toast.LENGTH_SHORT).show();
                        Show();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();
            }
        });
    }

    public void Web(View v) {
        Uri uri = Uri.parse("https://github.com/matasalius");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    void Show() {
        arrayList.clear();
        for (int i=0; i<laptops.size(); i++)
            arrayList.add(laptops.get(i).toString());
        adapter.notifyDataSetChanged();

        Size();
    }
    void Size() {
        TextView tx = findViewById(R.id.textView);
        tx.setText("Size: " + laptops.size());
        TextView tx2 = findViewById(R.id.textView2);
        tx2.setText("Real size: " + laptops.realSize());
    }
}
