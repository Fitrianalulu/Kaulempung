package com.example.kaulempung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.widget.DatePicker;
import android.app.DatePickerDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextReTypePassword;
    private Button mButtonRegister;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResult;
    private Button btDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextEmail = findViewById(R.id.field_email);
        mEditTextPassword = findViewById(R.id.field_password);
        mEditTextReTypePassword = findViewById(R.id.field_retypepassword);
        mButtonRegister = findViewById(R.id.tombol_daftar);

        mButtonRegister.setOnClickListener(this);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tvDateResult = findViewById(R.id.hasil_ttl);
        btDatePicker = findViewById(R.id.pilih_tanggal);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tombol_daftar) {
            register();

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }


    /**
     * method ini dipanggil saat kita menekan button register
     */
    private void register() {
        String email = mEditTextEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();
        String retypePassword = mEditTextReTypePassword.getText().toString();

        if (!isValidateEmail(email)) {
            Toast.makeText(this, "Email kosong atau salah", Toast.LENGTH_LONG).show();
        } else if (!isEmptyField(password)) {
            Toast.makeText(this, "Password harus diisi", Toast.LENGTH_LONG).show();
        } else if (!isEmptyField(retypePassword)) {
            Toast.makeText(this, "Retype password harus diisi", Toast.LENGTH_LONG).show();
        } else if (!isMatch(password, retypePassword)) {
            Toast.makeText(this, "Password tidak cocok", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Register Berhasil", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * @param email Method dibawah ini untuk validasi email kosong atau salah
     */
    private boolean isValidateEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * @param yourField Method ini digunakan untuk validasi field kosong atau tidak
     */
    private boolean isEmptyField(String yourField) {
        return !TextUtils.isEmpty(yourField);
    }

    /**
     * @param password
     * @param retypePassword method ini digunakan untuk mencocokan password dengan retype password
     */
    private boolean isMatch(String password, String retypePassword) {
        return password.equals(retypePassword);
    }

    private void showDateDialog() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tvDateResult.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}

