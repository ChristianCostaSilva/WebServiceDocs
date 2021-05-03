package br.com.documentonorma.soap.webservices.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.documentonorma.soap.webservices.bean.Documento;

public interface DocumentoRepositorio extends JpaRepository<Documento, Integer>{

}
