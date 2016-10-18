package me.ppting.retrofit.base;

import me.ppting.retrofit.http.HttpUtil_Gank;
import me.ppting.retrofit.http.HttpUtil_Github;
import me.ppting.retrofit.service.GankService;
import me.ppting.retrofit.service.GithubService;
import me.ppting.retrofit.service.Stay4ItService;

/**
 * Created by PPTing on 2016/9/29.
 */

public abstract class BaseModel{
    public GankService gankService = HttpUtil_Gank.getInstance().create(GankService.class);
    public GithubService githubService = HttpUtil_Github.getInstance().create(GithubService.class);
    public Stay4ItService stay4ItService = HttpUtil_Gank.getInstance().create(Stay4ItService.class);
}
