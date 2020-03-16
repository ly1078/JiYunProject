package interfaces.carinfo;

import interfaces.IBasePresenter;
import interfaces.IBaseView;
import interfaces.goodsdetail.GoodsDetailConstract;
import model.bean.car.AddCarBean;
import model.bean.car.CarInfo;
import model.bean.special.RelatedBean;

public interface CarInfoConstract {

    interface  View extends IBaseView {
        void getCarInfoResult(CarInfo carInfo);
    }
    interface Persenter extends IBasePresenter<CarInfoConstract.View> {
        void getCarInfo();

    }

}
