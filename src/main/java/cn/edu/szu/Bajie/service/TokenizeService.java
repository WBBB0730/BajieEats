package cn.edu.szu.Bajie.service;

import java.util.Set;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/12/7 12:35
 * @version 1.0
 */
public interface TokenizeService {

    Set<String> parse(String text);

    Set<String> parse2Pinyin(String text);
}
