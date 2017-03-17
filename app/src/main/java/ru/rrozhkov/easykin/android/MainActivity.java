package ru.rrozhkov.easykin.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collection;

import ru.rrozhkov.easykin.android.context.MasterDataContext;
import ru.rrozhkov.easykin.android.context.SettingsContext;
import ru.rrozhkov.easykin.android.model.payment.impl.convert.PaymentArrayConverter;
import ru.rrozhkov.easykin.android.model.task.impl.convert.TaskArrayConverter;
import ru.rrozhkov.easykin.android.model.task.impl.convert.TaskArrayStatusConverter;
import ru.rrozhkov.easykin.android.ws.client.EasyKinService;
import ru.rrozhkov.easykin.model.category.CategoryFactory;
import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.easykin.model.task.Status;
import ru.rrozhkov.easykin.model.task.impl.filter.TaskFilterFactory;
import ru.rrozhkov.lib.collection.CollectionUtil;
import ru.rrozhkov.lib.filter.IFilter;
import ru.rrozhkov.lib.filter.util.FilterUtil;
import ru.rrozhkov.lib.timer.Timer;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String PREFS_NAME = "easykinSettings";
    private ListView listView;
    private MasterDataContext context = new MasterDataContext();
    private IFilter categoryFilter = null;
    private boolean isServiceAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("EasyKin");
        isServiceAvailable = new EasyKinService().ping()==1;
        if(!isServiceAvailable){
            Toast.makeText(this.getBaseContext(), "Service not available!", Toast.LENGTH_SHORT).show();
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.getMenu().clear();
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setVisibility(View.INVISIBLE);
            return;
        }

        initSettingsContext();
        refresh();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().clear();
        navigationView.getMenu().add(0,0,0,"Все");
        Collection<ICategory> categories = context.categories();
        for(ICategory category : categories){
            navigationView.getMenu().add(0,category.getId(),0,category.getName());
        }
    }

    @Override
    protected void onStop(){
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("showClosedTask", SettingsContext.instance().isShowClosedTask());
        // Commit the edits!
        editor.commit();
    }

    private void refresh(){
        if(!isServiceAvailable)
            return;
        context.init();
        updateList();
        Toast.makeText(this.getBaseContext(),"Tasks was reloaded!",Toast.LENGTH_SHORT).show();
    }

    private void updateList(){
        Collection<ITask> beans = context.tasks();
        if(!SettingsContext.instance().isShowClosedTask()){
            beans = FilterUtil.filter(beans, TaskFilterFactory.status(Status.OPEN));
        }
        ArrayAdapter<String> adapter;
        if(categoryFilter!=null){
            beans = FilterUtil.filter(beans, categoryFilter);
            adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, new TaskArrayConverter().convert(beans));
        }else
            adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, new TaskArrayStatusConverter().convert(beans));

        listView = (ListView) findViewById(R.id.taskLst);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String name = item.getTitle().toString();

        if(id==5){
            categoryFilter = null;
            setTitle("EasyKin\\"+name);
            Collection<IPayment> payments = context.finance();
            ArrayAdapter<String> adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, new PaymentArrayConverter().convert(payments));
            listView.setAdapter(adapter);
            return true;
        }
        if(id==6){
            categoryFilter = null;
            setTitle("EasyKin\\"+name);
            Collection<IPayment> payments = context.factPayments();
            ArrayAdapter<String> adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, new PaymentArrayConverter().convert(payments));
            listView.setAdapter(adapter);
            return true;
        }

        if(id==0 || id==9) {
            setTitle("EasyKin");
            categoryFilter = null;
        }
        if(id>0 && id!=9 && id!=5 && id!=6 ){
            setTitle("EasyKin\\"+name);
            categoryFilter = TaskFilterFactory.category(CategoryFactory.create(id,name));
        }

        updateList();

        return true;
    }

    private void initSettingsContext(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean showClosedTask = settings.getBoolean("showClosedTask", false);
        SettingsContext.instance().setShowClosedTask(showClosedTask);
    }
}
