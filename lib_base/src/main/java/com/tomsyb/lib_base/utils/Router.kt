package com.tomsyb.lib_base.utils

import android.content.Intent
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.core.LogisticsCenter
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Author:      HW
 * Date:        19-11-15 下午5:10
 * Description:
 */
object Router {

    /** 在Fragment中调用startActivityForResult */
    fun fragmentStartActivityForResult(fragment: Fragment, postcard: Postcard, requestCode: Int) {
        LogisticsCenter.completion(postcard)
        val intent = Intent(fragment.context, postcard.destination).putExtras(postcard.extras)
        fragment.startActivityForResult(intent, requestCode)
    }

    /** 根据ARouter的路径获取Activity的Classname */
    fun obtainClassName(route: String): String {
        return obtainClass(route)?.name ?: ""
    }

    fun obtainClass(route: String): Class<*>? {
        val postcard = ARouter.getInstance().build(route)
        LogisticsCenter.completion(postcard)
        return postcard.destination
    }


    object ModuleApp {
        private const val MODULE_NAME = "/app"

        object Activity {
            const val SPLASH = "$MODULE_NAME/SplashActivity"
            const val MAIN = "$MODULE_NAME/MainActivity"
            const val GUIDE = "$MODULE_NAME/GuideActivity"
            const val SCREEN_AD = "$MODULE_NAME/OpenScreenAdAdActivity"
        }

        object Fragment {

        }
    }

    object ModuleBase {
        private const val MODULE_NAME = "/base"

        object Activity {
            const val WEB = "$MODULE_NAME/WebActivity"
            const val GUIDE_ANIMATION = "$MODULE_NAME/AnimationGuideActivity"
            const val WEB_GAME = "$MODULE_NAME/GameWebActivity"
            const val CAMERA1 = "$MODULE_NAME/Camera1Activity"
            const val LOTTERY = "$MODULE_NAME/CircleLotteryActivity"
            const val PAY_COUPON = "$MODULE_NAME/PayCouponActivity"
            const val SELECT_COUPON = "$MODULE_NAME/SelectCouponActivity"
            const val SHOW_PIC_VIDEO_SINGLE = "${MODULE_NAME}/ShowPicVideoSingleActivity"
            const val RAFFLE_BENEFITS_ACTIVITY = "${MODULE_NAME}/RaffleBenefitsActivity"
            const val RECEIVED_BENEFITS_ACTIVITY = "${MODULE_NAME}/ReceivedBenifitsActivity"
            const val INTEGRAL_ACTIVITY = "${MODULE_NAME}/INTEGRAL_ACTIVITY"
            const val COLOR_SELECT = "${MODULE_NAME}/ColorSelectActivity"
        }

        object Fragment {
            const val DISCOVER = "$MODULE_NAME/DiscoverFragment"
        }

        object Service {
            const val DOWN_SERVICE = "$MODULE_NAME/DownloadService"
        }
    }

    object ModuleConversation {
        private const val MODULE_NAME = "/conversation"

        object Activity {

            // 群成员资料查看
            const val GROUPSEEMEMBERACT = "$MODULE_NAME/GroupSeeMemberActivity"

            // 单张图片预览
            const val IMGPREVIEWACT = "$MODULE_NAME/ImgPreviewActivity"

            // 个人用户信息陌生人
            const val PERSONALSTRANGERACT = "$MODULE_NAME/PersonalStrangerActivity"

            // 截屏信息页面
            const val PERSONAL_SCREEN_SHOT = "$MODULE_NAME/PersonalScreenShotActivity"

            // 个人用户信息朋友
            const val PERSONALFRIENDACT = "$MODULE_NAME/PersonalFriendActivity"

            // 附近人个人详情
            const val PERSONALNEARBYACT = "$MODULE_NAME/NearbyPeopleInfoActivity"

            // 客服资料
            const val CUSTOMSERVERACT = "$MODULE_NAME/CustomServerActivity"

            // 通知中心
            const val NOTIFYCENTERACT = "$MODULE_NAME/NotificationCenterActivity"

