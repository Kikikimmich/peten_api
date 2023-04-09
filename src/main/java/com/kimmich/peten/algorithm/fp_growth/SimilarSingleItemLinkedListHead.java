package com.kimmich.peten.algorithm.fp_growth;

/**
 * 相似项的链表表头
 */
public class SimilarSingleItemLinkedListHead {
    private String itemValue;  //  该项的值
    public Node next;   //该相似项的第一项
    private int supTotal; //该项的全局支持度

    public SimilarSingleItemLinkedListHead(String itemValue, Node node, int supTotal) {
        this.itemValue = itemValue;
        this.next = node;
        this.supTotal = supTotal;
    }

    public void addSupTotal() {
        this.supTotal = this.supTotal + 1;
    }

    public void setSupTotal(int supTotal) {
        this.supTotal = supTotal;
    }

    public String getItemValue() {
        return itemValue;
    }

    public int getSupTotal() {
        return supTotal;
    }
}
