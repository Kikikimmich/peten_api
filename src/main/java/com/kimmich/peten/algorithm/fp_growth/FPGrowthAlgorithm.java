package com.kimmich.peten.algorithm.fp_growth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class FPGrowthAlgorithm {

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    final private String filePath = "E:\\MyProject\\peten_api\\src\\main\\resources\\static\\file\\TransactSet.txt";
    private int minSupCnt;  //最小支持度计数
    ArrayList<ArrayList<String>> sourceDataSet; //事务集
    ArrayList<ArrayList<String>> freqSourceSortedDataSet;    //频繁项事务集,且按全局支持度降序排序
    Hashtable<String, SimilarSingleItemLinkedListHead> similarSingleItemLinkedListHeadsTable;//相似项表头哈希表;含非频繁项
    List<SimilarSingleItemLinkedListHead> similarSingleItemLinkedListHeadList;   //相似项表头列表;不含非频繁项,且按全局支持度排序
    List<TreeSet<String>> result;   //结果
    FPTree fpTree;
    private int printThreshold = 4;//输出结果的项集大小阈值
    private int cntOfFreqSet = 0;   //频繁项集的个数

    // 写出文件
    final String output = "E:\\MyProject\\peten_api\\src\\main\\resources\\static\\file\\FrequentItemSet.txt";
    FileWriter outputFileWriter = new FileWriter(new File(output));

    private void init() {
        similarSingleItemLinkedListHeadsTable = new Hashtable<>();
        similarSingleItemLinkedListHeadList = new ArrayList<>();
        fpTree = new FPTree();
        sourceDataSet = new ArrayList<>();
        result = new ArrayList<>();
    }

    public FPGrowthAlgorithm() throws IOException {
        init();
    }

    /**
     * 算法执行函数
     *
     * @param minSupCnt 最小支持度计数
     * @param path      文件路径
     * @param pT        输出结果的项集大小阈值
     */
    public void run(int minSupCnt, String path, int pT) throws IOException {
        this.printThreshold = pT;
        this.minSupCnt = minSupCnt;
        scanDataSet(path);
        buildFPTree();
        for (int i = similarSingleItemLinkedListHeadList.size() - 1; i >= 0; i--) {
            genFreqItemSet(similarSingleItemLinkedListHeadList.get(i).getItemValue()
                    , fpTree, similarSingleItemLinkedListHeadList, new TreeSet<>());
        }
        StringBuilder s = new StringBuilder();
        for (TreeSet<String> treeSet : result) {
            for (String integer : treeSet) {
                s.append(integer).append(" ");
            }
            outputFileWriter.write(s.toString());
            outputFileWriter.write("\n");
            s = new StringBuilder();
        }
        outputFileWriter.flush();
        outputFileWriter.close();

        logger.info("频繁项集个数:\t" + cntOfFreqSet);
    }

    /**
     * 构建相似项链表
     */
    private void buildSimilarSingleItemLinkedList(String item, Node curTreeNode) {
        //找到该item在相似项链表中的位置

        int index = searchForItemInHeadsList(item,
                (ArrayList<SimilarSingleItemLinkedListHead>) similarSingleItemLinkedListHeadList);
        if (similarSingleItemLinkedListHeadList.get(index).next == null) {
            similarSingleItemLinkedListHeadList.get(index).next = curTreeNode.children.get(item);
        } else {
            Node visitNode = similarSingleItemLinkedListHeadList.get(index).next;
            while (visitNode.nextSimilar != null) {

                visitNode = visitNode.nextSimilar;
            }
            if (visitNode != curTreeNode.children.get(item))
                visitNode.nextSimilar = curTreeNode.children.get(item);
        }
    }

    /**
     * 构建FP树
     */
    private void buildFPTree() {
        for (ArrayList<String> trans : freqSourceSortedDataSet) {
            Node curTreeNode = fpTree.root;
            for (String item : trans) {
                if (!curTreeNode.children.containsKey(item)) {
                    Node node = new Node(item, 1);
                    curTreeNode.children.put(item, node);
                    node.father = curTreeNode;
                    buildSimilarSingleItemLinkedList(item, curTreeNode);
                } else {
                    curTreeNode.children.get(item).sup++;
                }
                curTreeNode = curTreeNode.children.get(item);
            }
        }
    }

    /**
     * 生成频繁项集
     *
     * @param last                                   最后项
     * @param fPTree                                 条件FP树
     * @param fatherSimilarSingleItemLinkedListHeads 父树的相似项头结点链表
     * @param freqItemSet                            频繁项集
     */
    private void genFreqItemSet(String last, FPTree fPTree,
                                List<SimilarSingleItemLinkedListHead> fatherSimilarSingleItemLinkedListHeads, TreeSet<String> freqItemSet) {

        FPTree conditionalFPTree = new FPTree();
        List<SimilarSingleItemLinkedListHead> similarSingleItemLinkedListHeads = new ArrayList<>();

        TreeSet<String> localFreqItemSet = (TreeSet<String>) freqItemSet.clone();
        int index;
        index = searchForItemInHeadsList(last,
                (ArrayList<SimilarSingleItemLinkedListHead>) fatherSimilarSingleItemLinkedListHeads);

        Node firstNode = fatherSimilarSingleItemLinkedListHeads.get(index).next;
        HashSet<Node> record = new HashSet<>();  //用于记录前缀路径上出现的节点
        //记录前缀路径
        if (firstNode != null) {
            record.add(firstNode);
            Node nodeToVisitFather = firstNode;
            Node nodeToVisitSimilar = firstNode;
            while (nodeToVisitSimilar != null) {
                nodeToVisitSimilar.supInCFP = nodeToVisitSimilar.sup;
                nodeToVisitFather = nodeToVisitSimilar;
                while (nodeToVisitFather != null) {
                    // 计算supInCFT
                    if (nodeToVisitFather != nodeToVisitSimilar)
                        nodeToVisitFather.supInCFP += nodeToVisitSimilar.supInCFP;
                    record.add(nodeToVisitFather);
                    nodeToVisitFather = nodeToVisitFather.father;
                }
                nodeToVisitSimilar = nodeToVisitSimilar.nextSimilar;
            }

            //记录在子树中的支持度
            Hashtable<String, Integer> supRecord = new Hashtable<>();
            record.forEach(new Consumer<Node>() {
                @Override
                public void accept(Node node) {
                    String item = node.item;
                    if ("-1".equals(item)) {    //根节点
                        return;
                    }
                    if (supRecord.containsKey(item)) {
                        supRecord.put(item, supRecord.get(item) + node.supInCFP);
                    } else {
                        supRecord.put(item, node.supInCFP);
                    }

                }
            });
            //输出结果
            if (supRecord.get(last) >= minSupCnt) {
                localFreqItemSet.add(last);
                if (localFreqItemSet.size() >= printThreshold && !result.contains(localFreqItemSet)) {
                    cntOfFreqSet++;

//                    localFreqItemSet.forEach(new Consumer<Integer>() {
//                        @Override
//                        public void accept(Integer integer) {
//                            System.out.print(integer + " ");
//                        }
//                    });
                    result.add(localFreqItemSet);

//                    System.out.println("");
                }
            }

            //构建相似项链表
            record.forEach(new Consumer<Node>() {
                @Override
                public void accept(Node node) {
                    if ("-1".equals(node.item)) {    //根节点
                        Node visitNode = node;
                        buildConditionalFPTree(conditionalFPTree.root, visitNode, record,
                                (ArrayList<SimilarSingleItemLinkedListHead>) similarSingleItemLinkedListHeads, supRecord, last);
                    }
                }
            });
            //按支持度降序排序
            similarSingleItemLinkedListHeads.sort(new Comparator<SimilarSingleItemLinkedListHead>() {
                @Override
                public int compare(SimilarSingleItemLinkedListHead o1, SimilarSingleItemLinkedListHead o2) {
                    return o2.getSupTotal() - o1.getSupTotal();
                }
            });

            if (similarSingleItemLinkedListHeads.size() >= 1) {
                //递归搜索频繁项
                for (int i = similarSingleItemLinkedListHeads.size() - 1; i >= 0; i--) {
                    genFreqItemSet(similarSingleItemLinkedListHeads.get(i).getItemValue(),
                            conditionalFPTree, similarSingleItemLinkedListHeads, localFreqItemSet);
                    // similarSingleItemLinkedListHeads.remove(i);
                }
            }
        }
    }

    /**
     * 递归构建条件FP树
     *
     * @param rootNode                         以该节点为根向下建立条件FP树
     * @param originalNode                     rootNode对应在原树中的节点
     * @param record                           前缀路径
     * @param similarSingleItemLinkedListHeads 相似项表头链表
     * @param supRecord                        支持度计数的记录
     * @param last                             最后项
     */
    private void buildConditionalFPTree(Node rootNode, Node originalNode, HashSet<Node> record
            , ArrayList<SimilarSingleItemLinkedListHead> similarSingleItemLinkedListHeads, Hashtable<String, Integer> supRecord, String last) {
        if (originalNode.children != null) {
            for (String key : originalNode.children.keySet()) {    //遍历originalNode的所有儿子节点,检查其是否在前缀路径中
                Node tempNode = originalNode.children.get(key);
                if (record.contains(tempNode)) {
                    Node addedNode = new Node(tempNode.item, tempNode.supInCFP);
                    if (Objects.equals(last, key)) {    //去除last的所有节点
                        tempNode.supInCFP = 0;
                        continue;
                    }
                    if (supRecord.get(key) >= minSupCnt) {
                        //addedNode 拷贝 tempNode除儿子节点外的属性
                        addedNode.supInCFP = tempNode.supInCFP;
                        rootNode.children.put(tempNode.item, addedNode);
                        addedNode.father = rootNode;
                        //构建相似项表
                        int i = searchForItemInHeadsList(tempNode.item, similarSingleItemLinkedListHeads);
                        if (i == -1) {
                            similarSingleItemLinkedListHeads.add(new SimilarSingleItemLinkedListHead(key, addedNode, addedNode.supInCFP));
                        } else {
                            similarSingleItemLinkedListHeads.get(i).setSupTotal(similarSingleItemLinkedListHeads.get(i).getSupTotal() + addedNode.supInCFP);
                            Node visitNode = similarSingleItemLinkedListHeads.get(i).next;
                            while (visitNode.nextSimilar != null) {
                                visitNode = visitNode.nextSimilar;
                            }
                            if (visitNode != addedNode) {
                                visitNode.nextSimilar = addedNode;
                            }
                        }
                        buildConditionalFPTree(addedNode, originalNode.children.get(key), record, similarSingleItemLinkedListHeads, supRecord, last);
                        addedNode.supInCFP = 0; //将supInCFP重置为0;
                    } else {
                        buildConditionalFPTree(rootNode, originalNode.children.get(key), record, similarSingleItemLinkedListHeads, supRecord, last);
                    }

                }
            }
        }
    }

    /**
     * 在HeadList中搜索某项的位置
     *
     * @param item                             项
     * @param similarSingleItemLinkedListHeads 头结点链表
     * @return 位置,-1表示未找到
     */
    private int searchForItemInHeadsList(String item, ArrayList<SimilarSingleItemLinkedListHead> similarSingleItemLinkedListHeads) {
        for (int i = 0; i < similarSingleItemLinkedListHeads.size(); i++) {
            if (similarSingleItemLinkedListHeads.get(i).getItemValue().equals(item)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 去除相似项表(similarSingleItemLinkedListHeadsTable)的非频繁项,并按全局支持度对similarSingleItemLinkedListHeads降序排序
     */
    private void deleteNonFreqInSSILLHTAndSort() {
        Hashtable<String, SimilarSingleItemLinkedListHead> copyOfSSILLHT =
                (Hashtable<String, SimilarSingleItemLinkedListHead>) similarSingleItemLinkedListHeadsTable.clone();
        Set<String> keySet = copyOfSSILLHT.keySet();
        //删除非频繁项
        for (String key : keySet) {
            if (similarSingleItemLinkedListHeadsTable.get(key).getSupTotal() < minSupCnt) {//低于支持度阈值
                similarSingleItemLinkedListHeadsTable.remove(key);
            }
        }
        //按全局支持度排序
        similarSingleItemLinkedListHeadList = new ArrayList<>(similarSingleItemLinkedListHeadsTable.values());
        similarSingleItemLinkedListHeadList.sort(new Comparator<SimilarSingleItemLinkedListHead>() {
            @Override
            public int compare(SimilarSingleItemLinkedListHead o1, SimilarSingleItemLinkedListHead o2) {
                return o2.getSupTotal() - o1.getSupTotal();
            }
        });

    }

    /**
     * 去除事务集(sourceDataSet)的非频繁项,并且按全局支持度对每个事务的item进行降序排序
     * 其结果保存在freqSourceSortedDataSet
     */
    private void deleteNonFreqInSDSAndSort() {
        freqSourceSortedDataSet = (ArrayList<ArrayList<String>>) sourceDataSet.clone();
        for (int i = 0; i < sourceDataSet.size(); i++) {
            for (int j = 0; j < sourceDataSet.get(i).size(); j++) {
                String item = sourceDataSet.get(i).get(j);
                // 由于此时SSILLHT里的项都是频繁项,只需要确定item是否存在在其中即可,存在即代表频繁.
                if (visitSupTotal(item) == -1) {
                    //将非频繁项标记为最小整数值
                    freqSourceSortedDataSet.get(i).set(j, Integer.MIN_VALUE + "");
                }
            }
            //将标记的项移除.
            freqSourceSortedDataSet.get(i).removeIf(e -> Objects.equals(e, Integer.MIN_VALUE + ""));
            insertSort(freqSourceSortedDataSet.get(i));
        }
        freqSourceSortedDataSet.removeIf(e -> e.size() == 0);

    }

    /**
     * 插入排序
     * 排序规则:按事务中的item的全局支持度降序排序
     *
     * @param trans 事务
     */
    private void insertSort(ArrayList<String> trans) {
        for (int i = 1; i < trans.size(); i++) {
            String key = trans.get(i);
            int value = visitSupTotal(key);
            int j = i - 1;
            while (j > -1 && visitSupTotal(trans.get(j)) < value) {
                trans.set(j + 1, trans.get(j));
                j--;
            }
            trans.set(j + 1, key);
        }
    }

    /**
     * 访问某一item的全局支持度
     *
     * @param key item
     * @return 全局支持度
     */
    private int visitSupTotal(String key) {
        if (!similarSingleItemLinkedListHeadsTable.containsKey(key))
            return -1;
        return similarSingleItemLinkedListHeadsTable.get(key).getSupTotal();
    }

    /**
     * 扫描原数据集,生成事务集
     *
     * @param path 数据集路径
     * @throws IOException
     */

    private void scanDataSet(String path) throws IOException {
        if (path.equals("")) {
            path = filePath;
        }
        FileReader fr = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fr);
        String str;
//        int maxLength = 0;
        while ((str = bufferedReader.readLine()) != null) {
            ArrayList<String> transaction = new ArrayList<>();
            String[] tempEntry;
            tempEntry = str.split(" ");
            for (int i = 0; i < tempEntry.length; i++) {
                if (!tempEntry[i].equals("")) {
                    String itemValue = tempEntry[i];
                    transaction.add(itemValue);
                    if (!similarSingleItemLinkedListHeadsTable.containsKey(itemValue)) {
                        similarSingleItemLinkedListHeadsTable.put(itemValue, new SimilarSingleItemLinkedListHead(itemValue, null, 1));
                    } else {
                        //将该项的全局支持度+1
                        similarSingleItemLinkedListHeadsTable.get(itemValue).addSupTotal();
                    }
                }
            }
//            if(tempEntry.length>maxLength){
//                maxLength = tempEntry.length;
//            }

            sourceDataSet.add(transaction);

        }
//        System.out.println(maxLength);
        deleteNonFreqInSSILLHTAndSort();
        deleteNonFreqInSDSAndSort();
        bufferedReader.close();
        fr.close();
    }
}
