package com.example.employee.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employee.EmployeeDetailActivity;
import com.example.employee.R;
import com.example.employee.model.DataModel;

import java.util.List;
import java.util.Locale;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.ViewHolder> {
    Context context;
    List<DataModel> dataModels;

    public EmployeeListAdapter(Context context, List<DataModel> dataModels) {
        this.context = context;
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public EmployeeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeListAdapter.ViewHolder holder, int position) {
        DataModel dataModel = dataModels.get(position);
        holder.emp_name.setText(dataModel.getName().toUpperCase());
        holder.emp_email.setText(dataModel.getEmail().toLowerCase());
holder.ll.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), EmployeeDetailActivity.class);
        intent.putExtra("ID",dataModels.get(position).getId());
        intent.putExtra("Name",dataModels.get(position).getName());

        intent.putExtra("Address",dataModels.get(position).getAddress().getCity()+"\n\n"+
                        dataModels.get(position).getAddress().getStreet()
                );
        intent.putExtra("Company",dataModels.get(position).getCompany().getName());

        intent.putExtra("website",dataModels.get(position).getWebsite());
        intent.putExtra("email",dataModels.get(position).getEmail());
        intent.putExtra("phone",dataModels.get(position).getPhone());

        Log.i("checkid",dataModels.get(position).getId()+"");
        view.getContext().startActivity(intent);


    }
});

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView emp_name,emp_email;

        private LinearLayout ll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            emp_name = itemView.findViewById(R.id.empName);
            emp_email = itemView.findViewById(R.id.empEmail);
            ll = itemView.findViewById(R.id.lLL);


        }
    }

}
