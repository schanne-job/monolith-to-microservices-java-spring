package com.example.answerservice.model;

import java.util.ArrayList;
import java.util.List;

public class ParsedQuestion {
    public ArrayList<String> tokens;

    public ParsedQuestion(ArrayList<String> tokens) {
        this.tokens = tokens;
    }

    public ParsedQuestion() {}

    public ArrayList<String> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<String> tokens) {
        this.tokens = tokens;
    }
}
