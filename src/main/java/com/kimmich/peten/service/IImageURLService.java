package com.kimmich.peten.service;

import java.util.List;
import java.util.Map;

public interface IImageURLService {

    Map<String, String> getURLById(List<String> id);

    String getURLById(String id);

    String getURL(String md5);

    void add(String md5, String url);
}
