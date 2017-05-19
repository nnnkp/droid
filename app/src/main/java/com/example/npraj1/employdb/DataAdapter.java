package com.example.npraj1.employdb;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by npraj1 on 5/16/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Employee> employees;

    public DataAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.employee_details, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder holder, final int i) {

        if (employees.get(i).isOpen()) {
            holder.tv_employee_age.setVisibility(View.VISIBLE);
            holder.tv_employee_domain.setVisibility(View.VISIBLE);
            holder.tv_employee_project.setVisibility(View.VISIBLE);
            holder.btn_add.setVisibility(View.VISIBLE);
            holder.btn_show.setText("HIDE DETAILS");
        } else {
            holder.tv_employee_age.setVisibility(View.GONE);
            holder.tv_employee_domain.setVisibility(View.GONE);
            holder.tv_employee_project.setVisibility(View.GONE);
            holder.btn_add.setVisibility(View.GONE);
            holder.btn_show.setText("SHOW DETAILS");
        }
        holder.tv_employee_name.setText(employees.get(i).getEmployeeName());
        holder.tv_employee_age.setText(employees.get(i).getEmployeeAge());
        holder.tv_employee_domain.setText(employees.get(i).getEmployeeDomain());
        holder.tv_employee_project.setText(employees.get(i).getEmployeeProject());

        holder.btn_show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                employees.get(i).setOpen(false);
                holder.tv_employee_name.setVisibility(View.VISIBLE);
                if (holder.tv_employee_age.getVisibility() == View.GONE) {

                    employees.get(i).setOpen(true);
                    holder.tv_employee_age.setVisibility(View.VISIBLE);
                    holder.tv_employee_domain.setVisibility(View.VISIBLE);
                    holder.tv_employee_project.setVisibility(View.VISIBLE);
                    holder.btn_add.setVisibility(View.VISIBLE);
                    holder.btn_show.setText("HIDE DETAILS");
                } else {
                    employees.get(i).setOpen(false);

                    holder.tv_employee_age.setVisibility(View.GONE);
                    holder.tv_employee_domain.setVisibility(View.GONE);
                    holder.tv_employee_project.setVisibility(View.GONE);
                    holder.btn_add.setVisibility(View.GONE);
                    holder.btn_show.setText("SHOW DETAILS");
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void addItem(String employeeName, String employeeAge, String employeeDomain, String employeeProject) {
        employees.add(new Employee(employeeName, employeeAge, employeeDomain, employeeProject));
        notifyItemInserted(employees.size());
    }

    public void removeItem(int position) {
        employees.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, employees.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_employee_name;
        private TextView tv_employee_age;
        private TextView tv_employee_domain;
        private TextView tv_employee_project;

        private Button btn_show;
        private Button btn_add;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_employee_name = (TextView) itemView.findViewById(R.id.tv_employee_name);
            tv_employee_age = (TextView) itemView.findViewById(R.id.tv_employee_age);
            tv_employee_domain = (TextView) itemView.findViewById(R.id.tv_employee_domain);
            tv_employee_project = (TextView) itemView.findViewById(R.id.tv_employee_project);

            btn_show = (Button) itemView.findViewById(R.id.btn_show);
            btn_add = (Button) itemView.findViewById(R.id.btn_add);

        }
    }
}
