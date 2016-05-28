package com.smile.biz;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.smile.bean.ShoppingCartBean;
import com.smile.bean.StoreInfo;
import com.smile.shoppingcart.R;

import java.util.List;
import java.util.Map;

/**购物车商品数量操作类
 * Created by Smile on 2016/5/24.
 */
public class ShoppingCartBiz {
    /**
     * 全选
     * @param goodsMap
     * @param
     * @return
     */
    public static boolean selectAll(Map<Integer,List<ShoppingCartBean>> goodsMap, List<StoreInfo> StoreInfos, ImageView view){
         for(List<ShoppingCartBean> list:goodsMap.values()){
            for(ShoppingCartBean good:list){
                if(good.isGoodsCheckStatus()==true){
                    good.setGoodsCheckStatus(false);
                  //  list.get(i).setStoreCheckStatus(false);
                    view.setBackgroundResource(R.mipmap.radio_box);
                }else if(good.isGoodsCheckStatus()==false){
                    good.setGoodsCheckStatus(true);
                  //  list.get(i).setStoreCheckStatus(true);
                    view.setBackgroundResource(R.mipmap.radio_box_check);
                }
        }
        }
        for (StoreInfo StoreInfo:StoreInfos){
            if(StoreInfo.isStoreCheckStatus()==true&&checkChilden(StoreInfo,goodsMap)){
                Log.i("smile","ccc");
                StoreInfo.setStoreCheckStatus(false);

                view.setBackgroundResource(R.mipmap.radio_box);
            }else if(StoreInfo.isStoreCheckStatus()==false&&checkChilden(StoreInfo,goodsMap))
                Log.i("smile","zzz");
                StoreInfo.setStoreCheckStatus(true);
                view.setBackgroundResource(R.mipmap.radio_box_check);
        }
     /*  for(StoreInfo StoreInfo:StoreInfos){
            List<ShoppingCartBean> goodsList= goodsMap.get(StoreInfo.getShopId());
            for(ShoppingCartBean good :goodsList){
                if(StoreInfo.isStoreCheckStatus()==true&&checkChilden(StoreInfo,goodsMap)){
                    StoreInfo.setStoreCheckStatus(false);
                    good.setGoodsCheckStatus(false);
                    view.setBackgroundResource(R.mipmap.radio_box);
                }else if(StoreInfo.isStoreCheckStatus()==false||checkChilden(StoreInfo,goodsMap)){
                    view.setBackgroundResource(R.mipmap.radio_box);
                }
                else {
                    StoreInfo.setStoreCheckStatus(true);
                    good.setGoodsCheckStatus(true);
                    view.setBackgroundResource(R.mipmap.radio_box_check);
                }
                }
            }*/


        return true;
    }

    /**
     * 检查子item是否为被选中
     * @param info
     * @param goodsMap
     * @return
     */
    public static boolean checkChilden(StoreInfo info,Map<Integer,List<ShoppingCartBean>> goodsMap){
        List<ShoppingCartBean> beanList=goodsMap.get(info.getShopId());
        for(ShoppingCartBean good:beanList){
            if(good.isGoodsCheckStatus()==true&&info.isStoreCheckStatus()==true){
                return true;
            }else if(good.isGoodsCheckStatus()==false) {
                return false;
            }

        }
        return false;
    }
    /**
     * 勾与不勾选中选项
     *
     * @param isSelect 原先状态
     * @param mSelectAll_img
     * @return 是否勾上，之后状态
     */
    public static boolean checkItem(boolean isSelect, ImageView mSelectAll_img) {
        if (isSelect) {
            mSelectAll_img.setImageResource(R.mipmap.radio_box_check);
        } else {
            mSelectAll_img.setImageResource(R.mipmap.radio_box);
        }
        return isSelect;
    }


    /**
     * 设置显示编辑的状态
     * @param goodsMap
     * @return
     */
    public static boolean showEdit(Map<Integer,List<ShoppingCartBean>> goodsMap, TextView edit){
        for(List<ShoppingCartBean> list:goodsMap.values()){
            for(int i=0;i<list.size();i++){
                if(list.get(i).isGoodsEditShow()==true){
                    list.get(i).setGoodsEditShow(false);
                    //  list.get(i).setStoreCheckStatus(false);
                    edit.setText("编辑");

                }else {
                    list.get(i).setGoodsEditShow(true);
                    edit.setText("完成");
                }
            }
            }

        return true;

    }

    /**
     * 编辑数量显示与不显示
     * @param isEdit
     * @param editLayout  编辑视图
     * @param goodsInfoLayout 商品信息视图
     */
    public static void checkEdit(boolean isEdit, RelativeLayout editLayout,LinearLayout goodsInfoLayout){
        if(isEdit){
            goodsInfoLayout.setVisibility(View.GONE);
            editLayout.setVisibility(View.VISIBLE);
        }else{
            editLayout.setVisibility(View.GONE);
            goodsInfoLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 编辑商品数量
     * @param type  true为加  false为减
     * @param good
     */
    public static void editGoodsInfo(boolean type,ShoppingCartBean good,ImageView minus){

        if(type){
          // int i=good.getAmount()+1;
            good.setAmount(good.getAmount()+1);

        }
        else if(good.getAmount()>1){
            good.setAmount(good.getAmount()-1);
            minus.setBackgroundResource(R.mipmap.goods_minus);
        }else if(good.getAmount()==1){
            minus.setBackgroundResource(R.mipmap.goods_no_minus);

        }

    }

    /**
     * 店铺选择点击事件
     * @param goods
     * @param store
     */
    public static void checkStore(Map<Integer,List<ShoppingCartBean>> goods,StoreInfo store){
        List<ShoppingCartBean> beanList=goods.get(store.getShopId());
        for (ShoppingCartBean bean:beanList){
            if(bean.isGoodsCheckStatus()==true&&store.isStoreCheckStatus()==true)
                bean.setGoodsCheckStatus(false);
            else
                bean.setGoodsCheckStatus(true);
        }
        if(store.isStoreCheckStatus()==true)
            store.setStoreCheckStatus(false);
        else
            store.setStoreCheckStatus(true);

    }

    /**单选
     *
     * @param good
     */
    public static void checkOne(ShoppingCartBean good,List<StoreInfo> StoreInfos){
        //问题？？？不减一 会出现数组越界
        StoreInfo info=StoreInfos.get(good.getStoreID()-1);
        if(good.isGoodsCheckStatus()==true){
            good.setGoodsCheckStatus(false);
            info.setStoreCheckStatus(false);
        }else {
            good.setGoodsCheckStatus(true);

        }
        }

}

