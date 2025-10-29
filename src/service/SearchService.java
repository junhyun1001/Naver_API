package service;

public class SearchService extends AbstractNaverService implements NaverService {

    private String clientId;
    private String clientSecret;

    public SearchService(String clientId, String clientSecret) {
        super(clientId, clientSecret);
    }

    @Override
    public String searchNews(String query) {
        return sendNaverRequest("news", query);
    }

    @Override
    public String searchKin(String query) {
        return sendNaverRequest("kin", query);
    }
}
