package test.openerp.activities.timesheets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.openerp.R;

public class TimesheetsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timesheets);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Timesheets");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.bt_mytimesheet)
    void onClickMyTimesheets(){
        Intent i = new Intent(getApplicationContext(), TimeSheetListActivity.class);
        i.putExtra("TIMESHEET","My Timesheet");
        startActivity(i);
    }

    @OnClick(R.id.bt_teamtimesheet)
    void onClickTeamTimesheets(){
        Intent i = new Intent(getApplicationContext(), TimeSheetListActivity.class);
        i.putExtra("TIMESHEET","Team Timesheet");
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
