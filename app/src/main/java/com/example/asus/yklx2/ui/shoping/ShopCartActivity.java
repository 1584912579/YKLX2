package com.example.asus.yklx2.ui.shoping;

import android.os.Bundle;
import android.os.health.UidHealthStats;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.asus.yklx2.R;
import com.example.asus.yklx2.bean.CartsBean;
import com.example.asus.yklx2.bean.SellerBean;
import com.example.asus.yklx2.component.DaggerHttpComponent;
import com.example.asus.yklx2.module.HttpModule;
import com.example.asus.yklx2.ui.base.BaseActivity;
import com.example.asus.yklx2.ui.shoping.adapter.ElvShopcartAdapter;
import com.example.asus.yklx2.ui.shoping.contract.ShopCartContract;
import com.example.asus.yklx2.ui.shoping.presenter.ShopCartPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopCartActivity extends BaseActivity<ShopCartPresenter> implements ShopCartContract.View {

    @BindView(R.id.explv)
    ExpandableListView mExplv;
    @BindView(R.id.cbAllq)
    CheckBox mCbAllq;
    @BindView(R.id.tvMoneyq)
    TextView mTvMoneyq;
    @BindView(R.id.tvTotalq)
    TextView mTvTotalq;
    private ElvShopcartAdapter adapter;
    private String uid="14530";
    private int flag = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_shop_cart);
        ButterKnife.bind(this);
        //请求数据
        mPresenter.getCartssuess(uid);
        mCbAllq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter != null) {
                    adapter.changeAllState(mCbAllq.isChecked());
                }
            }
        });

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_shop_cart;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void getCartssuess(List<SellerBean> groupList, List<List<CartsBean.DataBean.ListBean>> childList) {
        //判断所有商家是否全部选中
        mCbAllq.setChecked(isSellerAddSelected(groupList));
        adapter = new ElvShopcartAdapter(ShopCartActivity.this,groupList,childList,mPresenter);
        mExplv.setAdapter(adapter);
        String[] strings = adapter.computeMoneyAndNum();
        mTvMoneyq.setText("总计：" + strings[0] + "元");
        mTvTotalq.setText("去结算("+strings[1]+"个)");
        //默认展开列表
        for (int i = 0; i < groupList.size(); i++) {
            mExplv.expandGroup(i);
        }


    }
    //判断所有商家是否全部选中
    private boolean isSellerAddSelected(List<SellerBean> groupList) {
        for (int i = 0; i < groupList.size(); i++) {
            SellerBean sellerBean = groupList.get(i);
            if (!sellerBean.isSelected()) {
                return false;
            }
        }
        return true;
    }
    //修改成功
    @Override
    public void getupdateCartssuess() {
        if (adapter!=null){
            adapter.updataSuccess();
        }
    }
    //
    @Override
    public void getDeleteCartsSuccess() {
        if (adapter!=null){
            adapter.delSuccess();
        }
    }
}
