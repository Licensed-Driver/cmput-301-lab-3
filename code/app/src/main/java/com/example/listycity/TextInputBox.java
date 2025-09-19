package com.example.listycity;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.util.LayoutDirection;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TextInputBox {
    Dialog dialogBox;
    BiConsumer<String, String> inputCallback;

    public TextInputBox(String title, String topHint, String bottomHint, Context context, BiConsumer<String, String> onInput) {
        inputCallback = onInput;

        // Create new layout view for input
        LinearLayout inputLayout = new LinearLayout(context);
        // Create input view
        TextInputEditText topInput = new TextInputEditText(context);
        TextInputEditText bottomInput = new TextInputEditText(context);

        // Specify which type will be input and set the input view as a child of the layout
        topInput.setHint(topHint);
        topInput.setInputType(InputType.TYPE_CLASS_TEXT);
        inputLayout.addView(topInput);

        bottomInput.setHint(bottomHint);
        bottomInput.setInputType(InputType.TYPE_CLASS_TEXT);
        inputLayout.addView(bottomInput);

        inputLayout.setOrientation(LinearLayout.VERTICAL);

        // Create a dialogue alert (pop-up)
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                // Set the input layout to be a child of this pop-up, so it is within the pop-up and shows when the alert comes
                .setView(inputLayout)
                // Make a button that adds the city in the dialogue box once pressed
                .setPositiveButton("Ok", (dialog, which) -> {
                    String cityText = String.valueOf(topInput.getText()).trim();
                    String provinceText = String.valueOf(bottomInput.getText()).trim();
                    inputCallback.accept(cityText, provinceText);
                    topInput.setText("");
                    bottomInput.setText("");
                })
                // Add a button that just closes the box with no action
                .setNegativeButton("Cancel", null);

        dialogBox = builder.create();

        // Make sure that the input is already focused and keyboard pops up right away
        dialogBox.setOnShowListener(d -> {
            topInput.requestFocus();
            dialogBox.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        });
    }

    public void showInputBox() {
        dialogBox.show();
    }

    public void changeCallback(BiConsumer<String, String> newCallback) {
        inputCallback = newCallback;
    }
}
