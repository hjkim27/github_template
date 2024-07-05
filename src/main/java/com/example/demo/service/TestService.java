package com.example.demo.service;

import com.example.demo.mapper.first.TestMapper;
import com.example.demo.mapper.second.SecondMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {

    private final TestMapper testMapper;
    private final SecondMapper secondMapper;

    public String getNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
        Timestamp now = testMapper.getNow();
        String strNow = sdf.format(now.getTime());
        return strNow;
    }

    public void currentDatabase() {
        log.info("firstDatabase : {}", testMapper.getDATABASE_NAME());
        log.info("secondDatabase : {}", secondMapper.getDATABASE_NAME());
    }
}
