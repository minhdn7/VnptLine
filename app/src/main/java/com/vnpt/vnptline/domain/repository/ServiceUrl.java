package com.vnpt.vnptline.domain.repository;

/**
 * Created by MinhDN on 1/2/2018.
 */

public class ServiceUrl {
    public static final String GET_TOKEN_DEV = "/vnptline/rest/token/get";

    // user
    public static final String CHECK_USER = "/vnptline/rest/user/check";
    public static final String LOGIN = "/vnptline/rest/user/login";
    public static final String CHANGE_PASSWORD = "/vnptline/rest/user/password/change";
    public static final String FORGOT_PASSWORD = "/vnptline/rest/user/password/forgot";
    public static final String REGISTER_USER = "/vnptline/rest/user/registration";
    public static final String LOGOUT_USER = "/vnptline/rest/customer/user/logout";
    // end

    // otp
    public static final String GET_OTP = "/vnptline/rest/otp/send";
    public static final String VERIFY_OTP = "/vnptline/rest/otp/verify";
    // end

    // hotel
    public static final String SEARCH_HOTEL = "/vnptline/rest/hotel/search";
    public static final String LAY_DANH_SACH_TIEN_ICH = "/vnptline/rest/hotel/amenities/list";
    public static final String LAY_CHI_TIET_HOTEL = "/vnptline/rest/hotel/get";
    public static final String LAY_HOTEL_NOI_BAT = "/vnptline/rest/hotel/adv";
    // end

    // room hotel
    public static final String LAY_DANH_SACH_PHONG = "/vnptline/rest/roomtype/available";
    public static final String DAT_PHONG = "/vnptline/rest/customer/booking";

    // end

    // lịch sử đặt phòng
    public static final String LICH_SU_DAT_PHONG = "/vnptline/rest/booking/history";
    // end

    // danh sách thông báo
    public static final String DANH_SACH_THONG_BAO = "/vnptline/rest/customer/notification/find";
    // end

    // lịch sử
    public static final String LICH_SU = "/vnptline/rest/customer/booking/history";
    public static final String CHI_TIET_LICH_SU = "/vnptline/rest/customer/booking/detail";
    // end

    // service
    public static final String TIM_PHONG_DANG_SU_DUNG = "/vnptline/rest/customer/booking/findBookedRoom";
    public static final String TIM_DICH_VU = "/vnptline/rest/customer/service/findServices";
    public static final String ORDER_DICH_VU = "/vnptline/rest/customer/service/order";
    public static final String SUPPORT_DICH_VU = "/vnptline/rest/customer/support/request";
    // end
}
