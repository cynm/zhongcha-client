package com.chengying.root.tools;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.util.Map;

/**
 * Created by root on 16-1-10.
 */
public class ImageShowTask extends AsyncTask<Void,Void,Boolean> {

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     * this programe is write to soulve the image download and show ,if it download before, no need download again.
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    public static Map<String,Bitmap> local;
    public ImageShowTask(ImageView imageView,String url){


    }
    @Override
    protected Boolean doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {

    }
}
