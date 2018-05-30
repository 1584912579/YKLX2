package com.example.asus.yklx2.component;

import com.example.asus.yklx2.module.HttpModule;
import com.example.asus.yklx2.ui.Details.ListDetailsActivity;
import com.example.asus.yklx2.ui.search.SearchActivity;
import com.example.asus.yklx2.ui.shoping.ShopCartActivity;

import dagger.Component;

/**
 * Created by asus on 2018/5/29.
 */
@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(SearchActivity searchActivity);
    void inject(ListDetailsActivity listDetailsActivity);
    void inject(ShopCartActivity shopCartActivity);
}
