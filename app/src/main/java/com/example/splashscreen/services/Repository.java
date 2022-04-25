package com.example.splashscreen.services;



import com.example.splashscreen.response_object.AdminResponseObject;
import com.example.splashscreen.response_object.ClassTeacherResponseObject;
import com.example.splashscreen.response_object.DriverLocationResponseObject;
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
            @Field("admincontact") String contact,
            @Field("gender") String admingender
    );
    //@GET("fetch_admin")
    //Call<List<AdminResponseObject>> fetchAllAdmin();

    //creating parent
    @FormUrlEncoded
    @POST("register_parent")
    Call<ParentResponseObject> createParent(
            @Field("parentsignupname") String parentname,
            @Field("parentsignupmail") String parentmail,
            @Field("parentsignuppassword") String parentpassword,
            @Field("parentsignupcontact") String parentcontact,
            @Field("parentchildsignup") String parentchild,
            @Field("parentsignupaddress") String parentaddress,
            @Field("gender") String parentgender
    );
    //@GET("fetch_parent")
    //Call<List<ParentResponseObject>> fetchAllParent();

    //creating driver
    @FormUrlEncoded
    @POST("register_driver")
    Call<DriverResponseObject> createDriver(
            @Field("driversignupname") String drivername,
            @Field("driversignupmail") String drivermail,
            @Field("driversignuppass") String driverpass,
            @Field("driversignupcontact") String drivercontact,
            @Field("gender") String drivergender
    );

    @GET("fetch_driver")
    Call<List<DriverResponseObject>> fetchAllDriver();


    @FormUrlEncoded
    @POST("add_driver_location")
    Call<DriverLocationResponseObject> createDriverLocation(
            @Field("driver_id") String driver_id,
            @Field("map_lat") String map_lat,
            @Field("map_long") String map_long,
            @Field("datetime_added") String datetime_added
    );

    //creatingg head teacher
    @FormUrlEncoded
    @POST("register_headteacher")
    Call<HeadTeacherResponseObject> createHeadTeacher(
            @Field("headteachersignupname") String headteachername,
            @Field("headteachersignupmail") String headteachermail,
            @Field("headteachercontact") String headteachercontact,
            @Field("headteachersignuppass") String headteacherpass,
            @Field("gender") String headteachergender
    );
    //@GET("fetch_headteacher")
    //Call<List<HeadTeacherResponseObject>> fetchAllHeadTeacher();

    //creating class teacher
    @FormUrlEncoded
    @POST("register_classteacher")
    Call<ClassTeacherResponseObject> createClassTeacher(
            @Field("teachersignupname") String teachername,
            @Field("teachersignupmail") String teachermail,
            @Field("teachersignupcontact") String teachercontact,
            @Field("teachersignuppass") String teacherpass,
            @Field("gender") String classteachergender
    );
    //@GET("fetch_classteacher")
    //Call<List<ClassTeacherResponseObject>> fetchAllClassTeacher();

    //login parent
    @FormUrlEncoded
    @POST("login_parent")
    Call<List<ParentResponseObject>> loginParent(
            @Field("parentsignupname") String parentname,
            @Field("parentsignuppassword") String parentpassword
    );

    //login admin
    @FormUrlEncoded
    @POST("login_admin")
    Call<List<AdminResponseObject>> loginAdmin(
            @Field("adminsignupname") String name,
            @Field("adminsignuppass") String password
    );

    //login driver
    @FormUrlEncoded
    @POST("login_driver")
    Call<List<DriverResponseObject>> loginDriver(
            @Field("driversignupname") String drivername,
            @Field("driversignuppass") String driverpass
    );

    //login headteacher
    @FormUrlEncoded
    @POST("login_headteacher")
    Call<List<HeadTeacherResponseObject>> loginHeadTeacher(
            @Field("headteachersignupname") String headteachername,
            @Field("headteachersignuppass") String headteacherpass
    );

    //login teacher
    @FormUrlEncoded
    @POST("login_classteacher")
    Call<List<ClassTeacherResponseObject>> loginClassTeacher(
            @Field("teachersignupname") String teachername,
            @Field("teachersignuppass") String teacherpass
    );

}