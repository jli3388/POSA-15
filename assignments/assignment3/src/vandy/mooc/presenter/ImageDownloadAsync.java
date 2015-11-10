package vandy.mooc.presenter;

import android.net.Uri;
import android.os.AsyncTask;
import vandy.mooc.model.ImageDownloadsModel;

/**
 * Created by josephli on 11/9/15.
 */
public class ImageDownloadAsync extends AsyncTask<ImageDownloadAndProcessUnit, Integer, ImageDownloadAndProcessUnit> {
    private static final String TAG = ImageDownloadAsync.class.getSimpleName();


    @Override
    protected ImageDownloadAndProcessUnit doInBackground(ImageDownloadAndProcessUnit... imageDwnldUnit) {
        final ImageDownloadAndProcessUnit unit = imageDwnldUnit[0];
        final ImageDownloadsModel imageDownloadsModel = new ImageDownloadsModel();
        Uri downloadedBitmapUri = imageDownloadsModel.downloadImage(unit.getRequiredPresenterOps().getActivityContext(),
                unit.getSrcUrl(), unit.getDirectoryPath());

        for (ImageEffectProcessor imageEffectProcessor : imageDwnldUnit[0].getEffectProcessorLst()) {
            downloadedBitmapUri = imageEffectProcessor.applyEffect(unit.getRequiredPresenterOps(), downloadedBitmapUri, unit.getDirectoryPath());
        }
        unit.setOutUri(downloadedBitmapUri);
        return unit;
    }

    @Override
    protected void onPostExecute(ImageDownloadAndProcessUnit unit) {
        super.onPostExecute(unit);
        unit.getRequiredPresenterOps().onProcessingComplete(unit.getSrcUrl(), unit.getOutUri());
    }
};
