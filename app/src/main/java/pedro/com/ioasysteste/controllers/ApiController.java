package pedro.com.ioasysteste.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pedro.com.ioasysteste.R;
import pedro.com.ioasysteste.controllers.connector.BindWithActivities;
import pedro.com.ioasysteste.models.data.AuthRequest;
import pedro.com.ioasysteste.models.data.Enterprise;
import pedro.com.ioasysteste.models.data.HeaderAuth;
import pedro.com.ioasysteste.models.data.ListEnterprise;
import pedro.com.ioasysteste.models.data.User;
import pedro.com.ioasysteste.models.services.ApiServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    public static final String BASE_API_URL = "http://empresas.ioasys.com.br/api/v1/";
    public static final String BASE_URL = "http://empresas.ioasys.com.br";
    private Retrofit mRetrofit;
    private ApiServices mApiServices;
    private User mUser;
    private HeaderAuth mHeaderAuth;
    private List<Enterprise> mEnterpriseList;
    private boolean mAuthenticationResponse;

    public ApiController() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiServices = mRetrofit.create(ApiServices.class);
    }

    public void setUser(String email, String password) {
        mUser = new User(email, password);
    }

    public void callingAuth() {
        Call<AuthRequest> authCall = mApiServices.authCall(mUser);

        authCall.enqueue(new Callback<AuthRequest>() {
            @Override
            public void onResponse(Call<AuthRequest> call, Response<AuthRequest> response) {
                setmAuthenticationResponse(response.isSuccessful()); //esse atributo será usado na view
                if (ismAuthenticationResponse()) {
                    mHeaderAuth = new HeaderAuth(
                            response.headers().get("access-token"),
                            response.headers().get("uid"),
                            response.headers().get("client")
                    );
                    BindWithActivities.toHomeActivity(mHeaderAuth);
                }
            }

            @Override
            public void onFailure(Call<AuthRequest> call, Throwable t) {
                BindWithActivities.toastMessage(BindWithActivities.sContext.getString(R.string.errorConection));
            }
        });

    }

    public void findEnterpriseByName(String name, HeaderAuth header) {
        mEnterpriseList = new ArrayList<Enterprise>();
        Map<String, String> headerHashMap = new HashMap<>();
        headerHashMap.put("access-token", header.getAcess_token());
        headerHashMap.put("client", header.getClient());
        headerHashMap.put("uid", header.getUid());

        final Call<ListEnterprise> enterpriseListCall = mApiServices.findEnterpriseByName(name, headerHashMap);

        enterpriseListCall.enqueue(new Callback<ListEnterprise>() {
            @Override
            public void onResponse(Call<ListEnterprise> call, Response<ListEnterprise> response) {
                if (!response.isSuccessful()) return;
                    mEnterpriseList.addAll(response.body().getEnterprises());
                    BindWithActivities.setupRecycler(mEnterpriseList);
            }

            @Override
            public void onFailure(Call<ListEnterprise> call, Throwable t) {
                BindWithActivities.toastMessage(BindWithActivities.sContext.getString(R.string.errorConection));
            }
        });
    }

    public void setmAuthenticationResponse(boolean mAuthenticationResponse) {
        this.mAuthenticationResponse = mAuthenticationResponse;
    }

    public boolean ismAuthenticationResponse() {
        return mAuthenticationResponse;
    }

}

