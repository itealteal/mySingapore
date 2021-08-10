package sg.edu.rp.id20033824.mysingapore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnToggle;
    Button btnInsert;
    ListView lv;
    CustomAdapter aa;
    ArrayList<Island> as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToggle = findViewById(R.id.btnToggle);
        btnInsert = findViewById(R.id.btnInsert);
        lv = findViewById(R.id.lv);

        as = new ArrayList<Island>();
//      ArrayAdapter<Song> aa = new ArrayAdapter<Song>(SecondActivity.this, android.R.layout.simple_list_item_1,as);
//      lv.setAdapter(aa);

        aa = new CustomAdapter(this, R.layout.row, as);
        lv.setAdapter(aa);


        DBHelper dbh = new DBHelper(MainActivity.this);
        as.clear();
        as.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();


        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnToggle.getText().toString().equalsIgnoreCase("Show Only Island with 5 Stars")){
                    btnToggle.setText("Show All Island");
                    btnToggle.setBackgroundColor(getResources().getColor(R.color.dodger));
                    DBHelper dbh = new DBHelper(MainActivity.this);
                    ArrayList<Island> temp = new ArrayList<Island>();
//                    ArrayAdapter<Song> aatemp = new ArrayAdapter<Song>(SecondActivity.this, android.R.layout.simple_list_item_1,temp);
                    CustomAdapter aatemp = new CustomAdapter(MainActivity.this, R.layout.row, temp);
                    lv.setAdapter(aatemp);
                    temp.clear();
                    temp.addAll(dbh.getAllSongsByStar());
                    aatemp.notifyDataSetChanged();
                }else{
                    btnToggle.setText("Show Only Island with 5 Stars");
                    btnToggle.setBackgroundColor(getResources().getColor(R.color.grey));
                    DBHelper dbh = new DBHelper(MainActivity.this);
                    ArrayList<Island> temp = new ArrayList<Island>();
//                    ArrayAdapter<Song> aatemp = new ArrayAdapter<Song>(SecondActivity.this, android.R.layout.simple_list_item_1,temp);
                    CustomAdapter aatemp = new CustomAdapter(MainActivity.this, R.layout.row, temp);
                    lv.setAdapter(aatemp);
                    temp.clear();
                    temp.addAll(dbh.getAllSongs());
                    aatemp.notifyDataSetChanged();
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Island data = as.get(position);
                Intent i = new Intent(MainActivity.this,
                        SecondActivity.class);
                i.putExtra("data",data);
                startActivity(i);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.insert,null);

                final EditText etTitle = viewDialog.findViewById(R.id.etTitle);
                final EditText etSingers = viewDialog.findViewById(R.id.etSingers);
                final EditText etYear = viewDialog.findViewById(R.id.etYear);
                final RatingBar rbStars = viewDialog.findViewById(R.id.rbStars);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Add new Island");
                myBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(etTitle.getText().toString().isEmpty()||etSingers.getText().toString().isEmpty()||etYear.getText().toString().isEmpty()) {
                            Toast.makeText(MainActivity.this,"Please fill in all field",Toast.LENGTH_SHORT).show();
                        }else {
                            String title = etTitle.getText().toString();
                            String singers = etSingers.getText().toString();
                            int year = Integer.parseInt(etYear.getText().toString());
                            int stars = (int) rbStars.getRating();
                            DBHelper dbh = new DBHelper(MainActivity.this);
                            long inserted_id = dbh.insertSong(title, singers, year, stars);
                            if (inserted_id != -1) {
                                Toast.makeText(MainActivity.this, "Insert successful",
                                        Toast.LENGTH_SHORT).show();
                            }
                            as.clear();
                            as.addAll(dbh.getAllSongs());
                            aa.notifyDataSetChanged();
                        }

                    }
                });
                myBuilder.setNegativeButton("CANCEL",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(MainActivity.this);
        as.clear();
        as.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();
    }

}