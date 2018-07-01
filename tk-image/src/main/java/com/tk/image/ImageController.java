package com.tk.image;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 图片处理服务
 */
public class ImageController extends HttpServlet {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/");


    @SuppressWarnings("unchecked")
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        JSONObject json = new JSONObject();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //为解析类提供配置信息
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建解析类的实例
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //开始解析
        sfu.setFileSizeMax(1024 * 1024 * 5);
        //每个表单域中数据会封装到一个对应的FileItem对象上
        try {
            List<FileItem> items = sfu.parseRequest(req);
            //区分表单域
            for (int i = 0; i < items.size(); i++) {
                FileItem item = items.get(i);
                //isFormField为true，表示这不是文件上传表单域
                if (!item.isFormField()) {
                    String path_date = getDateUrl();
                    String name = String.valueOf(System.currentTimeMillis());
                    String store = "/upload/image/" + path_date;
                    // 得到本地路径
                    String path = req.getSession().getServletContext().getRealPath(store);
                    File pathFile = new File(path);
                    if (!pathFile.exists()) {
                        pathFile.mkdirs();
                    }

                    String fileName = name + ".jpg";
                    File targetFile = new File(pathFile, fileName);
                    if (!targetFile.exists()) {
                        item.write(targetFile);
                        // 图片处理
                        String imageInfo = req.getParameter("imageinfo");
                        if (imageInfo != null && imageInfo.trim().length() > 0) {
                            Image image = JSON.parseObject(imageInfo, Image.class);
                            if (null != image && null != image.sizes && image.sizes.size() > 0) {
                                for (ImageInfo info : image.sizes) {
                                    doImage(path, fileName, name, "jpg", info.w, info.h);
                                }
                            }
                        }
                        //返回原图地址
                        json.put("code", 1);
                        json.put("url", store + fileName);
                        PrintWriter writer = resp.getWriter();
                        writer.println(json.toString());
                        writer.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 0);
            PrintWriter writer = resp.getWriter();
            writer.println(json.toString());
            writer.close();
        }
    }


    void doImage(String path, String fileName, String name, String suffix, int w, int h) {
        try {
            StringBuffer newFile = new StringBuffer();
            newFile.append(path).append("/").append(name);
            newFile.append("_").append(w).append("x").append(h).append(".");
            newFile.append(suffix);
            if (w == 0) {
                Thumbnails.of(path + "/" + fileName).height(h).outputQuality(0.9).toFile(newFile.toString());
            } else if (h == 0) {
                Thumbnails.of(path + "/" + fileName).width(w).outputQuality(0.9).toFile(newFile.toString());
            } else {
                Thumbnails.of(path + "/" + fileName).size(w, h).keepAspectRatio(false).outputQuality(0.9).toFile(newFile.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getDateUrl() {
        return formatter.format(new Date());
    }
}
