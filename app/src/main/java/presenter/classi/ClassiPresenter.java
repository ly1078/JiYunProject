package presenter.classi;

import base.BasePresenter;
import constan.NetResponse;
import interfaces.topic.ClassiConstract;
import model.HttpManager;
import model.bean.HomeBean;
import model.bean.TopicBean;
import utils.RxUtils;

public class ClassiPresenter extends BasePresenter<ClassiConstract.View> implements ClassiConstract.Persenter{

    @Override
    public void getTopicData(int page, int size) {

       addSubscribe( HttpManager.getInstance().getApiService().getTopic(page,size)
               .compose(RxUtils.<TopicBean>rxScheduler())
               .subscribeWith(new NetResponse<TopicBean>(mView) {
                   @Override
                   public void onNext(TopicBean homeBean) {
                       mView.getTopicDataReturn(homeBean);
                   }
               }));
    }
}
