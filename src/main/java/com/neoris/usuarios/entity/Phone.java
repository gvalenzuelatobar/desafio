package com.neoris.usuarios.entity;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PHONE" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "ID_PHONE")
	private Long idPhone;
	
	@Column(name = "NUMBER")
    private Integer number; 
	
	@Column(name = "CITYCODE")
    private Integer citycode; 
	
	@Column(name = "CONTRYCODE")
    private Integer contrycode;	
	
	@Column(name = "ID_USUARIO")
    private UUID idUsuario;

	
}
