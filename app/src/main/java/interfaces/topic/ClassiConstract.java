package interfaces.topic;

import interfaces.IBasePresenter;
import interfaces.IBaseView;
import model.bean.TopicBean;

public interface ClassiConstract {

    interface  View extends IBaseView {
        void getTopicDataReturn(TopicBean result);
    }

    interface Persenter extends IBasePresenter<ClassiConstract.View> {
        void getTopicData(int page,int size);
    }

}
