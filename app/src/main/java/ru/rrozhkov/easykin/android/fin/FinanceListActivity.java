package ru.rrozhkov.easykin.android.fin;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collection;

import ru.rrozhkov.easykin.android.R;
import ru.rrozhkov.easykin.android.context.MasterDataContext;
import ru.rrozhkov.easykin.android.context.impl.MasterDataContextFactory;
import ru.rrozhkov.easykin.android.model.payment.impl.convert.PaymentArrayConverter;
import ru.rrozhkov.easykin.model.fin.payment.IPayment;

public class FinanceListActivity extends AppCompatActivity {
    private ListView listView;
    private MasterDataContext context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ManageDataTask().execute((Void) null);

        setContentView(R.layout.activity_finance_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void rereshFinanceList() {
        Collection<IPayment> payments = context.finance();
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, new PaymentArrayConverter().convert(payments));
        listView = (ListView) findViewById(R.id.financeList);
        listView.setAdapter(adapter);
    }

    private void refresh(){
        rereshFinanceList();
        Toast.makeText(this.getBaseContext(),"Payments was reloaded!",Toast.LENGTH_SHORT).show();
    }
    public class ManageDataTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            FinanceListActivity.this.context = MasterDataContextFactory.instance(false);
            FinanceListActivity.this.context.init();
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            FinanceListActivity.this.refresh();
        }
    }
}
