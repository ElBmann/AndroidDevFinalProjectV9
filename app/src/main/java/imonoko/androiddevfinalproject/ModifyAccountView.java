package imonoko.androiddevfinalproject;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Erventz on 12/4/2017.
 */

public class ModifyAccountView extends LinearLayout {
    // TextViews to serve as labels

    // Text Views
    // Prompt for new info
    private TextView newUserNamePrompt;
    private TextView newEmailPrompt;
    private TextView newPasswordPrompt;

    // Label for old info
    private TextView oldUserNameLabel;
    private TextView oldEmailLabel;
    private TextView oldPasswordLabel;
    private TextView idLabel;

    // Old Info
    private TextView oldUserNameField;
    private TextView oldEmailField;
    private TextView idField;

    // EditTexts to collect the Info
    private EditText oldPasswordEntry;
    private EditText newUserNameEntry;
    private EditText newEmailEntry;
    private EditText newPasswordEntry;

    // Button to add account to database
    private Button modifyAccount;

    // TableLayout and TableRows to format the create account form
    private TableLayout table;

    private TableRow userNameRow;
    private TableRow emailRow;
    private TableRow passwordRow;
    private TableRow idRow;

    private Button Back;

    public ModifyAccountView(Context context, OnClickListener listener, OnClickListener back) {
        super(context);
        this.setOrientation(LinearLayout.VERTICAL);
        this.setBackgroundColor(Color.BLACK);

        // TableLayout Layout parameters
        LayoutParams tableParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        tableParams.weight = 1.0f;

        // TableRow Layout parameters
        TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tableRowParams.setMargins(0, 40, 0, 10);

        //EditText and TextView Layout parameters
        TableRow.LayoutParams editParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        editParams.weight = 0.20f;
        editParams.setMargins(5, 0, 0, 0);

        TableRow.LayoutParams textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT);
        textParams.weight = 0.40f;
        textParams.setMargins(0, 0, 5, 0);

        // Button Layout parameters
        LayoutParams buttonParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        buttonParams.weight = 0.25f;
        buttonParams.setMargins(140, 0, 140, 75);

        // add TableLayout to LinearLayout
        table = new TableLayout(context);
        table.setLayoutParams(tableParams);
        addView(table);

        // add TableRow
        idRow = new TableRow(context);
        idRow.setLayoutParams(tableRowParams);
        table.addView(idRow);

        // TextView for username
        idLabel = new TextView(context);
        idLabel.setText("ID: ");
        idLabel.setTextSize(16);
        idLabel.setTextColor(Color.RED);
        idLabel.setLayoutParams(textParams);
        idRow.addView(idLabel);

        // TextView for current username
        idField = new TextView(context);
        idField.setMaxLines(1);
        LoginActivity la = new LoginActivity();
        idField.setText("");
        idField.setGravity(Gravity.CENTER);
        idField.setHintTextColor(Color.parseColor("#444444")); // dark gray
        idField.setTextColor(Color.RED);
        idField.setLayoutParams(editParams);
        idRow.addView(idField);


        userNameRow = new TableRow(context);
        userNameRow.setLayoutParams(tableRowParams);
        table.addView(userNameRow);

        // TextView for username
        oldUserNameLabel = new TextView(context);
        oldUserNameLabel.setText("Current Username: ");
        oldUserNameLabel.setTextSize(16);
        oldUserNameLabel.setTextColor(Color.RED);
        oldUserNameLabel.setLayoutParams(textParams);
        userNameRow.addView(oldUserNameLabel);

        // TextView for current username
        oldUserNameField = new TextView(context);
        oldUserNameField.setMaxLines(1);
        oldUserNameField.setText("");
        oldUserNameField.setGravity(Gravity.CENTER);
        oldUserNameField.setHintTextColor(Color.parseColor("#5b5b5b")); // dark gray
        oldUserNameField.setTextColor(Color.RED);
        oldUserNameField.setLayoutParams(editParams);
        userNameRow.addView(oldUserNameField);


        emailRow = new TableRow(context);
        emailRow.setLayoutParams(tableRowParams);
        table.addView(emailRow);

        // TextView for email
        oldEmailLabel = new TextView(context);
        oldEmailLabel.setText("Current Email Address: ");
        oldEmailLabel.setTextSize(16);
        oldEmailLabel.setTextColor(Color.RED);
        oldEmailLabel.setLayoutParams(textParams);
        emailRow.addView(oldEmailLabel);


