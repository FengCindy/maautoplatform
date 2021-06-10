package com.automation.platform.service;

import com.automation.platform.domain.Ebook;
import com.automation.platform.domain.EbookExample;
import com.automation.platform.domain.EbookExample.Criteria;
import com.automation.platform.mapper.EbookMapper;
import com.automation.platform.req.EbookQueryReq;
import com.automation.platform.req.EbookSaveReq;
import com.automation.platform.resp.EbookQueryResp;
import com.automation.platform.resp.PageResp;
import com.automation.platform.util.CopyUtil;
import com.automation.platform.util.SnowFlake;
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

    @Resource
    private SnowFlake snowFlake;

    public PageResp<EbookQueryResp> list(EbookQueryReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
        criteria.andNameLike("%"+req.getName()+"%");}
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Ebook> ebooklist = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooklist);
        LOG.info("总行数:{}", pageInfo.getTotal());
        LOG.info("总页数:{}",pageInfo.getPages());

        List<EbookQueryResp> respList = new ArrayList<>();
        //快捷键  1：fori  2:iter
        for (Ebook ebook : ebooklist) {
            EbookQueryResp ebookQueryResp = new EbookQueryResp();
            BeanUtils.copyProperties(ebook, ebookQueryResp);
            respList.add(ebookQueryResp);
        }
        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);
        return pageResp;
    }

    public List<EbookQueryResp> all(EbookQueryReq req){
        EbookExample ebookExampleAll;
        ebookExampleAll = new EbookExample();
        Criteria criteria = ebookExampleAll.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%"+req.getName()+"%");}
        List<Ebook> ebooklistAll = ebookMapper.selectByExample(ebookExampleAll);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooklistAll);
        LOG.info("总行数:{}", pageInfo.getTotal());
        LOG.info("总页数:{}",pageInfo.getPages());

        List<EbookQueryResp> respList = new ArrayList<>();
        //快捷键  1：fori  2:iter
        for (Ebook ebook : ebooklistAll) {
            EbookQueryResp ebookQueryResp = new EbookQueryResp();
            BeanUtils.copyProperties(ebook, ebookQueryResp);
            respList.add(ebookQueryResp);
        }
        return respList;
    }

    /**
     * save
     * @param req
     */
    public void save(EbookSaveReq req){
        Ebook ebook = CopyUtil.copy(req,Ebook.class);
        if(ObjectUtils.isEmpty(req.getId())){
            //新增
            ebook.setId(snowFlake.nextId());
            ebookMapper.insertSelective(ebook);
        }
        else{
            //更新
            ebookMapper.updateByPrimaryKey(ebook);}
    }

    public void delete(Long id){
        ebookMapper.deleteByPrimaryKey(id);
    }
}
