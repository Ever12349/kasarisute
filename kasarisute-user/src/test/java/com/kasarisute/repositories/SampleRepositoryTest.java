package com.kasarisute.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kasarisute.KasarisuteUserApplication;
import com.kasarisute.domain.SampleTable;

@SpringBootTest(classes = KasarisuteUserApplication.class)
public class SampleRepositoryTest {
    
    @Autowired
    SampleRepository sampleRepository;

    @Test
    public void sampleTest() {

        System.out.println("hello test");
        SampleTable sampleTable = new SampleTable();
        sampleTable.setTitle("测试2");
        SampleTable save = sampleRepository.save(sampleTable);

        System.out.println("+++++===========+++++++++++++");
        System.out.println("sampleTest++++" + save);
        System.out.println("+++++===========+++++++++++++");
    }
}
