package sg.edu.rp.c346.id22020995.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd;
    Button btnClear;
    ListView lvTasks;
    ArrayList<String> alTasks;
    Spinner spnAddDelete;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        lvTasks = findViewById(R.id.listViewTasks);
        alTasks = new ArrayList<String>();
        spnAddDelete = findViewById(R.id.spinnerAddDelete);
        btnDelete = findViewById(R.id.buttonDelete);

        // create and set ArrayAdapter for lvTasks
        ArrayAdapter aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);
        lvTasks.setAdapter(aaTasks);

        // set OnClickListener for btnAdd
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskInput = etTask.getText().toString();
                alTasks.add(taskInput);
                aaTasks.notifyDataSetChanged();
                etTask.setText("");
            }
        });

        // set OnClickListener for btnClear
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set everything to empty/cleared
                etTask.setText("");
                alTasks.clear();
                aaTasks.notifyDataSetChanged();
            }
        });

        // set OnItemSelectedListener for spnAddDelete
        spnAddDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                switch (i){
                    case 0:
                        // add task selected
                        // add button enabled, delete button disabled
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etTask.setHint("Type in a new task here");
                        break;
                    case 1:
                        // delete task selected
                        // add button disabled, delete button enabled
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etTask.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // set OnClickListener for btnDelete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get position integer and remove the item in that position
                int posInput = Integer.parseInt(etTask.getText().toString());
                alTasks.remove(posInput);
                aaTasks.notifyDataSetChanged();
            }
        });
    }
}