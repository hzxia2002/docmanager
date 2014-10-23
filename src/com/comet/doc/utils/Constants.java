package com.comet.doc.utils;

import java.math.BigDecimal;

/**
 * Created by dell on 2014/6/26.
 */
public interface Constants {
    public static final BigDecimal BIG_DECIMAL_ZERO = new BigDecimal(0);

    // 申请单状态
    public static final String[] APPLY_STATUS_ARRAY =
            new String[]{"00", "07","10", "11", "30", "99"};

    public static final String[] APPLY_STATUS_NAME_ARRAY =
            new String[]{"新增","待审核", "待受理", "受理中", "通过", "驳回"};

    // 申请单状态-新增
    public static final String APPLY_STATUS_NEW = "00";
    
    //申请单状态-待审核
    public static final String APPLY_STATUS_CHECK = "07";

    // 申请单状态-待受理
    public static final String APPLY_STATUS_SUBMITTED = "10";

    // 申请单状态-受理中
    public static final String APPLY_STATUS_ACCEPTING = "11";

    // 申请单状态-受理完成
    public static final String APPLY_STATUS_ACCEPTED = "30";

    // 申请单状态-受理完成
    public static final String APPLY_STATUS_AUDIT_REJECT = "90";

    // 申请单状态-拒绝
    public static final String APPLY_STATUS_REJECT = "99";

    // 申请单状态
    public static final String[] PRODUCT_STATUS_ARRAY =
            new String[]{"00", "10", "11", "20", "21", "30", "35", "40", "70", "90", "99"};

    public static final String[] PRODUCT_STATUS_NAME_ARRAY =
            new String[]{"新增", "待审核", "审核中", "待受理", "受理中", "受理完成", "销售日期已设置", "在售", "销售完成", "审批驳回", "受理驳回"};

    // 产品状态-新增
    public static final String PRODUCT_STATUS_NEW = "00";

    // 产品状态-已提交
    public static final String PRODUCT_STATUS_SUBMITED = "10";

    // 产品状态-审核中
    public static final String PRODUCT_STATUS_CHECKING = "11";

    // 产品状态-待受理
    public static final String PRODUCT_STATUS_ACCEPTING = "20";

    // 产品状态-受理中
    public static final String PRODUCT_STATUS_TOACCEPTEND = "21";
    
    // 产品状态-受理完成
    public static final String PRODUCT_STATUS_ACCEPTED = "30";

    // 产品状态-销售日期设置完成
    public static final String PRODUCT_STATUS_DATE_SETTED = "35";

    // 产品状态-在售
    public static final String PRODUCT_STATUS_SALING = "40";

    // 销售完成
    public static final String PRODUCT_STATUS_SALED = "70";

    // 产品状态-审批驳回
    public static final String PRODUCT_STATUS_CHECK_REJECT = "90";

    // 产品状态-已拒绝
    public static final String PRODUCT_STATUS_REJECT = "99";

    // 产品订单状态
    public static final String[] PRODUCT_ORDER_STATUS_ARRAY =
            new String[]{"00", "01", "03", "05", "08", "10", "20", "40", "50",
                    "90", "91", "92", "96", "99"};

    public static final String[] PRODUCT_ORDER_STATUS_NAME_ARRAY =
            new String[]{"新增", "支付中", "支付失败", "支付完成", "已提交", "合同审查通过", "风险审查通过", "信财审查通过", "权益已生效",
                    "合同审查不通过", "风险审查不通过", "信财审查不通过", "用户取消", "无效"};

    // 产品订单状态新增
    public static final String PRODUCT_ORDER_STATUS_NEW = "00";

    // 支付中
    public static final String PRODUCT_ORDER_STATUS_PAYING = "01";

    // 支付失败
    public static final String PRODUCT_ORDER_STATUS_PAY_FAILURE = "03";

    // 支付完成
    public static final String PRODUCT_ORDER_STATUS_PAY = "05";

    // 订单提交
    public static final String PRODUCT_ORDER_STATUS_COMMITTED = "08";

    // 合同审查通过
    public static final String PRODUCT_ORDER_STATUS_CONTRACT = "10";

    // 风险审查通过
    public static final String PRODUCT_ORDER_STATUS_RISK = "20";

    // 信财审查通过，产品订单状态生效
    public static final String PRODUCT_ORDER_STATUS_VALID = "40";

    // 产品订单状态权益生效
    public static final String PRODUCT_ORDER_STATUS_RIGHT_VALID = "50";

    // 风险审查不通过
    public static final String PRODUCT_ORDER_STATUS_RISK_REJECT = "91";

    // 信财审查不通过
    public static final String PRODUCT_ORDER_STATUS_FINANCE_REJECT = "92";

    // 产品订单状态用户取消
    public static final String PRODUCT_ORDER_STATUS_USER_CANCEL = "96";

    // 产品订单状态无效
    public static final String PRODUCT_ORDER_STATUS_INVALID = "99";

    // 权益项 酒店住宿
    public static final String RIGHT_ITEM_HOTEL = "001001";

