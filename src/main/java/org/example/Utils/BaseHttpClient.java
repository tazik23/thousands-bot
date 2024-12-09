package org.example.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;

public abstract class BaseHttpClient {
    protected final OkHttpClient client = new OkHttpClient();
    protected final Gson gson = new Gson();
}
