package com.github.crummish.legallyme.document;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Loads and parses a file from a source directory into a standardized Document.
 */
public class DocumentLoader {
    private String basePath;

    public DocumentLoader(String basePath) {
        this.basePath = (basePath.endsWith("/") ? basePath.substring(0, basePath.length() - 1) : basePath);
    }

    public Document getDocument(State state, DocumentType documentType) throws IOException {
        String stateUrl = basePath + "/" + state.name();
        Document htmlDocument = Jsoup.connect(stateUrl).get();

        return htmlDocument;
    }
}
