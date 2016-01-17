package org.happy.wx.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.JsonResult;
import com.foxinmy.weixin4j.model.Button;
import com.foxinmy.weixin4j.model.MediaCounter;
import com.foxinmy.weixin4j.model.MediaDownloadResult;
import com.foxinmy.weixin4j.model.MediaItem;
import com.foxinmy.weixin4j.model.MediaRecord;
import com.foxinmy.weixin4j.model.MediaUploadResult;
import com.foxinmy.weixin4j.model.Pageable;
import com.foxinmy.weixin4j.mp.api.CustomApi;
import com.foxinmy.weixin4j.mp.api.DataApi;
import com.foxinmy.weixin4j.mp.api.GroupApi;
import com.foxinmy.weixin4j.mp.api.HelperApi;
import com.foxinmy.weixin4j.mp.api.MassApi;
import com.foxinmy.weixin4j.mp.api.MediaApi;
import com.foxinmy.weixin4j.mp.api.MenuApi;
import com.foxinmy.weixin4j.mp.api.MpApi;
import com.foxinmy.weixin4j.mp.api.NotifyApi;
import com.foxinmy.weixin4j.mp.api.QrApi;
import com.foxinmy.weixin4j.mp.api.TmplApi;
import com.foxinmy.weixin4j.mp.api.UserApi;
import com.foxinmy.weixin4j.mp.message.NotifyMessage;
import com.foxinmy.weixin4j.mp.message.TemplateMessage;
import com.foxinmy.weixin4j.mp.model.AutoReplySetting;
import com.foxinmy.weixin4j.mp.model.CustomRecord;
import com.foxinmy.weixin4j.mp.model.Following;
import com.foxinmy.weixin4j.mp.model.Group;
import com.foxinmy.weixin4j.mp.model.KfAccount;
import com.foxinmy.weixin4j.mp.model.KfSession;
import com.foxinmy.weixin4j.mp.model.MenuSetting;
import com.foxinmy.weixin4j.mp.model.QRParameter;
import com.foxinmy.weixin4j.mp.model.QRResult;
import com.foxinmy.weixin4j.mp.model.SemQuery;
import com.foxinmy.weixin4j.mp.model.SemResult;
import com.foxinmy.weixin4j.mp.model.User;
import com.foxinmy.weixin4j.mp.token.WeixinTokenCreator;
import com.foxinmy.weixin4j.mp.type.DatacubeType;
import com.foxinmy.weixin4j.mp.type.IndustryType;
import com.foxinmy.weixin4j.mp.type.Lang;
import com.foxinmy.weixin4j.token.TokenHolder;
import com.foxinmy.weixin4j.token.TokenStorager;
import com.foxinmy.weixin4j.tuple.MassTuple;
import com.foxinmy.weixin4j.tuple.MpArticle;
import com.foxinmy.weixin4j.tuple.MpVideo;
import com.foxinmy.weixin4j.type.MediaType;

@IocBean(name = "api", args = { "java:$conf.get('wx.appid')", "java:$conf.get('wx.secret')",
		"refer:loginTokenStorager" })
public class WxProxy {
	private final MediaApi mediaApi;
	private final NotifyApi notifyApi;
	private final CustomApi customApi;
	private final MassApi massApi;
	private final UserApi userApi;
	private final GroupApi groupApi;
	private final MenuApi menuApi;
	private final QrApi qrApi;
	private final TmplApi tmplApi;
	private final HelperApi helperApi;
	private final DataApi dataApi;
	private final TokenHolder tokenHolder;
	public static final String VERSION = "1.6.5";

	public WxProxy() {
		this(MpApi.DEFAULT_TOKEN_STORAGER);
	}

	public WxProxy(TokenStorager tokenStorager) {
		this(MpApi.DEFAULT_WEIXIN_ACCOUNT.getId(), MpApi.DEFAULT_WEIXIN_ACCOUNT.getSecret(), tokenStorager);
	}

	public WxProxy(String appid, String appsecret) {
		this(appid, appsecret, MpApi.DEFAULT_TOKEN_STORAGER);
	}

	public WxProxy(String appid, String appsecret, TokenStorager tokenStorager) {
		this(new TokenHolder(new WeixinTokenCreator(appid, appsecret), tokenStorager));
	}

	public WxProxy(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
		mediaApi = new MediaApi(tokenHolder);
		notifyApi = new NotifyApi(tokenHolder);
		customApi = new CustomApi(tokenHolder);
		massApi = new MassApi(tokenHolder);
		userApi = new UserApi(tokenHolder);
		groupApi = new GroupApi(tokenHolder);
		menuApi = new MenuApi(tokenHolder);
		qrApi = new QrApi(tokenHolder);
		tmplApi = new TmplApi(tokenHolder);
		helperApi = new HelperApi(tokenHolder);
		dataApi = new DataApi(tokenHolder);

	}

