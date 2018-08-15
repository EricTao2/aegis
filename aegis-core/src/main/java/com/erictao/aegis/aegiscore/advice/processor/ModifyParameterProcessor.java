package com.erictao.aegis.aegiscore.advice.processor;

import com.erictao.aegis.aegiscore.annotation.DefendModify;
import com.erictao.aegis.aegiscore.annotation.DefendReplay;

/**
 * @author TJX
 * @Title: ModifyParameterProcessor
 * @date 2018/8/218:32
 */
public interface ModifyParameterProcessor  extends AegisApiProcessor<DefendModify> {
    void setKey(String key);
}
