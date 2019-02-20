package com.github.erictao2.aegis.api.advice.processor;

import com.github.erictao2.aegis.api.annotation.DefendModify;
import com.github.erictao2.aegis.core.advice.processor.AegisAdviceProcessor;

/**
 * @author TJX
 * @Title: ModifyParameterProcessor
 * @date 2018/8/218:32
 */
public interface ModifyParameterProcessor  extends AegisAdviceProcessor<DefendModify> {
    void setKey(String key);
}
