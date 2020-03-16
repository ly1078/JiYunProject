package interfaces.goodsdetail;

import interfaces.IBasePresenter;
import interfaces.IBaseView;
import interfaces.home.HomeConstract;
import model.bean.HomeBean;
import model.bean.car.AddCarBean;
import model.bean.special.RelatedBean;

public interface GoodsDetailConstract {

    interface  View extends IBaseView {
        void getGoodsDetailResult(RelatedBean relatedBean);
        void addCarResult(AddCarBean carBean);
    }
    interface Persenter extends IBasePresenter<GoodsDetailConstract.View> {
        void getGoodsDetail(int id);
        void addCarInfo(int goodsId,int number,int productId);
    }

}
