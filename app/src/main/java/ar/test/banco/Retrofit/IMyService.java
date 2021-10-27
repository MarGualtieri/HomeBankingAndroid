package ar.test.banco.Retrofit;



import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface IMyService {

    @POST("register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("user") String user, @Field("password") String password);


    @POST("login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("user") String user, @Field("password") String password);


}