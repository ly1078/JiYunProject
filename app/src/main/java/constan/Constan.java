package constan;

import java.io.File;

import apps.MyApplication;

public class Constan {

    public static final String PATH_DATA = MyApplication.myApplication.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/shopapp";

    //商城的基础地址
    public static final String BASE_SHOP_URL = "https://cdwan.cn/api/";
}
