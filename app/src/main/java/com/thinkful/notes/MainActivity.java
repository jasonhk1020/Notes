package com.thinkful.notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private NoteListItemAdapter mAdapter;
    private NoteListItem mListItem;
    private Button mButton;

    public void setColor(){

        SharedPreferences prefs = getSharedPreferences("colors", MODE_PRIVATE);

        String backgroundColor = prefs.getString("BACKGROUND_COLOR", "WHITE");
        String foregroundColor = prefs.getString("FOREGROUND_COLOR", "GREY");

        View view = findViewById(R.id.mainRelativeLayout);

        switch (backgroundColor.toUpperCase()) {
            case "RED":
                view.setBackgroundColor(Color.RED);
                break;
            case "GREEN":
                view.setBackgroundColor(Color.GREEN);
                break;
            case "WHITE":
                view.setBackgroundColor(Color.WHITE);
                break;
        }

        switch (foregroundColor.toUpperCase()) {
            case "YELLOW":
                mRecyclerView.setBackgroundColor(Color.YELLOW);
                break;
            case "PURPLE":
                mRecyclerView.setBackgroundColor(Color.parseColor("#800080"));
                break;
            case "GRAY":
                mRecyclerView.setBackgroundColor(Color.GRAY);
                break;
            case "BLACK":
                mRecyclerView.setBackgroundColor(Color.BLACK);
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotesDBHelper.getInstance(this).getReadableDatabase();

        //display recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //add notelist items to view
        mAdapter = new NoteListItemAdapter(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        //create toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        //create add note button
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text in the EditText
                EditText editText = (EditText) findViewById(R.id.edit_text);

                // Create a new NoteListItem with the text
                mListItem = new NoteListItem(editText.getText().toString());

                // Add the item to the adapter
                mAdapter.addItem(mListItem);

                //add to database
                NoteDAO dao = new NoteDAO(MainActivity.this);
                dao.save(mListItem);

                // Set the EditText to an empty string
                editText.setText("");

                //scroll up
                mLayoutManager.scrollToPosition(0);

            }
        });

        //animate adding new item to view
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //get database entries
        NotesDBHelper.getInstance(this).getReadableDatabase();

        //set the colors
        setColor();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("Color", "settings");
            this.startActivityForResult(intent, 1);

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            if (data.hasExtra("Note")) {
                NoteListItem note = (NoteListItem)data.getSerializableExtra("Note");
                Toast.makeText(this, note.getText(),Toast.LENGTH_LONG).show();
                mAdapter.addItem(note);
            }
            if (data.hasExtra("Color")) {
                setColor();
            }
        }

    }
}
