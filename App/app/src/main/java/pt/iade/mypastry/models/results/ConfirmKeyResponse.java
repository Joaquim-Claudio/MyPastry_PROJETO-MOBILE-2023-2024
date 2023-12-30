package pt.iade.mypastry.models.results;

public class ConfirmKeyResponse extends Response{
    String key;
    boolean valid;
    public ConfirmKeyResponse(String msg, Object obj, String key, boolean valid) {
        super(msg, obj);
        this.key = key;
        this.valid = valid;
    }
    public String getKey() {
        return key;
    }
    public boolean isValid() {
        return valid;
    }
}
