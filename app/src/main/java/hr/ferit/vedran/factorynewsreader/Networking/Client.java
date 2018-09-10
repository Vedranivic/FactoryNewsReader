package hr.ferit.vedran.factorynewsreader.Networking;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vedra on 9.9.2018..
 */

public class Client {
    public static Retrofit retrofit = null;

    private static Cache provideCache(Context context){
        Cache cache = null;
        try {
            cache = new Cache(new File(context.getCacheDir(),
                    "feed-cache"),
                    Constants.cacheSize);
        } catch (Exception e) {
            Log.e(e.toString(), "Could not create Cache!");
        }
        return cache;
    }

    private static Interceptor provideCacheInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());

            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(5, TimeUnit.MINUTES)
                    .build();

            return response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-control", cacheControl.toString())
                    .build();
        };
    }

    private static Interceptor provideOfflineCacheInterceptor(Context context) {
        return chain -> {
            Request request = chain.request();

            if (!Utils.IsNetworkConnected(context)) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(1, TimeUnit.DAYS)
                        .build();

                request = request.newBuilder()
                        .removeHeader("Pragma")
                        .cacheControl(cacheControl)
                        .build();
            }

            return chain.proceed(request);
        };
    }

    public static Retrofit getClient(String baseURL, final Context context){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(provideOfflineCacheInterceptor(context))
                .addNetworkInterceptor(provideCacheInterceptor())
                .cache(provideCache(context))
                .build();

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
