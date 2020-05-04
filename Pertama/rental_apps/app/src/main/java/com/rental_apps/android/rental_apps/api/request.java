package com.rental_apps.android.rental_apps.api;


import com.rental_apps.android.rental_apps.model.model_user.ResponseLogin;
import com.rental_apps.android.rental_apps.model.model_user.ResponseRegister;

import org.json.JSONStringer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;



public interface request{
    @FormUrlEncoded
    @POST("Api/auth")
    Call<ResponseLogin> auth(@Field("USERNAME") String username,
                             @Field("PASSWORD") String password);

    @FormUrlEncoded
    @POST("Api/user")
    Call<ResponseRegister> userRegister(@Field("NAME")String name,
                                        @Field("NIK")String nik,
                                        @Field("USERNAME")String username,
                                        @Field("EMAIL")String email,
                                        @Field("NO_TELP")String no_telp,
                                        @Field("JENIS_KELAMIN")Character jenis_kelamin,
                                        @Field("ALAMAT")String alamat,
                                        @Field("PASSWORD")String password,
                                        @Field("ACTIVATED")Integer activated,
                                        @Field("GROUP_USER")Integer group_user);
    @FormUrlEncoded
    @PUT("Api/user")
    Call<ResponseRegister> userUpdate(@Field("ID_USER")String ID_USER,
                                      @Field("NAME")String name,
                                      @Field("NIK")String nik,
                                      @Field("USERNAME")String username,
                                      @Field("EMAIL")String email,
                                      @Field("NO_TELP")String no_telp,
                                      @Field("JENIS_KELAMIN")String jenis_kelamin,
                                      @Field("ALAMAT")String alamat,
                                      @Field("PASSWORD")String password,
                                      @Field("ACTIVATED")Integer activated,
                                      @Field("GROUP_USER")Integer group_user,
                                      @Field("PHOTO")String photo);





}
