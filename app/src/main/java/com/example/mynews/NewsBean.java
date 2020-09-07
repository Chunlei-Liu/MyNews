package com.example.mynews;

import java.util.List;

public class NewsBean {

    private ResultBean result;
    private int error_code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    //改成静态内部类
    public static class ResultBean {
        /**
         * stat : 1
         * data : [{"uniquekey":"0fa4def3089e7dddb25c85ea2d39deb7","title":"搞笑的GIF动态图：当老婆让你带孩子，你要去打球的时候只能这样做","date":"2019-12-01 12:43","category":"头条","author_name":"搞笑的生活段子","url":"http://mini.eastday.com/mobile/191201124314849.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20191201/20191201124314_f6b970f1f8920e1c77ab66c5bec3b0b1_7_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday.com/mobile/20191201/20191201124314_f6b970f1f8920e1c77ab66c5bec3b0b1_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday.com/mobile/20191201/20191201124314_f6b970f1f8920e1c77ab66c5bec3b0b1_1_mwpm_03200403.jpg"},{"uniquekey":"78f0e7517235623a79e7ada73dcabbe6","title":"郑渝铁路郑襄段今日通车！参建工人乘坐体验首发列车","date":"2019-12-01 12:41","category":"头条","author_name":"大河网","url":"http://mini.eastday.com/mobile/191201124117936.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20191201/20191201124117_d4d138f6e21035bf1c379f246a223a13_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20191201/20191201124117_d4d138f6e21035bf1c379f246a223a13_5_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20191201/20191201124117_d4d138f6e21035bf1c379f246a223a13_3_mwpm_03200403.jpg"},{"uniquekey":"b1f419c489769d3af5413cb04f735249","title":"新款奥迪A6 40TFSI、S6、RS6及S7明年组团来澳","date":"2019-12-01 12:36","category":"头条","author_name":"网络汽车资讯","url":"http://mini.eastday.com/mobile/191201123657241.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191201/20191201123657_a7ce17cfa0c97e98b5d5861b25920ce8_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20191201/20191201123657_a7ce17cfa0c97e98b5d5861b25920ce8_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20191201/20191201123657_a7ce17cfa0c97e98b5d5861b25920ce8_3_mwpm_03200403.jpg"},{"uniquekey":"c739def9da02c46523b3c97ff723b2d0","title":"怀念高以翔","date":"2019-12-01 12:36","category":"头条","author_name":"袖珍唯静茶庄","url":"http://mini.eastday.com/mobile/191201123600076.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20191201/20191201123600_28bd89207ba3f8966cd96f4ba96f1c4a_1_mwpm_03200403.jpg"},{"uniquekey":"349eeaadff4d67bc2d76f5d3b40a58e8","title":"\u201c泰\u201d强了！仿真\u201c光头强\u201d版安全帽交警都懵了","date":"2019-12-01 12:33","category":"头条","author_name":"泰国华人论坛","url":"http://mini.eastday.com/mobile/191201123353885.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20191201/20191201123353_53f093740a4499f3bdb8c3478866e2b1_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20191201/20191201123353_53f093740a4499f3bdb8c3478866e2b1_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20191201/20191201123353_53f093740a4499f3bdb8c3478866e2b1_3_mwpm_03200403.jpg"},{"uniquekey":"9233002c41989c6596570e6d48812077","title":"AirJordan11 \"UNC\" 11午夜蓝 开箱实拍","date":"2019-12-01 12:23","category":"头条","author_name":"铺尚大世界","url":"http://mini.eastday.com/mobile/191201122323533.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191201/20191201122323_b65b4b45c47ea884bd9b89a24b0003c8_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20191201/20191201122323_b65b4b45c47ea884bd9b89a24b0003c8_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20191201/20191201122323_b65b4b45c47ea884bd9b89a24b0003c8_6_mwpm_03200403.jpg"},{"uniquekey":"af413d0801f736c03e7f8c0327fcef9e","title":"光有张常宁显然不够，整体实力不足才是江苏女排被剃光头真因","date":"2019-12-01 12:15","category":"头条","author_name":"千千侃球","url":"http://mini.eastday.com/mobile/191201121538399.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191201/2019120112_f58333fbb5ee457399cb99cf57f2c673_4309_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191201/2019120112_70edeb36e7ac46a2ad6b3d12e3dcffe8_8550_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191201/2019120112_bd0747a9503b42a497345dbefea59a7a_5889_mwpm_03200403.jpg"},{"uniquekey":"82f02363234a21ccc72638c75a09963d","title":"\u201c雷布斯\u201d多有钱？雷军：有一个阶段我变得一无所有 除了有钱！","date":"2019-12-01 12:14","category":"头条","author_name":"大喇叭小老弟","url":"http://mini.eastday.com/mobile/191201121431867.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20191201/20191201121431_0c3103681b80c2b818b189d0490a8810_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday.com/mobile/20191201/20191201121431_0c3103681b80c2b818b189d0490a8810_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday.com/mobile/20191201/20191201121431_0c3103681b80c2b818b189d0490a8810_4_mwpm_03200403.jpg"},{"uniquekey":"3b29e473d9dfdd8c5eacc235d83f6d86","title":"最高5.5折！铁路部分动车组列车执行票价优化调整","date":"2019-12-01 12:14","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/191201121404633.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20191201/20191201121404_75719ffa92086e21f795f571913655d6_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20191201/20191201121404_75719ffa92086e21f795f571913655d6_1_mwpm_03200403.jpg"},{"uniquekey":"f66371a85549f0d31434c1d8f2eadda6","title":"连体婴套路太过无解？中路甄姬打野盘古都是天敌，他更是最优解","date":"2019-12-01 12:12","category":"头条","author_name":"老张说王者","url":"http://mini.eastday.com/mobile/191201121247975.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191201/20191201121247_49e404ed689580a0056d6c1215fd69eb_8_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191201/20191201121247_49e404ed689580a0056d6c1215fd69eb_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191201/20191201121247_49e404ed689580a0056d6c1215fd69eb_9_mwpm_03200403.jpg"},{"uniquekey":"22ab08f1a824284a8b6d3b279d2ba536","title":"外酥内软的早餐，平稳血压抗衰老，做起来不麻烦，吃起来真香","date":"2019-12-01 12:09","category":"头条","author_name":"火星历险记","url":"http://mini.eastday.com/mobile/191201120937443.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20191201/20191201120937_d2c760a8755c6eafa86b69821267aa8e_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20191201/20191201120937_d2c760a8755c6eafa86b69821267aa8e_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20191201/20191201120937_d2c760a8755c6eafa86b69821267aa8e_6_mwpm_03200403.jpg"},{"uniquekey":"b0c56328894803b29fd76de1f7d516be","title":"担心减产协议生变 国际市场油价大跌","date":"2019-12-01 12:06","category":"头条","author_name":"新民网","url":"http://mini.eastday.com/mobile/191201120657021.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20191201/20191201120657_b0dae155d6d13fc9d9af132e87faa254_1_mwpm_03200403.jpg"},{"uniquekey":"8ae3b0e4b35722ae88145e3bbeb5a549","title":"这几款手机很适合商务人士，只是拿在手上就很有面子，你会入手吗？","date":"2019-12-01 12:05","category":"头条","author_name":"手机大魔王","url":"http://mini.eastday.com/mobile/191201120552882.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20191201/20191201120552_17a24aa138cea14fa68316bec0c07bc9_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20191201/20191201120552_17a24aa138cea14fa68316bec0c07bc9_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20191201/20191201120552_17a24aa138cea14fa68316bec0c07bc9_1_mwpm_03200403.jpg"},{"uniquekey":"82809a697b0f65d1d6a8a787cd486a6b","title":"敷面膜时脸有刺痛感，有人说是脸太干导致的，真是这样吗","date":"2019-12-01 12:05","category":"头条","author_name":"海之声LI","url":"http://mini.eastday.com/mobile/191201120543986.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20191201/20191201120543_24a7f1bc530e81cb1b7e49b0f6deeef0_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20191201/20191201120543_24a7f1bc530e81cb1b7e49b0f6deeef0_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20191201/20191201120543_24a7f1bc530e81cb1b7e49b0f6deeef0_6_mwpm_03200403.jpg"},{"uniquekey":"878bc78dd74c586260f2570e4782c194","title":"企业入驻奖励空前  北京通州优势资源挖掘商业潜力","date":"2019-12-01 12:04","category":"头条","author_name":"北京商报","url":"http://mini.eastday.com/mobile/191201120434737.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20191201/20191201120434_bde99009802bcd92dd113882383783e1_1_mwpm_03200403.jpg"},{"uniquekey":"1a58e25b76858dc96a676c1034ca2d40","title":"检测胎儿肺部成熟度不必再\u201c羊穿\u201d！这个实验室落户广州","date":"2019-12-01 12:04","category":"头条","author_name":"广州看点","url":"http://mini.eastday.com/mobile/191201120416084.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20191201/20191201120416_f6a8f8a5dd606293c872ba6dbe9d4044_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20191201/20191201120416_f6a8f8a5dd606293c872ba6dbe9d4044_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20191201/20191201120416_f6a8f8a5dd606293c872ba6dbe9d4044_1_mwpm_03200403.jpg"},{"uniquekey":"5f723440dc61e29b175382cf28e930c9","title":"英国老太太收集1404只粉红豹，这才是真正的\u201c少女心\u201d啊\u2026\u2026","date":"2019-12-01 12:03","category":"头条","author_name":"时尚生活咖","url":"http://mini.eastday.com/mobile/191201120356890.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20191201/20191201120356_7bb7d25ef1eff7bc02c8c9141ebc37ce_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20191201/20191201120356_7bb7d25ef1eff7bc02c8c9141ebc37ce_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20191201/20191201120356_7bb7d25ef1eff7bc02c8c9141ebc37ce_5_mwpm_03200403.jpg"},{"uniquekey":"6b1c5729bf4c2131d4c0de6b3d580f8a","title":"《音浪合伙人》高燃收官，袁娅维温柔献唱《故乡的云》","date":"2019-12-01 12:02","category":"头条","author_name":"小四哥","url":"http://mini.eastday.com/mobile/191201120236745.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20191201/20191201120236_e0440e13ae531ee2ab0acb9e3faa40ed_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday.com/mobile/20191201/20191201120236_e0440e13ae531ee2ab0acb9e3faa40ed_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday.com/mobile/20191201/20191201120236_e0440e13ae531ee2ab0acb9e3faa40ed_3_mwpm_03200403.jpg"},{"uniquekey":"6b6e7a55f5ae74426927adbff9945c9a","title":"痘博士秋冬季有什么护肤重点 痘博士祛痘怎么样","date":"2019-12-01 12:02","category":"头条","author_name":"一个迷路的小孩儿","url":"http://mini.eastday.com/mobile/191201120200787.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20191201/20191201120200_6e2ae4b33789587f1348f5fb588add45_1_mwpm_03200403.jpg"},{"uniquekey":"ed812d0ecce483dc71e754e703b06b13","title":"抽查10多户新厕竟无一使用，农村改厕岂能\u201c为改而改\u201d","date":"2019-12-01 11:58","category":"头条","author_name":"解放网","url":"http://mini.eastday.com/mobile/191201115803417.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20191201/20191201115803_e52b536d5e671e3029cad19e42041ae7_1_mwpm_03200403.jpg"},{"uniquekey":"b0d0101b4d9bc1cc56149b36b449a15c","title":"敏感肌日常护肤五大误区","date":"2019-12-01 11:58","category":"头条","author_name":"敏感肌修复2019","url":"http://mini.eastday.com/mobile/191201115800306.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20191201/20191201115800_68411a6d82739795944bf02621fb7a8a_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20191201/20191201115800_68411a6d82739795944bf02621fb7a8a_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday.com/mobile/20191201/20191201115800_68411a6d82739795944bf02621fb7a8a_2_mwpm_03200403.jpg"},{"uniquekey":"fe4a101ae207b5c0b112d52cff968876","title":"王者荣耀：孙膑胜率榜夺魁的背后，更多是对Buff稳定性的追求","date":"2019-12-01 11:57","category":"头条","author_name":"苍耳苍耳","url":"http://mini.eastday.com/mobile/191201115715871.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191201/20191201115715_ec41e0f9587aaeb8dc9b0f6414ef0194_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191201/20191201115715_ec41e0f9587aaeb8dc9b0f6414ef0194_5_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191201/20191201115715_ec41e0f9587aaeb8dc9b0f6414ef0194_2_mwpm_03200403.jpg"},{"uniquekey":"e247e2116ccde2779c7e2ddc22bcb1dc","title":"未完·再来！申花发战恒大海报：一场可以左右冠军走向的比赛","date":"2019-12-01 11:55","category":"头条","author_name":"衣衫褴褛的文人","url":"http://mini.eastday.com/mobile/191201115541972.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191201/2019120111_e8fee754e8944026a62afce801155e64_7011_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20191201/2019120111_23450fc5e1194dc4913a2d748499a082_9473_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20191201/2019120111_aab9d258f7664277bf8d303b3139f0f4_8475_mwpm_03200403.jpg"},{"uniquekey":"527f3d32a550f2dca5628228ce2a5c30","title":"比气质还得看它，怪不得52岁的李若彤不输刘诗诗","date":"2019-12-01 11:54","category":"头条","author_name":"时尚健康生活","url":"http://mini.eastday.com/mobile/191201115436124.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20191201/20191201115436_ee1f878cb28d80becad283a78ad6b7f6_8_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20191201/20191201115436_ee1f878cb28d80becad283a78ad6b7f6_6_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20191201/20191201115436_ee1f878cb28d80becad283a78ad6b7f6_7_mwpm_03200403.jpg"},{"uniquekey":"bbd69de7226e4413b036fbb8141cc1c8","title":"尤文体育主管在伦敦与切尔西高层会面，讨论威廉和佩德罗转会事宜","date":"2019-12-01 11:54","category":"头条","author_name":"欧陆体育风云","url":"http://mini.eastday.com/mobile/191201115419720.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20191201/20191201115419_e49e1b79d3f5d59b15bed0f92e069405_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20191201/20191201115419_e49e1b79d3f5d59b15bed0f92e069405_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20191201/20191201115419_e49e1b79d3f5d59b15bed0f92e069405_3_mwpm_03200403.jpg"},{"uniquekey":"12e83d8a274989356fd41528eb730d2b","title":"半兽人加盟胡金秋受影响，两人打法重叠，广厦男篮遭遇大难题","date":"2019-12-01 11:53","category":"头条","author_name":"兄弟篮球","url":"http://mini.eastday.com/mobile/191201115330481.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191201/2019120111_beeb1b2f5d954dc9b27ae3a3899d796c_0402_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20191201/2019120111_fa7bc6f94a9a431691d0ce0583108abf_1449_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20191201/2019120111_8b552a051a044874a993019ea85cad5d_7472_cover_mwpm_03200403.jpg"},{"uniquekey":"fb62121ac245ac3096c9f9315d30f8f2","title":"韩日真的和解了吗？半岛刚发生的大事，两国竟然没有交换情报","date":"2019-12-01 11:51","category":"头条","author_name":"东方头条 阿尔法军事","url":"http://mini.eastday.com/mobile/191201115154705.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191201/2019120111_7b4de8b3bdcd427a8025805bcabeae66_1459_mwpm_03200403.jpg"},{"uniquekey":"721cd4382e1709e530d088498499e855","title":"美媒证实，伊朗战机低空掠过美舰，伊反潜机单挑美军两艘军舰","date":"2019-12-01 11:51","category":"头条","author_name":"东方头条 军中三剑客","url":"http://mini.eastday.com/mobile/191201115128060.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20191201/2019120111_2eb55325d6be4f10978e8eb74cd222f5_7854_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20191201/2019120111_d4409e5e13e144e7bea86a5ad9105fe3_6988_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20191201/2019120111_19871ed612d249acae01e885fd29e0e1_3748_mwpm_03200403.jpg"},{"uniquekey":"de3f88fcc65e76cd22fa717023829679","title":"赛季第1惨案！3节输快60分！哈登为60+杀人诛心，脸不要了","date":"2019-12-01 11:48","category":"头条","author_name":"茶余饭后话篮球","url":"http://mini.eastday.com/mobile/191201114839360.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191201/2019120111_fa0e2931c12e4f668f16a3d0029e36be_8621_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191201/2019120111_842f1f7af7ab4b27bf11e235f276fe77_5343_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191201/2019120111_9f641a378f794a59aca8c3e27d792ad8_3319_mwpm_03200403.jpg"},{"uniquekey":"c0d0823fa3906aa55bd84cfa5be4b478","title":"懂得为别人着想，脾气很好的三大星座，听说你上榜了，快来看看吧","date":"2019-12-01 11:48","category":"头条","author_name":"娱人频道","url":"http://mini.eastday.com/mobile/191201114828844.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20191201/20191201114828_c5c5bef156e755ad965e02f2d236c317_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05imgmini.eastday.com/mobile/20191201/20191201114828_c5c5bef156e755ad965e02f2d236c317_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05imgmini.eastday.com/mobile/20191201/20191201114828_c5c5bef156e755ad965e02f2d236c317_3_mwpm_03200403.jpg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * uniquekey : 0fa4def3089e7dddb25c85ea2d39deb7
             * title : 搞笑的GIF动态图：当老婆让你带孩子，你要去打球的时候只能这样做
             * date : 2019-12-01 12:43
             * category : 头条
             * author_name : 搞笑的生活段子
             * url : http://mini.eastday.com/mobile/191201124314849.html
             * thumbnail_pic_s : http://06imgmini.eastday.com/mobile/20191201/20191201124314_f6b970f1f8920e1c77ab66c5bec3b0b1_7_mwpm_03200403.jpg
             * thumbnail_pic_s02 : http://06imgmini.eastday.com/mobile/20191201/20191201124314_f6b970f1f8920e1c77ab66c5bec3b0b1_2_mwpm_03200403.jpg
             * thumbnail_pic_s03 : http://06imgmini.eastday.com/mobile/20191201/20191201124314_f6b970f1f8920e1c77ab66c5bec3b0b1_1_mwpm_03200403.jpg
             */
            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            //采用设置注入
            public void setDataBean(NewsInfoBean nib) {
                this.uniquekey = nib.getUniquekey();
                this.title = nib.getTitle();
                this.date = nib.getDate();
                this.category = nib.getCategory();
                this.author_name = nib.getAuthor_name();
                this.url = nib.getUrl();
                this.thumbnail_pic_s = nib.getThumbnail_pic_s();
                this.thumbnail_pic_s02 = nib.getThumbnail_pic_s02();
                this.thumbnail_pic_s03 = nib.getThumbnail_pic_s03();
            }

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
        }
    }
}

