package com.paprbit.popularmovies.net.retrofit.service;

import com.paprbit.popularmovies.net.retrofit.gson_pojo.Movie;
import com.paprbit.popularmovies.net.retrofit.gson_pojo.MovieResponse;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by ankush38u on 12/26/2015.
 */
public interface ApiService {


    @GET("movie/top_rated")
    Call<MovieResponse> getMoviesTopRated(@Query("api_key") String api_key);

    @GET("movie/popular")
    Call<MovieResponse> getMoviesPopular(@Query("api_key") String api_key);


    /*
    @GET("services/get-city.php")
    Call<ResponseBody> loadcity();

    @FormUrlEncoded
    @POST("services/register.php")
    Call<Message> registerUser(@Field("shop_name") String shop_name, @Field("phone") String phone,
                               @Field("email") String email, @Field("tin") String tin,
                               @Field("state") String state, @Field("city") String city, @Field("area") String area,
                               @Field("password") String password);



    @FormUrlEncoded
    @POST("session/login.php")
    Call<ResponseBody> login(@Field("phone") String phone, @Field("password") String password);



    @POST("session/checkSession.php")
    Call<Message> chkSession();

    @POST("session/logout.php")
    Call<Message> logout();

    @POST("services/get-profile.php")
    Call<ResponseBody> getProData();

    @FormUrlEncoded
    @POST("services/update-profile.php")
    Call<Message> updateUser(@Field("shop_name") String shop_name, @Field("phone") String phone,
                             @Field("email") String email, @Field("tin") String tin,
                             @Field("state") String state, @Field("city") String city, @Field("area") String area);

    @FormUrlEncoded
    @POST("services/update-password.php")
    Call<Message> updatePassword(@Field("password") String old_password, @Field("new_password") String new_password);

    @GET("services/get-schemes.php")
    Call<ResponseBody> loadSchemes();

    @GET("services/get-products.php")
    Call<ResponseBody> loadProducts();


    @FormUrlEncoded
    @POST("services/msg-admin.php")
    Call<Message> msgAdmin(@Field("msg") String message);

    @Headers("Content-Type: application/json")
    @POST("services/place-order.php")
    Call<Message> postRequest(@Body PurchaseRequest request);

    @POST("services/get-previous-orders.php")
    Call<ResponseBody> getPreviousOrders();

    @FormUrlEncoded
    @POST("services/get-order-detail.php")
    Call<ResponseBody> getOrderDetail(@Field("id") int id);

    @FormUrlEncoded
    @POST("services/cancel-order.php")
    Call<Message> cancelOrder(@Field("id") int id);

    @GET("services/about.php")
    Call<Message> about();*/


}
