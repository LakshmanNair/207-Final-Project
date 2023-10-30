import java.io.*;
import okhttp3.*;

public class main {
    public static void main(String []args) throws IOException{
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "{body}");

        Request request = new Request.Builder()
                .url("https://api.apilayer.com/image_upload/url?url=https%3A%2F%2Fmedia.istockphoto.com%2Fid%2F1322277517%2Fphoto%2Fwild-grass-in-the-mountains-at-sunset.jpg%3Fs%3D612x612%26w%3D0%26k%3D20%26c%3D6mItwwFFGqKNKEAzv0mv6TaxhLN3zSE43bWmFN--J5w%3D&delay=1")
                .addHeader("apikey", "y5S25XPsoAv0v8WuAoe83ZslYariXXsX")
                .method("POST", body})
            .build();
    Response response = client.newCall(request).execute();
    System.out.println(response.body().string());
}
}