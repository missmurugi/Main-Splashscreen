package com.example.splashscreen.services;



import com.example.splashscreen.response_object.AdminResponseObject;
import com.example.splashscreen.response_object.ClassTeacherResponseObject;
import com.example.splashscreen.response_object.DriverResponseObject;
import com.example.splashscreen.response_object.HeadTeacherResponseObject;
import com.example.splashscreen.response_object.ParentResponseObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Repository {
    @FormUrlEncoded
    @POST("register_admin")
    Call<AdminResponseObject> createAdmin(
            @Field("adminname") String name,
            @Field("adminemail") String email,
            @Field("adminpassword") String password,
            @Field("admincontact") String contact
    );
    @GET("fetch_admin")
    Call<List<AdminResponseObject>> fetchAllAdmin();

    //creating parent
    @FormUrlEncoded
    @POST("register_parent")
    Call<ParentResponseObject> createParent(
            @Field("parentsignupname") String parentname,
            @Field("parentsignupmail") String parentmail,
            @Field("parentsignuppassword") String parentpassword,
            @Field("parentsignupcontact") String parentcontact,
            @Field("parentchildsignup") String parentchild,
            @Field("parentsignupaddress") String parentaddress
    );
    @GET("fetch_parent")
    Call<List<ParentResponseObject>> fetchAllParent();

    //creating driver
    @FormUrlEncoded
    @POST("register_driver")
    Call<DriverResponseObject> createDriver(
            @Field("driversignupname") String drivername,
            @Field("driversignupmail") String drivermail,
            @Field("driversignuppass") String driverpass,
            @Field("driversignupcontact") String drivercontact
    );
    @GET("fetch_driver")
    Call<List<DriverResponseObject>> fetchAllDriver();

    //creatingg head teacher
    @FormUrlEncoded
    @POST("register_headteacher")
    Call<HeadTeacherResponseObject> createHeadTeacher(
            @Field("headteachersignupname") String headteachername,
            @Field("headteachersignupmail") String headteachermail,
            @Field("headteachercontact") String headteachercontact,
            @Field("headteachersignuppass") String headteacherpass
    );
    @GET("fetch_headteacher")
    Call<List<HeadTeacherResponseObject>> fetchAllHeadTeacher();

    //creating class teacher
    @FormUrlEncoded
    @POST("register_classteacher")
    Call<ClassTeacherResponseObject> createClassTeacher(
            @Field("teachersignupname") String teachername,
            @Field("teachersignupmail") String teachermail,
            @Field("teachersignupcontact") String teachercontact,
            @Field("teachersignuppass") String teacherpass
    );
    @GET("fetch_classteacher")
    Call<List<ClassTeacherResponseObject>> fetchAllClassTeacher();

}