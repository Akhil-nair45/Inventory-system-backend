package com.example.telusko.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sold {

	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int oid;
	
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public Sold(int oid, String pName, Integer qty) {
		super();
		this.oid = oid;
		this.pName = pName;
		this.qty = qty;
	}

	private String pName;
	
	private Integer qty;
	
	
	
	public Sold(String pName, Integer qty) {
		super();
		this.pName = pName;
		this.qty = qty;
	}

	public Sold() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Sold [oid=" + oid + ", pName=" + pName + ", qty=" + qty + "]";
	}

	

	
	
	
}
