package vandy.mooc.presenter;

import android.net.Uri;
import vandy.mooc.common.ContextView;

/**
 * Created by josephli on 11/9/15.
 */
public interface ImageEffectProcessor {
    public Uri applyEffect(ContextView ctxView, Uri srcUri, Uri directoryPathname);
}
