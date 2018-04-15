package com.jerry.security.core.social.qq.Connect;

import com.jerry.security.core.social.qq.api.QQ;
import com.jerry.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/14
 * Time: 21:17
 * Description:
 */
public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ api) {
        // 测试QQ是否可用
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getQQUserInfo();

        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        // 这个是个人主页，QQ不存在这个概念。
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        // 这里在做绑定解绑的时候使用
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        // 某些社交网站会有更新状态的操作
        // do nothing
    }
}
