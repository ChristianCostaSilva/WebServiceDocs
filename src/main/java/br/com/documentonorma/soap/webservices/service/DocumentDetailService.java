package br.com.documentonorma.soap.webservices.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.documentonorma.soap.webservices.bean.Documento;
import br.com.documentonorma.soap.webservices.messages.Status;
import br.com.documentonorma.soap.webservices.repositorio.DocumentoRepositorio;

@Component
public class DocumentDetailService {

	
	@Autowired
	private DocumentoRepositorio repositorio;
	
	public Documento findByID( Integer id) {
		Optional<Documento> DocumentOptional = repositorio.findById(id);
		if (DocumentOptional.isPresent() ) {
			return DocumentOptional.get();
		}
		return null;
	}
	
	 public Status deletebyID (Integer id) {
		 try {
			repositorio.deleteById(id);
			return Status.SUCCESS;
		} catch (Exception e) {
			return Status.FAILURE;
		}
				 
	 }
	 
	 
	 public Status insert (Documento document) {
		 try {
			    
				 repositorio.save(document);
//				id_temp = temp.getId();
				return Status.SUCCESS;
			} catch (Exception e) {
				return Status.FAILURE;
			}
		 
	 }
	
/*   private static List<Documento> documentos = new ArrayList<>();
	
	public Documento findByID( int id) {
		Optional<Documento> DocumentOptional = documentos.stream().filter
				(c-> c.getId() == id).findAny();
		if (DocumentOptional.isPresent() ) {
			return DocumentOptional.get();
		}
		return null;
	}
*/	
}
