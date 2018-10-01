package pedro.com.ioasysteste.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import pedro.com.ioasysteste.R;
import pedro.com.ioasysteste.controllers.ApiController;
import pedro.com.ioasysteste.controllers.connector.BindWithActivities;

public class AboutActivity extends AppCompatActivity {
    private TextView mInfoEnterpriseTextView;
    private ImageView mPhotoEnterprise;
    private Bundle pBundle;
    private boolean mPhotoWasDonwloaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbarAbout = findViewById(R.id.toolbarAboutId);
        setSupportActionBar(toolbarAbout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mInfoEnterpriseTextView = findViewById(R.id.infoEnterpriseId);
        mPhotoEnterprise = findViewById(R.id.imageAboutEntepriseId);

        pBundle = getIntent().getExtras();
        if (!pBundle.isEmpty()) {
            toolbarAbout.setTitle(pBundle.getString("nameEnterprise"));
            mInfoEnterpriseTextView.setText(pBundle.getString("infoEnterprise"));
            String photoUrl = pBundle.getString("photoEnterprise");

            if (pBundle.containsKey("photoEnterprise")) {
                downloadPhoto(photoUrl);
            } else {
                mPhotoEnterprise.setImageDrawable(getDrawable(R.drawable.imageReport));
            }
        }

        BindWithActivities.sContext = getApplicationContext();

        toolbarAbout.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mPhotoEnterprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pBundle.containsKey("photoEnterprise") && !mPhotoWasDonwloaded) {
                    downloadPhoto(pBundle.getString("photoEnterprise"));
                }
            }
        });
    }

    private void downloadPhoto(String photoUrl) {
        mPhotoWasDonwloaded = Glide.with(getApplicationContext())
                .load(ApiController.BASE_URL + photoUrl)
                .placeholder(R.drawable.loading)
                .error(R.drawable.download)
                .into(mPhotoEnterprise).getRequest().isComplete();
    }

}
