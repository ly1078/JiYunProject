package interfaces.goodsdetail;

import interfaces.IBasePresenter;
import interfaces.IBaseView;
import interfaces.home.HomeConstract;
import model.bean.HomeBean;
import model.bean.special.RelatedBean;

public interface GoodsDetailConstract {

    interface  View extends IBaseView {
        void getGoodsDetailResult(RelatedBean relatedBean);
    }

    interface Persenter extends IBasePresenter<GoodsDetailConstract.View> {
        void getGoodsDetail(int id);
    }

}
