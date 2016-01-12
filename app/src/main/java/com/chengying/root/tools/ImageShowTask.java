package com.chengying.root.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import com.chengying.root.zhongcha.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 16-1-10.
 * testted reult is ok!
 */
public class ImageShowTask extends AsyncTask<Void, Void, Boolean> {

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     * this programe is write to soulve the image download and show ,if it download before, no need download again.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    public static Map<String, Bitmap> memmory=new HashMap< >();
    public static Map<String, String> loacl=new HashMap<>();
    public Bitmap temp;
    private ImageView imageView;
    private String url;
    private Context context;

    public ImageShowTask(Context context, ImageView imageView, String url) {
        this.imageView = imageView;
        this.url = url;
    }

    public static String stringToMD5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }

    public Bitmap getNetBitMap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;

        try {
            myFileUrl = new URL(url);
            HttpURLConnection conn;

            conn = (HttpURLConnection) myFileUrl.openConnection();

            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bitmap;
    }

    private String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }

    public String saveFile(Bitmap bm, String fileName) throws IOException {
        String path = getSDPath() + "/zhongcha/";
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile.getPath();
    }

    public Bitmap getLocalBitMap(String localPath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(localPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(fis);
        return bitmap;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        //read memmory first
        if (!memmory.containsKey(url)) {

            if (loacl.containsKey(url)) {
                //is local exists it
                //load from local sd card
                temp = getLocalBitMap(loacl.get(url));


            } else {
                //download from network
                temp = getNetBitMap(url);

            }
            if (temp != null)
                memmory.put(url, temp);

        } else {
            if (!loacl.containsKey(url)) {

                try {
                    loacl.put(url, saveFile(memmory.get(url), stringToMD5(url)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        temp = memmory.get(url);
        if (temp != null)
            return true;
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean) {
            this.imageView.setImageBitmap(temp);
        } else {
              this.imageView.setImageResource(R.mipmap.example);
          //  Toast.makeText(context, "图片获取失败", Toast.LENGTH_SHORT).show();
        }
    }
}
