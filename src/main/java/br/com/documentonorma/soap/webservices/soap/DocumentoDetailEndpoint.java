package br.com.documentonorma.soap.webservices.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.com.documentonorma.DeleteDocumentRequest;
import br.com.documentonorma.DeleteDocumentResponse;
import br.com.documentonorma.DocumentDetail;
import br.com.documentonorma.GetDocumentDetailRequest;
import br.com.documentonorma.GetDocumentDetailResponse;
import br.com.documentonorma.InsertDocumentRequest;
import br.com.documentonorma.InsertDocumentResponse;
import br.com.documentonorma.Status;
import br.com.documentonorma.soap.webservices.bean.Documento;
import br.com.documentonorma.soap.webservices.service.DocumentDetailService;

@Endpoint
public class DocumentoDetailEndpoint {

	@Autowired
	DocumentDetailService service;
	
	@PayloadRoot( namespace= "http://documentonorma.com.br", localPart = "GetDocumentDetailRequest")
	@ResponsePayload
	public GetDocumentDetailResponse processDocumentDetailRequest ( @RequestPayload GetDocumentDetailRequest req) throws Exception {
		Documento documento = service.findByID(req.getId());
		if (documento == null) {
			
			throw new Exception("Documento não encontrado " + req.getId());
			
		}
		return convertToGetDocumentResponse(documento);
	}
	
	
	private GetDocumentDetailResponse convertToGetDocumentResponse(Documento documento) {
		GetDocumentDetailResponse resp = new GetDocumentDetailResponse();
		resp.setDocumentDetail(convertToDocumentDetail(documento));
        return resp;		
			
	}
	
	private DocumentDetail convertToDocumentDetail(Documento documento) {
		DocumentDetail documentDetail = new DocumentDetail();
		documentDetail.setId(documento.getId());
		documentDetail.setNorma(documento.getNorma());
		documentDetail.setDocumento(documento.getDocumento());
		return documentDetail;
		}
	
	
	private br.com.documentonorma.Status convertStatusSoap  (br.com.documentonorma.soap.webservices.messages.Status statusService) {
		if (statusService == br.com.documentonorma.soap.webservices.messages.Status.FAILURE)
			return Status.FAILURE;
		else
			return Status.SUCCESS;
		}
	
	@PayloadRoot( namespace= "http://documentonorma.com.br", localPart = "insertDocumentRequest")
	@ResponsePayload
	public InsertDocumentResponse insertDocumentRequest ( @RequestPayload InsertDocumentRequest req) {
		InsertDocumentResponse resp = new InsertDocumentResponse();
		resp.setStatus(convertStatusSoap(service.insert(convertDocument(req.getDocumentDetail()))));
	    return resp;
	}
	
	private Documento convertDocument( DocumentDetail document) {
		
		return new Documento(document.getId(), document.getNorma(), document.getDocumento());
	}
	
	@PayloadRoot( namespace= "http://documentonorma.com.br", localPart = "DeleteDocumentRequest")
	@ResponsePayload
	public DeleteDocumentResponse deleteDocumentRequest ( @RequestPayload DeleteDocumentRequest req) {
		DeleteDocumentResponse resp = new DeleteDocumentResponse();
		resp.setStatus(convertStatusSoap(service.deletebyID(req.getId())));
	    return resp;
	}
}
