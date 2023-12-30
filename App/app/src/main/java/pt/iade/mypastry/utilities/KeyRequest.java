package pt.iade.mypastry.utilities;

import android.util.Log;

import com.google.gson.Gson;

import java.net.URL;
import java.util.HashMap;

import pt.iade.mypastry.models.results.ConfirmKeyResponse;

public class KeyRequest {
    public static void GetKey(String email, KeyRequestResult result) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/mails/key"));

                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("email", email);

                    String response = request.performGetRequest(param);

                    ConfirmKeyResponse keyResponse = new Gson().fromJson(response, ConfirmKeyResponse.class);
                    result.result(keyResponse);

                } catch (Exception e){
                    Log.e("KeyRequest.GetKey", e.toString());
                }
            }
        });
        thread.start();
    }

    public interface KeyRequestResult{
        public void result(ConfirmKeyResponse response);
    }
}
