package base;

import java.lang.ref.WeakReference;

import interfaces.IBasePresenter;
import interfaces.IBaseView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    public V mView;
    private WeakReference<V> weakReference;

    //rxjava2被压式处理内存
    CompositeDisposable compositeDisposable;

    @Override
    public void onAttachView(V view) {
        weakReference = new WeakReference(view);
        mView = weakReference.get();
    }
    @Override
    public void unAttachView() {
        if(mView !=null){
            mView = null;
        }
    }
    /**
     * 添加请求数据的对象到被压式管理compositeDisposable
     * @param disposable
     */
    protected void addSubscribe(Disposable disposable){
        if(compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

}
