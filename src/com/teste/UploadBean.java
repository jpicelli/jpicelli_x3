package com.teste;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class UploadBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Part file;
	private List<ArquivoConfirmacao> arquivoConfirmacaoList = new ArrayList<>();
	private String mensagemErro;

	public void upload() throws IOException {
		mensagemErro = "";
		arquivoConfirmacaoList = new ArrayList<>();
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		String codigoCartorioStr = "";
		String numeroProtocoloCartorioStr = "";
		String dataProtocoloStr = "";

		try (Scanner scanner = new Scanner(file.getInputStream()).useDelimiter("\\A")) {

			if (scanner.hasNextLine()) scanner.nextLine();
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				if (scanner.hasNextLine()) {
					codigoCartorioStr = linha.substring(445, 447);
					numeroProtocoloCartorioStr = linha.substring(447, 457);
					dataProtocoloStr = linha.substring(458, 466);

					ArquivoConfirmacao arquivoConfirmacao = new ArquivoConfirmacao(codigoCartorioStr, 
							numeroProtocoloCartorioStr, dataProtocoloStr);
					arquivoConfirmacaoList.add(arquivoConfirmacao);
				}
			}
		} catch (IOException e) {
			arquivoConfirmacaoList = new ArrayList<>();
			mensagemErro = "IO Erro";
			e.printStackTrace();
		} catch (ParseException e) {
			arquivoConfirmacaoList = new ArrayList<>();
			mensagemErro = "Data do Protocolo Inválida: " + dataProtocoloStr;
			e.printStackTrace();
		} catch (NumberFormatException e) {
			arquivoConfirmacaoList = new ArrayList<>();
			mensagemErro = "Código de Cartório Inválido: " + codigoCartorioStr;
			e.printStackTrace();
		}
		context.redirect("listaArquivoConfirmacao.xhtml");
	}
	
	public void salvarTabela() {
		for (ArquivoConfirmacao arquivoConfirmacao : arquivoConfirmacaoList) {
			(new ArquivoConfirmacaoDAOImpl()).save(arquivoConfirmacao);
		}
	}

	public Part getFile() {
		return file;
	}

	public List<ArquivoConfirmacao> getArquivoConfirmacaoList() {
		return arquivoConfirmacaoList;
	}

	public void setArquivoConfirmacaoList(List<ArquivoConfirmacao> arquivoConfirmacaoList) {
		this.arquivoConfirmacaoList = arquivoConfirmacaoList;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
}
