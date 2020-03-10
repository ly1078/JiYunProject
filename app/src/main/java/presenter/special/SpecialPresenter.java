package presenter.special;

import base.BasePresenter;
import constan.NetResponse;
import interfaces.special.SpecialConstract;
import model.HttpManager;
import model.bean.SpecialLeadData;
import utils.RxUtils;

public class SpecialPresenter extends BasePresenter<SpecialConstract.View> implements SpecialConstract.Presenter {

    @Override
    public void getSpeciaLead() {
        addSubscribe(HttpManager.getInstance().getApiService().getSpecialLead()
                .compose(RxUtils.<SpecialLeadData>rxScheduler())
                .subscribeWith(new NetResponse<SpecialLeadData>(mView) {
                    @Override
                    public void onNext(SpecialLeadData leadData) {
                        mView.returnSpeciaLeadResult(leadData);
                    }
                }));
    }

    @Override
    public void getSpeciaListData(int id) {
        addSubscribe(HttpManager.getInstance().getApiService().getSpecialData(id)
                .compose(RxUtils.<SpecialLeadData>rxScheduler())
                .subscribeWith(new NetResponse<SpecialLeadData>(mView) {
                    @Override
                    public void onNext(SpecialLeadData listData) {
                        mView.returnSpeciaDataResult(listData);
                    }
                }));
    }

    /*@Override
    public void getRegist(String name, String paw,String verify) {
        addSubscribe(HttpManager.getInstance().getApiService().regist(name,paw,verify)
                .compose(RxUtils.<LoginBean>rxScheduler())
                .subscribeWith(new NetResponse<LoginBean>(mView) {
                    @Override
                    public void onNext(LoginBean registBean) {
                        mView.getRegistData(registBean);
                    }
                }));
    }*/
}
