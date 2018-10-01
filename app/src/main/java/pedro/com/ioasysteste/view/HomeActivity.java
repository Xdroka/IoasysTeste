package pedro.com.ioasysteste.view;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import pedro.com.ioasysteste.R;
import pedro.com.ioasysteste.controllers.connector.BindWithActivities;
import pedro.com.ioasysteste.models.data.HeaderAuth;

public class HomeActivity extends AppCompatActivity {
    private SearchView mSearchView;
    private TextView mInfoTextView;
    private HeaderAuth mheaderAuth;
    private RecyclerView pRecyclerView;
    private Toolbar mToolbarSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbarSearch = findViewById(R.id.toolbarSearchId);
        mInfoTextView = findViewById(R.id.infoTextViewId);
        pRecyclerView = findViewById(R.id.recyclerViewId);

        BindWithActivities.sRecyclerView = pRecyclerView;
        BindWithActivities.sContext = getApplicationContext();

        setSupportActionBar(mToolbarSearch);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (!bundle.isEmpty()) {
                mheaderAuth = new HeaderAuth(
                        bundle.getString("access-token"),
                        bundle.getString("uid"),
                        bundle.getString("client")
                );
            }
        }

        pRecyclerView.setNestedScrollingEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        mSearchView = (SearchView) menu.findItem(R.id.searchViewId).getActionView();
        setTitle("");

        if (mSearchView != null) {
            mSearchView.setQueryHint("Pesquisar");
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
//                    sendName(query);
                    // mSearchView.setEnabled(false);
                    InputMethodManager imm = (InputMethodManager) getApplicationContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    mInfoTextView.setText("");
                    BindWithActivities.sendName(newText, mheaderAuth);
                    return false;
                }
            });
        }

        return true;
    }

}
