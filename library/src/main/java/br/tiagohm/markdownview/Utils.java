package br.tiagohm.markdownview;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {
    private Utils() {
    }

    public static String getStringFromInputStream(InputStream is)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos.toString("UTF-8");
    }

    public static String getStringFromAssetFile(AssetManager asset, String filename) {
        InputStream is = null;

        try {
            is = asset.open(filename);
            String string = getStringFromInputStream(is);
            Log.i("zestinc", "string = " + string);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getStringFromContentUri(Context context, Uri uri) {
        InputStream is = null;

        try {
            is = context.getContentResolver().openInputStream(uri);
            String string = getStringFromInputStream(is);
            Log.i("zestinc", "string = " + string);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getStringFromFile(File file) {
        InputStream is = null;

        try {
            is = new FileInputStream(file);
            return getStringFromInputStream(is);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
