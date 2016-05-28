package com.smile.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.smile.bean.ShoppingCartBean;
import com.smile.bean.StoreInfo;
import com.smile.biz.ShoppingCartBiz;
import com.smile.shoppingcart.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**购物车适配器
 * Created by Smile on 2016/5/23.
 */
public class GoodsAdapter extends BaseExpandableListAdapter {
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    private Context context;
    private List<StoreInfo> shopInfos;
    private Map<Integer,List<ShoppingCartBean>> mDatass=new HashMap<Integer, List<ShoppingCartBean>>();
    public GoodsAdapter(Context context, Map<Integer,List<ShoppingCartBean>> mDatass,List<StoreInfo> shopInfos ) {
        this.context = context;
        this.mDatass=mDatass;
        this.shopInfos = shopInfos;
        mInflater=LayoutInflater.from(context);
    }


    @Override
    public int getGroupCount() {
        return shopInfos.size();
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        Integer[] keys = new Integer[mDatass.size()];
        mDatass.keySet().toArray(keys);
        int key= keys[groupPosition];
        int size=mDatass.get(key).size();
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return shopInfos.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        StoreInfo shopInfo= shopInfos.get(groupPosition);
        List<ShoppingCartBean> ShoppingCartBean = mDatass.get(shopInfo.getShopId());
        if(ShoppingCartBean == null)
            return null;
        return ShoppingCartBean.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
    //设置店铺名字(父Item)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder=null;

        if(convertView==null){
            viewHolder=new ChildViewHolder();
            convertView=mInflater.inflate(R.layout.item_goods_parent,null);
            convertView.setTag(viewHolder);
        }else{
            //viewHolder= (ChildViewHolder) convertView.getTag();
        }
        TextView tv_storename= (TextView) convertView.findViewById(R.id.id_tv_storename);
        //viewHolder.img_check.setOnClickListener(listener);
        ImageView img_check= (ImageView) convertView.findViewById(R.id.id_img_storercheck);
        StoreInfo shopInfo= shopInfos.get(groupPosition);
        List<ShoppingCartBean> beanList=mDatass.get(shopInfo.getShopId());
        tv_storename.setText(shopInfo.getShopName());

        boolean childen= ShoppingCartBiz.checkChilden(shopInfo,mDatass);

        if(childen&&shopInfo.isStoreCheckStatus()){
            ShoppingCartBiz.checkItem(true,img_check);
        }else
            ShoppingCartBiz.checkItem(false,img_check);

        img_check.setTag(shopInfo);
        img_check.setOnClickListener(listener);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        StoreInfo shopInfo= shopInfos.get(groupPosition);
        int key=shopInfo.getShopId();
        ChildViewHolder viewHolder=null;

        List<ShoppingCartBean> goods=mDatass.get(key);
        if(convertView==null){
            viewHolder=new ChildViewHolder();
            convertView=mInflater.inflate(R.layout.item_goods_children,null);
            ButterKnife.inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ChildViewHolder) convertView.getTag();
        }
        ShoppingCartBean good = goods.get(childPosition);
        if(good != null) {
            viewHolder.goodsImg.setBackgroundResource(R.mipmap.ic_launcher);
            viewHolder.goodsName.setText(good.getGoodsName());
            viewHolder.goodsSomeInfo.setText(good.getSomeInfo());
            viewHolder.goodsAmount.setText("X" + good.getAmount());
            viewHolder.goodsPrices.setText("" + good.getGoodsPrice()*good.getAmount());
            viewHolder.goodsHuibi.setText("" + good.getHuiBi());
            viewHolder.goodsnum_btn.setText("" + good.getAmount());
            ShoppingCartBiz.checkItem(good.isGoodsCheckStatus(), viewHolder.goodsCheck);
            RelativeLayout editLayout = (RelativeLayout) convertView.findViewById(R.id.id_rl_editgoods);
            LinearLayout goodsLayout = (LinearLayout) convertView.findViewById(R.id.id_lv_goodsinfo);
            ShoppingCartBiz.checkEdit(good.isGoodsEditShow(), editLayout, goodsLayout);
            viewHolder.minus_img.setOnClickListener(listener);
            viewHolder.add_img.setOnClickListener(listener);
            viewHolder.add_img.setTag(good);
            viewHolder.goodsCheck.setOnClickListener(listener);
            viewHolder.goodsCheck.setTag(good);
            viewHolder.minus_img.setTag(good);
           // viewHolder.goodsCheck.setTag(shopInfo);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //全选点击事件
                case R.id.id_img_allselect:
                    ImageView imageView= (ImageView) v.findViewById(R.id.id_img_allselect);
                        //selectAll(imageView);
                    ShoppingCartBiz.selectAll(mDatass,shopInfos,imageView);
                    notifyDataSetChanged();
                    break;
                //编辑点击事件
                case R.id.id_title_right:
                    TextView editText= (TextView) v.findViewById(R.id.id_title_right);
                   ShoppingCartBiz.showEdit(mDatass,editText);
                    notifyDataSetChanged();
                    break;
                //减商品
                case R.id.id_img_minus:
                    ShoppingCartBean goodm= (ShoppingCartBean) v.getTag();
                    ImageView minus_img= (ImageView) v.findViewById(R.id.id_img_minus);
                    ShoppingCartBiz.editGoodsInfo(false,goodm,minus_img);
                    notifyDataSetChanged();
                    break;
                //加商品
                case R.id.id_img_add:
                    ShoppingCartBean goods= (ShoppingCartBean) v.getTag();
                    ImageView add_img= (ImageView) v.findViewById(R.id.id_img_add);
                    ShoppingCartBiz.editGoodsInfo(true,goods,add_img);
                    notifyDataSetChanged();
                    break;
                //商品单选
                case R.id.id_img_check:
                    //ShopInfo shopInfo= (ShopInfo) v.getTag(2);
                    ShoppingCartBean cartBean= (ShoppingCartBean) v.getTag();
                    ShoppingCartBiz.checkOne(cartBean,shopInfos);
                    notifyDataSetChanged();
                    break;
                //商店选择点击事件
                case R.id.id_img_storercheck:
                    StoreInfo info= (StoreInfo) v.getTag();
                    ShoppingCartBiz.checkStore(mDatass,info);
                    notifyDataSetChanged();
                    break;
                case R.id.id_btn_pay:

                    }

            }


    };
    /**
     * 更新商品选中状态
     * @param good
     * @param type
     */
    private void updataGoodsCheckStatus(ShoppingCartBean good,boolean type){

    }

    /**
     * 全选点击事件
     */
    private void selectAll(ImageView view) {
         Log.i("smile","执行了");
        ShoppingCartBiz.selectAll(mDatass,shopInfos,view);

    }

    public View.OnClickListener getAdapterListener() {
        return listener;
    }

    class ChildViewHolder {
        @InjectView(R.id.id_img_goodsimg)
        ImageView goodsImg;
        @InjectView(R.id.id_tv_goods_name)
        TextView goodsName;
        @InjectView(R.id.id_tv_someinfo)
        TextView goodsSomeInfo;
        @InjectView(R.id.id_tv_amount)
        TextView goodsAmount;
        @InjectView(R.id.id_tv_goods_price)
        TextView goodsPrices;
        @InjectView(R.id.id_tv_goods_huibi)
        TextView goodsHuibi;
        @InjectView(R.id.id_img_check)
        ImageView goodsCheck;
        //编辑视图
        @InjectView(R.id.id_img_minus)
        ImageView minus_img;//减
        @InjectView(R.id.id_img_add)
        ImageView add_img;//加
        @InjectView(R.id.id__btn_goodsnum)
        Button goodsnum_btn;//总数量

    }
    }