    // 权益项 马术
    public static final String RIGHT_ITEM_HORSEMANSHIP = "002001";

    // 权益项 马术
    public static final String RIGHT_ITEM_BUY_HOUSE = "003001";

    // 银行代码
    public static final String BANK_CODE = "BANK";

    //证件类型
    public static final String CARD_TYPE_CODE = "CARD_TYPE";

    // 兑换订单
    public static final String ORDER_TYPE_EXCHANGE = "0";

    // 酒店订单
    public static final String ORDER_TYPE_HOTEL = "1";

    // 用户订单
    public static final String ORDER_SOURCE_USER = "0";

    // 后台订单
    public static final String ORDER_SOURCE_ADMIN = "1";

    // 用户订单状态
    public static final String[] USER_ORDER_STATUS_ARRAY =
            new String[]{"00", "10", "20", "30", "50", "99", "90", "91"};

    public static final String[] USER_ORDER_STATUS_NAME_ARRAY =
            new String[]{"待确认", "已审核", "已确认", "已入住", "已成交", "已删除", "申请取消", "已取消"};

    // 新增
    public static final String ORDER_STATUS_NEW = "00";

    // 已审核
    public static final String ORDER_STATUS_AUDIT = "10";

    // 已确认
    public static final String ORDER_STATUS_CONFIRMED = "20";

    // 已入住
    public static final String ORDER_STATUS_CHECKINED = "30";

    // 已成交
    public static final String ORDER_STATUS_DEALED = "50";

    // 已删除
    public static final String ORDER_STATUS_DELETED = "99";

    // 取消申请
    public static final String ORDER_STATUS_CANCEL_APPLY = "90";

    // 取消确认
    public static final String ORDER_STATUS_CANCELED = "91";

    //会员类型
    // 用户权益来源
    public static final String[] RIGHT_SOURCE_ARRAY =
            new String[]{"1", "2"};

    public static final String[] RIGHT_SOURCE_NAME_ARRAY =
            new String[]{"产品权益", "兑换权益"};

    // 产品权益
    public static final String RIGHT_SOURCE_PRODUCT = "1";

    // 兑换权益
    public static final String RIGHT_SOURCE_EXCHANGE = "2";

    // 内部用户
    public static final String  INNER_USER = "00";

    // 中信用户
    public static final String  CITIC_USER = "20";

    // 会员
    public static final String  USER_TYPE_MEMBER = "11";

    // 管家
    public static final String  USER_TYPE_ADMIN = "00";

    //权益变更申请状态
    public static final String  BENEFICIARY_STATUS_APPLY = "00";

    //权益变更通过状态
    public static final String  BENEFICIARY_STATUS_CHECKINED = "01";

    //权益变更驳回状态
    public static final String  BENEFICIARY_STATUS_INVALID = "02";

    //申请人类型为管家
    public static final String  APPLY_TYPE_MANAGER = "1";

    //申请人类型为用户
    public static final String  APPLY_TYPE_USER = "0";

    // 注册时的验证码
    public static final String  VALIDATION_CODE_REG = "VALIDATION_CODE_REG";

    // 登录时的验证码
    public static final String  VALIDATION_CODE_LOGIN = "VALIDATION_CODE_LOGIN";

    public static final String VALIDATE_CODE_TYPE_REGISTER = "0"; // 注册校验码
    public static final String VALIDATE_CODE_TYPE_PASSWORD = "1"; // 找回密码验证码
    public static final String VALIDATE_CODE_TYPE_ACTIVATE = "2"; // 账户激活验证码
    public static final String VALIDATE_CODE_TYPE_MODIFY_MOBILE = "3"; // 修改手机号码验证码
    public static final String VALIDATE_CODE_TYPE_USERNAME = "4"; // 发送用户名
    public static final String VALIDATE_CODE_TYPE_CERTIFICATE = "5"; // 用户实名认证验证码

    public static final String SMS_TEMPLATE_ID_REGISTER = "030000002_001"; // 用户注册短信
    public static final String SMS_TEMPLATE_ID_ACTIVATE = "030000002_001"; // 用户激活短信
    public static final String SMS_TEMPLATE_ID_PASSWORD = "030000002_001"; // 用户修改密码短信
    public static final String SMS_TEMPLATE_ID_MODIFY_MOBILE = "030000002_001"; // 用户修改密码短信
    public static final String SMS_TEMPLATE_ID_USERNAME = "030000002_001"; // 发送用户名短信
    public static final String SMS_TEMPLATE_ID_CERTIFICATE = "030000002_001"; // 用户实名认证验证码

    public static final String UNIT_YEAR = "Y"; // 单位-年
    public static final String UNIT_MONTH = "M"; // 单位-月
    public static final String UNIT_DAY = "D"; // 单位-天

    public static final String RIGHT_STATUS_VALID = "1"; // 权限状态-有效
    public static final String RIGHT_STATUS_INVALID = "0"; // 权限状态-待生效
    public static final String RIGHT_STATUS_DISABLED = "2"; // 权限状态-已失效
}
