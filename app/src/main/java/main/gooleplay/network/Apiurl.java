package main.gooleplay.network;

/**
 * 作者： Administrator
 * 时间： 2016/7/25
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class Apiurl {

    //=============================首页=====================================
    /**
     * 获取首页banner
     */
    public static final String HOME_BANNER = "/Inew/newBanner/";


    /**
     * 获取用户详情
     */
    public static final String MESSAGE_GETDETAIL = "/message/getTS.do";
    /**
     * 创建群
     */
    public static final String MESSAGE_CREATE_GROUP = "/message/createGroupChat.do";
    /**
     * 加入群
     */
    public  static  final String  MESSAGE_ADD_MEMBER= "/message/addGroupChat.do";
    /**
     *退出群
     */
    public static final String  MESSAGE_EXIT_MEMBER = "/message/exitGroupChat.do";
    /**
     *查看群成员
     */
    public static final String  MESSAGE_SEARCH_MEMBER = "/message/groupMember.do";
    /**
     *查看不在群内成员
     */
    public static final String  MESSAGE_WITHOUT_MEMBER = "/message/notGroupMember.do";
    /**
     * 解散群
     */
    public static final String  MESSAGE_DISSORE_GROUP = "/message/dissolve.do";
    /**
     * 编辑群名称
     */
    public static final String  MESSAGE_EDITGROUPNAME = "/message/editGroupChatName.do";

    //=============================学校======================================
    /**
     * 提交请假申请
     */
    public static final String SCHOOL_SUBLEAVE = "/school/subLeave.do" ;
    /**
     * 发布课程
     */
    public static final String SCHOOL_PUBLISHCAURSE = "/school/setSyllabus.do" ;
    /**
     * 发布通知
     */
    public static final String SCHOOL_PUBLISHINFOR = "/school/pushSave.do" ;
    /**
     * 发布食谱
     */
    public static final String SCHOOL_PUBLISHCOOK = "/school/setCookbook.do";
    /**
    * 获取班级列表
    */
    public static final String SCHOOL_GETCLASSLIST="/school/getTsClass.do";
    /**
     * 获取课程表
     */
    public static final String SCHOOL_GETSYLLABUSLISTS = "/school/getSyllabusList.do" ;
    /**
     * 删除课程表
     */
    public static final String SCHOOL_DETLTESYLLABUSLISTS = "/school/deleteSyllabus.do";
    /**
     * 获取食谱
     */
    public static final String SCHOOL_GETCOOKBOOK = "/school/getCookbook.do";
    /**
     * 请假
     */
    public static final String SCHOOL_GETLEAVES = "/school/getLeave.do" ;
    /**
     *删除请假
     */
    public static final String SCHOOL_DELETELEAVES = "/school/deleteLeave.do" ;
    /**
     * 教师端喂药列表
     */
    public static final String SCHOOL_MEDICINETLIST = "/school/teacherFeedingMedicine.do" ;
    /**
     * 教师端喂药详情
     */
    public static final String SCHOOL_MEDICINETDETAIL = "/school/medicineDetails.do" ;
    /**
     * 删除喂药
     */
    public static final String SCHOOL_MEDICINETDELETE = "/school/deleteMedicine.do" ;
    /**
     *完成喂药
     */
    public static final String SCHOOL_MEDICINETFINSH = "/school/finishMedicine.do" ;
    /**
     *家长获取喂药列表
     */
    public static final String SCHOOL_MEDICINESLIST = "/school/patriarchFeedingMedicine.do" ;
    /**
     * 发布委托喂药
     */
    public static final String SCHOOL_MEDICINEPUBLISH = "/school/publishMedicineCommissioned.do" ;
    //==============================个人中心============================
    /**
     * 意见反馈
     */
    public static final String FEEDBACK="/appUser/feedback.do";
    /**
     * 获取相册所有图片
     */
    public static final String FILCKR="/appUser/flickr.do";
    /**
     * 修改用户个性签名
     */
    public static final String SETSIGN="/appUser/setSignature.do";
    /**
     * 修改用户头像
     */
    public static final String AVATAR="/appUser/setAvatar.do";
    /**
     * 获取我的相册
     */
    public static final String MYFlICKR="/appUser/myFlickr.do";
    /**
     * 新建相册
     */
    public static final String CREATEEFILCK="/appUser/createFlickr.do";
    /**
     * 上传图片
     */
    public static final String UPLOADPHOTO="/appUser/uploadPhoto.do";
    /**
     * 我的动态
     */
    public static final String MYDYNAMICS= "/dynamics/myDynamic.do";
    /**
     * 删除动态
     */
    public static final String DELETEMYDYNAMICS= "/dynamics/deleteDynamic.do";

    /**
     * 版本更新地址
     */
    public static final String UPDATESYSTEM="/appUser/getVersion.do";

    //=========================动态================================
    /**
     * 获取动态详情
     */
    public static final String STATUSLIST ="/dynamics/getDynamic.do";
    /**
     * 获取动态详情
     */
    public static final String STATUSDETILS ="/dynamics/getDynamicDetail.do";
    /**
     * 提交评论
     */
    public static final String SUBCOMMENT ="/dynamics/subComment.do";
    /**
     * 提交赞
     */
    public static final String SUBPRAISE ="/dynamics/subPraise.do";
    /**
     * 删除评论
     */
    public static final String DELETERCOMMENTD ="/dynamics/deleteComment.do";
    /**
     * 获取班级列表uri
     */
    public static final String DYNAMICHEAD="/dynamics/getDynamicHead.do";
    /**
     *获取动态小窗口
     */
    public static  final String  STATUSINFO = "/dynamics/getDynamicLittleWindow.do";
    //=========================登录==============================
    public static final String GETISPLAY="/app/isPay";
    /**
     * 用户登录
     */
    public static final String APPLOGIN="/app/login.do";
    /**
     * 用户身份选择
     */
    public static final String SELECTUSER="/app/selectUser.do";
    /**
     * 扫描二维码登录
     */

    public static final String SCANLOGIN = "/app/qRCodeLogin.do";
    /**
     * 二次确认
     */
    public static final String CONFIRMCODELOGIN="/app/qRCodeConfirmLogin.do";
    /**
     * 获取二维码
     */
    public static final String CETQRCODE = "/app/getQRCode.do";

    public static final String USER_GETRECOMMENDLIST = "/rakuen/getRecommenda.do";
    //=========================乐园================================
    /**
     * 获取banner图
     */
    public static final String GETBANNERLIST = "/rakuen/getBanner.do";
    /**
     * 获取推荐列表
     */
    public static final String GETRECOMMENDLIST = "/rakuen/getRecommenda.do";
    /**
     * 获取全部推荐列表
     */
    public static final String GETALLRECOMMENDLIST = "/rakuen/getIndexAllRecommenda.do";

    /**
     * 获取某个专辑下的资源列表
     */
    public static final String USER_GETTHENELIST = "/rakuen/getCompilationsAllResource.do";
    /**
     * 获取某个资源具体信息
     */
    public static final String USER_GETSOURCEDETAIL = "/rakuen/getResourceDetail.do";
    /**
     * 收藏音乐专辑
     */
    public static final String SUBATTENTION = "/rakuen/saveResourceFavorites.do";
    /**
     * 获取收藏列表
     */
    public static final String GETATTENTIONLIST = "/rakuen/getResourceFavorites.do";
    /**
     *保存播放记录
     */
    public static final String SAVEPLAYRECORDING = "/rakuen/savePlayTheRecording.do";
    /**
     * 获取播放记录
     */
    public static final String GETPLAYRECORDING = "/rakuen/getResourcePlayRecordedCommand.do";
    /**
     * 获取专辑资源的列表
     */
    public static final String GETALUBMLIST = "/rakuen/getContent.do";
}
