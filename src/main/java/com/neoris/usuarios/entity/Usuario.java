package com.neoris.usuarios.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USUARIO" , uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Usuario {

	@Id	
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID_USUARIO")
	private UUID  idUsuario;
	
	@Column(name = "NAME")
    private String username; 	
	
	@Column(name = "EMAIL")
    private String email; 
    
    @Column(name = "PASSWORD")
    private String password; 
    
    @Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "FECHA_MODIFICACION")
	private Date fechaModificacion;

	@Column(name = "FECHA_INGRESO")
	private Date fechaIngreso;
	
	@Column(name = "ACTIVO")
	private Boolean activo;
	
	@Column(name = "TOKEN")
	private String token;
    
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USUARIO")
    private List<Phone> phone;

	
}
