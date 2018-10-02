package pedro.com.ioasysteste.controllers;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pedro.com.ioasysteste.R;
import pedro.com.ioasysteste.models.data.AuthRequest;
import pedro.com.ioasysteste.models.data.Enterprise;
import pedro.com.ioasysteste.models.data.HeaderAuth;
import pedro.com.ioasysteste.models.data.ListEnterprise;
import pedro.com.ioasysteste.models.data.User;
import pedro.com.ioasysteste.models.services.ApiServices;
import pedro.com.ioasysteste.view.connector.BindWithActivities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    private static final String BASE_API_URL = "http://empresas.ioasys.com.br/api/v1/";
    public static final String BASE_URL = "http://empresas.ioasys.com.br";
    private ApiServices mApiServices;
    private User mUser;
    private HeaderAuth mHeaderAuth;
    private List<Enterprise> mEnterpriseList;

    public ApiController() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiServices = mRetrofit.create(ApiServices.class);
    }

    public void setUser(String email, String password) {
        mUser = new User(email, password);
    }

    public void callingAuth(final BindWithActivities bind) {
        Call<AuthRequest> authCall = mApiServices.authCall(mUser);

        authCall.enqueue(new Callback<AuthRequest>() {
            @Override
            public void onResponse(@NonNull Call<AuthRequest> call, @NonNull Response<AuthRequest> response) {
                if (response.isSuccessful()) {
                    mHeaderAuth = new HeaderAuth(
                            response.headers().get("access-token"),
                            response.headers().get("uid"),
                            response.headers().get("client")
                    );
                    bind.toHomeActivity(mHeaderAuth);
                } else {
                    bind.errorLogin();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthRequest> call, @NonNull Throwable t) {
                bind.toastMessage(bind.getmContext().getString(R.string.errorConection));
            }
        });

    }

    public void findEnterpriseByName(String name, HeaderAuth header, final BindWithActivities bind) {
        mEnterpriseList = new ArrayList<>();
        Map<String, String> headerHashMap = new HashMap<>();
        headerHashMap.put("access-token", header.getAcess_token());
        headerHashMap.put("client", header.getClient());
        headerHashMap.put("uid", header.getUid());

        final Call<ListEnterprise> enterpriseListCall = mApiServices.findEnterpriseByName(name, headerHashMap);

        enterpriseListCall.enqueue(new Callback<ListEnterprise>() {
            @Override
            public void onResponse(@NonNull Call<ListEnterprise> call, @NonNull Response<ListEnterprise> response) {
                if (!response.isSuccessful()) return;
                if (response.body() != null) {
                    mEnterpriseList.addAll(response.body().getEnterprises());
                }
                bind.setupRecycler(mEnterpriseList);
            }

            @Override
            public void onFailure(@NonNull Call<ListEnterprise> call, @NonNull Throwable t) {
                bind.toastMessage(bind.getmContext().getString(R.string.errorConection));
            }
        });
    }

}

