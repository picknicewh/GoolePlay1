package main.gooleplay.rxTest;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 统一的接口
 * Created by wanghua on 2017/2/10.
 */
public interface ApiRequest {


    /*post请求*/
    @FormUrlEncoded
    @POST("{url}")
    Observable<ResponseBody> sendPost(@Path("url") String url, @FieldMap Map<String, Object> params);

    /*get请求*/
    @GET("{url}")
    Observable<ResponseBody> sendGet(@Path("url") String url, @QueryMap Map<String, Object> params);

    /*单文件文件上传*/
    @Multipart
    @POST("{url}")
    Observable<ResponseBody> uploadSingleFile(@Path("url") String url, @Part MultipartBody.Part photo);

    /*图文一起上传*/
    @Multipart
    @POST("{url}")
    Observable<ResponseBody> sendFilePost(@Path("url") String url, @Part MultipartBody.Part photo, @FieldMap Map<String, Object> params);

    /*多文件上传*/
    @Multipart
    @POST("{url}")
    Observable uploadFile(@Path("url") String url, @PartMap Map<String, ResponseBody> params);

    /*多文件上传*/
    @POST("{url}")
    Observable<ResponseBody> uploadFiles(
            @Path("url") String url,
            @Path("headers") Map<String, String> headers,
            @Part("filename") String description,
            @PartMap() Map<String, RequestBody> maps);

    /*下载文件*/
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

}