            // 转移群主
            const val GROUPTURNPEOPLEACT = "$MODULE_NAME/GroupTurnActivity"

            // 群成员界面
            const val GROUPMEMBERSACT = "$MODULE_NAME/GroupMembersActivity"

            // 升级为附近群完善资料界面
            const val GROUPNEARBYETACT = "$MODULE_NAME/GroupNearbyEtActivity"

            // 群标签界面
            const val GROUPTIPACT = "$MODULE_NAME/GroupTipActivity"

            // 福利
            const val WELFAREACT = "$MODULE_NAME/WelfareActivity"

            // 群举报界面
            const val GROUPREPORTACT = "$MODULE_NAME/ReportActivity"

            // 选择聊天记录
            const val REPORT_SELECTED_CHAT_HISTORY = "$MODULE_NAME/ReportChatHistorySelectedActivity"

            // 问题反馈
            const val ADVICEREPORTACT = "$MODULE_NAME/ReportAdviceActivity"

            // 群标签编辑界面
            const val GROUPTIPETACT = "$MODULE_NAME/GroupTipEtActivity"

            // 群标签查找结果界面
            const val GROUPTIPFINDACT = "$MODULE_NAME/GroupTipFindActivity"

            // 群聊资料
            const val GroupApplyDetailActivity = "$MODULE_NAME/GroupApplyDetailActivity"

            // 设置管理员
            const val GROUPSETINGMANAGERACT = "$MODULE_NAME/SettingGroupManagerActivity"

            // 管理群界面
            const val MANAGEMENTGROUPACT = "$MODULE_NAME/ManagementGroupActivity"
            const val GROUP_APPLY_LIST = "$MODULE_NAME/GroupApplyListActivity"//群审核列表页

            // 加群方式
            const val JSONGROUPSTYLEACT = "$MODULE_NAME/GroupJoinStyleActivity"

            // 查找方式
            const val FINDSTYLEACT = "$MODULE_NAME/FindStyleActivity"

            // 编辑资料
            const val EDITINGMATERIALSACT = "$MODULE_NAME/EditingMaterialsActivity"

            // 管理员列表
            const val GROUPMANAGERLISTSACT = "$MODULE_NAME/GroupManagerListActivity"

            // 新添加联系人搜索界面
            const val CONTACTSEARCHACT = "$MODULE_NAME/ContactSearchActivity"

            // 添加联系人搜索界面
            const val ADDFRIENDSSEARCHACT = "$MODULE_NAME/AddFriendsSearchActivity"

            // 本地数据用户搜索
            const val SEARCHLOCALGROUPACT = "$MODULE_NAME/SearchLocalGroupActivity"

            // 群列表搜索
            const val SEARCHGROUPLSITACT = "$MODULE_NAME/SearchGroupListActivity"

            // 添加朋友用户单独搜索界面
            const val ADDFRIENDSUSERSEARCHACT = "$MODULE_NAME/AddFriendsUserSearchActivity"

            // 更具用户id搜索再跳详情页面
            const val SEARCHUSERBYIDACT = "$MODULE_NAME/SearchUserByIdActivity"

            // 添加朋友群单独搜索界面
            const val ADDFRIENDSGROUPSEARCHACT = "$MODULE_NAME/AddFriendsGroupSearchActivity"

            // 聊天背景
            const val CHATBACKGROUNDACT = "$MODULE_NAME/ChatBackgroundActivity"

            // 提升群人数界面
            const val ADDGROUPPEOPLENUMACT = "$MODULE_NAME/AddGroupPeopleNumActivity"

            // 添加联系人发送验证界面
            const val ADDFRIENDSACT = "$MODULE_NAME/AddFriendsSendMsgActivity"

            // 添加联系人或群初始界面
            const val ADDCONTACTGROUPACT = "$MODULE_NAME/AddContactGroupActivity"

            //
            const val FRIENDS_CLEAN = "$MODULE_NAME/FriendsCleanActivity"
            const val FRIENDS_CLEAN_LIST = "$MODULE_NAME/FriendsCleanListActivity"

