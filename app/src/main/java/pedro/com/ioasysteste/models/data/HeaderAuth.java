package pedro.com.ioasysteste.models.data;

public class HeaderAuth {
    private String acess_token, uid, client;

    public HeaderAuth(String acess_token, String uid, String client) {
        this.acess_token = acess_token;

        this.uid = uid;
        this.client = client;
    }

    public String getAcess_token() {
        return acess_token;
    }

    public void setAcess_token(String acess_token) {
        this.acess_token = acess_token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }


}
