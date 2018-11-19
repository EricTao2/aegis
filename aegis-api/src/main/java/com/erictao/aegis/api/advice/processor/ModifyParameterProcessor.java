package com.erictao.aegis.api.advice.processor;

import com.erictao.aegis.api.annotation.DefendModify;
import com.erictao.aegis.core.advice.processor.AegisAdviceProcessor;

/**
 * @author TJX
 * @Title: ModifyParameterProcessor
 * @date 2018/8/218:32
 */
public interface ModifyParameterProcessor  extends AegisAdviceProcessor<DefendModify> {
    void setKey(String key);
}
