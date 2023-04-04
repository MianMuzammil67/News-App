package com.example.newsapp.Model;

import java.util.List;

public class ApiResponse {
String status;
int totalResults;
List<ApiResponse> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ApiResponse> getArticles() {
        return articles;
    }

    public void setArticles(List<ApiResponse> articles) {
        this.articles = articles;
    }
}
