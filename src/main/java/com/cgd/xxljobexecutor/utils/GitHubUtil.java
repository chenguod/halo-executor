package com.cgd.xxljobexecutor.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.TimeZone;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/11/24 22:32
 */
@Slf4j
public class GitHubUtil {

    private final static String USER_AGENT = "https://www.chenmx.net";

    private GitHubUtil() {
    }

    public static JSONArray getGitHubRepos(String githubUserId) {
        try {
            HttpResponse res = HttpRequest.get("https://api.github.com/users/" + githubUserId + "/repos").
                    connectionTimeout(20000).timeout(60000).header("User-Agent", USER_AGENT).send();
            if (HttpServletResponse.SC_OK != res.statusCode()) {
                return null;
            }
            res.charset("UTF-8");
            JSONArray result = JSONArray.parseArray(res.bodyText());

            String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat(pattern);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            JSONArray compatibleResult = new JSONArray();
            for (int i = 0; i < result.size(); i++) {
                JSONObject resultObject = result.getJSONObject(i);
                JSONObject compatibleObject = new JSONObject();

                compatibleObject.put("githubrepoId", resultObject.getString("id"));
                compatibleObject.put("githubrepoStatus", 0);
                compatibleObject.put("oId", "" + System.currentTimeMillis());
                compatibleObject.put("githubrepoDescription", resultObject.getString("description"));
                compatibleObject.put("githubrepoHomepage", resultObject.getString("homepage"));
                compatibleObject.put("githubrepoForksCount", resultObject.getLong("forks_count"));
                compatibleObject.put("githubrepoOwnerId", resultObject.getJSONObject("owner").getString("id"));
                compatibleObject.put("githubrepoStargazersCount", resultObject.getLong("stargazers_count"));
                compatibleObject.put("githubrepoWatchersCount", resultObject.getLong("watchers_count"));
                compatibleObject.put("githubrepoOwnerLogin", resultObject.getJSONObject("owner").getString("login"));
                compatibleObject.put("githubrepoHTMLURL", resultObject.getString("html_url"));
                compatibleObject.put("githubrepoLanguage", resultObject.getString("language"));
                compatibleObject.put("githubrepoUpdated", simpleDateFormat.parse(resultObject.getString("updated_at")).getTime());
                compatibleObject.put("githubrepoName", resultObject.getString("name"));
                compatibleObject.put("githubrepoFullName", resultObject.getString("full_name"));

                compatibleResult.add(compatibleObject);
            }

            // 排序
            ArrayList<String> tempResultList = new ArrayList<>();
            for (int i = 0; i < compatibleResult.size(); i++) {
                JSONObject compatibleObject = compatibleResult.getJSONObject(i);
                tempResultList.add(compatibleObject.toString());
            }
            tempResultList.sort((o1, o2) -> {
                int o1star = JSONObject.parseObject(o1).getInteger("githubrepoStargazersCount");
                int o2star = JSONObject.parseObject(o2).getInteger("githubrepoStargazersCount");
                return o2star - o1star;
            });
            JSONArray sortedCompatibleResult = new JSONArray();
            for (String json : tempResultList) {
                sortedCompatibleResult.add(JSONObject.parseObject(json));
            }

            return sortedCompatibleResult;
        } catch (JSONException e) {
            log.error("Gets GitHub repos failed because the request has been reached GitHub's limit, try again at later.");

            return null;
        } catch (Exception e) {
            log.error("Gets GitHub repos failed, please check your network connection to github.com");

            return null;
        }
    }


    public static boolean updateFile(String pat, String loginName, String repoName, String filePath, byte[] content) {
        String fullRepoName = loginName + "/" + repoName;
        try {
            HttpResponse response = HttpRequest.get("https://api.github.com/repos/" + fullRepoName + "/git/trees/main").header("Authorization", "token " + pat).
                    connectionTimeout(7000).timeout(60000).header("User-Agent", USER_AGENT).send();
            int statusCode = response.statusCode();
            response.charset("UTF-8");
            String responseBody = response.bodyText();
            if (200 != statusCode && 409 != statusCode) {
                log.error("Get git tree of file [" + filePath + "] failed: " + responseBody);
                return false;
            }

            JSONObject body = new JSONObject();
            body.put("message", ":memo: 更新博客");
            body.put("content", Base64.getEncoder().encodeToString(content));
            if (200 == statusCode) {
                JSONObject responseData = JSONObject.parseObject(responseBody);
                JSONArray tree = responseData.getJSONArray("tree");
                for (int i = 0; i < tree.size(); i++) {
                    JSONObject file = tree.getJSONObject(i);
                    if (StringUtils.equals(filePath, file.getString("path"))) {
                        body.put("sha", file.getString("sha"));
                        break;
                    }
                }
            }

            response = HttpRequest.put("https://api.github.com/repos/" + fullRepoName + "/contents/" + filePath).header("Authorization", "token " + pat).
                    connectionTimeout(7000).timeout(60000 * 2).header("User-Agent", USER_AGENT).bodyText(body.toString()).send();
            statusCode = response.statusCode();
            response.charset("UTF-8");
            responseBody = response.bodyText();
            if (200 != statusCode && 201 != statusCode) {
                log.error("Updates repo [" + repoName + "] file [" + filePath + "] failed: " + responseBody);
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("Updates repo [" + repoName + "] file [" + filePath + "] failed: " + e.getMessage());
            return false;
        }
    }


    public static boolean createOrUpdateGitHubRepo(String pat, String loginName, String repoName, String repoDesc, String repoHomepage) {
        try {
            JSONObject body = new JSONObject();
            body.put("name", repoName);
            body.put("description", repoDesc);
            body.put("homepage", repoHomepage);
            body.put("has_wiki", false);
            body.put("has_projects", false);
            HttpResponse response = HttpRequest.post("https://api.github.com/user/repos").header("Authorization", "token " + pat).
                    connectionTimeout(7000).timeout(30000).header("User-Agent", USER_AGENT).bodyText(body.toString()).send();
            int statusCode = response.statusCode();
            response.charset("UTF-8");
            String responseBody = response.bodyText();
            if (201 != statusCode && 422 != statusCode) {
                log.error("Creates GitHub repo [" + repoName + "] failed: " + responseBody);
                return false;
            }
            if (201 == statusCode) {
                return true;
            }

            response = HttpRequest.patch("https://api.github.com/repos/" + loginName + "/" + repoName).header("Authorization", "token " + pat).
                    connectionTimeout(7000).timeout(30000).header("User-Agent", USER_AGENT).bodyText(body.toString()).send();
            statusCode = response.statusCode();
            responseBody = response.bodyText();
            if (200 != statusCode) {
                log.error("Updates GitHub repo [" + repoName + "] failed: " + responseBody);
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("Creates or updates GitHub repo failed: " + e.getMessage());
            return false;
        }
    }

    public static JSONObject getGitHubUser(String pat) {
        try {
            HttpResponse response = HttpRequest.get("https://api.github.com/user").header("Authorization", "token " + pat).
                    connectionTimeout(7000).timeout(30000).header("User-Agent", USER_AGENT).send();
            if (200 != response.statusCode()) {
                return null;
            }
            response.charset("UTF-8");
            return JSONObject.parseObject(response.bodyText());
        } catch (Exception e) {
            log.error("Gets GitHub user info failed: " + e.getMessage());
            return null;
        }
    }
}
