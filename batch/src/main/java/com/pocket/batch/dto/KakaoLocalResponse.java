package com.pocket.batch.dto;

import java.util.List;

public class KakaoLocalResponse {

    private List<KakaoDocument> documents;

    public List<KakaoDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<KakaoDocument> documents) {
        this.documents = documents;
    }
}
