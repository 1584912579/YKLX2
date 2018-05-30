package com.example.asus.yklx2.ui.Details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.yklx2.R;
import com.example.asus.yklx2.bean.BaseBean;
import com.example.asus.yklx2.bean.DatailsBean;
import com.example.asus.yklx2.component.DaggerHttpComponent;
import com.example.asus.yklx2.module.HttpModule;
import com.example.asus.yklx2.ui.Details.contract.DetailsContract;
import com.example.asus.yklx2.ui.Details.presenter.DetailsPresenter;
import com.example.asus.yklx2.ui.base.BaseActivity;
import com.example.asus.yklx2.ui.shoping.ShopCartActivity;
import com.example.asus.yklx2.utils.MyApp;
import com.example.asus.yklx2.utils.MyApp2;
import com.youth.banner.Banner;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListDetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsContract.View {

    @BindView(R.id.lift2)
    ImageView mLift;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.tvPrice)
    TextView mTvPrice;
    @BindView(R.id.tvVipPrice)
    TextView mTvVipPrice;
    @BindView(R.id.tvShopCard)
    TextView mTvShopCard;
    @BindView(R.id.tvAddCard)
    TextView mTvAddCard;
    private DatailsBean.DataBean data;
    private final String uid = "14530";
    private final String token = "A753C3EF1D5CD17396618D2DF49D1544";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_details);
        ButterKnife.bind(this);
        //获取pid
        Intent intent = getIntent();
        int pid = intent.getIntExtra("pid", 0);
        //请求数据
        mPresenter.getPresenter(pid+"");
        //添加购物车
        mTvAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard();
            }
        });
        //购物车
        mTvShopCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到购物车
                Intent intent = new Intent(ListDetailsActivity.this, ShopCartActivity.class);
                startActivity(intent);
            }
        });
        mLift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListDetailsActivity.this.finish();
            }
        });
    }
    //添加到购物车
    private void addCard() {
        int pid = data.getPid();
        mPresenter.getAddCartPresenter(uid,pid+"");
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_list_details;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }
    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }
    @Override
    public void getDatailsSuccess(DatailsBean datailsBean) {
        data = datailsBean.getData();
        setData();
    }
    private void setData() {
        //判断传过来的bean不为空
        if (data == null) {
            return;
        }
        //设值图片加载起
        mBanner.setImageLoader(new MyApp2());
        String[] split = data.getImages().split("\\|");
        //设置图片集合
        mBanner.setImages(Arrays.asList(split));
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
        //添加值
        mTvTitle.setText(data.getTitle());

        mTvPrice.setText("原价:" + data.getSalenum());

        mTvVipPrice.setText("现价：" + data.getPrice());
    }
    @Override
    public void getAddCartSuccess(BaseBean baseBean) {
        Toast.makeText(ListDetailsActivity.this, "加入成功", Toast.LENGTH_SHORT).show();
    }
}
