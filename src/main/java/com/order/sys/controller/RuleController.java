package com.order.sys.controller;

import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageRule;
import com.order.sys.bean.model.ComRule;
import com.order.sys.services.RuleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rule")
public class RuleController {


    @Autowired
    private RuleServices ruleServices;

    @PostMapping("/merge")
    public BaseMessage<String> merge(@RequestParam("token") Integer token,
                                     @RequestParam("name") String name,
                                     @RequestParam("content") String content) {
        return ruleServices.mergeRule(token, name, content);
    }


    @PostMapping("/delete")
    public BaseMessage<String> delete(@RequestParam("token") Integer token,
                                      @RequestParam("ruleId") Integer ruleId)
    {
        return ruleServices.deleteRule(token,ruleId);
    }


    @PostMapping("/search")
    public BaseMessage<List<ComRule>> search(@RequestParam("token") Integer token,
                                             @RequestParam(value = "name",required = false) String name)
    {
        return ruleServices.search(token,name);
    }


    @PostMapping("/getAll")
    public BaseMessage<List<ComRule>> getAll(@RequestParam("officeId") Integer officeId)
    {
        return ruleServices.getAll(officeId);
    }

    @PostMapping("/getAllEachOffice")
    public BaseMessage<List<MessageRule>> getAllEachOffice()
    {
        return ruleServices.getAllRule();
    }

}
