package com.kimmich.peten.algorithm.nlp;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.*;

public class Test2 {
    private static List<String> extractKeywords(List<String> corpus, Set<String> vocabulary) {
        Map<String, Integer> documentFrequencies = new HashMap<>();
        Map<String, Map<String, Integer>> termFrequencies = new HashMap<>();

        // 计算每个文档中每个词的出现次数
        for (String document : corpus) {
            List<Term> terms = HanLP.segment(document);
            for (Term term : terms) {
                String word = term.word.toLowerCase();
                if (vocabulary.contains(word)) {
                    Map<String, Integer> documentTermFrequencies = termFrequencies.getOrDefault(document, new HashMap<>());
                    documentTermFrequencies.put(word, documentTermFrequencies.getOrDefault(word, 0) + 1);
                    termFrequencies.put(document, documentTermFrequencies);
                }
            }

            // 计算每个文档中每个词是否出现
            Set<String> uniqueTerms = new HashSet<>();
            for (Term term : terms) {
                String word = term.word.toLowerCase();
                if (vocabulary.contains(word)) {
                    uniqueTerms.add(word);
                }
            }

            // 计算每个词在多少个文档中出现
            for (String term : uniqueTerms) {
                documentFrequencies.put(term, documentFrequencies.getOrDefault(term, 0) + 1);
            }
        }

        // 计算每个词的TF-IDF值
        List<Map.Entry<String, Double>> scores = new ArrayList<>();
        for (String term : vocabulary) {
            double score = 0;
            for (Map.Entry<String, Map<String, Integer>> entry : termFrequencies.entrySet()) {
                String document = entry.getKey();
                Map<String, Integer> documentTermFrequencies = entry.getValue();
                double tf = (double) documentTermFrequencies.getOrDefault(term, 0) / documentTermFrequencies.size();
                double idf = Math.log((double) corpus.size() / documentFrequencies.getOrDefault(term, 0));
                score += tf * idf;
            }
            scores.add(new AbstractMap.SimpleEntry<>(term, score));
        }

        // 按照得分排序并返回前k个词作为关键词
        scores.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        List<String> keywords = new ArrayList<>();
        int k = 10;
        for (int i = 0; i < k && i < scores.size(); i++) {
            keywords.add(scores.get(i).getKey());
        }

        return keywords;
    }

    public static void main(String[] args) {
        String text = "这是一个样例文档。它包含多个句子，我们想要从中提取出关键词。这是通过TF-IDF算法完成的，该算法" +
                "计算了每个术语在整个语料库中的频率相对于其在文档中的重要性。";
//        List<String> corpus = Arrays
        String[] sentences = text.split("[。？！]");
        Set<String> vocabulary = new HashSet<>(Arrays.asList("样例", "文档", "关键词", "TF-IDF", "算法", "术语", "语料库"));
        List<String> keywords = extractKeywords(Arrays.asList(sentences), vocabulary);
        System.out.println(keywords);
    }
}
