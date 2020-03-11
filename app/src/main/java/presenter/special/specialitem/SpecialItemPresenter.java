package presenter.special.specialitem;

import base.BasePresenter;
import constan.NetResponse;
import interfaces.special.Special_ItemConstract;
import model.HttpManager;
import model.bean.special.SpecialItemShowData;
import model.bean.special.SpecialTabLeadList;
import utils.RxUtils;

public class SpecialItemPresenter extends BasePresenter<Special_ItemConstract.View> implements Special_ItemConstract.Presenter{
    @Override
    public void getSpeciaItemLead(int id) {
        addSubscribe(HttpManager.getInstance().getApiServiceII().getSpecialItemLeadData(id)
            .compose(RxUtils.rxScheduler())
                .subscribeWith(new NetResponse<SpecialTabLeadList>(mView) {
                    @Override
                    public void onNext(SpecialTabLeadList tabLeadList) {
                        mView.returnSpeciaItemLeadResult(tabLeadList);
                    }
                })
        );
    }

    @Override
    public void getSpeciaItemListData(int categoryId, int page, int size) {
        addSubscribe(HttpManager.getInstance().getApiService().getSpecialItemData(categoryId,page,size)
            .compose(RxUtils.rxScheduler())
                .subscribeWith(new NetResponse<SpecialItemShowData>(mView) {
                    @Override
                    public void onNext(SpecialItemShowData itemShowData) {
                        mView.returnSpeciaItemDataResult(itemShowData);
                    }
                }));
    }
}
