package kr.or.bit.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class NewsService {
  public String getNews() {
    String clientId = "vqLcW7JqiYZue0zeuYqs";
    String clientSecret = "3ItRGLhcxI";
    StringBuffer resp = new StringBuffer();

    try {
      String text = URLEncoder.encode("IT", "UTF-8");
      String apiURL = "https://openapi.naver.com/v1/search/news.json?query="
          + text + "&display=5";
      URL url = new URL(apiURL);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("X-Naver-Client-Id", clientId);
      con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
      int responseCode = con.getResponseCode();
      BufferedReader br;
      if (responseCode == 200) {
        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      } else {
        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
      }
      String inputLine;
      
      while ((inputLine = br.readLine()) != null) {
        resp.append(inputLine);
      }
      
      br.close();
    } catch (UnsupportedEncodingException e) {
      System.out.println("뉴스: " + e.getMessage());
    } catch (MalformedURLException e) {
      System.out.println("뉴스: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("뉴스: " + e.getMessage());
    }
    
    return resp.toString();
  }
}
