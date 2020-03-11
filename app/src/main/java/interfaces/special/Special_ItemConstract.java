package interfaces.special;

import interfaces.IBasePresenter;
import interfaces.IBaseView;
import model.bean.SpecialLeadData;
import model.bean.special.SpecialItemShowData;
import model.bean.special.SpecialTabLeadList;

public interface Special_ItemConstract {

    interface View extends IBaseView {
        void returnSpeciaItemLeadResult(SpecialTabLeadList tabLeadList);
        void returnSpeciaItemDataResult(SpecialItemShowData itemShowData);
    }

    interface Presenter extends IBasePresenter<Special_ItemConstract.View> {
        void getSpeciaItemLead(int id);
        void getSpeciaItemListData(int categoryId,int page,int size);
    }

}
