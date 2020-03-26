package fragments;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activity.SuerBuyActivity;
import com.example.jiyunproject.R;
import java.util.ArrayList;
import adapter.car.CarAdapter;
import base.BaseFragment;
import interfaces.carinfo.CarInfoConstract;
import model.bean.car.CarInfo;
import model.bean.car.DelCarInfo;
import presenter.carinfo.CarInfoPresenter;
import utils.ShowToast;

/**
 * 购物车
 */
public class ShoppingCarFragment extends BaseFragment<CarInfoConstract.Persenter> implements CarInfoConstract.View, View.OnClickListener {
    private RecyclerView recycler_car;
    private CarAdapter carAdapter;
    private ArrayList<CarInfo.DataBean.CartListBean> list;
    private CheckBox cb_all;
    private TextView tv_all_price;
    private TextView tv_edit_car;
    private Button btn_press;
    private ArrayList<CarInfo.DataBean.CartListBean> press_cbCheck;

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(View v) {
        recycler_car = v.findViewById(R.id.car_recycler);
        recycler_car.setLayoutManager(new LinearLayoutManager(context));
        list = new ArrayList();
        carAdapter = new CarAdapter(context, list);
        recycler_car.setAdapter(carAdapter);

        btn_press = v.findViewById(R.id.btn_press);
        cb_all = v.findViewById(R.id.cb_all);
        tv_all_price = v.findViewById(R.id.tv_all_price);
        tv_edit_car = v.findViewById(R.id.tv_edit_car);

        btn_press.setOnClickListener(this);
        tv_edit_car.setOnClickListener(this);
        cb_all.setOnClickListener(this);

        tv_all_price.setText("￥"+0);
        carAdapter.setCheckCb(new CarAdapter.CheckCb() {
            @Override
            public void setCbAllNumber() {
                showCheckNumber(cb_all,null);
            }
        });
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
        Log.i("tag", "====> " + carInfo);
        carAdapter.refreshAdapter((ArrayList<CarInfo.DataBean.CartListBean>) carInfo.getData().getCartList());
        carAdapter.setCarInfo(carInfo);
    }

    @Override
    public void delCarInfoResult(DelCarInfo delCarInfo) {
        if(delCarInfo.getErrno() == 0){
            ShowToast.show("删除成功");
            //list.remove(press_cbCheck.get(i));
            //刷新购物车

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getCarInfo();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_edit_car:
                /*int count = 0;
                ArrayList<CarInfo.DataBean.CartListBean> cbCheck = carAdapter.getCbCheck();
                int sum = 0;
                for (int i = 0; i <cbCheck.size() ; i++) {
                    sum+= cbCheck.get(i).getRetail_price()*cbCheck.get(i).getNumber();
                    count+=cbCheck.get(i).getNumber();
                }*/

                String s = tv_edit_car.getText().toString();
                carAdapter.setItemChange(s);
                if("编辑".equals(s)){

                    tv_all_price.setVisibility(View.INVISIBLE);
                    tv_edit_car.setText("完成");
                    btn_press.setText("删除所选");
                }
                if("完成".equals(s)){

                    showCheckNumber(cb_all,tv_all_price);
                    tv_all_price.setVisibility(View.VISIBLE);
                    btn_press.setText("下单");
                    tv_edit_car.setText("编辑");
                }

                break;

            case R.id.btn_press:
                press_cbCheck = carAdapter.getCbCheck();
                String s1 = btn_press.getText().toString();
                if("下单".equals(s1)){

                    ArrayList<CarInfo.DataBean.CartListBean> cbCheck = carAdapter.getCbCheck();
                    Intent intent = new Intent(context, SuerBuyActivity.class);
                    intent.putExtra("getAll",cbCheck);
                    startActivity(intent);
                    /*for (int i = 0; i < press_cbCheck.size(); i++) {
                        // 要购买的商品
                        CarInfo.DataBean.CartListBean cartListBean = press_cbCheck.get(i);
                        // 下单确认
                    }*/
                }
                if("删除所选".equals(s1)){
                    for (int i = 0; i < press_cbCheck.size(); i++) {
                        CarInfo.DataBean.CartListBean cartListBean = press_cbCheck.get(i);
                        presenter.delCarInfo(cartListBean.getProduct_id());
                        list.remove(press_cbCheck.get(i));
                    }
                    carAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.cb_all: // 全选
                boolean checked = cb_all.isChecked();
                carAdapter.addAllInfo(checked);
                showCheckNumber(cb_all,null);
                break;
        }
    }
    public void showCheckNumber(CheckBox cb_all,TextView tv_all_price){
        int count = 0;
        ArrayList<CarInfo.DataBean.CartListBean> cbCheck = carAdapter.getCbCheck();
        int sum = 0;
        for (int i = 0; i <cbCheck.size() ; i++) {
            sum+= cbCheck.get(i).getRetail_price()*cbCheck.get(i).getNumber();
            count+=cbCheck.get(i).getNumber();
        }
        if(tv_all_price!=null)
        tv_all_price.setText("￥"+sum);
        if(cb_all!=null)
        cb_all.setText("全选("+count+")");
    }
}
