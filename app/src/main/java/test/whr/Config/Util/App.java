package test.whr.Config.Util;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context context;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static DatabaseHelper dbHelper;
    private static App singleton;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;
        dbHelper = new DatabaseHelper();
        DatabaseManager.initializeInstance(dbHelper);
    }

    public static Context getAppContext() {
        return App.context;
    }

    public static final String TAG = App.class
            .getSimpleName();


    public static synchronized App getInstance() {
        return singleton;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return requestQueue;
    }



    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