            // 群介绍
            const val GROUPINTRODUCTIONACT = "$MODULE_NAME/EditSendActivity"

            // 黑名单
            const val BACKLISTACT = "$MODULE_NAME/BlacklistActivity"
            const val SINGLE_CONVERSATION = "$MODULE_NAME/SingleConversationActivity"
            const val SINGLE_SECRET_CONVERSATION = "$MODULE_NAME/SingleSecretConversationActivity"
            const val GROUP_CONVERSATION = "$MODULE_NAME/GroupConversationActivity"
            const val GLOBAL_SEARCH = "$MODULE_NAME/GlobalSearchActivity"
            const val DIRECTED_SEARCH = "$MODULE_NAME/DirectedSearchActivity"
            const val RECORD_SEARCH = "$MODULE_NAME/RecordSearchActivity"
            const val RECORD_MEDIA = "$MODULE_NAME/MediaRecordActivity"
            const val RECORD_FILE = "$MODULE_NAME/FileRecordActivity"
            const val SINGLE_CONVERSATION_SETTING = "$MODULE_NAME/ConversationFriendSettingActivity"
            const val CREATE_GROUP_HORN = "$MODULE_NAME/CreateGroupSpeakerActivity"

            const val BULK_ASSISTANT = "$MODULE_NAME/BulkAssistantActivity"
            const val BATCH_MESSAGE_CREATE = "$MODULE_NAME/BulkAssistantCreateActivity"
            const val TEXT_MAGNIFIER = "$MODULE_NAME/TextMagnifierActivity"

            // 群聊详情界面
            const val GROUP_CONVERSATION_SETTING = "$MODULE_NAME/ConversationGroupSettingActivity"
            const val GROUP_APPLY_QUESTION = "$MODULE_NAME/GroupApplyQuestionActivity"
            const val NAVIGATION = "$MODULE_NAME/NavigationMapActivity"
            const val LOCATION = "$MODULE_NAME/LocationActivity"
            const val LOCATIONNEW = "$MODULE_NAME/LocationNewActivity"
            const val MEDIA_PREVIEW = "$MODULE_NAME/MediaPreviewActivity"
            const val FILE_DETAIL = "$MODULE_NAME/FileDetailActivity"
            const val CAMERA = "$MODULE_NAME/CameraActivity"
            const val SELECT_CONTACTS = "$MODULE_NAME/SelectContactsActivity"//选择联系人
            const val CREATE_GROUP_CHAT = "$MODULE_NAME/CreateGroupActivity"//创建群
            const val GROUP_LIST = "$MODULE_NAME/GroupListActivity"//群列表
            const val GROUP_NOTIFICATION = "$MODULE_NAME/GroupNotificationActivity"//群列表
            const val CONTACT_LABELS_ACT = "$MODULE_NAME/FriendsLabelsActivity"//好友标签界面
            const val CONTACT_LABELS_INFO_ACT = "$MODULE_NAME/FriendsLabelsInfoActivity"//好友标签详情界面
            const val CONTACT_LABELS_SAVE_ACT = "$MODULE_NAME/FriendsLabelsSaveActivity"//好友标签保存界面
            const val GROUP_RESTRICTED = "$MODULE_NAME/GroupMemberRestrictedActivity"//限制群成员列表
            const val GROUP_SLIENT = "$MODULE_NAME/GroupSlientActivity"//群禁言列表

            // 转发
            const val FORWARD = "$MODULE_NAME/ForwardActivity"
            const val FAVORITE_EMOTION = "$MODULE_NAME/FavoriteEmotionActivity"

            // 选择好友
            const val SELECT_FRIEND = "$MODULE_NAME/SelectFriendActivity"
            const val IM_CALL_ACT2 = "$MODULE_NAME/SingleCallActivity2"////1v1语音、视频聊天
            const val SETTING_FONT_SIZE = "$MODULE_NAME/FontSizeSettingActivity"
            const val ANNOUNCEMENT = "$MODULE_NAME/SystemAnnouncementActivity"
            const val SYSTEM_NEWS = "$MODULE_NAME/SystemNewsActivity"

