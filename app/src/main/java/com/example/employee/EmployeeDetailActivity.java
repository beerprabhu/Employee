package com.example.employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class EmployeeDetailActivity extends AppCompatActivity {

    private TextView empId,empName,empAddress,empCompany,empWebsite,emp_email,emp_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);
        empId = findViewById(R.id.emp_id);
        empName = findViewById(R.id.emp_name);
        empAddress = findViewById(R.id.emp_address);
        empCompany = findViewById(R.id.company_name);
        empWebsite = findViewById(R.id.company_website);
        emp_email = findViewById(R.id.emp_email);
        emp_phone = findViewById(R.id.emp_phone);
        Intent i = getIntent();
        Log.i("IDa",i+"");
        Long id = i.getLongExtra("ID",0);
        String name = i.getStringExtra(("Name"));
        String address = i.getStringExtra(("Address"));
        String company = i.getStringExtra("Company");
        String website = i.getStringExtra("website");
        String email = i.getStringExtra("email");
        String phone = i.getStringExtra("phone");

        empId.setText(String.valueOf(id));
        empName.setText(name.toUpperCase());
        empAddress.setText(address);
        empCompany.setText(company);
        empWebsite.setText(website);
        emp_email.setText(email.toLowerCase());
        emp_phone.setText(phone);
        emp_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                String[] TO = {"email"};
                email.putExtra(Intent.EXTRA_EMAIL, TO);
                //need this to prompts email client only
                email.setType("message/rfc822");
                email.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });

        emp_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                Intent i = new Intent(Intent.ACTION_CALL);
//                i.setData(Uri.parse("tel:"+  phone));
//                startActivity(i);
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone)));
            }

        });


    }
}