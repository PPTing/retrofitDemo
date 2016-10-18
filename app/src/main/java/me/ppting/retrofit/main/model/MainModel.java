package me.ppting.retrofit.main.model;

import java.io.File;
import java.util.List;
import me.ppting.retrofit.base.BaseModel;

/**
 * Created by PPTing on 2016/9/29.
 */

public abstract class MainModel extends BaseModel{

    public interface MainModelCallback{
    }

    public abstract void uploadOneFile(File file);
    public abstract void uploadMoreFile(List<File> fileList);
    public abstract void getRepo(String user);

}
