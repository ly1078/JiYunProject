package presenter.special.specialitem;

import base.BasePresenter;
import constan.NetResponse;
import interfaces.goodsdetail.GoodsDetailConstract;
import model.HttpManager;
import model.bean.car.AddCarBean;
import model.bean.special.RelatedBean;
import utils.RxUtils;

public class GoodsDetailPresenter extends BasePresenter<GoodsDetailConstract.View> implements GoodsDetailConstract.Persenter {
    @Override
    public void getGoodsDetail(int id) {
        addSubscribe(HttpManager.getInstance().getApiService().getGoodsDetail(id)
            .compose(RxUtils.rxScheduler())
                .subscribeWith(new NetResponse<RelatedBean>(mView) {
                    @Override
                    public void onNext(RelatedBean relatedBean) {
                            mView.getGoodsDetailResult(relatedBean);
                    }
                })
        );
    }

    @Override
    public void addCarInfo(int goodsId, int number, int productId) {
        addSubscribe(HttpManager.getInstance().getApiService().addCar(goodsId,number,productId)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new NetResponse<AddCarBean>(mView) {
                    @Override
                    public void onNext(AddCarBean carBean) {
                        mView.addCarResult(carBean);
                    }
                })
        );
    }
}
