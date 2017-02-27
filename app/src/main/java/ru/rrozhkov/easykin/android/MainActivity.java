package ru.rrozhkov.easykin.android;

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

import java.util.Collection;

import ru.rrozhkov.easykin.android.context.MasterDataContext;
import ru.rrozhkov.easykin.android.model.payment.impl.convert.PaymentArrayConverter;
import ru.rrozhkov.easykin.android.model.task.impl.convert.TaskArrayStatusConverter;
import ru.rrozhkov.easykin.model.category.CategoryFactory;
import ru.rrozhkov.easykin.model.category.ICategory;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;
import ru.rrozhkov.easykin.model.task.ITask;
import ru.rrozhkov.easykin.model.task.Status;
import ru.rrozhkov.easykin.model.task.impl.filter.TaskFilterFactory;
import ru.rrozhkov.lib.filter.IFilter;
import ru.rrozhkov.lib.filter.util.FilterUtil;
import ru.rrozhkov.lib.timer.Timer;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    private MasterDataContext context = new MasterDataContext();
    private IFilter categoryFilter = null;
    private IFilter statusFilter = null;
    private String[] values = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("EasyKin");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context.init();
        this.values = new TaskArrayStatusConverter().convert(context.tasks());
        listView = (ListView) findViewById(R.id.taskLst);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Reload tasks....", Snackbar.LENGTH_LONG).show();

                context.init();
                MainActivity.this.values = new TaskArrayStatusConverter().convert(context.tasks());
                ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, MainActivity.this.values);
                MainActivity.this.listView.setAdapter(adapter);
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
        final SubMenu subMenu = navigationView.getMenu().addSubMenu("Категории");
        subMenu.add(0,1000,0,"Все");
        Collection<ICategory> categories = context.categories();
        for(ICategory category : categories){
            subMenu.add(0,1000+category.getId(),0,category.getName());
        }
        final SubMenu subMenu2 = navigationView.getMenu().addSubMenu("Статус");
        subMenu2.add(0,2000,0,"Все");
        for(Status status : context.statuses()){
            subMenu2.add(0,2000+Status.status(status),0,status.toString());
        }
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

        if(id==1005){
            setTitle("EasyKin\\"+name);
            Collection<IPayment> payments = context.finance();
            ArrayAdapter<String> adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, new PaymentArrayConverter().convert(payments));
            listView.setAdapter(adapter);
            return true;
        }
        if(id==1006){
            setTitle("EasyKin\\"+name);
            Collection<IPayment> payments = context.factPayments();
            ArrayAdapter<String> adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, new PaymentArrayConverter().convert(payments));
            listView.setAdapter(adapter);
            return true;
        }

        Collection<ITask> beans = context.tasks();
        this.values = new TaskArrayStatusConverter().convert(beans);
        if(id==1000 || id==1009) {
            setTitle("EasyKin");
            categoryFilter = null;
        }
        if(id>1000 && id!=1009 && id!=1005 && id!=1006 && id<2000){
            setTitle("EasyKin\\"+name);
            categoryFilter = TaskFilterFactory.category(CategoryFactory.create(id-1000,name));
        }

        if(id==2000) {
            statusFilter = null;
        }
        if(id==2001) {
            statusFilter = TaskFilterFactory.status(Status.OPEN);
        }
        if(id==2002) {
            statusFilter = TaskFilterFactory.status(Status.CLOSE);
        }

        if(statusFilter!=null){
            beans = FilterUtil.filter(beans, statusFilter);
        }
        if(categoryFilter!=null){
            beans = FilterUtil.filter(beans, categoryFilter);
        }
        this.values = new TaskArrayStatusConverter().convert(beans);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);

        return true;
    }
}
