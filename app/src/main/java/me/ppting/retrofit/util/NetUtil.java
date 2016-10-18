package me.ppting.retrofit.util;

/**
 * Created by PPTing on 2016/9/26.
 */

public class NetUtil {
    public static final String BASE_URL = "https://gank.io";
    public static final String DailyGank = "/api/day/";
    public static final String GITHUB = "https://api.github.com";
}
/*
*  1.baseUrl 跟域名 path 是绝对路径
*    baseUrl : https://api.github.com
*    path : /users/{user}/repos
*    url : https://api.github.com/users/tingya/repos
*  2.baseUrl 目录形式 path 相对路径
*    baseUrl: https://api.github.com/
*    path: users/{user}/repos
*    url:https://api.github.com/users/tingya/repos
*
*    or
*
*    baseUrl: https://api.github.com/users/
*    path:{user}/repos
*    url:https://api.github.com/users/tingya/repos
*
*  3.baseUrl 文件形式 path 相对路径
*    baseUrl:https://api.github.com/users
*    path:{user}/repos
*    url: https://api.github.com/tingya/repos
*    baseUrl must end in /: https://api.github.com/users
*  4.baseUrl 目录形式 path 绝对路径
*    baseUrl:  https://api.github.com/users/
*    path: /{user}/repos
*    url:  https://api.github.com/tingya/repos
*  5.baseUrl path 完整路径
*       baseUrl: https://api.github.com/users/
*       path https://api.github.com/users/{user}/repos
*       url: https://api.github.com/users/tingya/repos
*
*/