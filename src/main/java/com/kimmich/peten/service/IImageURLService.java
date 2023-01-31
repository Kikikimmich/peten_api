package com.kimmich.peten.service;

public interface IImageURLService {

    String getURL(String md5);

    void add(String md5, String url);
}
