package com.order.sys.services;

import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageRule;
import com.order.sys.bean.model.ComRule;

import java.util.List;

public interface RuleServices {

    BaseMessage<String> mergeRule(Integer token, String name,String content);
    BaseMessage<String> deleteRule(Integer token,Integer ruleId);
    BaseMessage<List<ComRule>> search(Integer token,String ruleName);
    BaseMessage<List<ComRule>> getAll(Integer officeId);
    BaseMessage<List<MessageRule>> getAllRule();
}
