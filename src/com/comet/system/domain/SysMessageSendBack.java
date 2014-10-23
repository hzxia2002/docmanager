package com.comet.system.domain;

import com.comet.system.domain.base.BaseSysMessageSendBack;



public class SysMessageSendBack extends BaseSysMessageSendBack {
	private static final long serialVersionUID = 1L;

    private String messageBody;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysMessageSendBack () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysMessageSendBack (Long id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageBody() {
        try {
            return new String(this.getFdMessageBody(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}