package interfaces;

public interface IBasePresenter<T extends IBaseView>{

    void onAttachView(T view);
    void unAttachView();
}