            const val TAG_USER_LIST = "$MODULE_NAME/TagUserListActivity"//标签列表

            //------------------------------------------------漂流瓶
            const val DRIFT_BOTTLE_MAIN_ACT = "$MODULE_NAME/DriftbottleMainActivity"//漂流瓶主页
            const val DRIFT_BOTTLE_INFO_ACT = "$MODULE_NAME/BottleInfoSettingActivity"//漂流瓶分身设置界面
            const val DRIFT_BOTTLE_ANSWER_ACT = "$MODULE_NAME/DriftbottleAnswerActivity"//回复瓶子

            //------------------------------------------------用户活跃度
            // 类型界面
            const val USERACTIVESTYLEACT = "$MODULE_NAME/UserActiveStyleActivity"
            const val USERACTIVEACT = "$MODULE_NAME/UserActiveActivity"// 成员选择界面
            const val GROUPACTIVEACT = "$MODULE_NAME/GroupActiveActivity"// 群成员活跃度
            const val NOTE_DETAIL = "$MODULE_NAME/GroupNoteDetailActivity"


            const val GROUP_MUTE_SETTING = "$MODULE_NAME/GroupMuteSettingActivity"

            //只看某人的消息
            const val SEE_SOMEONE_MSG = "$MODULE_NAME/SeeSomeoneMsgActivity"

            // 消息分组界面
            const val MESSAGE_GROUP_ACT = "$MODULE_NAME/MessageGroupActivity"

            // 消息創建界面
            const val MESSAGE_GROUP_CREATE = "$MODULE_NAME/MessageGroupCreateActivity"
            const val MESSAGE_GROUP_CARETE_SELECTED = "$MODULE_NAME/MessageGroupCreateSelected"
            const val MESSAGE_GROUP_CREATE_SELECTED_GROUP = "$MODULE_NAME/MessageGroupCreateGroupSelected"

            // ------------------------------------------------密聊
            const val SECRET_PASSWORD = "$MODULE_NAME/SecretPasswordActivity" // 密聊密码
            const val SECRET_MAIN = "$MODULE_NAME/SecretMainActivity" // 密聊主界面
            const val SEARCH_SECRET_RECORD = "$MODULE_NAME/SearchSecretRecordActivity" // 密聊聊天记录搜索
            const val SECRET_CHAT_SETTING = "$MODULE_NAME/SecretChatSettingActivity" // 密聊聊天设置

            //转发的聊天记录页
            const val FORWARD_CHAT_RECORD = "$MODULE_NAME/ForwardChatRecordActivity"
        }

        object Fragment {
            const val LIST = "$MODULE_NAME/ConversationHistoryFragment"
            const val RECORD = "$MODULE_NAME/RecordListFragment"
            const val CONTACT = "$MODULE_NAME/ContactFragment"
            const val FINDPEOPLE = "$MODULE_NAME/FragmentFindPeople"
            const val MYCOUPON = "$MODULE_NAME/MyCouponFragment"
            const val FINDGROUP = "$MODULE_NAME/FragmentFindGroup"
            const val BOTTLENETFRAGMENT = "$MODULE_NAME/DriftbottleNetFragment"
            const val BOTTLEMYSELFFRAGMENT = "$MODULE_NAME/DriftbottleMyselfFragment"
            // 密聊
            const val SECRET_CHAT = "$MODULE_NAME/SecretRecordFragment"
            const val SECRET_CONTACT = "$MODULE_NAME/SecretContactFragment"
            const val SINGLE_CONVERSATION = "$MODULE_NAME/SingleConversationFragment"
            const val SINGLE_SECRET_CONVERSATION = "$MODULE_NAME/SingleSecretConversationFragment"
            const val GROUP_CONVERSATION = "$MODULE_NAME/GroupConversationFragment"
        }

        object Service {
            const val IM_CALL_SERVICE = "$MODULE_NAME/SingleCallService"
        }

    }

    object ModuleMoment {
        private const val MODULE_NAME = "/moment"

