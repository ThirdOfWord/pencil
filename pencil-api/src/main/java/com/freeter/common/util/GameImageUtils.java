package com.freeter.common.util;

/* *
 *上传图片工具
 * @author wangkui
 * @date 2019/5/8 14:23
 * @param
 * @return
 */

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class GameImageUtils {

    public static String GameImage(MultipartFile file, String uploadLocation, String dbUrl) throws IllegalStateException, IOException {
        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取String类型的时间
        String createdate = sdf.format(date);
        //使用UUID给图片重命名，并去掉四个“-”
        String name = UUID.randomUUID().toString().replaceAll("-", "");
        //获取文件的扩展名
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        //File directory = new File(String.format("%s/img", "file/upload"));
        File directory = new File(String.format("%s", uploadLocation));
        Files.createDirectories(directory.toPath());
        String fileName = createdate + "-" + name + "." + ext;
        //Path path = Paths.get("file/upload", "img", fileName);
        Path path = Paths.get(uploadLocation, "", fileName);
        System.out.println("====>" + path.toFile().getAbsolutePath());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return dbUrl+ fileName ;
    }
    public static void deleteImg(String filePath,String imgUrl){
        int index = filePath .lastIndexOf("/");
        filePath  = filePath.substring(index + 1,filePath.length());
        //new File("D:/OnlineProject/car/file/upload/"+imgUrl+filePath).delete();//本地删除原先的图片
        new File("C:/opt/file/upload/"+imgUrl+filePath).delete();//线上删除原先的图片
    }
}

