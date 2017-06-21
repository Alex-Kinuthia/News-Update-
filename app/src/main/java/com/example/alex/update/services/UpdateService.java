package com.example.alex.update.services;

import android.app.DownloadManager;

import com.example.alex.update.Constants;
import com.example.alex.update.models.Update;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by alex on 6/19/17.
 */

public class UpdateService {
    private static OkHttpClient client = new OkHttpClient();

    public static void findUpdates(String update, Callback callback){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.UPDATE_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.SOURCE_QUERY_PARAMETER, update);
        urlBuilder.addQueryParameter("apiKey", Constants.UPDATE_API_KEY);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }


   public ArrayList<Update> processResults(Response response) {
       ArrayList<Update> updates = new ArrayList<>();

       try {
          String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject updateJSON = new JSONObject(jsonData);
                JSONArray dataJSON = updateJSON.getJSONArray("articles");
                for (int i = 0; i < dataJSON.length(); i++) {
                    JSONObject updataryJSON = dataJSON.getJSONObject(i);
                    String author = updataryJSON.getString("author");
                    String title = updataryJSON.optString("title");
                    String description = updataryJSON.optString("description");
                    String url = updataryJSON.optString("url");
                    String imageUrl = updataryJSON.optString("urlToImage");
                    String publishedAt = updataryJSON.optString("publishedAt");
                    Update update = new Update( author, title, description, url , imageUrl, publishedAt);
//                    if (!imageUrl.isEmpty()){
//                        beer.setImageUrl(imageUrl);
//                    }
                    updates.add(update);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
       }
        return updates;
   }
}

