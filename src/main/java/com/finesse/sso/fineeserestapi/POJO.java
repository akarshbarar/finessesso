package com.finesse.sso.fineeserestapi;

public class POJO {

	private String code;
	private String url;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Model [code=" + code + ", url=" + url + "]";
	}

	public POJO(String code, String url) {
		super();
		this.code = code;
		this.url = url;
	}
	
}
