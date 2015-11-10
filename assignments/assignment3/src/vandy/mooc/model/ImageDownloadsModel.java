package vandy.mooc.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import vandy.mooc.MVP;

import java.io.*;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class plays the "Model" role in the Model-View-Presenter (MVP)
 * pattern by defining an interface for providing data that will be
 * acted upon by the "Presenter" and "View" layers in the MVP pattern.
 * It implements the MVP.ProvidedModelOps so it can be created/managed
 * by the GenericPresenter framework.
 */
public class ImageDownloadsModel
       implements MVP.ProvidedModelOps {
    /**
     * Debugging tag used by the Android logger.
     */
    protected final static String TAG =
        ImageDownloadsModel.class.getSimpleName();

    /**
     * A WeakReference used to access methods in the Presenter layer.
     * The WeakReference enables garbage collection.
     */
    private WeakReference<MVP.RequiredPresenterOps> mPresenter;

    /**
     * Hook method called when a new instance of AcronymModel is
     * created.  One time initialization code goes here, e.g., storing
     * a WeakReference to the Presenter and initializing the sync and
     * async Services.
     *
     * @param presenter
     *            A reference to the Presenter layer.
     */
    @Override
    public void onCreate(MVP.RequiredPresenterOps presenter) {
        // Set the WeakReference.
        mPresenter =
            new WeakReference<>(presenter);
    }

    /**
     * Hook method called to shutdown the Model layer.
     *
     * @param isChangingConfigurations
     *        True if a runtime configuration triggered the onDestroy() call.
     */
    @Override
    public void onDestroy(boolean isChangingConfigurations) {
        // No-op.
    }

    /**
     * Download the image located at the provided Internet url using
     * the URL class, store it on the android file system using a
     * FileOutputStream, and return the path to the image file on
     * disk.
     *
     * @param context
     *          The context in which to write the file.
     * @param url
     *          The URL of the image to download.
     * @param directoryPathname
     *          Pathname of the directory to write the file.
     *
     * @return
     *        Absolute path to the downloaded image file on the file
     *        system.
     */
    public Uri downloadImage(Context context,
                             Uri url,
                             Uri directoryPathname) {
        // @@ TODO -- You fill in here, replacing "null" with the appropriate code.
        InputStream inS = null;
        OutputStream outS = null;
        Bitmap bitmap = null;
        try {
            Log.d(TAG, String.format("downloadImage( context, %s, %s ) begin.", url.toString(), directoryPathname.toString()));
            inS = (InputStream) new URL(url.toString()).getContent();
            bitmap = BitmapFactory.decodeStream(inS);
            final File targetDir = new File(directoryPathname.getPath());
            if (!targetDir.exists()) {
                targetDir.mkdir();
            }
            final File tmpImgFile = File.createTempFile("tmpImg", null, targetDir);
            outS = new FileOutputStream(tmpImgFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outS);
            Log.d(TAG, String.format("downloadImage returning [%s]", tmpImgFile.toString()));
            return Uri.fromFile(tmpImgFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inS.close();
            } catch (Throwable t) {
                t.printStackTrace();
            }
            try {
                outS.close();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return null;
    }
}
