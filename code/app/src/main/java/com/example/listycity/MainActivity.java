package com.example.listycity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private ListView cityList;
    private AccordionListAdapter cityAdapter;
    private ArrayList<ListItem> dataList;
    private MaterialButton addButton;
    TextInputBox inputBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListItem[] cities = {new ListItem("Calgary", "AB"), new ListItem("Vancouver", "BC"), new ListItem("Edmonton", "AB"), new ListItem("Moscow", "RU"), new ListItem("Perth", "AU"), new ListItem("London", "UK"), new ListItem("Osaka", "JP"), new ListItem("Guangzhou", "CH")};

        dataList = new ArrayList<ListItem>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new AccordionListAdapter(this, R.layout.content, dataList);
        cityList = findViewById(R.id.city_list);
        cityList.setAdapter(cityAdapter);

        addButton = findViewById(R.id.add_button);

        cityList.setOnItemClickListener(
            (AdapterView<?> parent, View view, int position, long id) -> {
                cityAdapter.toggle(position);
            });

        addButton.setOnClickListener(v -> {

            if(inputBox == null) {
                // Get the context of the view
                Context context = v.getContext();

                inputBox = new TextInputBox("Add City", "Enter New City", "Enter New Province Name", context, (cityText, provinceText) -> {
                    if (!cityText.isEmpty() || !provinceText.isEmpty()) {
                        dataList.add(new ListItem(cityText, provinceText));
                        cityAdapter.notifyDataSetChanged();
                    }
                });

                inputBox.showInputBox();
            } else {
                inputBox.showInputBox();
            }

        });

    }
}