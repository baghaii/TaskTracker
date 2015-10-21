package com.androidatelier.tasktracker;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> mItems;
    ArrayAdapter<String> mItemsAdapter;
    ListView mLvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        readItems();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLvItems = (ListView) findViewById(R.id.lvItems);
        mItems = new ArrayList<String>();
        mItemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mItems);
        mLvItems.setAdapter(mItemsAdapter);
        mItems.add("First Item");
        mItems.add("Second Item");
        setUpListViewListener();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNotification(View view) {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, NotificationReceiverActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("New Task")
                .setContentText("Subject").setSmallIcon(R.drawable.ic_assignment_ind_black_24dp)
                .setContentIntent(pIntent)
                .addAction(R.drawable.ic_assignment_turned_in_black_24dp, "Done", pIntent)
                .addAction(R.drawable.ic_alarm_black_24dp, "Snooze", pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);
    }

    public void addToDoItem(View view) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        mItemsAdapter.add(etNewItem.getText().toString());
        etNewItem.setText("");
        saveItems();
    }

    public void setUpListViewListener() {
        mLvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mItems.remove(i);
                mItemsAdapter.notifyDataSetChanged();
                saveItems();
                return true;
            }
        });

        mLvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editItems(mItems.get(i));
            }
        });

    }

    private void editItems(String itemName) {
        Intent intent = new Intent(this, TaskDetailsActivity.class);
        intent.putExtra("itemName", itemName);
        startActivity(intent);
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File toDoFile = new File(filesDir, "todo.txt");
        try {
            mItems = new ArrayList<String>(FileUtils.readLines(toDoFile));
        } catch (IOException e) {
            mItems = new ArrayList<String>();
            e.printStackTrace();
        }
    }

    private void saveItems() {
        File filesDir = getFilesDir();
        File toDoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(toDoFile, mItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
