package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.exception.DocumentNotFoundException;
import com.app.exception.OrderMethodNotFoundException;
import com.app.model.Document;
import com.app.model.OrderMethod;
import com.app.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements IDocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	
	@Override
	public void saveDocument(Document document) {
		documentRepository.save(document);
		
	}

	@Override
	public List<Object[]> getDocumentIdAndName() {
		
		return documentRepository.getDocumentIdAndName();
	}

	@Override
	public Optional<Document> getDocumentById(Integer id) {
		
		return documentRepository.findById(id);
	}

	@Override
	public Document getOneDocument(Integer id) {
		
		return documentRepository.findById(id).orElseThrow(()->new DocumentNotFoundException("Document "+id+" does not exist !"));
	}
	
	@Override
	public void deleteDocument(Integer id) {
		Document doc = getOneDocument(id);
		documentRepository.delete(doc);
	}

//	@Override
//	public Page<Object[]> getDocumentIdAndNameByPagenation(Pageable pageable) {
//		
//		return documentRepository.getDocumentIdAndNameByPagenation(pageable);
//	}

}
