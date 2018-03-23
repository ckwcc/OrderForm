package com.ckw.orderform.NetLoader;

import com.ckw.orderform.repository.OrderBean;
import com.ckw.orderform.repository.OrderListBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ckw
 * on 2017/12/12.
 * 网络请求api接口
 */

public interface ApiService {


    /*
    * 增加
    * */
    @FormUrlEncoded
    @POST("addOrder")
    Observable<Response<String>> addOrder(@Field("orderStr") String jsonStr);

    /*
    * 删除
    * */
    @FormUrlEncoded
    @POST("deleteOrder")
    Observable<Response<String>> deleteOrder(@Field("orderStr") String jsonStr);


    /*
   * 修改
   * */
    @FormUrlEncoded
    @POST("updateOrder")
    Observable<Response<String>> updateOrder(@Field("orderStr") String jsonStr);

    /*
    * 查询
    * */
    @FormUrlEncoded
    @POST("getAllOrder")
    Observable<Response<OrderListBean>> getAllOrder(@Field("orderStr") String jsonStr);

    /*
    * 得到所有
    * */
    @GET("getAllOrder")
    Observable<Response<OrderListBean>> getAllOrder();

}
