package presenter.regist;

import android.widget.ImageView;

import base.BasePresenter;
import constan.NetResponse;
import interfaces.regist.RegsitConstract;
import model.HttpManager;
import model.bean.LoginBean;
import model.bean.RegistBean;
import utils.RxUtils;

public class RegistPresenter extends BasePresenter<RegsitConstract.View> implements RegsitConstract.Presenter {

    @Override
    public void getRegist(String name, String paw,String verify) {
        addSubscribe(HttpManager.getInstance().getApiService().regist(name,paw,verify)
                .compose(RxUtils.<LoginBean>rxScheduler())
                .subscribeWith(new NetResponse<LoginBean>(mView) {
                    @Override
                    public void onNext(LoginBean registBean) {
                        mView.getRegistData(registBean);
                    }
                }));
    }
}
