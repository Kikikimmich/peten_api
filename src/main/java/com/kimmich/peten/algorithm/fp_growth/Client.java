package com.kimmich.peten.algorithm.fp_growth;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        String path = "E:\\MyProject\\peten_api\\src\\main\\resources\\static\\file\\TransactSet.txt";
        FPGrowthAlgorithm fpGrowthAlgorithm = new FPGrowthAlgorithm();
        fpGrowthAlgorithm.run(1000, path, 5);
    }
}
