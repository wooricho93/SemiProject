package ex1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AlbumCrawling {

	public static void main(String[] args) {
		AlbumCrawling album = new AlbumCrawling();
		album.crwaling();
	}
	
	public void crwaling() {
		AlbumDAO dao = AlbumDAO.newInstance();
		
		try {
			int k = 1;
			for (int i = 1; i <= 10; i++) {
				Document doc = Jsoup.connect("http://www.yes24.com/24/category/bestseller?CategoryNumber=003&sumgb=09&year=2022&month=12&PageNumber=" + i).get();
				
				Elements base = doc.select(".title a:eq(0)");
				
				for (int j = 0; j < base.size(); j++) {
					try {
						Document doc2 = Jsoup.connect("http://www.yes24.com" + base.get(j).attr("href")).get();
						// 앨범 카테고리 가져오기
						Element category = doc2.select(".yesAlertDl li:eq(0) a").get(1);
						// 앨범 제목 가져오기
						Element title = doc2.select(".gd_name").get(0);
						// 앨범 가격 가져오기
						Element price = doc2.select(".yes_m").get(1);
						String price2 = price.text().toString();
						price2 = price2.replaceAll(",", "");
						int price3 = Integer.parseInt(price2);
						// 앨범 주인 가져오기
						Element singer = doc2.select(".gd_auth").get(0);
						// 앨범 제작사 가져오기
						Element ent = doc2.select(".gd_pub").get(0);
						// 발매일 가져오기
						Element releasedate = doc2.select(".gd_date").get(0);
						// 이미지 가져오기
						Element image = doc2.select(".gd_imgArea img").get(0);
						
						String image2 = image.toString();
						
						String productNum = image2.split("goods/")[1].split("/XL")[0];
						Long productNum2 = Long.parseLong(productNum);
						
//						System.out.println("번호: " + k);
//						System.out.println("카테고리: " + category.text());
//						System.out.println("앨범명: " + title.text());
//						System.out.println("가격: " + price3 + "원");
//						System.out.println("가수: " + singer.text().split("정보")[0]);
//						System.out.println("제작사: " + ent.text());
//						System.out.println("발매일: " + releasedate.text());
//						System.out.println("사진: " + image.attr("src"));
//						System.out.println("상품번호: " + productNum);
//						System.out.println("============================================================================================================");
						
						AlbumVO vo = new AlbumVO();
						
						vo.setNum(k);
						vo.setProductNum(productNum2);
						vo.setCategory(category.text());
						vo.setTitle(title.text());
						vo.setPrice(price3);
						vo.setSinger(singer.text().split("정보")[0]);
						vo.setEnt(ent.text());
						vo.setReleasedate(releasedate.text());
						vo.setPictureurl(image.attr("src"));
						
						dao.albumInsert(vo);
						
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
