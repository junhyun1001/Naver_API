import service.NaverService;

public class Application {

    public static void main(String[] args) {
        /*
            curl "https://openapi.naver.com/v1/search/news.xml?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&sort=sim" \
                -H "X-Naver-Client-Id: {애플리케이션 등록 시 발급받은 클라이언트 아이디 값}" \
                -H "X-Naver-Client-Secret: {애플리케이션 등록 시 발급받은 클라이언트 시크릿 값}"
         */

        final String NAVER_CLIENT_ID = System.getenv("NAVER_CLIENT_ID");
        final String NAVER_CLIENT_SECRET = System.getenv("NAVER_CLIENT_SECRET");

        NaverService naverSearchService = new NaverService(NAVER_CLIENT_ID, NAVER_CLIENT_SECRET);

        String body = naverSearchService.searchNews("삼성전자");

        System.out.println(body);
    }
}