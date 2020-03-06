package interfaces.regist;

import interfaces.IBasePresenter;
import interfaces.IBaseView;
import interfaces.login.LoginConstract;
import model.bean.LoginBean;
import model.bean.RegistBean;

public interface RegsitConstract {

    interface View extends IBaseView {
        void getRegistData(LoginBean registBean);
    }

    interface Presenter extends IBasePresenter<RegsitConstract.View> {
        void getRegist(String name,String paw,String verify);
    }

}
