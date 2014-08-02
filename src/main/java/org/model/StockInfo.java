package org.model;

public class StockInfo {
	private String symbol;
	private String ceilingPrice;
	private String floorPrice;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCeilingPrice() {
		return ceilingPrice;
	}
	public void setCeilingPrice(String ceilingPrice) {
		this.ceilingPrice = ceilingPrice;
	}
	public String getFloorPrice() {
		return floorPrice;
	}
	public void setFloorPrice(String floorPrice) {
		this.floorPrice = floorPrice;
	}
}
