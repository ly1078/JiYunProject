package constan;

import interfaces.IBaseView;
import io.reactivex.subscribers.ResourceSubscriber;

public abstract class NetResponse<T> extends ResourceSubscriber<T> {
    IBaseView iBaseViewl;

    public NetResponse(IBaseView iBaseViewl) {
        this.iBaseViewl = iBaseViewl;
    }
    @Override
    public void onError(Throwable t) {

        iBaseViewl.showLog(t.getMessage());
    }
    @Override
    public void onComplete() {

    }
}
