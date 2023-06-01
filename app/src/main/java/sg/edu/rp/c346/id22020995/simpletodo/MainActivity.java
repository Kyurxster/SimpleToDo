package sg.edu.rp.c346.id22020995.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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
                if (TextUtils.isEmpty(etTask.getText().toString())){
                    Toast toast = Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    String taskInput = etTask.getText().toString();
                    alTasks.add(taskInput);
                    aaTasks.notifyDataSetChanged();
                    etTask.setText("");
                }
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
                        // change hint and empty input
                        etTask.setHint("Type in a new task here");
                        etTask.setText("");
                        // change input type to text
                        etTask.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        // delete task selected
                        // add button disabled, delete button enabled
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        // change hint and empty input
                        etTask.setText("");
                        etTask.setHint("Type in the index of the task to be removed");
                        // change input type to number
                        etTask.setInputType(InputType.TYPE_CLASS_NUMBER);
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
                if (TextUtils.isEmpty(etTask.getText().toString())){
                    Toast toast = Toast.makeText(MainActivity.this, "Please enter an index", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (alTasks.size() != 0) {
                        // if arrayList is not empty
                        // get position integer
                        int posInput = Integer.parseInt(etTask.getText().toString());
                        if (posInput<(alTasks.size())) {
                            // if posInput is < alTask size (object exists in arrayList)
                            alTasks.remove(posInput);
                            aaTasks.notifyDataSetChanged();
                            etTask.setText("");
                        } else {
                            // if posInput is > alTask size (object does not exist in arrayList)
                            Toast toast = Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT);
                            toast.show();
                            etTask.setText("");
                        }
                    } else {
                        // if arrayList is empty
                        Toast toast = Toast.makeText(MainActivity.this, "You do not have any task to remove", Toast.LENGTH_SHORT);
                        toast.show();
                        etTask.setText("");
                    }
                }
            }
        });
    }
}