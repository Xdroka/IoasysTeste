package pedro.com.ioasysteste.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthRequest {

     @SerializedName("success")
    @Expose
    private boolean success;

    /**
     * No args constructor for use in serialization
     */
    public AuthRequest() {
    }

    /**
     * @param success
     */
    public AuthRequest(Object enterprise, boolean success) {
        super();
        this.success = success;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}