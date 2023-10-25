package com.demo.entity;

import org.springframework.web.multipart.MultipartFile;

public class Goods {
    private String goodid;
    private String gName;
    private double goprice;
    private double grprice;
    private int gstore;
    private String gricture;
    private String gstypeid;
    private MultipartFile logoImage;
    private String typename;
	public String getGoodid() {
		return goodid;
	}
	public void setGoodid(String goodid) {
		this.goodid = goodid;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public double getGoprice() {
		return goprice;
	}
	public void setGoprice(double goprice) {
		this.goprice = goprice;
	}
	public double getGrprice() {
		return grprice;
	}
	public void setGrprice(double grprice) {
		this.grprice = grprice;
	}
	public int getGstore() {
		return gstore;
	}
	public void setGstore(int gstore) {
		this.gstore = gstore;
	}
	public String getGricture() {
		return gricture;
	}
	public void setGricture(String gricture) {
		this.gricture = gricture;
	}
	public String getGstypeid() {
		return gstypeid;
	}
	public void setGstypeid(String gstypeid) {
		this.gstypeid = gstypeid;
	}
	public MultipartFile getLogoImage() {
		return logoImage;
	}
	public void setLogoImage(MultipartFile logoImage) {
		this.logoImage = logoImage;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
}
