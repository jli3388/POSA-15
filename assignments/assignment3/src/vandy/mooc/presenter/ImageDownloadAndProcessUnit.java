package vandy.mooc.presenter;

import android.net.Uri;
import vandy.mooc.MVP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josephli on 11/9/15.
 */
public class ImageDownloadAndProcessUnit {
    private Uri directoryPath;
    private Uri srcUrl;
    private Uri outUri;
    private List<ImageEffectProcessor> effectProcessorLst = new ArrayList<>();
    private MVP.RequiredPresenterOps requiredPresenterOps;

    public ImageDownloadAndProcessUnit(MVP.RequiredPresenterOps requiredPresenterOps, Uri srcUrl, Uri directoryPath) {
        this.requiredPresenterOps = requiredPresenterOps;
        this.srcUrl = srcUrl;
        this.directoryPath = directoryPath;
    }

    public Uri getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(Uri srcUrl) {
        this.srcUrl = srcUrl;
    }

    public Uri getOutUri() {
        return outUri;
    }

    public void setOutUri(Uri outUri) {
        this.outUri = outUri;
    }

    public void addToEffectProcessorLst(ImageEffectProcessor effectProcessor) {
        this.effectProcessorLst.add(effectProcessor);
    }

    public List<ImageEffectProcessor> getEffectProcessorLst() {
        return effectProcessorLst;
    }

    public MVP.RequiredPresenterOps getRequiredPresenterOps() {
        return requiredPresenterOps;
    }

    public void setRequiredPresenterOps(MVP.RequiredPresenterOps requiredPresenterOps) {
        this.requiredPresenterOps = requiredPresenterOps;
    }

    public Uri getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(Uri directoryPath) {
        this.directoryPath = directoryPath;
    }
}
