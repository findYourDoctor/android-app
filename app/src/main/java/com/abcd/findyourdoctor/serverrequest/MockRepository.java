package com.abcd.findyourdoctor.serverrequest;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;

public class MockRepository {
    private static final MockRepository ourInstance = new MockRepository();

    public static MockRepository getInstance() {
        return ourInstance;
    }

    private MockRepository() {
    }

    public  <T> T getData(Context context, Class<T> tClass, String fileName) {
        String apiRes = loadJSONFromAsset(context, fileName);

        Gson gson = new Gson();

        return gson.fromJson(apiRes, tClass);

    }

    public String loadJSONFromAsset(Context context, String jsonFileName) {
        //get_app_data.json
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
