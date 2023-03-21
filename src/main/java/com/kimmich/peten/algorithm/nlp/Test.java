package com.kimmich.peten.algorithm.nlp;
import java.util.*;
import com.hankcs.hanlp.HanLP;

public class Test {

    public static List<String> extractTags(String text, int k) {
        List<String> sentences = HanLP.extractSummary(text, k);
        Set<String> vocabulary = new HashSet<>(Arrays.asList("样例", "文档", "关键词", "TF-IDF", "算法", "术语", "语料库"));
        List<String> keywords = extractKeywords(sentences, vocabulary);
        return keywords;
    }

    private static List<String> extractKeywords(List<String> sentences, Set<String> vocabulary) {
        List<String> keywords = new ArrayList<>();
        Map<String, Double> tfidf = new HashMap<>();
        for (String sentence : sentences) {
            List<String> words = HanLP.extractKeyword(sentence, 20);
            for (String word : words) {
                if (vocabulary.contains(word)) {
                    tfidf.put(word, tfidf.getOrDefault(word, 0.0) + 1.0);
                }
            }
        }
        for (Map.Entry<String, Double> entry : tfidf.entrySet()) {
            keywords.add(entry.getKey());
        }
        keywords.sort((a, b) -> tfidf.get(b).compareTo(tfidf.get(a)));
        if (keywords.size() > 10) {
            keywords = keywords.subList(0, 10);
        }
        return keywords;
    }

    public static void main(String[] args) {
        String text = "这是一篇关于文本标签提取的样例文档，我们将介绍如何使用TF-IDF算法提取关键词，并将其作为标签来描述文本的内容。";
        List<String> tags = extractTags(text, 5);
        System.out.println(tags);
    }
}
