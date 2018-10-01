package pedro.com.ioasysteste.models.services;

import java.util.Map;

import pedro.com.ioasysteste.models.data.AuthRequest;
import pedro.com.ioasysteste.models.data.ListEnterprise;
import pedro.com.ioasysteste.models.data.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    @POST("users/auth/sign_in")
    Call<AuthRequest> authCall(@Body User user);

    @GET("enterprises")
    Call<ListEnterprise> findEnterpriseByName(@Query("name") String nameEnterprise
            , @HeaderMap Map<String, String> headers);

}
