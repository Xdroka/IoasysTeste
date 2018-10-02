package pedro.com.ioasysteste.view.connector;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import pedro.com.ioasysteste.models.data.Enterprise;
import pedro.com.ioasysteste.models.data.HeaderAuth;
import pedro.com.ioasysteste.view.AboutActivity;
import pedro.com.ioasysteste.view.HomeActivity;
import pedro.com.ioasysteste.view.RecylerAdapter;

public class BindWithActivities {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    public BindWithActivities(Context context) {
        this.mContext = context;
    }

    public void toastMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public void errorLogin() {
        mEmailEditText.setError("Verifique se o email está correto");
        mPasswordEditText.setError("Verifique se a senha está correta");
    }

    public void setupRecycler(List<Enterprise> enterpriseList) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        RecylerAdapter mAdapter = new RecylerAdapter(enterpriseList, mContext);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void toHomeActivity(HeaderAuth headerAuth) {
        Intent intent = new Intent(mContext, HomeActivity.class);
        intent.putExtra("access-token", headerAuth.getAcess_token());
        intent.putExtra("uid", headerAuth.getUid());
        intent.putExtra("client", headerAuth.getClient());
        mContext.startActivity(intent);
    }

    public void toAboutActivity(Enterprise enterprise) {
        Intent intent = new Intent(mContext, AboutActivity.class);
        intent.putExtra("nameEnterprise", enterprise.getEnterpriseName());
        intent.putExtra("infoEnterprise", enterprise.getDescription());
        if (enterprise.getPhoto() != null) {
            intent.putExtra("photoEnterprise", enterprise.getPhoto().toString());
        }
        mContext.startActivity(intent);
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    public void setmEmailEditText(EditText mEmailEditText) {
        this.mEmailEditText = mEmailEditText;
    }

    public void setmPasswordEditText(EditText mPasswordEditText) {
        this.mPasswordEditText = mPasswordEditText;
    }

}

