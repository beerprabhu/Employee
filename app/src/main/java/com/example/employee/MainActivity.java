package com.example.employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.example.employee.adapter.EmployeeListAdapter;
import com.example.employee.model.DataModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private RecyclerView employeelist;
    MainActivity activity;
 List<DataModel> dataModelList;
private EmployeeListAdapter adapter;
LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employeelist = findViewById(R.id.employee_list);
        dataModelList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        employeelist.setLayoutManager(linearLayoutManager);
         adapter = new EmployeeListAdapter(MainActivity.this,dataModelList);
    employeelist.setAdapter(adapter);
        checkConnection();

    }

    public void checkConnection()
    {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(activity.CONNECTIVITY_SERVICE);
        NetworkInfo  activeNetwork = manager.getActiveNetworkInfo();
        if(null!= activeNetwork)
        {
            fetchEmployeeList();
        }
        else
        {
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show();
        }

    }
    public void fetchEmployeeList()
    {
        RetrofitClient.getRetrofitClint().getUsers().enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    dataModelList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {

            }
        });
    }


}