package com.tarena.allrun.entity;

import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.jivesoftware.smack.packet.Message;

public class PrivateChatEntity {

	// 保存当前用户与所有好友的聊天内容
	// ArrayList<message>
	// ConcurrentHashMap,Vector是安全的
	/**
	 * string是friend user
	 */
	public static ConcurrentHashMap<String, Vector<Message>> map = new ConcurrentHashMap<String, Vector<Message>>();

	public static void addMessage(String friendUser, Message message) {
		Vector<Message> vector = map.get(friendUser);
		if (vector == null) {
			vector = new Vector<Message>();
		}
		vector.add(message);
		map.put(friendUser, vector);
	}
}
