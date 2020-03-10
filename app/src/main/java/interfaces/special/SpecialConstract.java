package interfaces.special;

import interfaces.IBasePresenter;
import interfaces.IBaseView;
import model.bean.SpecialLeadData;

public interface SpecialConstract {

    interface View extends IBaseView {
        void returnSpeciaLeadResult(SpecialLeadData leadData);
        void returnSpeciaDataResult(SpecialLeadData listData);
    }

    interface Presenter extends IBasePresenter<SpecialConstract.View> {
        void getSpeciaLead();
        void getSpeciaListData(int id);
    }

}
