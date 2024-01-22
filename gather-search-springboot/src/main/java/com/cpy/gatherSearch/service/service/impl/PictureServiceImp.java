package com.cpy.gatherSearch.service.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cpy.gatherSearch.model.entity.Picture;
import com.cpy.gatherSearch.service.service.PictureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author:成希德
 */
@Service
public class PictureServiceImp implements PictureService {

    @Override
    public List<Picture> getPictureList(String searchValue) throws IOException {
        LinkedList<Picture> list = new LinkedList<>();
        Document doc = Jsoup.connect("https://www.bing.com/images/search?q="+searchValue+"&first=").get();
            Elements newsHeadlines = doc.select("div.iuscp.isv");
            for (Element element : newsHeadlines) {
                String json = element.select("a.iusc").attr("m");
                JSONObject entries = JSONUtil.parseObj(json);
                String src =(String) entries.get("murl");
                String title=(String)entries.get("t");
                Picture picture=new Picture();
                picture.setTitle(title);
                picture.setSrc(src);
                if (!src.equals(""))list.add(picture);
                else return list;
            }
            return list;

    }
}
