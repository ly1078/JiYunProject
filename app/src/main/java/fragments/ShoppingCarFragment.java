package fragments;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jiyunproject.R;

import java.util.ArrayList;

import adapter.car.CarAdapter;
import base.BaseFragment;
import interfaces.IBasePresenter;
import interfaces.IBaseView;
import interfaces.carinfo.CarInfoConstract;
import model.bean.car.CarInfo;
import presenter.carinfo.CarInfoPresenter;

/**
 * 购物车
 */
public class ShoppingCarFragment extends BaseFragment<CarInfoConstract.Persenter> implements CarInfoConstract.View {
    private RecyclerView recycler_car;
    private CarAdapter carAdapter;
    private ArrayList<CarInfo.DataBean.CartListBean> list;

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(View v) {
        recycler_car = v.findViewById(R.id.car_recycler);
        recycler_car.setLayoutManager(new LinearLayoutManager(context));
        ArrayList<CarInfo.DataBean.CartListBean> list = new ArrayList();
        carAdapter = new CarAdapter(context, list);
        recycler_car.setAdapter(carAdapter);
    }

    @Override
    protected CarInfoConstract.Persenter initPresenter() {
        return new CarInfoPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_car;
    }
    @Override
    public void getCarInfoResult(CarInfo carInfo) {
        Log.i("tag","====> "+carInfo);
        carAdapter.refreshAdapter(carInfo.getData().getCartList());
        carAdapter.setCarInfo(carInfo);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getCarInfo();
    }
}
