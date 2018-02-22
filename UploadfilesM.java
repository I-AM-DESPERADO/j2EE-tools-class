package com.trace.app.framework.toolsmodel;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by cx on 2017/6/19.
 */
public class UploadfilesM {
    public static String saveFile(HttpServletRequest request, MultipartFile file, String imageUrl,String menuName) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String time = String.valueOf(System.currentTimeMillis());
                String fileName = time + file.getOriginalFilename();
                String returnUrl = Constant.FILE_Upload_PATH +menuName+ "/" + imageUrl + "/" + fileName;

                String filePath = Constant.FILE_PATH_C + returnUrl;
                String fileUrl = Constant.FILE_PATH_C + Constant.FILE_Upload_PATH +menuName+ "/" + imageUrl;
                System.out.println(fileUrl);
                File uploadDir = new File(fileUrl);
                if (!uploadDir.exists())
                    uploadDir.mkdirs();

                //String localFilePath = uploadDir.getPath() + File.separator + time + file.getOriginalFilename();
                //filePath = "/uploads/" + UUID.randomUUID() + "_" + System.currentTimeMillis() + ".png";
                // 文件保存路径
                // localFilePath = request.getSession().getServletContext().getRealPath("/") + filePath;
                //System.out.println("filepath=" + filePath);
                // 转存文件
                //System.out.println(file.getOriginalFilename());
                file.transferTo(new File(filePath));//把文件复制到路径里面去，如果存在就覆盖，不另建
                //setFilePath(filePath);
                return returnUrl;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
//下面的
    public static String saveFile(HttpServletRequest request, MultipartFile file, String filePath) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String fileName =  file.getOriginalFilename();
                // String returnUrl = Constant.FILE_PATH_IMAGE +menuName+ "/" + companySid + "/" + fileName;
                String returnUrl = filePath + "/" + fileName;
                //String filePath = Constant.FILE_PATH_C + Constant.FILE_PATH_SERVER + returnUrl;
                //String fileUrl = Constant.FILE_PATH_C + Constant.FILE_PATH_SERVER + Constant.FILE_PATH_IMAGE +menuName+ "/" + imageUrl;
                System.out.println(filePath);
                File uploadDir = new File(filePath);
                if (!uploadDir.exists())
                    uploadDir.mkdirs();
                file.transferTo(new File(filePath+"/"+fileName));//把文件复制到路径里面去，如果存在就覆盖，不另建
                return returnUrl;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
