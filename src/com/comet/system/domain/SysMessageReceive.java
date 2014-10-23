package com.comet.system.domain;

import com.comet.system.domain.base.BaseSysMessageReceive;



public class SysMessageReceive extends BaseSysMessageReceive {
	private static final long serialVersionUID = 1L;

    private String messageBody;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysMessageReceive () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysMessageReceive (Long id) {
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