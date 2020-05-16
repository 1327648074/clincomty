package cl.web.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class PictureController {
    @CrossOrigin
    @PostMapping("/api/img/bg")
    @ResponseBody
    public String bgImgUpload(MultipartFile file){
        String folder = "E:/img";
        File image = new File(folder);
        UUID uuid = UUID.randomUUID();
        File f = new File(image,uuid.toString().replaceAll("/","")+".jpg");
        if (!f.getParentFile().exists()){
            f.getParentFile().mkdirs();
        }
        try{
            file.transferTo(f);
            return "http://localhost:8443/api/bgImage/"+ f.getName();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    @CrossOrigin
    @PostMapping("/api/img/u")
    @ResponseBody
    public String uImgUpload(MultipartFile file){
        String folder = "E:/img";
        File image = new File(folder);
        UUID uuid = UUID.randomUUID();
        File f = new File(image,uuid.toString().replaceAll("/","")+".jpg");
        if (!f.getParentFile().exists()){
            f.getParentFile().mkdirs();
        }
        try{
            file.transferTo(f);
            return "http://localhost:8443/api/uImage/"+ f.getName();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