	public TokenHolder getTokenHolder() {
		return tokenHolder;
	}

	public String uploadImage(InputStream is, String fileName) throws WeixinException {
		return mediaApi.uploadImage(is, fileName);
	}

	public MpVideo uploadVideo(InputStream is, String fileName, String title, String description)
			throws WeixinException {
		return mediaApi.uploadVideo(is, fileName, title, description);
	}

	public MediaUploadResult uploadMedia(boolean isMaterial, InputStream is, String fileName) throws WeixinException {
		return mediaApi.uploadMedia(isMaterial, is, fileName);
	}

	public File downloadMediaFile(String mediaId, boolean isMaterial) throws WeixinException {
		return mediaApi.downloadMediaFile(mediaId, isMaterial);
	}

	public MediaDownloadResult downloadMedia(String mediaId, boolean isMaterial) throws WeixinException {
		return mediaApi.downloadMedia(mediaId, isMaterial);
	}

	public String uploadMaterialArticle(List<MpArticle> articles) throws WeixinException {
		return mediaApi.uploadMaterialArticle(articles);
	}

	public List<MpArticle> downloadArticle(String mediaId) throws WeixinException {
		return mediaApi.downloadArticle(mediaId);
	}

	public JsonResult updateMaterialArticle(String mediaId, int index, List<MpArticle> articles)
			throws WeixinException {
		return mediaApi.updateMaterialArticle(mediaId, index, articles);
	}

	public JsonResult deleteMaterialMedia(String mediaId) throws WeixinException {
		return mediaApi.deleteMaterialMedia(mediaId);
	}

	public String uploadMaterialVideo(InputStream is, String title, String introduction) throws WeixinException {
		return mediaApi.uploadMaterialVideo(is, title, introduction);
	}

	public MediaCounter countMaterialMedia() throws WeixinException {
		return mediaApi.countMaterialMedia();
	}

	public MediaRecord listMaterialMedia(MediaType mediaType, Pageable pageable) throws WeixinException {
		return mediaApi.listMaterialMedia(mediaType, pageable);
	}

	public List<MediaItem> listAllMaterialMedia(MediaType mediaType) throws WeixinException {
		return mediaApi.listAllMaterialMedia(mediaType);
	}

	public JsonResult sendNotify(NotifyMessage notify) throws WeixinException {
		return notifyApi.sendNotify(notify);
	}

	public JsonResult sendNotify(NotifyMessage notify, String kfAccount) throws WeixinException {
		return notifyApi.sendNotify(notify, kfAccount);
	}

	public List<CustomRecord> getCustomRecord(String openId, Date starttime, Date endtime, int pagesize, int pageindex)
			throws WeixinException {
		return customApi.getCustomRecord(openId, starttime, endtime, pagesize, pageindex);
	}

	public List<KfAccount> listKfAccount(boolean isOnline) throws WeixinException {
		return customApi.listKfAccount(isOnline);
	}

	public JsonResult createAccount(String id, String name, String pwd) throws WeixinException {
		return customApi.createAccount(id, name, pwd);
	}

	public JsonResult updateAccount(String id, String name, String pwd) throws WeixinException {
		return customApi.updateAccount(id, name, pwd);
	}

	public JsonResult uploadAccountHeadimg(String id, File headimg) throws WeixinException, IOException {
		return customApi.uploadAccountHeadimg(id, headimg);
	}

	public JsonResult deleteAccount(String id) throws WeixinException {
		return customApi.deleteAccount(id);
	}

	public JsonResult createKfSession(String userOpenId, String kfAccount, String text) throws WeixinException {
		return customApi.createKfSession(userOpenId, kfAccount, text);
	}

	public JsonResult closeKfSession(String userOpenId, String kfAccount, String text) throws WeixinException {
		return customApi.closeKfSession(userOpenId, kfAccount, text);
	}

	public KfSession getKfSession(String userOpenId) throws WeixinException {
		return customApi.getKfSession(userOpenId);
	}

	public List<KfSession> listKfSession(String kfAccount) throws WeixinException {
		return customApi.listKfSession(kfAccount);
	}

	public List<KfSession> listKfSessionWait() throws WeixinException {
		return customApi.listKfSessionWait();
	}

	public String uploadMassArticle(List<MpArticle> articles) throws WeixinException {
		return massApi.uploadArticle(articles);
	}

	public String[] massByGroupId(MassTuple tuple, boolean isToAll, int groupId) throws WeixinException {
		return massApi.massByGroupId(tuple, isToAll, groupId);
	}

	public String[] massArticleByGroupId(List<MpArticle> articles, int groupId) throws WeixinException {
		return massApi.massArticleByGroupId(articles, groupId);
	}

