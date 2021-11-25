package com.cgd.xxljobexecutor.xxlJob;

import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.dao.halo.PostsDao;
import com.cgd.xxljobexecutor.utils.GitHubUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/11/25 21:54
 */
@Component
public class BackUp {

    @Autowired
    private PostsDao postsDao;

    @XxlJob("BackUpHandler")
    public ReturnT backUp(String param) {
        String site = "https://www.chenmx.net";
        JSONObject gitHubUser = GitHubUtil.getGitHubUser(param);
        String loginName = gitHubUser.getString("login");
        String repository = "halo-blog";
        String repositoryDesc = "✍️ 晓果冻的个人博客 - 一个热爱生活的90后";
        GitHubUtil.createOrUpdateGitHubRepo(param, loginName, repository, repositoryDesc, site);
        List<String> list = postsDao.getPosts("'"+site+"?p='");
        StringBuilder sb = new StringBuilder();
        list.stream().forEach(e -> {
            sb.append(e);
        });
        String readme = "<p align=\"center\"><img alt=\"晓果冻的个人博客\" src=\"https://cdn.jsdelivr.net/gh/chenguod/picture/202111251402173.jpg\" width=\"130\"></p><h2 align=\"center\">\n" +
                "\n" +
                "晓果冻的个人博客\n" +
                "</h2>\n" +
                "\n" +
                "<h4 align=\"center\">一个热爱生活的90后</h4>\n" +
                "<p align=\"center\"><a title=\"晓果冻的个人博客\" target=\"_blank\" href=\"https://github.com/chenguod/halo-blog\"><img src=\"https://img.shields.io/github/last-commit/chenguod/halo-blog.svg?style=flat-square&color=FF9900\"></a>\n" +
                "<a title=\"GitHub repo size in bytes\" target=\"_blank\" href=\"https://github.com/chenguod/halo-blog\"><img src=\"https://img.shields.io/github/repo-size/chenguod/halo-blog.svg?style=flat-square\"></a>\n" +
                "<a title=\"由halo驱动\" target=\"_blank\" href=\"https://github.com/halo-dev/halo\"><img src=\"https://img.shields.io/badge/halo-1.4.13-f1e05a.svg?style=flat-square&color=blueviolet\"></a>\n" +
                "<a title=\"Hits\" target=\"_blank\" href=\"https://github.com/dwyl/hits\"><img src=\"http://hits.dwyl.com/chengd/halo-blog.svg\"></a></p>\n" +
                "\n" +
                "### 最新\n" +
                "\n" +
                sb +
                "\n" +
                "\n" +
                "\n" +
                "---\n" +
                "\n" +
                "本仓库通过 [halo-executor](https://github.com/chenguod/halo-executor) 自动进行同步更新 ❤️ \n" +
                "\n";

        Boolean ok = GitHubUtil.updateFile(param, loginName, repository, "README.md", readme.getBytes(Charset.forName("UTF-8")));
        if (ok) {
            return ReturnT.SUCCESS;
        } else {
            return ReturnT.FAIL;
        }
        //todo  将markdown备份到github
        //GitHubUtil.updateFile(param, loginName, repository, "backup.zip", zipData);
    }
}