        object Activity {
            const val MOMENT_ADMIN = "$MODULE_NAME/MomentAdminActivity"
            const val MOMENT_INTRODUCE = "$MODULE_NAME/MomentIntroduceActivity"
            const val MOMENT_CREATE = "$MODULE_NAME/CreateMomentActivity"
            const val MOMENT_MINE = "$MODULE_NAME/MyCommentActivity"
            const val MOMENT_PIC_VIDEO = "$MODULE_NAME/MomentPicVideoActivity"
            const val MOMENT_INDEN = "$MODULE_NAME/MomentIndenActivity"
            const val MOMENT_SAY_HI = "$MODULE_NAME/SayHiActivity"
            const val MOMENT_SAY_HI_INFO = "$MODULE_NAME/SayHiMomentInfoActivity"
            const val MOMENT_SAY_HI_LIST = "${MODULE_NAME}/SayHiListActivity" // 打招呼界面
            const val CUSTOM_TAG = "${MODULE_NAME}/CustomMomentTagActivity"// 自定义标签
            const val MOMENT_MANAGER = "${MODULE_NAME}/MessageManagerActivity"
            const val MOMENT_SEARCH = "${MODULE_NAME}/MomentSearchActivity"
            const val MOMENT_HOT_TOPIC = "${MODULE_NAME}/MomHotTopicActivity"
            const val MOMENT_STAR = "${MODULE_NAME}/MessageStarActivity"
            const val MOMENT_HOTTOPIC = "${MODULE_NAME}/MomentHotTopicActivity"
            const val MOMENT_COMMENT = "${MODULE_NAME}/MessageCommentActivity"
            const val MOMENT_AT_MINE = "${MODULE_NAME}/MessageAtMineActivity"
            const val MOMENT_WITH_MINE = "${MODULE_NAME}/MomentMineActivity"
            const val MOMENT_WITH_MINE_DETAIL = "${MODULE_NAME}/MomentMineDetailActivity"
            const val MOMENT_MAIN = "${MODULE_NAME}/MomentMainActivity"//瞬间主页
            const val MOMENT_PEOPLE_NEARBY = "${MODULE_NAME}/PeopleNearbyActivity"
            const val MOMENT_DETAIL = "${MODULE_NAME}/MomentDetailActivity"
            const val MOMENT_CREATE_SET = "${MODULE_NAME}/CreateMomentSetActivity"
            const val MOMENT_LIKE_LIST = "${MODULE_NAME}/MomentLikeListActivity"//瞬间点赞列表
        }

        object Fragment {
            const val MOMENT = "$MODULE_NAME/MomentFragment"
            const val HOTTOPIC = "$MODULE_NAME/MomentHotTopicFragment"
        }
    }

    object ModuleUser {
        private const val MODULE_NAME = "/user"

