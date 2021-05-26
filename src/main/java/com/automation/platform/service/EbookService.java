package com.automation.platform.service;

import com.automation.platform.domain.Ebook;
import com.automation.platform.domain.EbookExample;
import com.automation.platform.mapper.EbookMapper;
import com.automation.platform.req.EbookReq;
import com.automation.platform.resp.EbookResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
    @Resource //@Autowired
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
        criteria.andNameLike("%"+req.getName()+"%");}
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Ebook> ebooklist = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooklist);
        LOG.info("总行数:{}", pageInfo.getTotal());
        LOG.info("总页数:{}",pageInfo.getPages());

        List<EbookResp> respList = new ArrayList<>();
        //快捷键  1：fori  2:iter
        for (Ebook ebook : ebooklist) {
            EbookResp ebookResp = new EbookResp();
//            ebookResp.setId(ebook.getId());
            BeanUtils.copyProperties(ebook,ebookResp);
            respList.add(ebookResp);
        }
        return respList;
    }
}
