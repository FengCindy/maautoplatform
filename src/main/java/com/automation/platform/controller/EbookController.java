package com.automation.platform.controller;

import com.automation.platform.req.EbookReq;
import com.automation.platform.resp.CommonResp;
import com.automation.platform.resp.EbookResp;
import com.automation.platform.resp.PageResp;
import com.automation.platform.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/all")
    public CommonResp List(EbookReq req)
    {
        CommonResp<List<EbookResp>> respAll = new CommonResp<>();
        List<EbookResp> all = ebookService.all(req);
        respAll.setContent(all);
        return respAll;
    }


    @GetMapping("/list")
    public CommonResp list(EbookReq req)
    {
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}
