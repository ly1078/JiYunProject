package presenter.carinfo;

import base.BasePresenter;
import constan.NetResponse;
import interfaces.carinfo.CarInfoConstract;
import model.HttpManager;
import model.bean.car.CarInfo;
import model.bean.car.DelCarInfo;
import utils.RxUtils;

public class CarInfoPresenter extends BasePresenter<CarInfoConstract.View> implements CarInfoConstract.Persenter {
    @Override
    public void getCarInfo() {

        addSubscribe(HttpManager.getInstance().getApiService().getCarInfo()
            .compose(RxUtils.rxScheduler())
                .subscribeWith(new NetResponse<CarInfo>(mView) {
                    @Override
                    public void onNext(CarInfo carInfo) {
                        mView.getCarInfoResult(carInfo);
                    }
                })
        );

    }

    @Override
    public void delCarInfo(int productIds) {
        addSubscribe(HttpManager.getInstance().getApiService().delCarInfo(productIds)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new NetResponse<DelCarInfo>(mView) {
                    @Override
                    public void onNext(DelCarInfo delCarInfo) {
                        mView.delCarInfoResult(delCarInfo);
                    }
                })
        );
    }
}
