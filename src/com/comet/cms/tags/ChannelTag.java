package com.comet.cms.tags;

import com.comet.cms.domain.CmsArticle;
import com.comet.cms.manager.CmsArticleManager;
import com.comet.core.utils.SpringUtils;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.jstl.core.LoopTag;
import javax.servlet.jsp.tagext.IterationTag;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 12-7-26
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
public class ChannelTag extends ForEachSupport implements LoopTag, IterationTag {
    //*********************************************************************
    // 'Private' state (implementation details)

    private String begin_;                      // stores EL-based property
    private String end_;                        // stores EL-based property
    private String step_;                       // stores EL-based property
    private String items_;			            // stores EL-based property

    private Long channelId;
    private String channelCode;
    private int size = 0;
    private Boolean showContent = Boolean.FALSE;
    private Boolean queryChildren = Boolean.FALSE;


    //*********************************************************************
    // Constructor

    public ChannelTag() {
        super();
        init();
    }


    //*********************************************************************
    // Tag logic

    /* Begins iterating by processing the first item. */
    public int doStartTag() throws JspException {
        try {
            evaluateChannelCode();

            // 获取数据
            CmsArticleManager manager = (CmsArticleManager) SpringUtils.getBean("cmsArticleManager");

            List<CmsArticle> list = manager.query(channelId, channelCode, size, showContent, queryChildren);

            pageContext.setAttribute("infoList", list);
            this.setItems("${infoList}");

            // evaluate any expressions we were passed, once per invocation
            evaluateExpressions();

            // chain to the parent implementation
            return super.doStartTag();
        } catch (Exception e) {
            throw new JspException(e);
        }
    }


    // Releases any resources we may have (or inherit)
    public void release() {
        super.release();
        init();
    }


    //*********************************************************************
    // Accessor methods

    // for EL-based attribute
    public void setBegin(String begin_) {
        this.begin_ = begin_;
        this.beginSpecified = true;
    }

    // for EL-based attribute
    public void setEnd(String end_) {
        this.end_ = end_;
        this.endSpecified = true;
    }

    // for EL-based attribute
    public void setStep(String step_) {
        this.step_ = step_;
        this.stepSpecified = true;
    }

    public void setItems(String items_) {
        this.items_ = items_;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setShowContent(Boolean showContent) {
        this.showContent = showContent;
    }

    public void setQueryChildren(Boolean queryChildren) {
        this.queryChildren = queryChildren;
    }

    //*********************************************************************
    // Private (utility) methods

    // (re)initializes state (during release() or construction)
    private void init() {
        // defaults for interface with page author
        begin_ = null;          // (no expression)
        end_ = null;            // (no expression)
        step_ = null;           // (no expression)
        items_ = null;		// (no expression)
    }

    private void evaluateChannelCode() throws JspException {
        if(channelCode != null) {
            Object r = ExpressionEvaluatorManager.evaluate(
                    "channelCode", channelCode, String.class, this, pageContext);
            if (r == null)
                throw new NullAttributeException("forEach", "channelCode");
            channelCode = (String) r;
        }
    }

    /* Evaluates expressions as necessary */
    private void evaluateExpressions() throws JspException {
        /*
         * Note: we don't check for type mismatches here; we assume
         * the expression evaluator will return the expected type
         * (by virtue of knowledge we give it about what that type is).
         * A ClassCastException here is truly unexpected, so we let it
         * propagate up.
         */

        if (begin_ != null) {
            Object r = ExpressionEvaluatorManager.evaluate(
                    "begin", begin_, Integer.class, this, pageContext);
            if (r == null)
                throw new NullAttributeException("forEach", "begin");
            begin = ((Integer) r).intValue();
            validateBegin();
        }

        if (end_ != null) {
            Object r = ExpressionEvaluatorManager.evaluate(
                    "end", end_, Integer.class, this, pageContext);
            if (r == null)
                throw new NullAttributeException("forEach", "end");
            end = ((Integer) r).intValue();
            validateEnd();
        }

        if (step_ != null) {
            Object r = ExpressionEvaluatorManager.evaluate(
                    "step", step_, Integer.class, this, pageContext);
            if (r == null)
                throw new NullAttributeException("forEach", "step");
            step = ((Integer) r).intValue();
            validateStep();
        }

        if (items_ != null) {
            rawItems = ExpressionEvaluatorManager.evaluate(
                    "items", items_, Object.class, this, pageContext);
            // use an empty list to indicate "no iteration", if relevant
            if (rawItems == null)
                rawItems = new ArrayList();
        }
    }
}
