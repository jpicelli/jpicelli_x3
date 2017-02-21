package com.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ARQUIVO_CONFIRMACAO")
public class ArquivoConfirmacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STOCK_ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "CODIGO_CARTORIO")
	private Integer codigoCartorio;

	@Column(name = "NUMERO_PROTOCOLO_CARTORIO")
	private String numeroProtocoloCartorio;
	
	@Column(name = "DATA_PROTOCOLO")
	private Date dataProtocolo;

	public ArquivoConfirmacao() {
		super();
	}
	
	public ArquivoConfirmacao(String codigoCartorioStr, String numeroProtocoloCartorioStr, String dataProtocoloStr) throws ParseException {
		Integer codigoCartorio = Integer.parseInt(codigoCartorioStr);
		
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		Date dataProtocolo = formatter.parse(dataProtocoloStr);

		this.codigoCartorio = codigoCartorio;
		this.numeroProtocoloCartorio = numeroProtocoloCartorioStr;
		this.dataProtocolo = dataProtocolo;
	}
	
	public Integer getCodigoCartorio() {
		return codigoCartorio;
	}
	public void setCodigoCartorio(Integer codigoCartorio) {
		this.codigoCartorio = codigoCartorio;
	}
	public String getNumeroProtocoloCartorio() {
		return numeroProtocoloCartorio;
	}
	public void setNumeroProtocoloCartorio(String numeroProtocoloCartorio) {
		this.numeroProtocoloCartorio = numeroProtocoloCartorio;
	}
	public Date getDataProtocolo() {
		return dataProtocolo;
	}
	public void setDataProtocolo(Date dataProtocolo) {
		this.dataProtocolo = dataProtocolo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
