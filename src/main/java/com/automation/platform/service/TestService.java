package com.automation.platform.service;

import com.automation.platform.domain.Test;
import com.automation.platform.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {
    @Resource //@Autowired
    private TestMapper testMapper;

    public List<Test> list(){
        return testMapper.list();
    }
}
