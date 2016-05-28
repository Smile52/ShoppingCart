package com.smile.shoppingcart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.smile.adapter.GoodsAdapter;
import com.smile.bean.ShoppingCartBean;
import com.smile.bean.StoreInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.id_elv_goods)
    ExpandableListView listView;

    @InjectView(R.id.id_img_allselect)
    ImageView mAllSelect_img;//全选图片
    @InjectView(R.id.id_tv_price)
    TextView mPrice_tv;//总价
    @InjectView(R.id.id_tv_huibi_num)
    TextView mHuiBi_tv;//慧币
    @InjectView(R.id.id_btn_pay)
    Button mPay_btn;//结算

    @InjectView(R.id.id_title_right)
    TextView mEdit_tv;//编辑
    private List<StoreInfo> storeInfos=new ArrayList<StoreInfo>();
    private Map<Integer,List<ShoppingCartBean>> datas=new HashMap<Integer, List<ShoppingCartBean>>();
    private GoodsAdapter goodsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main,null);
        setContentView(view);
        ButterKnife.inject(this,view);
        initDatas();
        initViews();

    }

    private void initViews() {
        goodsAdapter=new GoodsAdapter(MainActivity.this,datas,storeInfos);
        View.OnClickListener listener=goodsAdapter.getAdapterListener();
        if(listener!=null){
            mAllSelect_img.setOnClickListener(listener);//全选点击事件
            mPay_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  /*  Intent mIntent= new Intent(getActivity(), SubmintOrderActivity.class);
                    startActivity(mIntent);*/
                    Log.i("smile","wwwwww");
                }
            });//结算点击事件
            mEdit_tv.setOnClickListener(listener);//编辑点击事件
        }

        //Log.i("smile",""+s);
        listView.setAdapter(goodsAdapter);
        //默认展开
        final int groupCount=listView.getCount();
        for (int i=0;i<groupCount;i++){
            listView.expandGroup(i);
        }
        //点击父Item子Item不收缩
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    private void initDatas() {
        ArrayList<ShoppingCartBean> beanList  = new ArrayList<>();
        StoreInfo StoreInfo = new StoreInfo();
        StoreInfo.setShopId(1);
        StoreInfo.setShopName("海澜之家");
        StoreInfo.setStoreCheckStatus(true);
        storeInfos.add(StoreInfo);
        for(int i=1;i<=5;i++){
            ShoppingCartBean cartBean=new ShoppingCartBean();
            cartBean.setId(i);
            cartBean.setStoreID(StoreInfo.getShopId());
            cartBean.setStoreName(StoreInfo.getShopName());
            cartBean.setGoodsName("衬衫");
            cartBean.setGoodsPrice(128);
            cartBean.setSomeInfo("颜色：白色,尺寸：L");
            cartBean.setHuiBi(25);
            cartBean.setAmount(1);
            cartBean.setGoodsCheckStatus(true);
            cartBean.setStoreCheckStatus(true);
            cartBean.setGoodsEditShow(false);
            beanList.add(cartBean);
        }
        datas.put(1,beanList);
        StoreInfo = new StoreInfo();
        StoreInfo.setShopId(2);
        StoreInfo.setShopName("阿玛尼");
        StoreInfo.setStoreCheckStatus(true);
        storeInfos.add(StoreInfo);
        beanList  = new ArrayList<>();
        for(int j=6;j<=8;j++){
            ShoppingCartBean cartBean=new ShoppingCartBean();
            cartBean.setId(j);
            cartBean.setStoreID(StoreInfo.getShopId());
            cartBean.setStoreName(StoreInfo.getShopName());
            cartBean.setGoodsName("西装");
            cartBean.setGoodsPrice(188);
            cartBean.setSomeInfo("颜色：黑色,尺寸：L");
            cartBean.setHuiBi(25);
            cartBean.setAmount(2);
            cartBean.setGoodsCheckStatus(true);
            cartBean.setStoreCheckStatus(true);
            cartBean.setGoodsEditShow(false);
            beanList.add(cartBean);
        }
        datas.put(2,beanList);
    
    }
}
