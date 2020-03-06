package presenter.home;

import android.util.Log;

import base.BasePresenter;
import constan.NetResponse;
import interfaces.home.HomeConstract;
import model.HttpManager;
import model.bean.HomeBean;
import utils.RxUtils;

public class HomPresenter extends BasePresenter<HomeConstract.View> implements HomeConstract.Persenter {

    @Override
    public void getHomeData() {
       addSubscribe( HttpManager.getInstance().getApiService().getHome()
               .compose(RxUtils.<HomeBean>rxScheduler())
               .subscribeWith(new NetResponse<HomeBean>(mView) {
                   @Override
                   public void onNext(HomeBean homeBean) {
                       Log.i("tag:","onNext=============> "+homeBean.toString());
                       mView.getHomeDataReturn(homeBean);
                   }
               }));
    }
}