        object Activity {
            const val CHOOSE_LOGIN = "$MODULE_NAME/LoginChooseActivity"
            const val PHONE_EMAIL_INPUT = "$MODULE_NAME/PhoneEmailActivity"
            const val PHONE_EMAIL_INPUT_REM = "$MODULE_NAME/PhoneEmailRememberActivity"
            const val PHONE_EMAIL_VERIFY = "$MODULE_NAME/PhoneEmailVerifyActivity"
            const val REGISTER_SUCCESS = "$MODULE_NAME/RegisterSuccessActivity"
            const val NAME_REGISTER = "$MODULE_NAME/NameRegisterActivity"
            const val NAME_REGISTER_VERIFY = "$MODULE_NAME/NameRegisterVerifyActivity"
            const val LOGIN_ID = "$MODULE_NAME/IdLoginActivity"
            const val LOGIN_ID_REM = "$MODULE_NAME/IdLoginRemActivity"
            const val ME_USER_SETTING = "$MODULE_NAME/UserSettingActivity"
            const val ME_EDIT_TEXT = "$MODULE_NAME/EditTextActivity"
            const val QR_CODE = "$MODULE_NAME/QrCodeActivity"
            const val ME_SETTING_NOTIFICATION = "$MODULE_NAME/NotificationSetActivity"
            const val ME_SETTING_SECURITY = "$MODULE_NAME/SecurityActivity"
            const val ME_SETTING_EXCHANGE_SECRET_KEY = "$MODULE_NAME/SecretKeyActivity"
            const val ME_SETTING_LOGIN_PWD = "$MODULE_NAME/SetLoginPwdActivity"
            const val ME_SETTING_UNLOCK_PWD = "$MODULE_NAME/SetUnlockPwdActivity"
            const val ME_SETTING_UNLOCK_DESTROY_INPUT = "$MODULE_NAME/UnlockDestroyInputActivity"
            const val ME_SETTING_DEVICE_MANAGE = "$MODULE_NAME/DeviceManageActivity"
            const val ME_SETTING_DEVICE_MANAGE_DETAIL = "$MODULE_NAME/DeviceManageDetailActivity"
            const val ME_SETTING_CLEAR_RECORD = "$MODULE_NAME/ClearChatRecordActivity"
            const val ME_SETTING_PRIVACY = "$MODULE_NAME/PrivacyActivity"
            const val ME_COMMON = "$MODULE_NAME/SetCommonActivity"
            const val ME_SETTING_CLOAKING = "$MODULE_NAME/CloakingMemberActivity"
            const val ME_SETTING_CLOAKING_SEARCH = "$MODULE_NAME/CloakingSearchActivity"
            const val ME_SETTING_ADD_MY_WAY = "$MODULE_NAME/AddMyWayActivity"
            const val ME_SETTING_FIND_PSD_SETTING = "$MODULE_NAME/FindPsdSettingActivity"
            const val ME_SETTING_FIND_PSD_GET = "$MODULE_NAME/FindPsdGetCodeActivity"
            const val ME_SETTING_FIND_PSD_ET = "$MODULE_NAME/FindPsdEtCodeActivity"
            const val ME_SETTING_LANGUAGE = "$MODULE_NAME/SetLanguageActivity"
            const val ME_SETTING_DARK_MODE = "$MODULE_NAME/SetDarkModeActivity"
            const val ME_SHARE = "$MODULE_NAME/ShareActivity"
            const val ME_CHAT_SECRET = "$MODULE_NAME/SetChatSecretActivity"
            const val FINGER_VERIFY = "$MODULE_NAME/FingerVerifyActivity"
            const val SETTING = "$MODULE_NAME/SetActivity"
            const val STORAGE = "$MODULE_NAME/ClearStorageActivity"
            const val ACCOUNT_VERIFY_PWD = "$MODULE_NAME/AccountVerifyPwdActivity"//账号验证，密码输入
            const val MODIFY_PHONE_BY_LOGIN_PWD = "$MODULE_NAME/ModifyPhoneByLoginPwdActivity"//账号验证，手机号输入
            const val MODIFY_PHONE_BY_SECU_QUES =
                "$MODULE_NAME/ModifyPhoneBySecuQuesActivity"//通过密保问题修改手机号
            const val ACCOUNT_SECURITY_VERIFY = "$MODULE_NAME/AccountSecurityVerifyActivity"//账号验证，直接验证手机号
            //账号验证方式选择也
            const val ACCOUNT_SECURITY = "$MODULE_NAME/AccountSecurityActivity"
            const val LOGIN_SET = "$MODULE_NAME/LoginSetActivity"//登录设置
            const val SET_SECURITY_QUESTION = "$MODULE_NAME/SetSecurityQuestionActivity"//设置密保问题
            const val FORGET_PWD_INPUT = "$MODULE_NAME/ForgetPwdActivity"//忘记密码输入手机号或者id
            const val FORGET_MODIFY_PWD = "$MODULE_NAME/ForgetPwdSetNewActivity"//忘记密码，验证密保修改密码
            const val FORGET_UNLOCK_DESTROY =
                "$MODULE_NAME/ForgetUnlockDestroyPwdActivity"//忘记解锁、销毁密码
            const val SET_ACCOUNT = "$MODULE_NAME/SetAccountActivity"//设置-账号
            const val LOCK_DELAY = "$MODULE_NAME/LockDelayActivity"//锁屏延时

