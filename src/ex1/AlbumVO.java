package ex1;

/*
NUM NUMBER(5) NOT NULL,
TITLE VARCHAR2(1000),
PRICE NUMBER(20),
SINGER VARCHAR2(1000),
ENT VARCHAR2(1000),
CATEGORY VARCHAR2(1000),
RELEASEDATE VARCHAR2(20),
CONTENT VARCHAR2(4000),
PICTUREURL VARCHAR2(4000)
 */

public class AlbumVO {
	private int num;
	private Long productNum;
	private String title;
	private int price;
	private String singer;
	private String ent;
	private String category;
	private String releasedate;
	private String pictureurl;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getEnt() {
		return ent;
	}
	public void setEnt(String ent) {
		this.ent = ent;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public Long getProductNum() {
		return productNum;
	}
	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}
}
