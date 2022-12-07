package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.service.TokenizeService;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.hutool.extra.tokenizer.Word;
import cn.hutool.extra.tokenizer.engine.hanlp.HanLPEngine;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TokenizeServiceImpl implements TokenizeService {

    private static final String[] CUSTOM_DICTIONARY = {"sensitive_words.txt"};

    private static TokenizerEngine tokenizerEngine;

    @PostConstruct
    void loadDictionary(){

        Arrays.stream(CUSTOM_DICTIONARY)
                .forEach(dic->{

                    try {
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(
                                        new ClassPathResource(dic).getStream()
                                )
                        );

                        reader.lines().forEach(com.hankcs.hanlp.dictionary.CustomDictionary::add);

                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        tokenizerEngine =new HanLPEngine();
    }


    @Override
    public Set<String> parse(String text) {
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                tokenizerEngine.parse(text),
                                Spliterator.ORDERED
                        ),
                        false
                ).map(Word::getText)
                .collect(Collectors.toSet());


    }

    @Override
    public Set<String> parse2Pinyin(String text) {
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                tokenizerEngine.parse(text),
                                Spliterator.ORDERED
                        ),
                        false
                ).map(word -> PinyinUtil.getPinyin(word.getText()))
                .collect(Collectors.toSet());
    }
}
