package interfaces.login;

import interfaces.IBasePresenter;
import interfaces.IBaseView;
import model.bean.LoginBean;

public interface LoginConstract {

    interface View extends IBaseView{
        void getLoginData(LoginBean loginBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getLogin(String name,String paw);
    }

}
