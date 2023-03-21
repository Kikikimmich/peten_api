package com.kimmich.peten.algorithm.nlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.CRF.CRFSegment;

import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        String text = "想买一只柴柴，然后看到网上有人说柴犬护食，视频里面的柴犬还特别凶，要真是那样只能丢弃的话自己也舍不得\n" +
                "\n" +
                "有没有什么柴柴能从小养成的好习惯，因为还没买不知道具体年龄，那里的狗狗也就是45日—12月，希望能养成乖乖的，不护食但是护主的qvq";
        List<String> keywords = HanLP.extractKeyword(text, 5);
        String summary = HanLP.getSummary(text, 5);
        System.out.println(summary);
        System.out.println(keywords);
    }
}

