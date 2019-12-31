package com.amt.indiaiptv.utils.horizontalgridview;

import java.util.ArrayList;
import java.util.List;

public class TitleModel {
    public static List<Title> getTitleList() {
        List<Title> titleList = new ArrayList<>();
//        titleList.add(new Title("我的"));
        titleList.add(new Title("频道"));
        titleList.add(new Title("精选"));
        titleList.add(new Title("斗罗大陆"));
//        titleList.add(new Title("VIP"));
        titleList.add(new Title("电视剧"));
        titleList.add(new Title("电影"));
//        titleList.add(new Title("综艺"));
//        titleList.add(new Title("少儿"));
//        titleList.add(new Title("动漫"));
        titleList.add(new Title("4K专区"));
//        titleList.add(new Title("新闻"));
//        titleList.add(new Title("NBA"));
//        titleList.add(new Title("游戏"));
//        titleList.add(new Title("纪录片"));
//        titleList.add(new Title("知否"));
//        titleList.add(new Title("小视频"));
//        titleList.add(new Title("音乐"));
//        titleList.add(new Title("体育"));
//        titleList.add(new Title("王者荣耀"));
//        titleList.add(new Title("生活"));
        titleList.add(new Title("娱乐"));
        titleList.add(new Title("王者荣耀"));
        return titleList;
    }
}
