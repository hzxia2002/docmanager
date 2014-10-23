package com.comet.system.domain;

import com.comet.system.domain.base.BaseSysMessageReceiveBack;



public class SysMessageReceiveBack extends BaseSysMessageReceiveBack {
	private static final long serialVersionUID = 1L;

    private String messageBody;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysMessageReceiveBack () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysMessageReceiveBack (Long id) {
		super(id);
	}

    /*[CONSTRUCTOR MARKER END]*/

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageBody() {
        try {
            if(this.getFdMessageBody() != null) {
                return new String(this.getFdMessageBody(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        return "";
    }
}