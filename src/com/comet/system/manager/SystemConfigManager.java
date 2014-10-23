package com.comet.system.manager;

import org.springframework.stereotype.Service;

/**
 * Project:bcscmis
 *
 * <p></p>
 *
 * Create On 2009-12-13 下午10:19:22
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
@Service
public class SystemConfigManager {
	/**
	 * 文档存放根目录
	 */
	private String docRoot;

	/**
	 * 文档存放临时目录
	 */
	private String tempDocRoot;

	/**
	 * 图片文件存放目录
	 */
	private String imageRoot;

	/**
	 * 图片访问前缀
	 */
	private String imageWebPath;

    private String forumUrl;

    private String smartUploadVersion;

	/**
	 * @return the wwwImageRoot
	 */
	public String getImageRoot() {
		return imageRoot;
	}

	/**
	 * @param imageRoot the wwwImageRoot to set
	 */
	public void setImageRoot(String imageRoot) {
		this.imageRoot = imageRoot;
	}

	/**
	 * @return the imageWebPath
	 */
	public String getImageWebPath() {
		return imageWebPath;
	}

	/**
	 * @param imageWebPath the imageWebPath to set
	 */
	public void setImageWebPath(String imageWebPath) {
		this.imageWebPath = imageWebPath;
	}

	/**
	 * @return the docRoot
	 */
	public String getDocRoot() {
		return docRoot;
	}

	/**
	 * @param docRoot the docRoot to set
	 */
	public void setDocRoot(String docRoot) {
		this.docRoot = docRoot;
	}

	/**
	 * @return the tempDocRoot
	 */
	public String getTempDocRoot() {
		return tempDocRoot;
	}

	/**
	 * @param tempDocRoot the tempDocRoot to set
	 */
	public void setTempDocRoot(String tempDocRoot) {
		this.tempDocRoot = tempDocRoot;
	}

    public String getForumUrl() {
        return forumUrl;
    }

    public void setForumUrl(String forumUrl) {
        this.forumUrl = forumUrl;
    }

    public String getSmartUploadVersion() {
        return smartUploadVersion;
    }

    public void setSmartUploadVersion(String smartUploadVersion) {
        this.smartUploadVersion = smartUploadVersion;
    }

}
