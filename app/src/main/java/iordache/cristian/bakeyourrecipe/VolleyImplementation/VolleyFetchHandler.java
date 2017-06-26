package iordache.cristian.bakeyourrecipe.VolleyImplementation;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by cii51253 on 01/06/2017.
 */

public class VolleyFetchHandler {

    private static VolleyFetchHandler mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;

    //Constructor for VolleyFetchHandler
    private VolleyFetchHandler(Context context){
        mContext = context;
        requestQueue = getRequestQueue();
    }

    //Initiliaze variable requestQueue
    public RequestQueue getRequestQueue(){

        if (requestQueue == null) {

            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return requestQueue;
    }

    //Returns an instance of the VolleyFetchHandler class
    public static  synchronized  VolleyFetchHandler getmInstance(Context context){

        if (mInstance == null) {

            mInstance = new VolleyFetchHandler(context);
        }

        return mInstance;
    }

    //Adds each request to the requestQueue
    public<T> void addToRequesQueue(Request<T> request){

        requestQueue.add(request);
    }


}
