package com.order.sys.services.impl;

import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageRule;
import com.order.sys.bean.model.ComRule;
import com.order.sys.bean.model.ComStaff;
import com.order.sys.constants.ErrorCode;
import com.order.sys.repository.ComAccountRepository;
import com.order.sys.repository.ComRuleRepository;
import com.order.sys.repository.ComStaffRepository;
import com.order.sys.services.RuleServices;
import com.order.sys.util.FindObjUtil;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServicesImpl implements RuleServices {


    @Autowired
    private ComRuleRepository comRuleRepository;

    @Autowired
    private ComAccountRepository comAccountRepository;
    @Autowired
    private ComStaffRepository comStaffRepository;

    @Override
    public BaseMessage<String> mergeRule(Integer token, String name, String content) {
        ComStaff comStaff = FindObjUtil.permissionCheck(token,comAccountRepository,comStaffRepository);
        if(comStaff == null)
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.PERMISSION_DENY);



        ComRule comRule = comRuleRepository.findByName(name,comStaff.getStaff_office_id());
        if (comRule == null) {
            ComRule comRule1 = new ComRule();
            comRule1.setCom_rule_name(name);
            comRule1.setCom_rule_text(content);
            comRule1.setCom_office_id(comStaff.getStaff_office_id());
            comRuleRepository.save(comRule1);
            return MessageInputUtil.baseMessageSuccessInput("增加成功");
        }

        comRule.setCom_rule_name(name);
        comRule.setCom_rule_text(content);
        comRuleRepository.save(comRule);


        return MessageInputUtil.baseMessageSuccessInput("修改成功(规则名称唯一)");
    }

    @Override
    public BaseMessage<String> deleteRule(Integer token, Integer ruleId) {
        ComStaff comStaff = FindObjUtil.permissionCheck(token,comAccountRepository,comStaffRepository);
        if(comStaff == null)
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.PERMISSION_DENY);


        comRuleRepository.deleteById(ruleId);
        return MessageInputUtil.baseMessageSuccessInput("删除成功");
    }

    @Override
    public BaseMessage<List<ComRule>> search(Integer token,String ruleName) {
        ComStaff comStaff = FindObjUtil.permissionCheck(token, comAccountRepository, comStaffRepository);
        if (comStaff == null)
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.PERMISSION_DENY);

        if(ruleName == null || ruleName.isEmpty())
        {
            return MessageInputUtil.baseMessageSuccessInput(comRuleRepository.findAll(comStaff.getStaff_office_id()));
        }

        List<ComRule> comRuleList = comRuleRepository.findAllByName(ruleName, comStaff.getStaff_office_id());
        return MessageInputUtil.baseMessageSuccessInput(comRuleList);
    }

    @Override
    public BaseMessage<List<ComRule>> getAll(Integer officeId) {
        return MessageInputUtil.baseMessageSuccessInput(comRuleRepository.findAll(officeId));
    }

    @Override
    public BaseMessage<List<MessageRule>> getAllRule() {
        List<MessageRule> ruleList = comRuleRepository.findAllEachOffice();
        for(MessageRule r : ruleList)
        {
            List<ComRule> comRuleList =  getAll(r.getOfficeId()).getData();
            r.setOfficeRule(comRuleList);
        }

        return MessageInputUtil.baseMessageSuccessInput(ruleList);
    }
}
