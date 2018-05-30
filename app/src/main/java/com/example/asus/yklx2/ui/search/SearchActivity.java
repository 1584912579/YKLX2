package com.example.asus.yklx2.ui.search;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.asus.yklx2.R;
import com.example.asus.yklx2.bean.SearchBean;
import com.example.asus.yklx2.component.DaggerHttpComponent;
import com.example.asus.yklx2.module.HttpModule;
import com.example.asus.yklx2.ui.Details.ListDetailsActivity;
import com.example.asus.yklx2.ui.base.BaseActivity;
import com.example.asus.yklx2.ui.base.BaseContract;
import com.example.asus.yklx2.ui.search.adapter.XrvListAdapter;
import com.example.asus.yklx2.ui.search.contract.SearchContract;
import com.example.asus.yklx2.ui.search.presenter.SearchPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {

    private XRecyclerView xrv;
    private ImageView iii;
    private ImageView lift;
    private String keywords;
    private GridLayoutManager gridLayoutManager;
    private int pp =1;
    private int pscid=1;
    private LinearLayout sdsd;
    private boolean isRefresh = true;
    private XrvListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search);
        xrv = (XRecyclerView) findViewById(R.id.xrv);
        iii = (ImageView) findViewById(R.id.iii);
        lift = (ImageView) findViewById(R.id.lift);
        sdsd = (LinearLayout) findViewById(R.id.sdsd);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        xrv.setLayoutManager(linearLayoutManager);
        lift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });
        //点击搜索
        sdsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        //获取pscid
        Intent intent = getIntent();
        keywords = intent.getStringExtra("keywords");
        gridLayoutManager = new GridLayoutManager(SearchActivity.this, pp);
        xrv.setLayoutManager(gridLayoutManager);
        mPresenter.getPresenter(keywords,pscid+"");

        iii.setImageResource(R.drawable.kind_liner);

        iii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pp==1){

                    adapter.setType(1);
                    iii.setImageResource(R.drawable.kind_liner);
                    pp=2;
                    gridLayoutManager= new GridLayoutManager(SearchActivity.this, 1);
                    xrv.setLayoutManager(gridLayoutManager);
                }else {
                    pp=1;
                    adapter.setType(2);
                    iii.setImageResource(R.drawable.kind_grid);
                    gridLayoutManager= new GridLayoutManager(SearchActivity.this, 2);
                    xrv.setLayoutManager(gridLayoutManager);
                }

            }
        });

        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                isRefresh = true;
                pscid=1;
                mPresenter.getPresenter(keywords,pscid+"");
            }

            @Override
            public void onLoadMore() {
                //加载更多
                isRefresh = false;
                pscid++;
                mPresenter.getPresenter(keywords,pscid+"");
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build().inject(this);
    }

    @Override
    public void getProductsSuccess(SearchBean searchBean) {

        List<SearchBean.DataBean> list = searchBean.getData();
        final List<SearchBean.DataBean> tempList = new ArrayList<>();
        tempList.addAll(list);
        //创建适配器
        if (isRefresh) {
            adapter = new XrvListAdapter(this, list);
            xrv.setAdapter(adapter);
            adapter.refresh(tempList);
            xrv.refreshComplete();//设置刷新完成
        } else {
            if (adapter != null) {
                //判断适配器是否创建过
                adapter.loadMore(tempList);
                xrv.loadMoreComplete();//设置加载更多完成
            }
        }
        adapter.setOnListItemClickListener(new XrvListAdapter.OnListItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(SearchActivity.this, ListDetailsActivity.class);
                intent.putExtra("pid",tempList.get(position).getPid());
                startActivity(intent);
            }
        });
    }
}
