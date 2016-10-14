package me.ppting.okhttpdemo.login.model;

import me.ppting.okhttpdemo.util.NetUtil;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by PPTing on 2016/10/13.
 */

public interface GetDayGankService {
    @GET(NetUtil.DailyGank+"{year}/{month}/{day}")
    Call<DayGankInfo> getDayGank(@Path("year") String year,@Path("month") String month,@Path("day") String day);
}