            // 输入预设暗语界面
            const val ME_CHAT_SECRET_INPUT = "$MODULE_NAME/InputSecretActivity"
            const val ME_CHAT_SECRET_DETAIL = "$MODULE_NAME/ChatSecretDetailActivity"
            const val ME_ABOUT = "$MODULE_NAME/AboutActivity"
            const val ME_BUG_ADVICE = "$MODULE_NAME/BugAdviceActivity"
            const val ME_BELLS_LIST = "$MODULE_NAME/BellsSelectActivity"

            const val ME_PRETTY_NUM_INVITE = "$MODULE_NAME/InviterActivity"//
            const val ME_PRETTY_NUM_DETAIL = "$MODULE_NAME/PrettyNumDetailActivity"//
            const val ME_PRETTY_NUM_MAIN = "$MODULE_NAME/PrettyNumMainActivity"//
            const val ME_PRETTY_NUM_SQUARE = "$MODULE_NAME/PrettyNumSquareActivity"//
            const val ME_PRETTY_NUM_SQUARE_DETAIL = "$MODULE_NAME/PrettyNumSquareDetailActivity"//
            const val PRETTY_MINE = "$MODULE_NAME/MyPrettyActivity"

            // 新设备验证相关
            const val NEW_VFC_EQUIPMENT_ACT = "$MODULE_NAME/NewEquipmentVfcActivity"//新设备登录验证
            const val NEW_VFC_IDS_ACT = "$MODULE_NAME/VfcIdsActivity"//身份验证
            const val NEW_VFC_CODE_ACT = "$MODULE_NAME/VfcCodeActivity"//验证码验证
            const val SHARE_IMG_TO_BAT_ACT = "$MODULE_NAME/ShareImgToBatActivity"//分享相册到bat
            const val PHONE_EMAIL_BINDED = "$MODULE_NAME/PhoneNumBindedActivity"//已经绑定的手机号
            const val PHONE_EMAIL_MODIFY1 = "$MODULE_NAME/PhoneEmailModify1Activity"//更换手机号、邮箱1
            const val PHONE_EMAIL_MODIFY2 = "$MODULE_NAME/PhoneEmailModify2Activity"//更换手机号、邮箱2

            //手机号邮箱验证
            const val PHONE_EMAIL_VERIFY_ONLY = "$MODULE_NAME/PhoneVerifyActivity"//
            //验证登录密码
            const val VERIFY_LOGIN_PWD = "$MODULE_NAME/VerifyLoginPwdActivity"//

            const val WEB_LOGIN_DISPOSE = "$MODULE_NAME/WebLoginDisposeActivity"//web登录处理
            const val WEB_LOGIN = "$MODULE_NAME/WebLoginActivity"//web登录

            const val USER_TAG = "$MODULE_NAME/UserTagsActivity"//个人标签
            const val USER_TAG_EDIT = "$MODULE_NAME/UserTagsEditActivity"//个人标签编辑

            //等级
            const val GRADE_MAIN = "$MODULE_NAME/GradeMainActivity"//等级主页
            const val GRADE_RANKING_LIST = "$MODULE_NAME/GradeRankingListActivity"//等级排行列表
            const val GRADE_TODAY_SCORE_LIST =
                "$MODULE_NAME/TodayScrollDetailListActivity"//今日积分成长列表

            //vip
            const val VIP_MAIN = "$MODULE_NAME/VipMainActivity"//VIP
            const val VIP_CENTER = "$MODULE_NAME/VipCenterActivity"//VIP
            const val VIP_PERMISSION_SET = "$MODULE_NAME/VipPermissionSetActivity"//VIP权益设置
            const val VIP_COLOR_SET = "$MODULE_NAME/NameColorActivity"//VIP颜色设置

            const val AREA_SELECT = "$MODULE_NAME/AreaActivity"//地区选择

