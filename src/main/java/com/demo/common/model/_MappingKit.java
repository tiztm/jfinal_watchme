package com.demo.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("blog", "id", Blog.class);
		arp.addMapping("mail", "id", Mail.class);
		arp.addMapping("mybooks", "id", Mybooks.class);
		arp.addMapping("xianyu", "id", Xianyu.class);
		arp.addMapping("xianyu1", "id", Xianyu1.class);
		arp.addMapping("xianyutypes", "xianyutype", Xianyutypes.class);
		arp.addMapping("zk_posts", "id", ZkPosts.class);
	}
}
