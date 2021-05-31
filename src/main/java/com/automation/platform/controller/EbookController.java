package com.automation.platform.controller;

import com.automation.platform.req.EbookQueryReq;
import com.automation.platform.req.EbookSaveReq;
import com.automation.platform.resp.CommonResp;
import com.automation.platform.resp.EbookQueryResp;
import com.automation.platform.resp.PageResp;
import com.automation.platform.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/all")
    public CommonResp all(EbookQueryReq req)
    {
        CommonResp<List<EbookQueryResp>> respAll = new CommonResp<>();
        List<EbookQueryResp> all = ebookService.all(req);
        respAll.setContent(all);
        return respAll;
    }


    @GetMapping("/list")
    public CommonResp list(EbookQueryReq req)
    {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req)
    {
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }
}
