package pedro.com.ioasysteste.controllers.connector;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import pedro.com.ioasysteste.controllers.ApiController;
import pedro.com.ioasysteste.models.data.Enterprise;
import pedro.com.ioasysteste.models.data.HeaderAuth;
import pedro.com.ioasysteste.view.AboutActivity;
import pedro.com.ioasysteste.view.HomeActivity;
import pedro.com.ioasysteste.view.RecylerAdapter;

public class BindWithActivities {
    public static Context sContext;
    public static RecyclerView sRecyclerView;

    public static void toastMessage(String msg) {
        try {
            Toast.makeText(sContext, msg, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setupRecycler(List<Enterprise> enterpriseList) {
        sRecyclerView.setLayoutManager(new LinearLayoutManager(sContext));
        RecylerAdapter mAdapter = new RecylerAdapter(enterpriseList);
        sRecyclerView.setAdapter(mAdapter);

    }

    public static void sendName(String enterpriseName, HeaderAuth mheaderAuth) {
        ApiController apiController = new ApiController();
        apiController.findEnterpriseByName(enterpriseName, mheaderAuth);
    }


    public static void toHomeActivity(HeaderAuth headerAuth) {
        Intent intent = new Intent(sContext, HomeActivity.class);
        intent.putExtra("access-token", headerAuth.getAcess_token());
        intent.putExtra("uid", headerAuth.getUid());
        intent.putExtra("client", headerAuth.getClient());
        sContext.startActivity(intent);
    }

    public static void toAboutActivity(Enterprise enterprise) {
        Intent intent = new Intent(sContext, AboutActivity.class);
        intent.putExtra("nameEnterprise", enterprise.getEnterpriseName());
        intent.putExtra("infoEnterprise", enterprise.getDescription());
        if (enterprise.getPhoto() != null) {
            intent.putExtra("photoEnterprise", enterprise.getPhoto().toString());
        }
        sContext.startActivity(intent);
    }

}