	public String[] massByOpenIds(MassTuple tuple, String... openIds) throws WeixinException {
		return massApi.massByOpenIds(tuple, openIds);
	}

	public String[] massArticleByOpenIds(List<MpArticle> articles, String... openIds) throws WeixinException {
		return massApi.massArticleByOpenIds(articles, openIds);
	}

	public JsonResult deleteMassNews(String msgid) throws WeixinException {
		return massApi.deleteMassNews(msgid);
	}

	public JsonResult previewMassNews(String toUser, String toWxName, MassTuple tuple) throws WeixinException {
		return massApi.previewMassNews(toUser, toWxName, tuple);
	}

	public String getMassNewStatus(String msgId) throws WeixinException {
		return massApi.getMassNewStatus(msgId);
	}

	public User getUser(String openId) throws WeixinException {
		return userApi.getUser(openId);
	}

	public User getUser(String openId, Lang lang) throws WeixinException {
		return userApi.getUser(openId, lang);
	}

	public List<User> getUsers(String... openIds) throws WeixinException {
		return userApi.getUsers(openIds);
	}

	public List<User> getUsers(Lang lang, String... openIds) throws WeixinException {
		return userApi.getUsers(lang, openIds);
	}

	public Following getFollowing(String nextOpenId) throws WeixinException {
		return userApi.getFollowing(nextOpenId);
	}

	public List<User> getAllFollowing() throws WeixinException {
		return userApi.getAllFollowing();
	}

	public JsonResult remarkUserName(String openId, String remark) throws WeixinException {
		return userApi.remarkUserName(openId, remark);
	}

	public Group createGroup(String name) throws WeixinException {
		return groupApi.createGroup(name);
	}

	public List<Group> getGroups() throws WeixinException {
		return groupApi.getGroups();
	}

	public int getGroupByOpenId(String openId) throws WeixinException {
		return groupApi.getGroupByOpenId(openId);
	}

	public JsonResult modifyGroup(int groupId, String name) throws WeixinException {
		return groupApi.modifyGroup(groupId, name);
	}

	public JsonResult moveGroup(int groupId, String openId) throws WeixinException {
		return groupApi.moveGroup(groupId, openId);
	}

	public JsonResult moveGroup(int groupId, String... openIds) throws WeixinException {
		return groupApi.moveGroup(groupId, openIds);
	}

	public JsonResult deleteGroup(int groupId) throws WeixinException {
		return groupApi.deleteGroup(groupId);
	}

	public JsonResult createMenu(List<Button> btnList) throws WeixinException {
		return menuApi.createMenu(btnList);
	}

	public List<Button> getMenu() throws WeixinException {
		return menuApi.getMenu();
	}

	public JsonResult deleteMenu() throws WeixinException {
		return menuApi.deleteMenu();
	}

	@Aop("timeOut")
	public QRResult createQR(QRParameter parameter) throws WeixinException {
		return qrApi.createQR(parameter);
	}

	public File createQRFile(QRParameter parameter) throws WeixinException {
		return qrApi.createQRFile(parameter);
	}

	public JsonResult setTmplIndustry(IndustryType... industryType) throws WeixinException {
		return tmplApi.setTmplIndustry(industryType);
	}

	public String getTemplateId(String shortId) throws WeixinException {
		return tmplApi.getTemplateId(shortId);
	}

	public JsonResult sendTmplMessage(TemplateMessage tplMessage) throws WeixinException {
		return tmplApi.sendTmplMessage(tplMessage);
	}

	public String getShorturl(String url) throws WeixinException {
		return helperApi.getShorturl(url);
	}

	public SemResult semantic(SemQuery semQuery) throws WeixinException {
		return helperApi.semantic(semQuery);
	}

	public List<String> getCallbackip() throws WeixinException {
		return helperApi.getCallbackip();
	}

	public MenuSetting getMenuSetting() throws WeixinException {
		return helperApi.getMenuSetting();
	}

	public AutoReplySetting getAutoReplySetting() throws WeixinException {
		return helperApi.getAutoReplySetting();
	}

	public List<?> datacube(DatacubeType datacubeType, Date beginDate, Date endDate) throws WeixinException {
		return dataApi.datacube(datacubeType, beginDate, endDate);
	}

	public List<?> datacube(DatacubeType datacubeType, Date beginDate, int offset) throws WeixinException {
		return dataApi.datacube(datacubeType, beginDate, offset);
	}

	public List<?> datacube(DatacubeType datacubeType, int offset, Date endDate) throws WeixinException {
		return dataApi.datacube(datacubeType, offset, endDate);
	}

	public List<?> datacube(DatacubeType datacubeType, Date date) throws WeixinException {
		return dataApi.datacube(datacubeType, date);
	}
}
