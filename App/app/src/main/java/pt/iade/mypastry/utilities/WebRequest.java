package pt.iade.mypastry.utilities;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * Abstracts away a lot of the headaches with HTTP requests on Android.
 *
 * @author Nathan Campos <nathan@innoveworkshop.com>
 */
public class WebRequest {
    public final static String LOCALHOST = "http://10.0.2.2:8080";

    protected URL url;

    /**
     * Creates a new web request object.
     *
     * @param url URL of our request.
     */
    public WebRequest(URL url) {
        this.url = url;
    }

    /**
     * Performs a simple GET request.
     *
     * @return Raw response from the web server.
     */
    public String performGetRequest() throws IOException, URISyntaxException {
        return performGetRequest(null);
    }

    /**
     * Performs a GET request and gets the response from the server as a String.
     *
     * @param params URL parameters.
     *
     * @return Raw response from the web server.
     */
    public String performGetRequest(HashMap<String, String> params) throws IOException, URISyntaxException {
        Log.i("WebRequest", "Sending GET to " + url);
        URI uri = buildUri(params);
        HttpURLConnection urlConnection = (HttpURLConnection) uri.toURL().openConnection();
        String result = null;
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = readStreamToString(in);
            Log.i("WebRequest-Response", result);
        } finally {
            urlConnection.disconnect();
        }

        return result;
    }

    /**
     * Performs a POST request with a plain text body.
     *
     * @param params URL parameters.
     * @param body Raw request body.
     * @param contentType MIME type of the body content.
     *
     * @return Raw response from the web server.
     */
    public String performPostRequest(HashMap<String, String> params, String body, String contentType) throws IOException, URISyntaxException {
        byte[] postData = body.getBytes(StandardCharsets.UTF_8);

        Log.i("WebRequest", "Sending POST to " + url);
        Log.i("WebRequest-Body", body);
        URI uri = buildUri(params);
        HttpURLConnection urlConnection = (HttpURLConnection) uri.toURL().openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", contentType);
        urlConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
        urlConnection.setUseCaches(false);
        urlConnection.setDoOutput(true);

        // Send request body.
        OutputStream os = urlConnection.getOutputStream();
        os.write(postData, 0, postData.length);
        os.flush();

        // Get request response.
        InputStream is = new BufferedInputStream(urlConnection.getInputStream());
        String result = readStreamToString(is);
        Log.i("WebRequest-Response", result);

        os.close();
        is.close();

        return result;
    }

    /**
     * Performs a POST request with a JSON body.
     *
     * @param params URL parameters.
     * @param obj JSON serializable object.
     *
     * @return Raw response from the web server.
     */
    public String performPostRequest(HashMap<String, String> params, Serializable obj) throws IOException, URISyntaxException {
        return performPostRequest(params, new Gson().toJson(obj), "application/json");
    }

    /**
     * Performs a POST request with a JSON body.
     *
     * @param obj JSON serializable object.
     *
     * @return Raw response from the web server.
     */
    public String performPostRequest(Serializable obj) throws IOException, URISyntaxException {
        return performPostRequest(null, obj);
    }

    /**
     * Performs a POST request with an URL-encoded body.
     *
     * @param params URL parameters.
     * @param bodyParams Body form parameters.
     *
     * @return Raw response from the web server.
     */
    public String performPostRequest(HashMap<String, String> params, HashMap<String, String> bodyParams) throws IOException, URISyntaxException {
        String body = null;
        for (String key : bodyParams.keySet()) {
            if (body == null) {
                body = key + "=" + URLEncoder.encode(bodyParams.get(key), StandardCharsets.UTF_8.toString());
            } else {
                body += "&" + key + "=" + URLEncoder.encode(bodyParams.get(key), StandardCharsets.UTF_8.toString());
            }
        }

        return performPostRequest(params, body, "application/x-www-form-urlencoded");
    }

    /**
     * Performs a POST request with an URL-encoded body.
     *
     * @param bodyParams Body form parameters.
     *
     * @return Raw response from the web server.
     */
    public String performPostRequest(HashMap<String, String> bodyParams) throws IOException, URISyntaxException {
        return performPostRequest(null, bodyParams);
    }


    /**
     * Performs a simple DELETE request.
     *
     * @return Raw response from the web server.
     */
    public String performDeleteRequest() throws IOException, URISyntaxException {
        return performDeleteRequest(null);
    }

    /**
     * Performs a DELETE request and gets the response from the server as a String.
     *
     * @param params URL parameters.
     *
     * @return Raw response from the web server.
     */
    public String performDeleteRequest(HashMap<String, String> params) throws IOException, URISyntaxException {
        Log.i("WebRequest", "Sending DELETE to " + url);
        URI uri = buildUri(params);
        HttpURLConnection urlConnection = (HttpURLConnection) uri.toURL().openConnection();
        urlConnection.setRequestMethod("DELETE");

        String result = null;
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = readStreamToString(in);
            Log.i("WebRequest-Response", result);
        } finally {
            urlConnection.disconnect();
        }

        return result;
    }

    protected String readStreamToString(InputStream in) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        for (int result = bis.read(); result != -1; result = bis.read()) {
            buf.write((byte) result);
        }

        return buf.toString("UTF-8");
    }

    protected URI buildUri(HashMap<String, String> params) throws IOException, URISyntaxException {
        URI uri = new URI(url.toString());

        if (params != null) {
            String query = uri.getQuery();
            for (String key : params.keySet()) {
                if (query == null) {
                    //query = key + "=" + URLEncoder.encode(params.get(key), StandardCharsets.UTF_8.toString());
                    query = key + "=" + params.get(key);
                } else {
                    //query += "&" + key + "=" + URLEncoder.encode(params.get(key), StandardCharsets.UTF_8.toString());
                    query += "&" + key + "=" + params.get(key);
                }
            }

            uri = new URI(uri.getScheme(), uri.getAuthority(),
                    uri.getPath(), query, uri.getFragment());
        }

        return uri;
    }
}