package httpclient;

import okhttp3.*;

/**
 * OkHttp
 * http://square.github.io/okhttp/
 * <p>
 * Created by medusar on 10/11/16.
 */
public class OkHttp {

    // singleton
    private static final OkHttpClient client = new OkHttpClient();

    public static String get(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String post(String url, String data, String contentType) throws Exception {
        RequestBody body = RequestBody.create(MediaType.parse(contentType), data);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static void main(String[] args) throws Exception {
        String response = get("http://www.baidu.com");
        System.out.println(response);

        System.out.println(post("https://www.baidu.com/s", "wd=java", "application/x-www-form-urlencoded"));
    }
}
