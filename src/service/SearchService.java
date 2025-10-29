package service;

/*
    curl "https://openapi.naver.com/v1/search/news.xml?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&sort=sim" \
        -H "X-Naver-Client-Id: {애플리케이션 등록 시 발급받은 클라이언트 아이디 값}" \
        -H "X-Naver-Client-Secret: {애플리케이션 등록 시 발급받은 클라이언트 시크릿 값}"
 */

import model.HttpException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class SearchService implements NaverService {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String clientId;
    private final String clientSecret;

    public SearchService(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public String searchNews(String query) {
        return sendNaverRequest(client, "news", query);
    }

    @Override
    public String searchKin(String query) {
        return sendNaverRequest(client, "kin", query);
    }

    private String sendNaverRequest(HttpClient client, String endPoint, String query) {
        String baseUrl = "https://openapi.naver.com/v1/search/%s.json".formatted(endPoint);
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("%s?query=%s".formatted(baseUrl, encodedQuery)))
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new HttpException(response.statusCode(), response.body());
            }
            return response.body();
        } catch (IOException | InterruptedException | HttpException e) {
            throw new RuntimeException(e);
        }
    }
}
