package com.example.npraj1.employdb;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private Paint p = new Paint();

    List<Employee> employees = new ArrayList<>();
    private AlertDialog.Builder alertDialog;
    private EditText et_employeeName, et_employeeAge, et_employeeDomain, et_employeeProject;
    private int edit_position;
    private View view;
    private boolean add = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDialog();

    }

    private void initViews() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.rv_employee);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        Employee emp1 = new Employee("Niranjan", "22", "Java", "RAM Security");
        Employee emp2 = new Employee("Praveen", "22", "ios", "Taco Bell");
        Employee emp3 = new Employee("Govind", "22", "Java", "RAM Perf");
        Employee emp4 = new Employee("Chirag", "22", "Android", "Taco Bell");
        Employee emp5 = new Employee("Pranita", "22", "ios", "Sun Super");
        Employee emp6 = new Employee("Sourabh", "22", "Hybris", "Whirlpool");
        Employee emp7 = new Employee("Sandesh", "22", "Drupal", "Taj");
        Employee emp8 = new Employee("Aman", "22", "Java", "RAM");
        employees = new ArrayList<>(Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8));

//        RecyclerView.Adapter adapter = new DataAdapter(employees);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

        dataAdapter = new DataAdapter(employees);
        recyclerView.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();

        initSwipe();

    }

    private void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
                    dataAdapter.removeItem(position);
                } else {
                    removeView();
                    edit_position = position;
                    alertDialog.setTitle("Edit Employee");
                    et_employeeName.setText(employees.get(position).getEmployeeName());
                    et_employeeAge.setText(employees.get(position).getEmployeeAge());
                    et_employeeDomain.setText(employees.get(position).getEmployeeDomain());
                    et_employeeProject.setText(employees.get(position).getEmployeeProject());
                    alertDialog.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if (dX > 0) {
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_edit_white);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete_white);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void removeView() {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    private void initDialog() {
        alertDialog = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String employeeName = et_employeeName.getText().toString();
                String employeeAge = et_employeeAge.getText().toString();
                String employeeDomain = et_employeeDomain.getText().toString();
                String employeeProject = et_employeeProject.getText().toString();
                if (add) {
                    add = false;

                    dataAdapter.addItem(employeeName, employeeAge, employeeDomain, employeeProject);
                    dialog.dismiss();
                } else {
                    employees.set(edit_position, new Employee(employeeName, employeeAge, employeeDomain, employeeProject));
                    dataAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });
        et_employeeName = (EditText) view.findViewById(R.id.et_employeeName);
        et_employeeAge = (EditText) view.findViewById(R.id.et_employeeAge);
        et_employeeDomain = (EditText) view.findViewById(R.id.et_employeeDomain);
        et_employeeProject = (EditText) view.findViewById(R.id.et_employeeProject);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                removeView();
                add = true;
                alertDialog.setTitle("Add Employee");
                et_employeeName.setText("");
                et_employeeAge.setText("");
                et_employeeDomain.setText("");
                et_employeeProject.setText("");
                alertDialog.show();
                break;
        }
    }
}
