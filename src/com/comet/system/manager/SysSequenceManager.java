package com.comet.system.manager;

import com.comet.system.daoservice.SysSequenceService;
import com.comet.system.domain.SysSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-10-13
 * Time: 下午2:14
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SysSequenceManager {
    @Autowired
    private SysSequenceService sysSequenceService;

    /**
     * 取得序列号的下一个值
     *
     * @param code
     * @return
     */
    public Long getNextValue(String code) {
        Long ret = 1L;
        SysSequence sequence = sysSequenceService.findUniqueByProperty("id", code);

        if(sequence != null) {
            ret = sequence.getLastid();

            sequence.setLastid(ret.longValue() + 1);
        } else {
            sequence = new SysSequence();

            sequence.setId(code);
            sequence.setLastid(2L);
        }

        sysSequenceService.save(sequence);

        return ret;
    }

    /**
     * 取得格式化字符
     *
     * @param code
     * @param length
     * @return
     */
    public String getNextValue(String code, int length) {
        String ret = String.valueOf(getNextValue(code));

        if(ret.length() < length) {
            String tmp = "";
            for(int i=0; i<length-ret.length(); i++) {
                tmp = "0" + tmp;
            }

            ret = tmp + ret;
        }

        return ret;
    }
}
