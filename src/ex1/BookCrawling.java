package ex1;

import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class BookCrawling {

	public static void main(String[] args) {
		BookCrawling book = new BookCrawling();
		book.crawling();
	}

	public void crawling() {
		BookDAO dbb = BookDAO.newInstance();
		
		try {
			int k = 1;
			for (int i = 1; i <= 8; i++) {
			// yes24 월간 베스트셀러 페이지 링크
			Document doc = Jsoup.connect("http://www.yes24.com/24/category/bestseller?CategoryNumber=001&sumgb=09&year=2022&month=11&PageNumber=" + i).get();
			// 책 제목 가져오기
			Elements title = doc.select(".goodsTxtInfo p:eq(0) a:eq(0)");
			// 책 가격 가져오기
//			Elements price = doc.select(".priceB");
			// 줄거리 가져오기
			Elements summary = doc.select(".read");
			
				for (int j = 0; j < title.size(); j++) {
					try {
						// 도서 상세 페이지 링크
						Document doc2 = Jsoup.connect("http://www.yes24.com" + title.get(j).attr("href")).get();
						
//						Element summary = doc2.select(".infoWrap_txtInner").get(0);
						
						Element price = doc2.select(".yes_m").get(1);
						// 카테고리 가져오기
						Element category = doc2.select(".yesAlertDl li:eq(0) a").get(1);
						// 책 저자 가져오기
						Element author = doc2.select(".gd_auth").get(0);
						// 출판사 가져오기
						Element pub = doc2.select(".gd_pub").get(0);
						// 평점 가져오기
						Element grade = doc2.select(".yes_b").get(0);
						
						Element image = doc2.select(".gd_imgArea .gImg").get(0);
						
						Elements isbn = doc2.select(".b_size .txt");
						
						String price2 = price.text().toString();
						price2 = price2.replaceAll(",", "");
						int price3 = Integer.parseInt(price2);
						
						String isbn2 = isbn.text().split("ISBN13")[1].split("ISBN10")[0].split("전체 리뷰")[0].trim();
						long isbn3 = Long.parseLong(isbn2);
						
						System.out.println("순번: " + k);
						System.out.println("카테고리: " + category.text());
						System.out.println("제목: " + title.get(j).text());
						System.out.println("가격: " + price3);
						System.out.println("줄거리: " + summary.get(j).text());
						System.out.println("저자: " + author.text().split("정보")[0]);
						System.out.println("출판사: " + pub.text());
						System.out.println("평점: " + grade.text());
						System.out.println("ISBN: " + isbn.text().split("ISBN13")[1].split("ISBN10")[0].split("전체 리뷰")[0].trim());
						System.out.println("이미지URL: " + image.attr("src"));
						System.out.println("==================================================================================");
						
//						BookVO vo = new BookVO();
//						
//						vo.setNum(k);
//						vo.setCategory(category.text());
//						vo.setTitle(title.get(j).text());
//						vo.setPrice(price3);
//						vo.setSummary(summary.get(j).text());
//						vo.setAuthor(author.text().split("정보")[0]);
//						vo.setPub(pub.text());
//						vo.setRatingavg(grade.text());
//						vo.setPictureUrl(image.attr("src"));
//						vo.setIsbn(isbn3);
//						dbb.book45Insert(vo);
						
						k++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
