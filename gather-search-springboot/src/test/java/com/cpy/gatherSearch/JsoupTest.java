package com.cpy.gatherSearch;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cpy.gatherSearch.Exception.CommonException;
import com.cpy.gatherSearch.common.StatuesCode;
import com.cpy.gatherSearch.model.dto.post.PostAddRequest;
import com.cpy.gatherSearch.model.entity.Post;
import com.cpy.gatherSearch.model.entity.User;
import com.cpy.gatherSearch.service.service.PostService;
import com.cpy.gatherSearch.utils.IsUser;
import com.cpy.gatherSearch.utils.VerifyUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author:成希德
 * jsoup爬虫测试类
 */
@SpringBootTest
public class JsoupTest {
    @Resource
    PostService postService;
@Test
    void getPictureTest() throws IOException {
    String searchValue="小黑子";
    Integer first=1;
    Document doc = Jsoup.connect("https://www.bing.com/images/search?q="+searchValue+"&first="+first).get();
    Elements newsHeadlines = doc.select("div.iuscp.isv");
    for (Element element : newsHeadlines) {
        String json = element.select("a.iusc").attr("m");
        JSONObject entries = JSONUtil.parseObj(json);
        String url =(String) entries.get("murl");
        String title = (String) entries.get("t");
        System.out.println(url + ": " + title);
//        if (!src.equals(""))System.out.println(title+":"+src);
    }
}

    @Test
    public void postTest() throws IOException {
        Document document = Jsoup.connect("https://www.diyifanwen.com/zuowen/ernianjixiejingzuowen/").get();
        Elements select = document.select("div.alllist-data ul li a");
        for (Element element : select) {
            String url = "https:" + element.attr("href");
            System.out.println(url);
            putHttp(url);
        }
    }
    public void putHttp(String url) throws IOException {
        String title="";
        String content="";
        Document document = Jsoup.connect(url).get();
        Elements select = document.select("div.content ");
        Elements p = select.select("p");
        title=p.get(0).text();
         content = select.html();
//        for (Element element : select) {
//            String text = element.text();
//            if (title.equals("")){
//                title=text;
//            }
//            content+=text+"\n";
//        }
//        System.out.println(content);
        addPost(title,content);
    }

    public boolean addPost(String title,String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setUserId(12L);
        boolean flag = postService.save(post);
        if (!flag) {
            throw new CommonException(StatuesCode.PARAMS_ERROR, "帖子参数错误");
        }
        /*es存储*/
        //获取mysql中的post
        Post byId = postService.getById(post.getId());
        boolean b = postService.saveAndUpdatePostEs(byId);
        if (!b) {
            throw new CommonException(StatuesCode.SYSTEM_EXCEPTION, "elastic serch error");
        }

        return true;
    }
}
