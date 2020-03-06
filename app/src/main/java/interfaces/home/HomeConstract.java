package interfaces.home;

import interfaces.IBasePresenter;
import interfaces.IBaseView;
import model.bean.HomeBean;

public interface HomeConstract {

    interface  View extends IBaseView{
        void getHomeDataReturn(HomeBean result);
    }

    interface Persenter extends IBasePresenter<View>{
        void getHomeData();
    }
}
