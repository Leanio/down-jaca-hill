package br.edu.ifpb.mt.ads.dac.util.qualifiers.impl;

import java.io.Serializable;

public class WrapperLong implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	
	public WrapperLong() {
		
	}

	public WrapperLong(Long codigo) {
		super();
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	
}
