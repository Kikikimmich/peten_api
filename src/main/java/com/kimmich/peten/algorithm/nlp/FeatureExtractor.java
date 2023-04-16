package com.kimmich.peten.algorithm.nlp;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 基于hannlp和TF-IDF模型提取文章的特征信息，返回特征向量，去掉一些特征无关的单词
*
* */
public class FeatureExtractor {
    private static final int TOP_K = 50; // 选取TF-IDF排名前K的关键词作为特征
    private static final double MIN_TF_IDF = 0.1; // 过滤掉TF-IDF小于该值的关键词
    private static final String[] STOP_WORDS = {"的", "了", "是", "在", "我", "有", "和", "就", "不", "人", "都", "一", "一个", "上", "也", "很", "到", "说", "要", "去", "你", "会", "着", "没有", "看", "好", "自己", "这"};


    private static String content = "基于文本特征的内容推荐是一种利用自然语言处理技术，从用户历史行为和物品信息中挖掘关键词、主题和情感等文本特征，" +
            "通过计算相似度来预测用户可能感兴趣的内容，并向其推荐相关的物品或信息。具体来说，该算法会对用户历史行为和物品信息进行文本分析，" +
            "提取关键词、主题和情感等特征，并根据这些特征计算用户与物品之间的相似度，从而推荐最符合用户需求的内容。基于文本特征的内容推荐可以应用于各种领域，" +
            "如搜索引擎、社交媒体、新闻资讯等，已经成为提高用户满意度和参与度的重要推荐手段。";


    private static String content2 = "其中，TF表示该词语在文档中出现的次数（词频），DF表示该词语在所有文档中出现的文档频率（Document Frequency），N表示文档总数。当一个词语在所有文档中都出现时，DF的值为N，log(N/DF)的值为0，表示该词语对于区分不同文档的重要性为0";

    private static String longContent = "热度必须是变化的，而且一个内容或者事件不能长时间处于高热度或者是低热度的状态，也就是说热度模型的设计必须包含热度的增长和热度的消退。" +
            "高热度内容需要随着函数因子以一定的速度消退，低热度的内容则需要一定的刺激函数触发其热度的增长。"
            + "内容推荐是以内容输出为主的网站或系统维持用户数量、系统活跃的核心内容。随着用户需求和个性化呈现的不断提高，基于协同过滤、深度学习、自然语言处理等技术的推荐算法已经成为很多网站和应用的核心功能。" +
            "通过分析用户历史行为、兴趣和偏好，推荐算法可以为用户提供更加个性化和精准的内容，从而提高用户留存率和忠诚度。" +
            "同时，推荐算法也可以优化内容生态和商业模式，增加收入来源和利润空间。因此，内容推荐已经成为现代互联网生态系统中不可或缺的一部分。";


    public static void main(String[] args) {
//        Map<String, Double> map = extractFeatures(content, longContent, 2);

//        Map<String, Double> map2 = extractFeatures(content2, longContent, 2);


//        System.out.println(map.size());

//        System.out.println(cosineSimilarity(map, map2));

    }

    //
    /**
     * 提取文章的特征信息，返回特征向量
     * @param content 文章
     * @param contentCount 文档库
     * @param contentGroup 文档库数量
     * */
    public static Map<String, Double> extractFeatures(String content, String contentGroup, int contentCount) {

        List<String> docWords = segment(contentGroup);

        // 分词
        List<String> words = segment(content);

        // 计算TF-IDF，并选取排名前K的关键词
        List<String> keywords = HanLP.extractKeyword(content, TOP_K);
        Map<String, Double> featureVector = new HashMap<>();
        for (String keyword : keywords) {
            double tfIdf = calculateTfIdf(keyword, words, docWords, contentCount);
            if (tfIdf >= MIN_TF_IDF) {
                featureVector.put(keyword, tfIdf);
            }
        }

        return featureVector;
    }

    private static List<String> segment(String content){
        List<Term> terms = StandardTokenizer.segment(content);
        List<String> docWords = new ArrayList<>();
        for (Term term : terms) {
            String word = term.word.trim();
            if (word.length() > 1 && !isStopWord(word)) {
                docWords.add(word);
            }
        }
        return docWords;
    }

    // 计算关键词的TF-IDF值
    private static double calculateTfIdf(String keyword, List<String> words) {
        int tf = 0;
        for (String word : words) {
            if (word.equals(keyword)) {
                tf++;
            }
        }

        // 文档值会影响tfIdf的正负。
        int docCount = 100; // 假设只有一个文档
        double idf = Math.log((double) docCount / (double) (1 + tf));
        double tfIdf = tf * idf;

        return tfIdf;
    }
    /**
     * 计算单词的TF-IDF值。
     *
     * TF-IDF = TF * log(N / DF)
     *
     * 其中，TF表示该词语在文档中出现的次数（词频），
     * DF表示该词语在所有文档中出现的文档频率（Document Frequency），
     * N表示文档总数。当一个词语在所有文档中都出现时，
     * DF的值为N，log(N/DF)的值为0，表示该词语对于区分不同文档的重要性为0。
     *
     * @param words 当前文档单词
     * @param docWords 文档组单词
     * @param docCount 文档个数
     *
     * */
    private static double calculateTfIdf(String keyword, List<String> words, List<String> docWords, int docCount) {
        int tf = 0;
        for (String word : words) {
            if (word.equals(keyword)) {
                tf++;
            }
        }

        int df = 0;
        for (String word : docWords) {
            if (word.equals(keyword)) {
                df++;
            }
        }

        double idf = Math.log((double) (docCount + 1) / (double) (df + 1)) + 1.0;
        double tfIdf = tf * idf;

        return tfIdf;
    }

    // 判断单词是否为停用词
    private static boolean isStopWord(String word) {
        for (String stopWord : STOP_WORDS) {
            if (word.equals(stopWord)) {
                return true;
            }
        }
        return false;
    }



    /*
    * 计算余项相似度之前的处理。
    *
    * */


    /*
    *
    * 相似度计算
    *
    *
    * */

    // 计算两个向量的余弦相似度
    private static double cosineSimilarity(Map<String, Double> v1, Map<String, Double> v2) {
        double dotProduct = 0.0;
        double v1Norm = 0.0;
        double v2Norm = 0.0;

        for (String key : v1.keySet()) {
            if (v2.containsKey(key)) {
                dotProduct += v1.get(key) * v2.get(key);
            }
            v1Norm += v1.get(key) * v1.get(key);
        }

        for (String key : v2.keySet()) {
            v2Norm += v2.get(key) * v2.get(key);
        }

        if (v1Norm == 0.0 || v2Norm == 0.0) {
            return 0.0;
        } else {
            return dotProduct / (Math.sqrt(v1Norm) * Math.sqrt(v2Norm));
        }
    }

    // 判断文章类别（正类或负类）
    public static boolean classify(Map<String, Double> featureVector, Map<String, Double> positiveVector, Map<String, Double> negativeVector) {
        double simPositive = cosineSimilarity(featureVector, positiveVector);
        double simNegative = cosineSimilarity(featureVector, negativeVector);

        //        THRESHOLD = 0.5
        return simPositive >= simNegative && simPositive >= 0.5;
    }



}