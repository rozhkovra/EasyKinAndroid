package ru.rrozhkov.easykin.android.task;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collection;

import ru.rrozhkov.easykin.android.FilesSettings;
import ru.rrozhkov.easykin.android.R;
import ru.rrozhkov.easykin.android.context.MasterDataContext;
import ru.rrozhkov.easykin.android.context.SettingsContext;
import ru.rrozhkov.easykin.android.context.impl.MasterDataContextFactory;
import ru.rrozhkov.easykin.android.db.impl.EasyKinDBHelper;
import ru.rrozhkov.easykin.android.db.impl.EasyKinDBManager;
import ru.rrozhkov.easykin.android.model.payment.impl.convert.PaymentArrayConverter;
import ru.rrozhkov.easykin.android.model.task.impl.convert.TaskArrayConverter;
import ru.rrozhkov.easykin.android.model.task.impl.convert.TaskArrayStatusConverter;
import ru.rrozhkov.easykin.core.filter.IFilter;
import ru.rrozhkov.easykin.core.filter.util.FilterUtil;
import ru.rrozhkov.easykin.model.category.CategoryFactory;
import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.easykin.model.task.Priority;
import ru.rrozhkov.easykin.model.task.Status;
import ru.rrozhkov.easykin.task.impl.filter.TaskFilterFactory;


import static ru.rrozhkov.easykin.android.FilesSettings.SHOW_CLOSED_TASK;
import static ru.rrozhkov.easykin.android.FilesSettings.SHOW_ONLY_IMPORTANT_TASK;

public class TaskListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    final private static TaskFilterFactory taskFilterFactory = TaskFilterFactory.instance();
    final static private CategoryFactory categoryFactory = new CategoryFactory();
    private ListView listView;
    private MasterDataContext context;
    private IFilter categoryFilter = null;
    private EasyKinDBHelper dbHelper;
    private boolean online = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        online = getIntent().getBooleanExtra("online", false);
        dbHelper = new EasyKinDBHelper(this);
        EasyKinDBManager.init(dbHelper);

        new ManageDataTask().execute((Void) null);

        setContentView(R.layout.activity_task_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("EasyKin");
        initSettingsContext();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(TaskListActivity.this, AddTaskActivity.class);
//                startActivity(intent);
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void refreshNavView() {
        Collection<ITask> beans = context.tasks();
        if(!SettingsContext.instance().isShowClosedTask()){
            beans = FilterUtil.filter(beans, taskFilterFactory.status(Status.OPEN));
        }
        if(SettingsContext.instance().isShowOnlyImportantTask()){
            beans = FilterUtil.filter(beans, taskFilterFactory.priority(Priority.IMPOTANT_FAST));
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().clear();
        navigationView.getMenu().add(0,0,0,"Все ("+beans.size()+")");
        Collection<ICategory> categories = context.categories();
        for(ICategory category : categories){
            if(category.getId()==5) {
                Collection<IPayment> payments = context.finance();
                navigationView.getMenu().add(0, category.getId(), 0, category.getName() + " (" +
                        payments.size() + ")");

            }else {
                navigationView.getMenu().add(0, category.getId(), 0, category.getName() + " (" +
                        FilterUtil.filter(beans, taskFilterFactory.category(category)).size() + ")");
            }
        }
    }

    @Override
    protected void onStop(){
        super.onStop();

        SharedPreferences settings = getSharedPreferences(FilesSettings.EASYKIN_SETTINGS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("showClosedTask", SettingsContext.instance().isShowClosedTask());
        editor.commit();
    }

    private void refresh(){
        rereshTaskLst();
        refreshNavView();
        Toast.makeText(this.getBaseContext(),"Tasks was reloaded!",Toast.LENGTH_SHORT).show();
    }

    private void rereshTaskLst(){
        Collection<ITask> beans = context.tasks();
        if(!SettingsContext.instance().isShowClosedTask()){
            beans = FilterUtil.filter(beans, taskFilterFactory.status(Status.OPEN));
        }
        if(SettingsContext.instance().isShowOnlyImportantTask()){
            beans = FilterUtil.filter(beans, taskFilterFactory.priority(Priority.IMPOTANT_FAST));
        }
        ArrayAdapter<String> adapter;
        if(categoryFilter!=null){
            beans = FilterUtil.filter(beans, categoryFilter);
            adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, new TaskArrayConverter().convert(beans));
            setTitle(getTitle());
        }else {
            adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, new TaskArrayStatusConverter().convert(beans));
            setTitle("EasyKin");
        }

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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

        int id = item.getItemId();
        String name = item.getTitle().toString();

        if(id==5){
            categoryFilter = null;
            setTitle(name);
            Collection<IPayment> payments = context.finance();
            ArrayAdapter<String> adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, new PaymentArrayConverter().convert(payments));
            listView.setAdapter(adapter);
            return true;
        }
        if(id==6){
            categoryFilter = null;
            setTitle(name);
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
            setTitle(name);
            categoryFilter = taskFilterFactory.category(categoryFactory.create(id,name));
        }

        rereshTaskLst();

        return true;
    }

    private void initSettingsContext(){
        SharedPreferences settings = getSharedPreferences(FilesSettings.EASYKIN_SETTINGS, 0);
        boolean showClosedTask = settings.getBoolean(SHOW_CLOSED_TASK, false);
        SettingsContext.instance().setShowClosedTask(showClosedTask);
        boolean showOnlyImportantTask = settings.getBoolean(SHOW_ONLY_IMPORTANT_TASK, false);
        SettingsContext.instance().setShowOnlyImportantTask(showOnlyImportantTask);
    }

    public class ManageDataTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            TaskListActivity.this.context = MasterDataContextFactory.instance(online);
            TaskListActivity.this.context.init();
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            TaskListActivity.this.refresh();
            TaskListActivity.this.context.replicate();
        }
    }
}
