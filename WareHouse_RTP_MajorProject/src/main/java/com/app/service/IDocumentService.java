package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.model.Document;

public interface IDocumentService {

	public void saveDocument(Document document);
	public List<Object[]> getDocumentIdAndName();
	public Optional<Document> getDocumentById(Integer id);
	public void deleteDocument(Integer id);
	Document getOneDocument(Integer id);
	
	//Page<Object[]> getDocumentIdAndNameByPagenation(Pageable pageable);
}
