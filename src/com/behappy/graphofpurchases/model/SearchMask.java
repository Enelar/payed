package com.behappy.graphofpurchases.model;

public class SearchMask {
	/*
	 * $1 - catch number 
	 * $2 - catch string
	 * $* - skip symbols until next literal
	 */
	private String mask = null;

	public SearchMask(String mask) {
		this.mask = mask;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}
}