        // TextView for old email
        oldEmailField = new TextView(context);
        oldEmailField.setMaxLines(1);
        oldEmailField.setText("");
        oldEmailField.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS); // email input
        oldEmailField.setGravity(Gravity.CENTER);
        oldEmailField.setHintTextColor(Color.parseColor("#5b5b5b"));
        oldEmailField.setTextColor(Color.RED);
        oldEmailField.setLayoutParams(editParams);
        emailRow.addView(oldEmailField);


        passwordRow = new TableRow(context);
        passwordRow.setLayoutParams(tableRowParams);
        table.addView(passwordRow);

        // TextView for password
        oldPasswordLabel = new TextView(context);
        oldPasswordLabel.setText("Current Password: ");
        oldPasswordLabel.setTextSize(16);
        oldPasswordLabel.setBackgroundColor(Color.parseColor("#1e1e1e"));
        oldPasswordLabel.setTextColor(Color.GREEN);
        oldPasswordLabel.setLayoutParams(textParams);
        passwordRow.addView(oldPasswordLabel);

        // EditText for current password
        oldPasswordEntry = new EditText(context);
        oldPasswordEntry.setMaxLines(1);
        oldPasswordEntry.setText("");
        oldPasswordEntry.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD); // password input
        oldPasswordEntry.setHint("enter your current password");
        oldPasswordEntry.setGravity(Gravity.CENTER);
        oldPasswordEntry.setBackgroundColor(Color.parseColor("#1e1e1e"));
        oldPasswordEntry.setHintTextColor(Color.parseColor("#5b5b5b"));
        oldPasswordEntry.setTextColor(Color.GREEN);
        oldPasswordEntry.setLayoutParams(editParams);
        passwordRow.addView(oldPasswordEntry);

        userNameRow = new TableRow(context);
        userNameRow.setLayoutParams(tableRowParams);
        table.addView( userNameRow );

        // TextView for username
        newUserNamePrompt = new TextView(context);
        newUserNamePrompt.setText("New Username: ");
        newUserNamePrompt.setTextSize(16);
        newUserNamePrompt.setBackgroundColor(Color.parseColor("#1e1e1e"));
        newUserNamePrompt.setTextColor(Color.GREEN);
        newUserNamePrompt.setLayoutParams(textParams);
        userNameRow.addView( newUserNamePrompt );

        // editText for username
        newUserNameEntry = new EditText(context);
        newUserNameEntry.setMaxLines(1);
        newUserNameEntry.setText("");
        newUserNameEntry.setHint("enter your new username");
        newUserNameEntry.setGravity(Gravity.CENTER);
        newUserNameEntry.setBackgroundColor(Color.parseColor("#1e1e1e"));// light gray
        newUserNameEntry.setHintTextColor(Color.parseColor("#5b5b5b")); // dark gray
        newUserNameEntry.setTextColor(Color.GREEN);
        newUserNameEntry.setLayoutParams(editParams);
        userNameRow.addView( newUserNameEntry );


        emailRow = new TableRow(context);
        emailRow.setLayoutParams(tableRowParams);
        table.addView( emailRow );

        // TextView for newEmail
        newEmailPrompt = new TextView(context);
        newEmailPrompt.setText("New Email Address: ");
        newEmailPrompt.setTextSize(16);
        newEmailPrompt.setBackgroundColor(Color.parseColor("#1e1e1e"));
        newEmailPrompt.setTextColor(Color.GREEN);
        newEmailPrompt.setLayoutParams(textParams);
        emailRow.addView( newEmailPrompt );

        newEmailEntry = new EditText(context);
        newEmailEntry.setMaxLines(1);
        newEmailEntry.setText("");
        newEmailEntry.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS); // newEmail input
        newEmailEntry.setHint("enter your new email address");
        newEmailEntry.setGravity(Gravity.CENTER);
        newEmailEntry.setBackgroundColor(Color.parseColor("#1e1e1e"));
        newEmailEntry.setHintTextColor(Color.parseColor("#5b5b5b"));
        newEmailEntry.setTextColor(Color.GREEN);
        newEmailEntry.setLayoutParams(editParams);
        emailRow.addView( newEmailEntry );


        passwordRow = new TableRow(context);
        passwordRow.setLayoutParams(tableRowParams);
        table.addView( passwordRow );

        // TextView for newPassword
        newPasswordPrompt = new TextView(context);
        newPasswordPrompt.setText("New Password: ");
        newPasswordPrompt.setTextSize(16);
        newPasswordPrompt.setBackgroundColor(Color.parseColor("#1e1e1e"));
        newPasswordPrompt.setTextColor(Color.GREEN);
        newPasswordPrompt.setLayoutParams(textParams);
        passwordRow.addView( newPasswordPrompt );

        newPasswordEntry = new EditText(context);
        newPasswordEntry.setMaxLines(1);
        newPasswordEntry.setText("");
        newPasswordEntry.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD); // newPassword input
        newPasswordEntry.setHint("enter your new password");
        newPasswordEntry.setGravity(Gravity.CENTER);
        newPasswordEntry.setBackgroundColor(Color.parseColor("#1e1e1e"));
        newPasswordEntry.setHintTextColor(Color.parseColor("#5b5b5b"));
        newPasswordEntry.setTextColor(Color.GREEN);
        newPasswordEntry.setLayoutParams(editParams);
        passwordRow.addView( newPasswordEntry );

        modifyAccount = new Button(context);
        modifyAccount.setText("Update Account");
        modifyAccount.setGravity(Gravity.CENTER);
        modifyAccount.setTextColor(Color.parseColor("#5b5b5b"));
        modifyAccount.setOnClickListener(listener);
        modifyAccount.setEnabled(true);
        modifyAccount.setMaxLines(1);
        modifyAccount.setLayoutParams(buttonParams);
        addView(modifyAccount);

        //back button
        Back = new Button(context);
        Back.setText("BACK");
        Back.setGravity(Gravity.CENTER);
        Back.setTextColor(Color.parseColor("#5b5b5b"));
        Back.setOnClickListener(back);
        Back.setEnabled(true);
        Back.setMaxLines(1);
        Back.setLayoutParams(buttonParams);
        addView(Back);
    }

    public EditText getOldPW() {
        return oldPasswordEntry;
    }

    public EditText getName() {
        return newUserNameEntry;
    }

    public EditText getMail() {
        return newEmailEntry;
    }

    public EditText getPW() {
        return newPasswordEntry;
    }

    public void setTextViewID(String id)
    {
        idField.setText(id);
    }
    public void setTextViewName(String name)
    {
        oldUserNameField.setText(name);
    }
    public void setTextViewEmail(String email)
    {
        oldEmailField.setText(email);
    }
}