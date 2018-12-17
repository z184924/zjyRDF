package cn.zhangjingyao.util;

/**
 * 天气工具类
 */
public class WeatherUtil {
    public static String getWeatherJson(String cityCode){
        String sk = HttpClientUtil.doGet("http://www.weather.com.cn/data/sk/" + cityCode + ".html");
        String cityInfo = HttpClientUtil.doGet("http://www.weather.com.cn/data/cityinfo/" + cityCode + ".html");
        String result="{\"sk\":"+sk+",\"cityInfo\":"+cityInfo+"}";
        return result;
    }
}

/*
101010100=北京
101010200=海淀
101010300=朝阳
101010400=顺义
101010500=怀柔
101010600=通州
101010700=昌平
101010800=延庆
101010900=丰台
101011000=石景山
101011100=大兴
101011200=房山
101011300=密云
101011400=门头沟
101011500=平谷
101011600=八达岭
101011700=佛爷顶
101011800=汤河口
101011900=密云上甸子
101012000=斋堂
101012100=霞云岭


101020100=上海
101020200=闵行
101020300=宝山
101020400=川沙
101020500=嘉定
101020600=南汇
101020700=金山
101020800=青浦
101020900=松江
101021000=奉贤
101021100=崇明
101021101=陈家镇
101021102=引水船
101021200=徐家汇
101021300=浦东


101030100=天津
101030200=武清
101030300=宝坻
101030400=东丽
101030500=西青
101030600=北辰
101030700=宁河
101030800=汉沽
101030900=静海
101031000=津南
101031100=塘沽
101031200=大港
101031300=平台
101031400=蓟县


101040100=重庆
101040200=永川
101040300=合川
101040400=南川
101040500=江津
101040600=万盛
101040700=渝北
101040800=北碚
101040900=巴南
101041000=长寿
101041100=黔江
101041200=万州天城
101041300=万州龙宝
101041400=涪陵
101041500=开县
101041600=城口
101041700=云阳
101041800=巫溪
101041900=奉节
101042000=巫山
101042100=潼南
101042200=垫江
101042300=梁平
101042400=忠县
101042500=石柱
101042600=大足
101042700=荣昌
101042800=铜梁
101042900=璧山
101043000=丰都
101043100=武隆
101043200=彭水
101043300=綦江
101043400=酉阳
101043500=金佛山
101043600=秀山
101043700=沙坪坝


101050101=哈尔滨
101050102=双城
101050103=呼兰
101050104=阿城
101050105=宾县
101050106=依兰
101050107=巴彦
101050108=通河
101050109=方正
101050110=延寿
101050111=尚志
101050112=五常
101050113=木兰
101050201=齐齐哈尔
101050202=讷河
101050203=龙江
101050204=甘南
101050205=富裕
101050206=依安
101050207=拜泉
101050208=克山
101050209=克东
101050210=泰来
101050301=牡丹江
101050302=海林
101050303=穆棱
101050304=林口
101050305=绥芬河
101050306=宁安
101050307=东宁
101050401=佳木斯
101050402=汤原
101050403=抚远
101050404=桦川
101050405=桦南
101050406=同江
101050407=富锦
101050501=绥化
101050502=肇东
101050503=安达
101050504=海伦
101050505=明水
101050506=望奎
101050507=兰西
101050508=青冈
101050509=庆安
101050510=绥棱
101050601=黑河
101050602=嫩江
101050603=孙吴
101050604=逊克
101050605=五大连池
101050606=北安
101050701=大兴安岭
101050702=塔河
101050703=漠河
101050704=呼玛
101050705=呼中
101050706=新林
101050707=阿木尔
101050708=加格达奇
101050801=伊春
101050802=乌伊岭
101050803=五营
101050804=铁力
101050805=嘉荫
101050901=大庆
101050902=林甸
101050903=肇州
101050904=肇源
101050905=杜蒙
101051002=七台河
101051003=勃利
101051101=鸡西
101051102=虎林
101051103=密山
101051104=鸡东
101051201=鹤岗
101051202=绥滨
101051203=萝北
101051301=双鸭山
101051302=集贤
101051303=宝清
101051304=饶河


101060101=长春
101060102=农安
101060103=德惠
101060104=九台
101060105=榆树
101060106=双阳
101060201=吉林
101060202=舒兰
101060203=永吉
101060204=蛟河
101060205=磐石
101060206=桦甸
101060207=烟筒山
101060301=延吉
101060302=敦化
101060303=安图
101060304=汪清
101060305=和龙
101060306=天池
101060307=龙井
101060308=珲春
101060309=图们
101060310=松江
101060311=罗子沟
101060312=延边
101060401=四平
101060402=双辽
101060403=梨树
101060404=公主岭
101060405=伊通
101060406=孤家子
101060501=通化
101060502=梅河口
101060503=柳河
101060504=辉南
101060505=集安
101060506=通化县
101060601=白城
101060602=洮南
101060603=大安
101060604=镇赉
101060605=通榆
101060701=辽源
101060702=东丰
101060801=松原
101060802=乾安
101060803=前郭
101060804=长岭
101060805=扶余
101060901=白山
101060902=靖宇
101060903=临江
101060904=东岗
101060905=长白


101070101=沈阳
101070102=苏家屯
101070103=辽中
101070104=康平
101070105=法库
101070106=新民
101070107=于洪
101070108=新城子
101070201=大连
101070202=瓦房店
101070203=金州
101070204=普兰店
101070205=旅顺
101070206=长海
101070207=庄河
101070208=皮口
101070209=海洋岛
101070301=鞍山
101070302=台安
101070303=岫岩
101070304=海城
101070401=抚顺
101070403=清原
101070404=章党
101070501=本溪
101070502=本溪县
101070503=草河口
101070504=桓仁
101070601=丹东
101070602=凤城
101070603=宽甸
101070604=东港
101070605=东沟
101070701=锦州
101070702=凌海
101070703=北宁
101070704=义县
101070705=黑山
101070706=北镇
101070801=营口
101070802=大石桥
101070803=盖州
101070901=阜新
101070902=彰武
101071001=辽阳
101071002=辽阳县
101071003=灯塔
101071101=铁岭
101071102=开原
101071103=昌图
101071104=西丰
101071201=朝阳
101071202=建平
101071203=凌源
101071204=喀左
101071205=北票
101071206=羊山
101071207=建平县
101071301=盘锦
101071302=大洼
101071303=盘山
101071401=葫芦岛
101071402=建昌
101071403=绥中
101071404=兴城


101080101=呼和浩特
101080102=土默特左旗
101080103=托克托
101080104=和林格尔
101080105=清水河
101080106=呼和浩特市郊区
101080107=武川
101080201=包头
101080202=白云鄂博
101080203=满都拉
101080204=土默特右旗
101080205=固阳
101080206=达尔罕茂明安联合旗
101080207=石拐
101080301=乌海
101080401=集宁
101080402=卓资
101080403=化德
101080404=商都
101080405=希拉穆仁
101080406=兴和
101080407=凉城
101080408=察哈尔右翼前旗
101080409=察哈尔右翼中旗
101080410=察哈尔右翼后旗
101080411=四子王旗
101080412=丰镇
101080501=通辽
101080502=舍伯吐
101080503=科尔沁左翼中旗
101080504=科尔沁左翼后旗
101080505=青龙山
101080506=开鲁
101080507=库伦旗
101080508=奈曼旗
101080509=扎鲁特旗
101080510=高力板
101080511=巴雅尔吐胡硕
101080512=通辽钱家店
101080601=赤峰
101080602=赤峰郊区站
101080603=阿鲁科尔沁旗
101080604=浩尔吐
101080605=巴林左旗
101080606=巴林右旗
101080607=林西
101080608=克什克腾旗
101080609=翁牛特旗
101080610=岗子
101080611=喀喇沁旗
101080612=八里罕
101080613=宁城
101080614=敖汉旗
101080615=宝过图
101080701=鄂尔多斯
101080703=达拉特旗
101080704=准格尔旗
101080705=鄂托克前旗
101080706=河南
101080707=伊克乌素
101080708=鄂托克旗
101080709=杭锦旗
101080710=乌审旗
101080711=伊金霍洛旗
101080712=乌审召
101080713=东胜
101080801=临河
101080802=五原
101080803=磴口
101080804=乌拉特前旗
101080805=大佘太
101080806=乌拉特中旗
101080807=乌拉特后旗
101080808=海力素
101080809=那仁宝力格
101080810=杭锦后旗
101080811=巴盟农试站
101080901=锡林浩特
101080902=朝克乌拉
101080903=二连浩特
101080904=阿巴嘎旗
101080905=伊和郭勒
101080906=苏尼特左旗
101080907=苏尼特右旗
101080908=朱日和
101080909=东乌珠穆沁旗
101080910=西乌珠穆沁旗
101080911=太仆寺旗
101080912=镶黄旗
101080913=正镶白旗
101080914=正兰旗
101080915=多伦
101080916=博克图
101080917=乌拉盖
101080918=白日乌拉
101080919=那日图
101081000=呼伦贝尔
101081001=海拉尔
101081002=小二沟
101081003=阿荣旗
101081004=莫力达瓦旗
101081005=鄂伦春旗
101081006=鄂温克旗
101081007=陈巴尔虎旗
101081008=新巴尔虎左旗
101081009=新巴尔虎右旗
101081010=满洲里
101081011=牙克石
101081012=扎兰屯
101081014=额尔古纳
101081015=根河
101081016=图里河
101081101=乌兰浩特
101081102=阿尔山
101081103=科尔沁右翼中旗
101081104=胡尔勒
101081105=扎赉特旗
101081106=索伦
101081107=突泉
101081108=霍林郭勒
101081201=阿拉善左旗
101081202=阿拉善右旗
101081203=额济纳旗
101081204=拐子湖
101081205=吉兰太
101081206=锡林高勒
101081207=头道湖
101081208=中泉子
101081209=巴彦诺尔贡
101081210=雅布赖
101081211=乌斯太
101081212=孪井滩


101090101=石家庄
101090102=井陉
101090103=正定
101090104=栾城
101090105=行唐
101090106=灵寿
101090107=高邑
101090108=深泽
101090109=赞皇
101090110=无极
101090111=平山
101090112=元氏
101090113=赵县
101090114=辛集
101090115=藁城
101090116=晋洲
101090117=新乐
101090201=保定
101090202=满城
101090203=阜平
101090204=徐水
101090205=唐县
101090206=高阳
101090207=容城
101090208=紫荆关
101090209=涞源
101090210=望都
101090211=安新
101090212=易县
101090213=涞水
101090214=曲阳
101090215=蠡县
101090216=顺平
101090217=雄县
101090218=涿州
101090219=定州
101090220=安国
101090221=高碑店
101090301=张家口
101090302=宣化
101090303=张北
101090304=康保
101090305=沽源
101090306=尚义
101090307=蔚县
101090308=阳原
101090309=怀安
101090310=万全
101090311=怀来
101090312=涿鹿
101090313=赤城
101090314=崇礼
101090402=承德
101090403=承德县
101090404=兴隆
101090405=平泉
101090406=滦平
101090407=隆化
101090408=丰宁
101090409=宽城
101090410=围场
101090411=塞罕坎
101090501=唐山
101090502=丰南
101090503=丰润
101090504=滦县
101090505=滦南
101090506=乐亭
101090507=迁西
101090508=玉田
101090509=唐海
101090510=遵化
101090511=迁安
101090601=廊坊
101090602=固安
101090603=永清
101090604=香河
101090605=大城
101090606=文安
101090607=大厂
101090608=霸州
101090609=三河
101090701=沧州
101090702=青县
101090703=东光
101090704=海兴
101090705=盐山
101090706=肃宁
101090707=南皮
101090708=吴桥
101090709=献县
101090710=孟村
101090711=泊头
101090712=任丘
101090713=黄骅
101090714=河间
101090715=曹妃甸
101090801=衡水
101090802=枣强
101090803=武邑
101090804=武强
101090805=饶阳
101090806=安平
101090807=故城
101090808=景县
101090809=阜城
101090810=冀州
101090811=深州
101090901=邢台
101090902=临城
101090903=邢台县浆水
101090904=内邱
101090905=柏乡
101090906=隆尧
101090907=南和
101090908=宁晋
101090909=巨鹿
101090910=新河
101090911=广宗
101090912=平乡
101090913=威县
101090914=清河
101090915=临西
101090916=南宫
101090917=沙河
101090918=任县
101091001=邯郸
101091002=峰峰
101091003=临漳
101091004=成安
101091005=大名
101091006=涉县
101091007=磁县
101091008=肥乡
101091009=永年
101091010=邱县
101091011=鸡泽
101091012=广平
101091013=馆陶
101091014=魏县
101091015=曲周
101091016=武安
101091101=秦皇岛
101091102=青龙
101091103=昌黎
101091104=抚宁
101091105=卢龙
101091106=北戴河


101100101=太原
101100102=清徐
101100103=阳曲
101100104=娄烦
101100105=太原古交区
101100106=太原北郊
101100107=太原南郊
101100201=大同
101100202=阳高
101100203=大同县
101100204=天镇
101100205=广灵
101100206=灵邱
101100207=浑源
101100208=左云
101100301=阳泉
101100302=盂县
101100303=平定
101100401=晋中
101100402=榆次
101100403=榆社
101100404=左权
101100405=和顺
101100406=昔阳
101100407=寿阳
101100408=太谷
101100409=祁县
101100410=平遥
101100411=灵石
101100412=介休
101100501=长治
101100502=黎城
101100503=屯留
101100504=潞城
101100505=襄垣
101100506=平顺
101100507=武乡
101100508=沁县
101100509=长子
101100510=沁源
101100511=壶关
101100601=晋城
101100602=沁水
101100603=阳城
101100604=陵川
101100605=高平
101100701=临汾
101100702=曲沃
101100703=永和
101100704=隰县
101100705=大宁
101100706=吉县
101100707=襄汾
101100708=蒲县
101100709=汾西
101100710=洪洞
101100711=霍州
101100712=乡宁
101100713=翼城
101100714=侯马
101100715=浮山
101100716=安泽
101100717=古县
101100801=运城
101100802=临猗
101100803=稷山
101100804=万荣
101100805=河津
101100806=新绛
101100807=绛县
101100808=闻喜
101100809=垣曲
101100810=永济
101100811=芮城
101100812=夏县
101100813=平陆
101100901=朔州
101100902=平鲁
101100903=山阴
101100904=右玉
101100905=应县
101100906=怀仁
101101001=忻州
101101002=定襄
101101003=五台县豆村
101101004=河曲
101101005=偏关
101101006=神池
101101007=宁武
101101008=代县
101101009=繁峙
101101010=五台山
101101011=保德
101101012=静乐
101101013=岢岚
101101014=五寨
101101015=原平
101101100=吕梁
101101101=离石
101101102=临县
101101103=兴县
101101104=岚县
101101105=柳林
101101106=石楼
101101107=方山
101101108=交口
101101109=中阳
101101110=孝义
101101111=汾阳
101101112=文水
101101113=交城


101110101=西安
101110102=长安
101110103=临潼
101110104=蓝田
101110105=周至
101110106=户县
101110107=高陵
101110108=杨凌
101110200=咸阳
101110201=三原
101110202=礼泉
101110203=永寿
101110204=淳化
101110205=泾阳
101110206=武功
101110207=乾县
101110208=彬县
101110209=长武
101110210=旬邑
101110211=兴平
101110300=延安
101110301=延长
101110302=延川
101110303=子长
101110304=宜川
101110305=富县
101110306=志丹
101110307=安塞
101110308=甘泉
101110309=洛川
101110310=黄陵
101110311=黄龙
101110312=吴起
101110401=榆林
101110402=府谷
101110403=神木
101110404=佳县
101110405=定边
101110406=靖边
101110407=横山
101110408=米脂
101110409=子洲
101110410=绥德
101110411=吴堡
101110412=清涧
101110501=渭南
101110502=华县
101110503=潼关
101110504=大荔
101110505=白水
101110506=富平
101110507=蒲城
101110508=澄城
 */