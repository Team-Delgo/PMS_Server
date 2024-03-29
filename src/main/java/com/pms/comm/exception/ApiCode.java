package com.pms.comm.exception;

public enum ApiCode {

    // -------------------------COMMON-------------------------
    SUCCESS(API.CODE.SUCCESS, "SUCCESS"), //200

    PARAM_ERROR(API.CODE.PARAM_ERROR, "PARAM_ERROR"),
    PARAM_DATE_ERROR(API.CODE.PARAM_DATE_ERROR, "PARAM_DATE_ERROR"),

    // TOKEN ERROR
    TOKEN_ERROR(API.CODE.TOKEN_ERROR, "TOKEN_ERROR"),

    // LOGIN ERROR
    LOGIN_ERROR(API.CODE.LOGIN_ERROR, "LOGIN_ERROR"),

    // DB ERROR
    DB_FAIL(API.CODE.DB_ERROR, "DB ERROR"),
    DB_DELETE_ERROR(API.CODE.DB_DELETE_ERROR, "DB DELETE ERROR"),
    NOT_FOUND_DATA(API.CODE.NOT_FOUND_DATA, "DB NOT_FOUND_DATA"),

    // NCP ERROR
    PHOTO_UPLOAD_ERROR(API.CODE.PHOTO_UPLOAD_ERROR, "NCP PHOTO_UPLOAD_ERROR"),
    SMS_ERROR(API.CODE.SMS_ERROR, "NCP SMS ERROR"),

    // ------------------------FUNCTION----------------------------
    // COUPON ERROR
    COUPON_SELECT_ERROR(API.CODE.COUPON_SELECT_ERROR, "존재하지 않는 쿠폰 번호입니다."),
    COUPON_DUPLICATE_ERROR(API.CODE.COUPON_DUPLICATE_ERROR, "이미 존재하는 쿠폰입니다."),

    //REVIEW ERROR
    REVIEW_DUPLICATE_ERROR(API.CODE.REVIEW_DUPLICATE_ERROR, "REVIEW_DUPLICATE_ERROR"),

    //BOOKING ERROR
    BOOKING_CANCEL_ERROR(API.CODE.BOOKING_CANCEL_ERROR, "BOOKING_CANCEL_ERROR"),
    BOOKING_CANCEL_STATE_ERROR(API.CODE.BOOKING_CANCEL_STATE_ERROR, "취소 대기 중인 상태가 아닙니다."),


    // EMAIL ERROR
    EMAIL_NOT_EXIST(API.CODE.EMAIL_NOT_EXIST, "존재하지 않는 이메일입니다."),
    EMAIL_DUPLICATE_ERROR(API.CODE.EMAIL_DUPLICATE_ERROR, "이미 존재하는 이메일입니다."),

    // SMS AUTH ERROR
    AUTH_NO_NOT_MATCHING(API.CODE.AUTH_NO_NOT_MATCHING, "인증번호가 일치하지 않습니다."),

    // PHONE NO ERROR
    PHONE_NO_NOT_EXIST(API.CODE.PHONE_NO_NOT_EXIST, "존재하지 않는 전화번호입니다."),
    PHONE_NO_DUPLICATE_ERROR(API.CODE.PHONE_NO_DUPLICATE_ERROR, "이미 존재하는 전화번호입니다."),

    UNKNOWN_ERROR(API.CODE.UNKNOWN_ERROR, "알수 없는 오류");


    private int code;
    private String msg;

    ApiCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public static ApiCode CODE(int intCode) {
        ApiCode apiCode = null;
        for (ApiCode code : ApiCode.values()) {
            if (intCode == code.getCode()) {
                apiCode = code;
                break;
            }
        }

        if (apiCode == null) {
            apiCode = ApiCode.SUCCESS;
        }

        return apiCode;
    }
}