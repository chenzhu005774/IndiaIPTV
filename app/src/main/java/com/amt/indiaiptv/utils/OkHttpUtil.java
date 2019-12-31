package com.amt.indiaiptv.utils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;

public class OkHttpUtil {

    /**
     * Get请求
     * @param url   URL地址
     * @return  返回结果
     */
    public static String get(String url){
        String result=null;
        try {
            OkHttpClient okHttpClient=new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            result=response.body().string();
            LogUtils.i("Get请求返回：{}",result);
            return result;
        }catch (Exception e){
            LogUtils.i("OkHttp[Get]请求异常"+e);
            return result;
        }
    }

    /**
     * Post请求
     * @param url       URL地址
     * @param params    参数
     * @return  返回结果
     */
    public static String post(String url,Map<String,String> params){
        String result=null;
        if (params==null){
            params=new HashMap<String, String>();
        }
        try {
            OkHttpClient okHttpClient=new OkHttpClient();
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            LogUtils.i("params：{}", new JSONObject(params).toString());
            for (Map.Entry<String,String> map:params.entrySet()){
                String key=map.getKey();
                String value;
                if (map.getValue()==null){
                    value="";
                }else{
                    value=map.getValue();
                }
                formBodyBuilder.add(key,value);
            }
            FormBody formBody =formBodyBuilder.build();
            Request request = new Request.Builder().url(url).post(formBody).build();
            Response response = okHttpClient.newCall(request).execute();
            result=response.body().string();
            LogUtils.i("Post请求返回：{}",result);
            return result;
        }catch (Exception e){
            LogUtils.i("OkHttp[Post]请求异常"+e);
            return result;
        }
    }

    /**
     * 上传文件请求（Post请求）
     * @param url       URL地址
     * @param params    文件 key：参数名 value：文件 （可以多文件）
     * @return  返回结果
     */
    public static String upload(String url, Map<String, File> params){
        String result=null;
        try {
            OkHttpClient okHttpClient=new OkHttpClient();
            MultipartBody.Builder multipartBodyBuilder =new MultipartBody.Builder().setType(MultipartBody.FORM);

            for (Map.Entry<String,File> map:params.entrySet()){
                String key=map.getKey();
                File file=map.getValue();
                if (file==null||(file.exists() && file.length() == 0)){
                    continue;
                }
                multipartBodyBuilder.addFormDataPart(key,file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
            }
            RequestBody requestBody =multipartBodyBuilder.build();
            Request request = new Request.Builder().url(url).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            result=response.body().string();
            LogUtils.i("Upload请求返回：{}",result);
            return result;
        }catch (Exception e){
            LogUtils.i("OkHttp[Upload]请求异常"+e);
            return result;
        }
    }

    /**
     * 下载文件请求（Get请求）
     * @param url       URL地址
     * @param savePath  保存路径（包括文件名）
     * @return  文件保存路径
     */
    public static String download(String url,String savePath){
        String result=null;
        try {
            OkHttpClient okHttpClient=new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            File file=new File(savePath);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            BufferedSink sink = Okio.buffer((Okio.sink(file)));
            sink.writeAll(response.body().source());
            sink.flush();
            sink.close();
            result=savePath;
            LogUtils.i("Download请求返回：{}",result);
            return result;
        }catch (Exception e){
            LogUtils.i("OkHttp[Download]请求异常" );
            return result;
        }
    }

    /**
     * @param url          下载连接
     * @param destFileDir  下载的文件储存目录
     * @param destFileName 下载文件名称
     * @param listener     下载监听
     */
    public void download(final String url, final String destFileDir, final String destFileName, final OnDownloadListener listener) {
        Request request = new Request.Builder().url(url).build();
        OkHttpClient okHttpClient=new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败监听回调
                listener.onDownloadFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                File dir = new File(destFileDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, destFileName);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中更新进度条
                        listener.onDownloading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    listener.onDownloadSuccess(file);
                } catch (Exception e) {
                    listener.onDownloadFailed(e);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    public interface OnDownloadListener {
        /**
         * @param file 下载成功后的文件
         */
        void onDownloadSuccess(File file);

        /**
         * @param progress 下载进度
         */
        void onDownloading(int progress);

        /**
         * @param e 下载异常信息
         */
        void onDownloadFailed(Exception e);
    }

}