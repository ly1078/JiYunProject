package presenter.login;

import base.BasePresenter;
import constan.NetResponse;
import interfaces.IBasePresenter;
import interfaces.IBaseView;
import interfaces.login.LoginConstract;
import model.HttpManager;
import model.bean.LoginBean;
import utils.RxUtils;
import utils.ShowToast;

public class LoginPresenter extends BasePresenter<LoginConstract.View> implements LoginConstract.Presenter {

    @Override
    public void getLogin(String name, String paw) {

        addSubscribe(HttpManager.getInstance().getApiService().login(name,paw)
                .compose(RxUtils.<LoginBean>rxScheduler())
                .subscribeWith(new NetResponse<LoginBean>(mView) {
                    @Override
                    public void onNext(LoginBean r) {
                        if(r.getErrno() == 0) {
                            mView.getLoginData(r);
                        }else{
                            ShowToast.show(r.getErrmsg());
                        }
                    }
                }));
    }
}
