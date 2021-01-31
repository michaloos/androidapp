package com.example.projekt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projekt.Adapter.SlowkoAdapter;
import com.example.projekt.EntityClass.SlowkoModel;

import java.util.ArrayList;
import java.util.List;


public class GetData extends AppCompatActivity {

    private List<SlowkoModel> list;
    List<SlowkoModel> listALl;
    private RecyclerView recyclerView;
    SlowkoAdapter slowkoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        list = ClassDatabase.getDatabase(getApplicationContext()).getDao().getAllData();
        this.listALl = new ArrayList<>(list);
    }

    @Override
    protected void onResume(){
        super.onResume();
        getData();
    }

    private void getData(){
        list = new ArrayList<>();
        list = ClassDatabase.getDatabase(getApplicationContext()).getDao().getAllData();

        recyclerView.setAdapter(new SlowkoAdapter(getApplicationContext(), list, new SlowkoAdapter.DeleteItem() {
            @Override
            public void itemDelete(int position, int id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GetData.this);
                builder.setMessage(R.string.czy_jestes_pewny).setTitle(R.string.usuwanie_elementu);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClassDatabase.getDatabase(getApplicationContext()).getDao().deleteData(id);
                        getData();
                        Toast.makeText(GetData.this,"Słowo zostało usunięte!",Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getData();
                        Toast.makeText(GetData.this,"Słowo nie zostało usunięte!",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }));
        slowkoAdapter = (SlowkoAdapter) recyclerView.getAdapter();

    }


    //do wyszukiwania słówka w bazie
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recyclermenu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                slowkoAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}