            //封号
            const val BANNED_SHOW = "$MODULE_NAME/AccountBannedActivity"//被封号展示页
            const val ACCOUNT_APPEAL = "$MODULE_NAME/AccountAppealActivity"//账号申诉页
            const val ACCOUNT_APPEAL_RESULT = "$MODULE_NAME/AccountAppealResultActivity"//账号申诉结果

            //拉新福利
            const val INVITE_NEW_USER_DATA = "$MODULE_NAME/InviteNewUserDataActivity"//拉新总页
            const val INVITE_NEW_USER_LIST = "$MODULE_NAME/InviteUserListActivity"//用户列表

            //群靓号
            const val GROUP_PRETTY_SQUARE = "$MODULE_NAME/GroupPrettySquareActivity"//群靓号广场
            const val GROUP_PRETTY_SQUARE_DETAIL =
                "$MODULE_NAME/GroupPrettySquareDetailActivity"//群靓号广场详情
            const val GROUP_PRETTY_DETAIL = "$MODULE_NAME/GroupPrettyDetailActivity"//群靓号详情
            const val SELECT_SELF_GROUP = "$MODULE_NAME/SelectSelfGroupActivity"//选择自己是群主的群

            const val COUPON_ACTIVITY = "$MODULE_NAME/AllCouponActivity"// 所有券
            const val BAT_SHOP_ACTIVITY = "$MODULE_NAME/BatShopActivity"// 蝙蝠商城
            const val BALANCE_RECHARGE = "$MODULE_NAME/BalanceRechargeActivity"// 余额充值

            //积分
            const val SCORE_LOTTERY = "$MODULE_NAME/ScoreLotteryActivity"//积分抽奖

            // 合成VIP
            const val MERGE_VIP_COUPON = "$MODULE_NAME/MergeCouponActivity"

            // 合成记录
            const val MERGE_VIP_COUPON_REOCRD = "$MODULE_NAME/MergeCouponRecordActivity"

            // 合成折扣券
            const val MERGE_REBATE_ACT = "$MODULE_NAME/MergeRebateActivity"

            const val FIND_BACK_SECU_QUES = "$MODULE_NAME/FindBackSecuQuesActivity"//找回密保

            // 收藏
            const val COLLECTION_ACTIVITY = "$MODULE_NAME/CollectionActivity"

            // 收藏查询
            const val COLLECTION_SEARCH_ACTIVITY = "$MODULE_NAME/CollectionSearchActivity"

            // 收藏详情
            const val COLLECTION_DETAIL_ACTIVITY = "$MODULE_NAME/CollectionDetailActivity"
            const val BUBBLE_MEYSELF_ACT = "$MODULE_NAME/MyBubbleActivity"

            // 气泡商城
            const val BUBBLE_SHOP = "$MODULE_NAME/BubbleShopActivity"
            const val BUBBLE_DETAIL = "$MODULE_NAME/BubbleDetailActivity"

            // 自动回复
            const val AUTOMATIC_RESPONSE_ACTIVITY = "$MODULE_NAME/AutomaticResponseActivity"
            const val AUTOMATIC_RESPONSE_INTRO_ACTIVITY = "$MODULE_NAME/AutomaticResponseIntroActivity"
            // 账号管理
            const val ACCOUNT_MANAGE = "$MODULE_NAME/AccountManageActivity"
            // 官方推荐群聊
            const val RECOMMEND_GROUP_ACTIVITY = "$MODULE_NAME/RecommendGroupActivity"
            //通过验证登录密码修改手机号
            const val MODIFY_PHONE_BY_VERIFY_LOGIN_PWD = "$MODULE_NAME/ModifyPhoneByVerifyLoginPwdActivity"
            //通过验证登录密码修改手机号
            const val FIND_PWD_BY_EMAIL = "$MODULE_NAME/FindPwdByEmailActivity"
            //邮箱手机号互相修改
            const val PHONE_EMAIL_MODIFY_EACH_OTHER_BY_SIGN = "$MODULE_NAME/PhoneEmailEachOtherModifyBySignActivity"
        }

        object Fragment {
            const val ME = "$MODULE_NAME/MeFragment"
        }
    }
}