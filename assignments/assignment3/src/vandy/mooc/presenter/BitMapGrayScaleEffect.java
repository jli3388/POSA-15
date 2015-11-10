package vandy.mooc.presenter;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import vandy.mooc.MVP;
import vandy.mooc.common.BitmapUtils;
import vandy.mooc.common.ContextView;

import java.io.File;

/**
 * Created by josephli on 11/9/15.
 */
public class BitMapGrayScaleEffect {
    private static final String TAG = BitMapGrayScaleEffect.class.getSimpleName();


    public Uri applyEffect(ContextView ctxView, Uri srcUri, Uri directoryPathname) {
        Log.d(TAG, "applyEffect start");
        final File file = new File(srcUri.toString());
        Log.d(TAG, String.format("exist %b - %s", file.exists(), srcUri.toString()));
        final Uri uri = BitmapUtils.grayScaleFilter(ctxView.getActivityContext(), srcUri, directoryPathname);
        Log.d(TAG, String.format("binmapGrayScaleEffect returning %s", uri.toString()));
        return uri;
    }
}