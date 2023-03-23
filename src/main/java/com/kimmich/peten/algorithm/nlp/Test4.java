package com.kimmich.peten.algorithm.nlp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import static java.lang.Math.*;

// 体征向量的提取
public class Test4 {

    // 构建词汇表，记录每个单词在文本中出现的次数
    private static Map<String, Integer> buildVocabulary(String text) {
        Map<String, Integer> vocabulary = new HashMap<>();
        for (Term term : HanLP.segment(text)) {
            String word = term.word.trim();
            if (!word.isEmpty()) {
                vocabulary.put(word, vocabulary.getOrDefault(word, 0) + 1);
            }
        }
        return vocabulary;
    }

    // 计算 TF-IDF 值
    private static double computeTfIdf(String word, Map<String, Integer> vocabulary, Map<String, Integer> documentFrequencies, int numDocuments) {
        int termFrequency = vocabulary.getOrDefault(word, 0);
        double inverseDocumentFrequency = log((double) numDocuments / documentFrequencies.getOrDefault(word, 1));
        return termFrequency * inverseDocumentFrequency;
    }

    // 提取特征，返回一个向量
    public static double[] extractFeatures(String text, Map<String, Integer> documentFrequencies, int numDocuments) {
        Map<String, Integer> vocabulary = buildVocabulary(text);
        Set<String> words = vocabulary.keySet();
        double[] features = new double[words.size()];
        int i = 0;
        for (String word : words) {
            features[i++] = computeTfIdf(word, vocabulary, documentFrequencies, numDocuments);
        }
        return features;
    }

    public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

}
