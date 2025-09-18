package com.Navpaksa.Navpaksa.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

	public Product() {
		super();
	}
	public Product(UUID id, String name, int quantity, String pack, String hsn, String batchNo, LocalDate mfg,
			LocalDate exp, double mrp, double rate, float igst, float cgst, float sgst, double amount, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.pack = pack;
		this.hsn = hsn;
		this.batchNo = batchNo;
		this.mfg = mfg;
		this.exp = exp;
		this.mrp = mrp;
		this.rate = rate;
		this.igst = igst;
		this.cgst = cgst;
		this.sgst = sgst;
		this.amount = amount;
		this.company=company;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String name;
	private int quantity;
	private String pack;
	private String hsn;
	private String batchNo;
	private LocalDate mfg;
	private LocalDate exp;
	private double mrp;
	private double rate;
	@Column(nullable = true)
	private float igst;
	@Column(nullable = true)
	private float cgst;
	@Column(nullable = true)
	private float sgst;
	private double amount;
	@ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public String getHsn() {
		return hsn;
	}
	public void setHsn(String hsn) {
		this.hsn = hsn;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public LocalDate getMfg() {
		return mfg;
	}
	public void setMfg(LocalDate mfg) {
		this.mfg = mfg;
	}
	public LocalDate getExp() {
		return exp;
	}
	public void setExp(LocalDate exp) {
		this.exp = exp;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public float getIgst() {
		return igst;
	}
	public void setIgst(float igst) {
		this.igst = igst;
	}
	public float getCgst() {
		return cgst;
	}
	public void setCgst(float cgst) {
		this.cgst = cgst;
	}
	public float getSgst() {
		return sgst;
	}
	public void setSgst(float sgst) {
		this.sgst = sgst;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
}
